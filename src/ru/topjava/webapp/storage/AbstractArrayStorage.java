package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    final public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    final public void update(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " doesn't exist");
        } else {
            storage[index] = r;
        }
    }

    final public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    final public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    final public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}