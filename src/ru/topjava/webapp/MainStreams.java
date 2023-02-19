package ru.topjava.webapp;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainStreams {
    public static void main(String[] args) {
        System.out.print("minValue():");
        System.out.println(minValue(new int[]{5, 5, 7, 1, 2, 3, 3}));
        System.out.print("oddOrEven():");
        System.out.println(oddOrEven(List.of(1, 2, 3, 4, 5, 6)));
    }

    static int minValue(int[] values) {
        return IntStream.of(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> a * 10 + b);
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        var ref = new Object() {
            int sum = 0;
        };
        return integers.stream().peek(integer -> ref.sum += integer)
                .collect(Collectors.partitioningBy(x -> x % 2 == 0)).get(ref.sum % 2 == 0);
    }
}