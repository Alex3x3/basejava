package ru.topjava.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class SingleLineSection extends Section implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String content;

    public SingleLineSection() {
    }

    public SingleLineSection(String content) {
        Objects.requireNonNull(content, "content must not be null");
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleLineSection that = (SingleLineSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return content;
    }
}