package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    final protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    final protected void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    final protected void fillDeletedElement(int index) {
        storage[index] = storage[size - 1];
    }
}