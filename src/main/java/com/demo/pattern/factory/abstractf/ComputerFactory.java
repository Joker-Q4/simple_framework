package com.demo.pattern.factory.abstractf;

import com.demo.pattern.factory.entity.Keyboard;
import com.demo.pattern.factory.entity.Mouse;

public interface ComputerFactory {

    Mouse createMouse();
    Keyboard createKeyboard();
}
