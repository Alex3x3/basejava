package ru.topjava.webapp.storage;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void moveElementsWhenDelete(int index) {
        storage[index] = storage[size];
    }

    @Override
    protected int moveElementsWhenSave(int index) {
        return size;
    }
}