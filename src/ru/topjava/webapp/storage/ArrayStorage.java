package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is overfilled");
        } else if (getIndex(uuid) >= 0) {
            System.out.println("Resume " + uuid + " already exists");
        } else {
            storage[size] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " doesn't exist");
        } else {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}