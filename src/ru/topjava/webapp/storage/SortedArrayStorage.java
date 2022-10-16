package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is overfilled");
        } else if (size == 0) {
            storage[0] = r;
            size++;
        } else {
            int index = this.getIndex(uuid);
            if (index < 0) {
                index = index * -1 - 1;
                System.arraycopy(storage, index, storage, index + 1, size - index);
                storage[index] = r;
                size++;
            } else {
                System.out.println("Resume " + uuid + " already exists");
            }
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " doesn't exist");
        } else {
            size--;
            System.arraycopy(storage, index + 1, storage, index, size - index);
            storage[size] = null;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}