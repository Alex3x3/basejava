package ru.topjava.webapp.storage;

import ru.topjava.webapp.storage.serializer.ObjectStreamSerializer;

import java.nio.file.Path;

public class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}

