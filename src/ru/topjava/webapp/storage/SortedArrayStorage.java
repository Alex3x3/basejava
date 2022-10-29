package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInStorage(Object searchKey, Resume r) {
        int index = (int) searchKey * -1 - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        size++;
        storage[index] = r;
    }

    @Override
    protected void deleteFromStorage(Object searchKey) {
        int index = (int) searchKey;
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
        storage[size] = null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}