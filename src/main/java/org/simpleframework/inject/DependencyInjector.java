package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.BeanContainer;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

@Slf4j
public class DependencyInjector {

    /**
     * Bean容器
     */
    private BeanContainer beanContainer;

    public DependencyInjector() {
        beanContainer = BeanContainer.getInstance();
    }


    public void doIoc(){

        if(ValidationUtil.isEmpty(beanContainer.getClasses())){
            log.warn("empty classSet in BeanContainer.");
            return;
        }
        //1.遍历Bean容器中所有的Class对象

        for (Class<?> clazz : beanContainer.getClasses()) {
            //2.遍历Class对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if(ValidationUtil.isEmpty(fields)){
                continue;
            }
            for (Field field : fields) {
                //3.找出被Autowired标记的成员变量
                if(field.isAnnotationPresent(Autowired.class)){
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
                    //4.获取这些成员变量的类型
                    Class<?> fieldClass = field.getType();
                    //5.获取这些成员变量的类型在容器里对应的实例
                    Object fieldValue = getFieldInstance(fieldClass, autowiredValue);
                    if(fieldValue == null){
                        throw new RuntimeException("unable to inject relevant type, target field is:" + fieldClass.getName());
                    }
                    //6.通过反射将对应的成员变量实例注入到成员变量所在类的实例里
                    Object targetObject = beanContainer.getBean(clazz);
                    ClassUtil.setField(field, targetObject, fieldValue, true);
                }
            }
        }
    }

    /**
     * 根据Class对象在beanContainer里获取其实例或者实现类
     * @param fieldClass Class对象
     * @param autowiredValue 注解中的值
     * @return 实例或实现类
     */
    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object fieldValue = beanContainer.getBean(fieldClass);
        if(fieldValue != null){
            return fieldValue;
        }
        Class<?> implementedClass = getImplementClass(fieldClass, autowiredValue);
        if (implementedClass != null){
            beanContainer.getBean(implementedClass);
        }
        return null;
    }

    /**
     * 获取接口的实现类
     * @param fieldClass 接口
     * @param autowiredValue 注解中的值
     * @return 实现类
     */
    private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if(ValidationUtil.isEmpty(classSet)){
            return null;
        }
        if(ValidationUtil.isEmpty(autowiredValue)){
            if(classSet.size() == 1){
                return classSet.iterator().next();
            }
            //如果多于两个实现类且未被指定，抛出异常
            throw new RuntimeException("multiple implement classes for " + fieldClass.getName() + "please set @Autowired's value to pick one");
        }
        for (Class<?> clazz : classSet) {
            if(autowiredValue.equals(clazz.getSimpleName())){
                return clazz;
            }
        }
        //没有被容器管理
        return null;
    }
}





























