package ru.topjava.webapp.model;

public enum SectionType {

    OBJECTIVE("Позиция"),
    PERSONAL("Личные качества"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");
    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}