package org.simpleframework.inject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.annotation.BeanContainer;

public class DependencyInjectorTest {

    @DisplayName("依赖注入：doIoc")
    @Test
    public void doIocTest(){
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.joker");
    }
}
