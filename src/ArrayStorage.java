/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        int resumeNum = size();
        if (resumeNum < storage.length) {
            storage[resumeNum] = r;
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
        int deletePos = -1;
        int resumesNum = size();
        for (int i = 0; i < resumesNum; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                deletePos = i;
                break;
            }
        }

        if (deletePos == -1) {
            return;
        }

        for (int i = deletePos; i < resumesNum - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[resumesNum - 1] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int resumeNum = size();
        Resume[] destination = new Resume[resumeNum];
        System.arraycopy(storage, 0, destination, 0, resumeNum);
        return destination;
    }

    int size() {
        int resumeNum = 0;
        for (Resume resume : storage) {
            if (resume == null) {
                break;
            }
            resumeNum++;
        }
        return resumeNum;
    }
}