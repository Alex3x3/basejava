package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

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
    final public void doUpdate(Resume r, String searchKey) {
        storage.put(searchKey, r);
    }

    @Override
    final protected void doSave(Resume r, String searchKey) {
        storage.put(searchKey, r);
    }

    @Override
    final protected Resume doGet(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    final protected void doDelete(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    final protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    final protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected List<Resume> copyStorage() {
        return new ArrayList<>(storage.values());
    }
}