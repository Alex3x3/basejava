package ru.topjava.webapp;

import ru.topjava.webapp.model.Resume;

import java.util.*;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1, "Name1");

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2, "Name2");

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3, "Name3");

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4, "Name4");

    public static void main(String[] args) {
        Collection<Resume> list = new ArrayList<>();
        list.add(RESUME_1);
        list.add(RESUME_2);
        list.add(RESUME_3);
        list.add(RESUME_4);
        System.out.println("List initial collection:\n" +
                list + "\n");

        System.out.println("Trying to remove element while iterating throw" +
                " enhanced loop:");
        try {
            for (Resume r : list) {
                System.out.println(r);
                if (Objects.equals(r.getUuid(), UUID_4)) {
                    list.remove(r);
                }
            }
            System.out.println("list without uuid4:" +
                    list);
        } catch (ConcurrentModificationException e) {
            System.out.println("\nYou can't remove elements while while iterating" +
                    " trow enhanced loop. UUID_4 element wasn't removed");
        }

        Iterator<Resume> iterator = list.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println("\nlist without uuid1 (removed while iteration):\n" +
                list);

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);

        System.out.println("\nDouble search in map. Don't use such code");
        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        System.out.println("\nNo double search in map. Use such code");
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        System.out.println();
        List<Resume> resumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        try {
            resumes.remove(1);
        } catch (UnsupportedOperationException e) {
            System.out.println("You can't change the size of list obtained throw" +
                    " Arrays.asList(...)");
        }
        System.out.println(resumes);
    }
}