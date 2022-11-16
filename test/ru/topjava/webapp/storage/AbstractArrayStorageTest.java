package ru.topjava.webapp.storage;

import org.junit.jupiter.api.Test;
import ru.topjava.webapp.exception.StorageException;
import ru.topjava.webapp.model.Resume;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

abstract class AbstractArrayStorageTest extends AbstractStorageTest<Integer> {
    AbstractArrayStorageTest(AbstractStorage<Integer> s) {
        super(s);
    }

    @Test
    void saveOverflow() {
        fillStorage();
        assertThrows(StorageException.class, () -> storage.save(new Resume("noName")),
                "Storage overflow test failed. Save method didn't throw exception");
    }

    private void fillStorage() {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("noName"));
            }
        } catch (StorageException e) {
            fail(e.getMessage());
        }
    }
}