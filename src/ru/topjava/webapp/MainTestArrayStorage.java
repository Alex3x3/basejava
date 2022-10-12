package ru.topjava.webapp;

import ru.topjava.webapp.model.Resume;
import ru.topjava.webapp.storage.ArrayStorage;

import java.util.InputMismatchException;

/**
 * Test for your ru.topjava.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        String oldUuid = r2.getUuid();
        System.out.println("\nUpdate " + oldUuid + " resume");
        while (true) {
            System.out.print("Input new uuid parameter ");
            try {
                ARRAY_STORAGE.update(ARRAY_STORAGE.get(oldUuid));
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + " try again\n");
            }
        }
        System.out.println(oldUuid + " resume became " + r2.getUuid());

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}