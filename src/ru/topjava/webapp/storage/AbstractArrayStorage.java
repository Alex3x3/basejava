package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    final public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    final public void update(Resume r) {
        String uuid = r.getUuid();
        int index = (int) getSearchKey(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            storage[index] = r;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    final public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    final public int size() {
        return size;
    }

    @Override
    protected Integer checkNotExistElement(String uuid) {
        int searchKey = (int) getSearchKey(uuid);
        if (searchKey >= 0) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    protected Integer checkExistElement(String uuid) {
        int searchKey = (int) getSearchKey(uuid);
        if (searchKey < 0) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage[(int) searchKey];
    }
}