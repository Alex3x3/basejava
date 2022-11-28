package ru.topjava.webapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Period {

    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

    public Period(String title, LocalDate startDate, LocalDate endDate, String description) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");

        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (!Objects.equals(title, period.title)) return false;
        if (!Objects.equals(startDate, period.startDate)) return false;
        if (!Objects.equals(endDate, period.endDate)) return false;
        return Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM.yy");
        return startDate.format(dtf) + " - " + endDate.format(dtf) + " " + title +
                (description != null ? ("\n" + description) : "");
    }
}