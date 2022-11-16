package ru.topjava.webapp;

import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.model.Resume;
import ru.topjava.webapp.storage.AbstractStorage;
import ru.topjava.webapp.storage.SortedArrayStorage;

/**
 * Test for your ru.topjava.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final AbstractStorage<Integer> ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Name1");
        Resume r2 = new Resume("uuid2", "Name2");
        Resume r3 = new Resume("uuid3", "Name3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        try {
            System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        } catch (NotExistStorageException e) {
            System.out.println("Resume \"dummy\" isn't presented in storage");
        }

        String uuid = r2.getUuid();
        System.out.println("\nUpdate " + uuid + " resume");
        Resume r4 = new Resume("uuid2", "updatedName2");
        ARRAY_STORAGE.update(r4);

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}