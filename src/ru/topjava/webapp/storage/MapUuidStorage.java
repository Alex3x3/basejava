package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    final public void clear() {
        storage.clear();
    }

    @Override
    final public int size() {
        return storage.size();
    }

    @Override
    final public void doUpdate(Resume r, Object searchKey) {
        storage.put((String) searchKey, r);
    }

    @Override
    final protected void doSave(Resume r, Object searchKey) {
        storage.put((String) searchKey, r);
    }

    @Override
    final protected Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    final protected void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    final protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    final protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    @Override
    protected List<Resume> copyStorage() {
        return new ArrayList<>(storage.values());
    }
}