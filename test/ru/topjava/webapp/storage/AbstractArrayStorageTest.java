package ru.topjava.webapp.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.topjava.webapp.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {

    Resume r1 = new Resume();
    Resume r2 = new Resume();
    Resume r3 = new Resume();
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    AbstractArrayStorage testStorage;

    protected AbstractArrayStorageTest(AbstractArrayStorage s) {
        testStorage = s;
    }

    @BeforeEach
    void setUp() {
        testStorage.clear();
        r1.setUuid(UUID_1);
        r2.setUuid(UUID_2);
        r3.setUuid(UUID_3);
        testStorage.save(r1);
        testStorage.save(r2);
        testStorage.save(r3);
    }

    @Test
    void clear() {
        fillStorage();
        testStorage.clear();
        assertEquals(0, testStorage.size(), "Storage wasn't fully cleared");
    }

    @Test
    void update() {
        Resume r = new Resume();
        r.setUuid(UUID_1);
        testStorage.update(r);
        assertNotSame(r1, testStorage.get(UUID_1), "Update wasn't done");
        assertEquals(r1, testStorage.get(UUID_1), "Old and new(updated) resume not equals");
    }

    @Test
    void save() {
        assertEquals(3, testStorage.size(), "Resumes can't be saved to the storage");

        Resume r = new Resume();
        r.setUuid(UUID_1);
        testStorage.save(r);
        assertEquals(1, countInstances(r), "Storage contains duplicates");

        fillStorage();
        assertDoesNotThrow(() -> testStorage.save(new Resume()), "Storage overfilled");
    }

    @Test
    void get() {
        assertEquals(r1, testStorage.get(UUID_1), "Wrong resume was returned");
    }

    @Test
    void delete() {
        testStorage.delete(UUID_1);
        assertEquals(0, countInstances(r1), "Delete wasn't done");
    }

    @Test
    void getAll() {
        assertEquals(3, testStorage.getAll().length);
    }

    @Test
    void size() {
        assertEquals(3, testStorage.size());
    }

    private void fillStorage() {
        for (int i = testStorage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            Resume r = new Resume();
            r.setUuid("uuid" + (i + 1));
            testStorage.save(r);
        }
    }

    private int countInstances(Resume resume) {
        int count = 0;
        for (Resume r : testStorage.storage) {
            if (r == null) {
                break;
            } else if (r.equals(resume)) {
                count++;
            }
        }
        return count;
    }
}