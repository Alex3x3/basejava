package ru.topjava.webapp.model;

public enum ContactType {

    PHONE_NUMBER("Номер телефона"),
    SKYPE("Skype"),
    EMAIL("Email"),
    LINKEDIN("LinkedIn"),
    STACKOVERFLOW("StackOverFlow"),
    HOMEPAGE("Домашняя страница");
    final String contactType;

    ContactType(String type) {
        this.contactType = type;
    }

    @Override
    public String toString() {
        return contactType;
    }
}
