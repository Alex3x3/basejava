package ru.topjava.webapp.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.topjava.webapp.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";

    final static protected Resume RESUME_1 = new Resume();
    final static protected Resume RESUME_2 = new Resume();
    final static protected Resume RESUME_3 = new Resume();
    final static protected Resume RESUME_4 = new Resume();


    final protected AbstractArrayStorage storage;

    static {
        RESUME_1.setUuid(UUID_1);
        RESUME_2.setUuid(UUID_2);
        RESUME_3.setUuid(UUID_3);
        RESUME_4.setUuid(UUID_4);
    }

    protected AbstractArrayStorageTest(AbstractArrayStorage s) {
        storage = s;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0, "Clear test 1 failed");
        assertArrayEquals(storage.getAll(), new Resume[0], "Clear test 2 failed");
    }

    @Test
    void update() {
        Resume r = new Resume();
        r.setUuid(UUID_1);
        storage.update(r);
        assertNotSame(RESUME_1, storage.get(UUID_1), "Update test 1 failed. Update wasn't done");
        assertEquals(RESUME_1, storage.get(UUID_1), "Update test 2 failed." +
                " Old and new(updated) resume not equals");
    }

    @Test
    void updateNotExist() {
        assertDoesNotThrow(() -> storage.update(RESUME_4), "Update not exist resume test failed");
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4, "Save test 1 failed. Storage doesn't contain saved resume");
        assertSize(4, "Save test 2 failed. Storage size didn't increase after saving");
    }

    @Test
    void saveExist() {
        assertDoesNotThrow(() -> storage.save(RESUME_3), "Save exist test 1 failed. " +
                "Save method threw exception");
        assertSize(3, "Save exist test 2 failed. Storage size changed");
    }

    @Test
    void saveOverflow() {
        fillStorage();
        assertDoesNotThrow(() -> storage.save(new Resume()), "Storage overflow test failed. " +
                "Save method threw exception");
    }

    @Test
    void get() {
        assertGet(RESUME_1, "Get RESUME_1 test. Wrong resume was returned");
        assertGet(RESUME_2, "Get RESUME_2 test. Wrong resume was returned");
        assertGet(RESUME_3, "Get RESUME_3 test. Wrong resume was returned");
    }

    @Test
    void getNotExist() {
        assertNull(storage.get(UUID_4), "Get not exist resume test failed");
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertNull(storage.get(UUID_1), "Delete test 1 failed. Storage contains deleted resume." );
        assertSize(2, "Delete test 2 failed. Storage size didn't decrease after delete");
    }

    @Test
    void deleteNotExist() {
        assertDoesNotThrow(() -> storage.delete(UUID_4), "Delete not exist test 1 failed. " +
                "Delete threw exception");
        assertSize(3, "Delete not exist test 2 failed. Storage size changed");
    }

    @Test
    void getAll() {
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(expected, storage.getAll(), "GET ALL. Test 1 failed");
        assertEquals(3, storage.getAll().length, "GET ALL. Test 2 failed");
    }

    @Test
    void size() {
        assertSize(3, "Wrong size returned");
    }

    private void fillStorage() {
        for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            Resume r = new Resume();
            r.setUuid("uuid" + (i + 1));
            storage.save(r);
        }
    }

    private void assertSize(int size, String message) {
        assertEquals(size, storage.size(), message);
    }

    private void assertGet(Resume r, String message) {
        assertEquals(r, storage.get(r.getUuid()), message);
    }
}