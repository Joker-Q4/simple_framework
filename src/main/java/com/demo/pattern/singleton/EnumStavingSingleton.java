package com.demo.pattern.singleton;

public class EnumStavingSingleton {

    private EnumStavingSingleton(){}

    public static EnumStavingSingleton getInstance(){
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder{
        HOLDER;
        private final EnumStavingSingleton instance;
        ContainerHolder(){
            instance = new EnumStavingSingleton();
        }
    }
}
