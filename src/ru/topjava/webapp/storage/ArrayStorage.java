package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        if (find(uuid) >= 0) {
            System.out.println("Storage already contains the resume with " + uuid +
                    " uuid.\nNew " + uuid + " instance won't be saved to the storage");
            return;
        }

        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Storage is overfilled.\nResume " + uuid +
                    " won't be saved to the storage");
        }
    }

    public Resume get(String uuid) {
        int index = find(uuid);
        if (index >= 0) {
            return storage[index];
        }

        System.out.println("Resume " + uuid + " wasn't found");
        return null;
    }

    public void delete(String uuid) {
        int index = find(uuid);
        if (index >= 0) {
            for (int i = index; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }

            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + " wasn't found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        String oldUuid = resume.getUuid();
        if (find(resume.getUuid()) >= 0) {
            Scanner console = new Scanner(System.in);
            String[] inputUuid = console.nextLine().trim().split(" ");

            if (inputUuid.length > 1 || inputUuid[0].length() == 0) {
                throw new InputMismatchException("Wrong uuid parameter");
            } else {
                resume.setUuid(inputUuid[0]);
            }
        } else {
            System.out.println("Resume " + oldUuid + " wasn't found. Nothing to update");
        }
    }

    private int find(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}