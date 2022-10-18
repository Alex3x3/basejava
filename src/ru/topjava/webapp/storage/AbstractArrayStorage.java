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

    final public void save(Resume r) {
        String uuid = r.getUuid();
        int index;
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is overfilled");
        } else if ((index = getIndex(uuid)) >= 0) {
            System.out.println("Resume " + uuid + " already exists");
        } else {
            index = moveElementsWhenSave(index);
            storage[index] = r;
            size++;
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

    final public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " doesn't exist");
        } else {
            size--;
            moveElementsWhenDelete(index);
            storage[size] = null;
        }
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

    protected abstract void moveElementsWhenDelete(int index);

    protected abstract int moveElementsWhenSave(int index);
}