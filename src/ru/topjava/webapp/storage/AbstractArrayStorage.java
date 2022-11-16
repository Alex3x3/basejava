package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.StorageException;
import ru.topjava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    final public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    @Override
    final public int size() {
        return size;
    }

    @Override
    final public void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }

    @Override
    final protected void doSave(Resume r, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    @Override
    final protected Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    final protected void doDelete(Integer index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    final protected boolean isExist(Integer index) {
        return index >= 0;
    }

    @Override
    protected List<Resume> copyStorage() {
        List<Resume> list = new ArrayList<>();
        for (Resume r : storage) {
            if (r == null) {
                break;
            }
            list.add(r);
        }
        return list;
    }

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);
}