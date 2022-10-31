package ru.topjava.webapp.storage;

import org.junit.jupiter.api.Test;
import ru.topjava.webapp.exception.StorageException;
import ru.topjava.webapp.model.Resume;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    AbstractArrayStorageTest(AbstractStorage s) {
        super(s);
    }

    @Test
    void saveOverflow() {
        fillStorage();
        Resume r = new Resume();
        r.setUuid("uuid10001");
        assertThrows(StorageException.class, () -> storage.save(r),
                "Storage overflow test failed. Save method didn't throw exception");
    }

    private void fillStorage() {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                Resume r = new Resume();
                r.setUuid("uuid" + (i + 1));
                storage.save(r);
            }
        } catch (StorageException e) {
            fail(e.getMessage());
        }
    }
}