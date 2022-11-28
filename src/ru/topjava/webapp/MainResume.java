package ru.topjava.webapp;

import ru.topjava.webapp.model.*;
import ru.topjava.webapp.util.DateUtil;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MainResume {
    public static void main(String[] args) {
        Resume resume = new Resume("Иван Петров");
        fillContactSections(resume);

        String singleLineContent = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";
        fillSingleLineSection(resume, SectionType.OBJECTIVE, singleLineContent);

        singleLineContent = "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.";
        fillSingleLineSection(resume, SectionType.PERSONAL, singleLineContent);

        List<String> multiLineContent = new ArrayList<>();
        multiLineContent.add("""
                Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения
                автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring
                Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных
                DIY смет.""");
        multiLineContent.add("""
                Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat,
                Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.""");
        fillMultiLineSection(resume, SectionType.ACHIEVEMENT, multiLineContent);

        multiLineContent = new ArrayList<>();
        multiLineContent.add("""
                Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data,
                Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports,
                Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).""");
        multiLineContent.add("""
                Администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport,
                OpenCmis, Bonita, pgBouncer""");
        fillMultiLineSection(resume, SectionType.QUALIFICATIONS, multiLineContent);

        List<Period> periods = new ArrayList<>();
        periods.add(new Period("Инженер по программному тестированию",
                DateUtil.of(1997, Month.SEPTEMBER),
                DateUtil.of(2000, Month.JANUARY),
                """
                        Тестирование, отладка, внедрение ПО цифровой
                        телефонной станции Alcatel 1000 S12 (CHILL,
                        ASM)."""));
        periods.add(new Period("Инженер по программному тестированию",
                DateUtil.of(2000, Month.FEBRUARY),
                DateUtil.of(2010, Month.FEBRUARY),
                """
                        Тестирование, отладка, внедрение ПО цифровой
                        телефонной станции Alcatel 2000 S900 (WORM,
                        ASM)."""));

        List<Company> companies = new ArrayList<>();
        companies.add(new Company("Alcatel", "alcatel.com", periods));

        periods = new ArrayList<>();
        periods.add(new Period("Java архитектор",
                DateUtil.of(2012, Month.APRIL),
                DateUtil.of(2014, Month.OCTOBER),
                """
                        Организация процесса разработки системы ERP для разных окружений: релизная
                        политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация
                        Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO."""));
        companies.add(new Company("RIT Center", "ritcenter.com", periods));

        fillCompanySection(resume, SectionType.EXPERIENCE, companies);

        periods = new ArrayList<>();
        periods.add(new Period("Functional Programming Principles in Scala' by Martin Odersky",
                DateUtil.of(2011, Month.MARCH),
                DateUtil.of(2011, Month.APRIL),
                null));
        periods.add(new Period(
                """
                        Курс "Объектно-ориентированный анализ ИС. Концептуальное
                        моделирование на UML.\"""",
                DateUtil.of(2013, Month.MARCH),
                DateUtil.of(2013, Month.APRIL),
                null));

        companies = new ArrayList<>();
        companies.add(new Company("Udemy", "udemy.com", periods));
        fillCompanySection(resume, SectionType.EDUCATION, companies);

        System.out.println("Печать резюме:\n");
        printName(resume);

        System.out.println();
        printContacts(resume);

        System.out.println();
        printSection(resume, SectionType.OBJECTIVE);

        System.out.println();
        printSection(resume, SectionType.PERSONAL);

        System.out.println();
        printSection(resume, SectionType.ACHIEVEMENT);

        System.out.println();
        printSection(resume, SectionType.QUALIFICATIONS);

        System.out.println();
        printSection(resume, SectionType.EXPERIENCE);

        System.out.println();
        printSection(resume, SectionType.EDUCATION);
    }

    private static void fillContactSections(Resume resume) {
        resume.fillContacts(ContactType.PHONE_NUMBER, "+79998886655");
        resume.fillContacts(ContactType.SKYPE, "skype:IvanPetrov");
        resume.fillContacts(ContactType.EMAIL, "ivan.petrov@mail.ru");
        resume.fillContacts(ContactType.LINKEDIN, "linkedInProfile");
        resume.fillContacts(ContactType.STACKOVERFLOW, "stackoverflowprofile");
        resume.fillContacts(ContactType.HOMEPAGE, "homepage");
    }

    private static void fillSingleLineSection(Resume resume, SectionType sectionType, String content) {
        SingleLineSection singleLineSection = new SingleLineSection(content);
        resume.fillSections(sectionType, singleLineSection);
    }

    private static void fillMultiLineSection(Resume resume, SectionType sectionType, List<String> content) {
        MultiLineSection multiLineSection = new MultiLineSection(content);
        resume.fillSections(sectionType, multiLineSection);
    }

    private static void fillCompanySection(Resume resume, SectionType sectionType, List<Company> companies) {
        CompanySection companySection = new CompanySection(companies);
        resume.fillSections(sectionType, companySection);
    }

    private static void printName(Resume resume) {
        System.out.print("Имя: ");
        System.out.println(resume.getFullName());
    }

    private static void printContacts(Resume resume) {
        System.out.println("Контакты:");
        for (var entry : resume.getContacts().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void printSection(Resume resume, SectionType sectionType) {
        System.out.println(sectionType + ":");
        System.out.println(resume.getSections().get(sectionType));
    }
}