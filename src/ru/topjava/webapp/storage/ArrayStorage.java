package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInStorage(Object searchKey, Resume r) {
        storage[size] = r;
        size++;
    }

    @Override
    protected void deleteFromStorage(Object searchKey) {
        int index = (int) searchKey;
        size--;
        storage[index] = storage[size];
        storage[size] = null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}