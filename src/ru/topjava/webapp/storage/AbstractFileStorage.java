package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.StorageException;
import ru.topjava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    final protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid(), e);
        }
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    @Override
    final protected void doSave(Resume r, File file) {
        try {
            if (file.createNewFile()) {
                doWrite(r, file);
            } else {
                throw new StorageException("File create error", r.getUuid());
            }
        } catch (IOException e) {
            throw new StorageException("File write error", file.getName(), e);
        }
    }

    @Override
    final protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    protected abstract Resume doRead(File file) throws IOException;

    @Override
    final protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    final protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    final protected boolean isExist(File searchKey) {
        return searchKey.exists();
    }

    @Override
    final public void clear() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory not exist or read error", null);
        }
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    final public int size() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory not exist or read error", null);
        }
        return files.length;
    }

    @Override
    final protected List<Resume> copyStorage() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory not exist or read error", null);
        }
        List<Resume> list = new ArrayList<>();
        for (File file : files) {
            Resume r = doGet(file);
            list.add(r);
        }
        return list;
    }
}