package ru.topjava.webapp.storage;

import ru.topjava.webapp.storage.serializer.ObjectStreamSerializer;

import java.io.File;

public class ObjectFileStorageTest extends AbstractStorageTest {

    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}
