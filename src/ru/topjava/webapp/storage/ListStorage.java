package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

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
        storage.set((int) searchKey, r);
    }

    @Override
    final protected void doSave(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    final protected Resume doGet(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    final protected void doDelete(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    final protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    final protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected List<Resume> copyStorage() {
        return new ArrayList<>(storage);
    }
}