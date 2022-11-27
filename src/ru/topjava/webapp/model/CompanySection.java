package ru.topjava.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompanySection extends Section {

    private final List<Company> companies;

    public CompanySection(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return new ArrayList<>(companies);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;

        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return companies != null ? companies.hashCode() : 0;
    }

    @Override
    public String toString() {
        return companies.stream().map(Object::toString)
                .collect(Collectors.joining("\n\n"));
    }
}
