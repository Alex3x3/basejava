package ru.topjava.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MultiLineSection extends Section implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<String> content;

    public MultiLineSection() {
    }

    public MultiLineSection(List<String> content) {
        Objects.requireNonNull(content, "items must not be null");
        this.content = content;
    }

    public List<String> getContent() {
        return new ArrayList<>(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultiLineSection that = (MultiLineSection) o;

        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return "-" + content.stream().map(Object::toString)
                .collect(Collectors.joining("\n-"));
    }
}