package ru.topjava.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MultiLineSection extends Section {

    private final List<String> content;

    public MultiLineSection(List<String> content) {
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
        return content != null ? content.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "-" + content.stream().map(Object::toString)
                .collect(Collectors.joining("\n-"));
    }
}