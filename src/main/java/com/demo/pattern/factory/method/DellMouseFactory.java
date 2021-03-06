package com.demo.pattern.factory.method;

import com.demo.pattern.factory.entity.DellMouse;
import com.demo.pattern.factory.entity.Mouse;

public class DellMouseFactory implements MouseFactory {

    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }
}
