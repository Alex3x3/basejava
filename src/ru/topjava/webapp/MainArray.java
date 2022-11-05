package ru.topjava.webapp;

import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.model.Resume;
import ru.topjava.webapp.storage.AbstractStorage;
import ru.topjava.webapp.storage.MapResumeStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Interactive test for ru.topjava.webapp.storage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private final static AbstractStorage ARRAY_STORAGE = new MapResumeStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Введите одну из команд - (list | size | save fullName | delete uuid |" +
                    " get uuid | update uuid fullName | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 4) {
                System.out.println("Неверная команда");
                continue;
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "save":
                    if (params.length != 3) {
                        System.out.println("Не верно указано имя. Сохранение не выполнено");
                        continue;
                    }
                    ARRAY_STORAGE.save(new Resume(formatName(params[1]) + " " + formatName(params[2])));
                    printAll();
                    break;
                case "delete":
                    try {
                        ARRAY_STORAGE.delete(params[1]);
                    } catch (NotExistStorageException e) {
                        System.out.println("В хранилище отсутсвует запись с данным UUID. Удаление не выполнено");
                    }
                    printAll();
                    break;
                case "get":
                    try {
                        System.out.println(ARRAY_STORAGE.get(params[1]));
                    } catch (NotExistStorageException e) {
                        System.out.println("В хранилище отсутсвует запись с данным UUID");
                    }
                    break;
                case "update":
                    if (params.length != 4) {
                        System.out.println("Не верно указано параметры. Обновление не выполнено");
                        continue;
                    }
                    ARRAY_STORAGE.update(new Resume(params[1], formatName(params[2]) + " "
                            + formatName(params[3])));
                    printAll();
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        List<Resume> list = ARRAY_STORAGE.getAllSorted();
        System.out.println("----------------------------");
        if (list.size() == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : list) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }

    private static String formatName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}