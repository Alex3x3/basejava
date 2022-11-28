package ru.topjava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        System.out.println(file.getAbsolutePath());

        File dir = new File("./src/ru/topjava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nBaseJava project list:");
        listFilesForFolder(new File("."));
    }

    private static void listFilesForFolder(File folder) {
        try {
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                if (file.isDirectory()) {
                    System.out.println("\nDir: " + file);
                    listFilesForFolder(file);
                } else {
                    System.out.println("File: " + file.getName());
                }
            }
        } catch (NullPointerException ignored) {
        }
    }
}
