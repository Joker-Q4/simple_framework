package org.simpleframework.core.annotation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
     * 存放所有被标记的目标对象的Map
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * 加载bean的注解列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION =
            Arrays.asList(Component.class, Controller.class, Service.class, Repository.class);

    /**
     * 获取bean容器实例
     * @return 单例BeanContainer
     */
    public static BeanContainer getInstance(){
        return ContainHolder.HOLDER.instance;
    }

    private enum ContainHolder{
        HOLDER;

        private BeanContainer instance;
        ContainHolder(){
            instance = new BeanContainer();
        }

    }

    /**
     * 容器是否已经加载过Bean
     */
    private boolean loaded = false;

    /**
     * 是否已经加载过Bean
     * @return 是否已经加载
     */
    public boolean isLoaded(){
        return loaded;
    }

    /**
     * Bean实例数量
     * @return 数量
     */
    public int size(){
        return beanMap.size();
    }

    /**
     * 扫描加载所有Bean
     * @param packageName 包名
     */
    public synchronized void loadBeans(String packageName){
        //判断bean容器是否被加载过
        if(loaded){
            log.warn("BeanContainer has been loaded.");
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if(ValidationUtil.isEmpty(classSet)){
            log.warn("extract nothing from packageName" + packageName);
            return;
        }
        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
                //如果类上面标记了定义的注解
                if(clazz.isAnnotationPresent(annotation)){
                    //将目标类本身作为键，实例作为值,放入到beanMap中
                    beanMap.put(clazz, ClassUtil.newInstance(clazz, true));

                }
            }
        }
        loaded = true;
    }

    /**
     * 添加一个class对象及其bean实例
     * @param clazz Class对象
     * @param bean bean实例
     * @return 原有的bean实例，没有则返回null
     */
    public Object addBean(Class<?> clazz, Object bean){
        return beanMap.put(clazz, bean);
    }

    /**
     * 移除一个IOC容器管理的对象
     * @param clazz Class对象
     * @return 删除的bean实例，没有则返回null
     */
    public Object removeBean(Class<?> clazz){
        return beanMap.remove(clazz);
    }

    /**
     * 根据Class对象获取Bean实例
     * @param clazz Class对象
     * @return 相应Bean实例
     */
    public Object getBean(Class<?> clazz){
        return beanMap.get(clazz);
    }

    /**
     * 获取容器管理的所有对象的集合
     * @return Class集合
     */
    public Set<Class<?>> getClasses(){
        return beanMap.keySet();
    }

    /**
     * 获取所有Bean集合
     * @return Bean集合
     */
    public Set<Object> getBeans(){
        return new HashSet<>(beanMap.values());
    }

    /**
     * 根据注解筛选出Bean的Class集合
     * @param annotation 注解
     * @return Class集合
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation){
        //1.获取beanMap的所有对象
        Set<Class<?>> keySet = getClasses();
        if(ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        //2.通过注解筛选出被注解标记的对象，并添加到classSet里
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            //类是否被相关注解标记
            if(clazz.isAnnotationPresent(annotation)){
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0?classSet:null;
    }

    /**
     * 通过接口或者父类获取实现类或子类的Class集合，不包括其本身
     * @param interfaceOrClass 接口或父类
     * @return Class集合
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass){
        //1.获取beanMap的所有对象
        Set<Class<?>> keySet = getClasses();
        if(ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        //2.判断keySet里面的元素是否是传入的接口或者类的子类，并添加到classSet里
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            if(interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)){
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0?classSet:null;
    }
}