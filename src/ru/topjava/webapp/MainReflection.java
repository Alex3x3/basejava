package ru.topjava.webapp;

import ru.topjava.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Resume r = new Resume("uuid1", "Name1");
        Method method = r.getClass().getDeclaredMethod("toString");
        System.out.println(method.invoke(r));
    }
}
