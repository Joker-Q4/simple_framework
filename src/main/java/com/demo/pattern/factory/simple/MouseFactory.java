package com.demo.pattern.factory.simple;

import com.demo.pattern.factory.entity.DellMouse;
import com.demo.pattern.factory.entity.HpMouse;
import com.demo.pattern.factory.entity.Mouse;

public class MouseFactory {

    public static Mouse createMouse(int type){
        switch (type){
            case 0: return new DellMouse();
            case 1: return new HpMouse();
            default: return null;
        }
    }

    public static void main(String[] args) {
        Mouse mouse = createMouse(1);
        mouse.sayHi();
    }
}
