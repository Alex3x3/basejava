package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.StorageException;
import ru.topjava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
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
    final public void doUpdate(Resume r, Object index) {
        storage[(int) index] = r;
    }

    @Override
    final protected void doSave(Resume r, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, (int) index);
            size++;
        }
    }

    @Override
    final protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    final protected void doDelete(Object index) {
        fillDeletedElement((int) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    final protected boolean isExist(Object index) {
        return (int) index >= 0;
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