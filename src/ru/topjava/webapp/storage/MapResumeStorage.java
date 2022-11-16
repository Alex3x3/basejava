package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
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
    final public void doUpdate(Resume r, Resume resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    final protected void doSave(Resume r, Resume resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    final protected Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    final protected void doDelete(Resume resume) {
        storage.remove(resume.getUuid());
    }

    @Override
    final protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    final protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected List<Resume> copyStorage() {
        return new ArrayList<>(storage.values());
    }
}