package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.StorageException;
import ru.topjava.webapp.model.Resume;
import ru.topjava.webapp.storage.serializer.StreamSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final StreamSerializer streamSerializer;

    protected FileStorage(File directory, StreamSerializer streamSerializer) {
        Objects.requireNonNull(directory, "directory must not be null");
        this.streamSerializer = streamSerializer;
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
            streamSerializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid(), e);
        }
    }

    @Override
    final protected void doSave(Resume r, File file) {
        try {
            if (!file.createNewFile()) {
                throw new StorageException("File create error", r.getUuid());
            }
            streamSerializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", file.getName(), e);
        }
    }

    @Override
    final protected Resume doGet(File file) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

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
            throw new StorageException("Directory not exist or read error");
        }
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    final public int size() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory not exist or read error");
        }
        return files.length;
    }

    @Override
    final protected List<Resume> copyStorage() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory not exist or read error");
        }
        List<Resume> list = new ArrayList<>();
        for (File file : files) {
            Resume r = doGet(file);
            list.add(r);
        }
        return list;
    }
}