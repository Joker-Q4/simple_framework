package org.simpleframework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
     * 获取类集合
     * @param packageName 包名
     * @return 类集合
     */
    public static Set<Class<?>> extractPackageClass(String packageName){
        //1.获取类加载器
        ClassLoader classLoader = getClassLoader();
        //2.通过类加载器获取加载的资源
        URL url = classLoader.getResource(packageName.replace(".", "/"));
        if(url == null){
            log.warn("unable to retrieve anything from package " + packageName);
            return null;
        }
        //3.根据不同的资源类型，采用不同的方式获取资源的集合\
        Set<Class<?>> classSet = null;
        //过滤出文件类型的资源
        if(url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)){
            classSet = new HashSet<>();
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet, packageDirectory, packageName);
        }
        return classSet;
    }

    /**
     * 递归获取目标package下面的所有class文件
     * @param emptyClassSet 装载目标类的集合
     * @param fileSource 文件或者目录
     * @param packageName 包名
     */
    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        if(!fileSource.isDirectory()){
            return;
        }
        //如果是文件夹，则列出所有文件和子文件夹
        File[] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if(file.isDirectory()){
                    return true;
                }else {
                    //获取文件的绝对值路径
                    String absoluteFilePath = file.getAbsolutePath();
                    if(absoluteFilePath.endsWith(".class")){
                        //class文件直接加载
                        addToClassSet(absoluteFilePath);
                    }
                    return false;
                }
            }

            private void addToClassSet(String absoluteFilePath) {
                //1.从class文件的绝对值路径中提取包含了package的类名
                absoluteFilePath = absoluteFilePath.replace(File.separator, ".");
                String className = absoluteFilePath.substring(absoluteFilePath.indexOf(packageName));
                className = className.substring(0, className.lastIndexOf("."));
                //2.通过反射获取Class对象，并加入classSet里
                Class<?> targetClass = loadClass(className);
                emptyClassSet.add(targetClass);
            }
        });

        if(files != null){
            //递归调用
            for (File file : files) {
                extractClassFile(emptyClassSet, file, packageName);
            }
        }
    }

    /**
     * 获取class对象
     * @param className 类名
     * @return class对象
     */
    public static Class<?> loadClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取ClassLoader
     * @return ClassLoader实例
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static void main(String[] args) {
        extractPackageClass("com.joker.dto");
    }
}
