package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.StorageException;
import ru.topjava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        checkStorageOverflow(uuid);
        Object searchKey = checkNotExistElement(uuid);
        saveInStorage(searchKey, r);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = checkExistElement(uuid);
        return getResume(searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = checkExistElement(uuid);
        deleteFromStorage(searchKey);
    }

    protected abstract Object checkNotExistElement(String uuid);

    protected abstract void saveInStorage(Object searchKey, Resume r);

    protected abstract Object checkExistElement(String uuid);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void deleteFromStorage(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    private void checkStorageOverflow(String uuid) {
        if (size() >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", uuid);
        }
    }
}