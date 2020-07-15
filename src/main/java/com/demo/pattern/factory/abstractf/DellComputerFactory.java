package com.demo.pattern.factory.abstractf;

import com.demo.pattern.factory.entity.DellKeyboard;
import com.demo.pattern.factory.entity.DellMouse;
import com.demo.pattern.factory.entity.Keyboard;
import com.demo.pattern.factory.entity.Mouse;

public class DellComputerFactory implements ComputerFactory {

    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new DellKeyboard();
    }
}
