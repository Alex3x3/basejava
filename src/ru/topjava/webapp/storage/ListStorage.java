package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume r) {
        int index = checkExistElement(r.getUuid());
        storage.set(index, r);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer checkNotExistElement(String uuid) {
        int searchKey = getSearchKey(uuid);
        if (searchKey >= 0) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    protected void saveInStorage(Object searchKey, Resume r) {
        storage.add(r);
    }

    @Override
    protected Integer checkExistElement(String uuid) {
        int searchKey = getSearchKey(uuid);
        if (searchKey == -1) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected void deleteFromStorage(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume r = new Resume();
        r.setUuid(uuid);
        return storage.indexOf(r);
    }
}