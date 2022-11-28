package ru.topjava.webapp;

import ru.topjava.webapp.model.SectionType;

public class MainSingleton {
    private static MainSingleton instance;

    public static void getInstance() {
        if (instance == null) {
            instance = new MainSingleton();
        }
    }

    private MainSingleton() {
    }

    public static void main(String[] args) {
        MainSingleton.getInstance();
        Singleton instance = Singleton.valueOf("INSTANCE2");
        System.out.println(instance.ordinal());

        for (SectionType type : SectionType.values()) {
            System.out.println(type);
        }
    }

    public enum Singleton {
        INSTANCE1,
        INSTANCE2
    }
}