package org.simpleframework.util;

import org.elasticsearch.Assertions;
import org.junit.Test;

import java.util.Set;

public class ClassUtilTest {

    @Test
    public void extractPackageClassTest(){
        Set<Class<?>> classes = ClassUtil.extractPackageClass("com.joker.entity");
        System.out.println(classes);
    }
}
