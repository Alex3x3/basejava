package ru.topjava.webapp.model;

import ru.topjava.webapp.util.DateUtil;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {

        Resume resume = new Resume(uuid, fullName);

        resume.fillContacts(ContactType.PHONE_NUMBER, "+7 (000) 111-22-33");
        resume.fillContacts(ContactType.SKYPE, "skype:contact");
        resume.fillContacts(ContactType.EMAIL, "name@mail.ru");
        resume.fillContacts(ContactType.LINKEDIN, "linkedin:profile");
        resume.fillContacts(ContactType.STACKOVERFLOW, "stackoverflow:profile");
        resume.fillContacts(ContactType.HOMEPAGE, "www.homepage.ru");

        resume.fillSections(SectionType.PERSONAL, new SingleLineSection("Personal data"));
        resume.fillSections(SectionType.OBJECTIVE, new SingleLineSection("Objective data"));
        resume.fillSections(SectionType.ACHIEVEMENT,
                new MultiLineSection(Arrays.asList("Achievement1", "Achievement2")));
        resume.fillSections(SectionType.QUALIFICATIONS,
                new MultiLineSection(Arrays.asList("HTML/CSS", "Java", "Spring")));
        resume.fillSections(SectionType.EXPERIENCE,
                new CompanySection(
                        Arrays.asList(new Company("Company1", "https://company1.ru",
                                        Arrays.asList(new Period("Company1 title1",
                                                        DateUtil.of(2000, Month.JANUARY),
                                                        DateUtil.of(2004, Month.DECEMBER),
                                                        "Company1 period1 description"),
                                                new Period("Company1 title2",
                                                        DateUtil.of(2005, Month.JANUARY),
                                                        DateUtil.of(2009, Month.DECEMBER),
                                                        "Company1 period2 description"))),

                                new Company("Company2", "https://company2.ru",
                                        Arrays.asList(new Period("Company2 title1",
                                                        DateUtil.of(2010, Month.JANUARY),
                                                        DateUtil.of(2014, Month.DECEMBER),
                                                        "Company2 period1 description"),
                                                new Period("Company2 title2",
                                                        DateUtil.of(2015, Month.JANUARY),
                                                        DateUtil.of(2019, Month.DECEMBER),
                                                        "Company2 period2 description"))))));

        resume.fillSections(SectionType.EDUCATION,
                new CompanySection(
                        Arrays.asList(new Company("University1", "https://university1.ru",
                                        List.of(new Period("University1 title1",
                                                DateUtil.of(2000, Month.SEPTEMBER),
                                                DateUtil.of(2005, Month.JULY),
                                                null))),

                                new Company("University2", "https://university2.ru",
                                        List.of(new Period("University2 title1",
                                                DateUtil.of(2005, Month.SEPTEMBER),
                                                DateUtil.of(2010, Month.JULY),
                                                null))))));

        return resume;
    }
}
