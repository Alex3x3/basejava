import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int resumeCount = 0;

    void clear() {
        Arrays.fill(storage, 0, resumeCount, null);
        resumeCount = 0;
    }

    void save(Resume r) {
        if (resumeCount < storage.length) {
            storage[resumeCount] = r;
            resumeCount++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        boolean isFound = false;
        for (int i = 0; i < resumeCount; i++) {
            if (!isFound) {
                if (storage[i].uuid.equals(uuid)) {
                    isFound = true;
                }
            }

            if (isFound && i < resumeCount - 1) {
                storage[i] = storage[i + 1];
            }
        }
        storage[resumeCount - 1] = null;
        resumeCount--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, resumeCount);
    }

    int size() {
        return resumeCount;
    }
}