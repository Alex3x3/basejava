package ru.topjava.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String uuid;
    private final String fullName;
    private final Map<ContactType, String> contacts = new HashMap<>();
    private final Map<SectionType, Section> sections = new HashMap<>();

    public Resume(String fullName) {
        Objects.requireNonNull(fullName, "fullName must not be null");
        uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<ContactType, String> getContacts() {
        return Map.copyOf(contacts);
    }

    public Map<SectionType, Section> getSections() {
        return Map.copyOf(sections);
    }

    public void fillContacts(ContactType contactType, String contact) {
        contacts.put(contactType, contact);
    }

    public void fillSections(SectionType sectionType, Section content) {
        sections.put(sectionType, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return 31 * fullName.hashCode() + uuid.hashCode();
    }

    @Override
    public String toString() {
        return "UUID: " + uuid + ", full name: " + fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int result = fullName.compareTo(o.fullName);
        if (result == 0) {
            result = this.uuid.compareTo(o.uuid);
        }
        return result;
    }
}