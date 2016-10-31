package org.xander.chessboard;

import java.util.Arrays;

public class oo {
    public static void main(String[] args) {
        String[] array = new String[10000000];

        for (int i = 0; i < array.length; i++) {
            array[i] = "asdasda";
        }

        long start = System.nanoTime();
        String[] result = toUpcase(array);
//        for (String s : result) {
//            System.out.println(s);
//        }
        long end = System.nanoTime() - start;
        System.out.println("sequential - " + end/1_000_000_000);
        System.out.println(result.length);
        long start1 = System.nanoTime();

        String[] strings = toUpcaseStream(array);
//        for (String string : strings) {
//            System.out.println(strings);
//        }
        long end1 = System.nanoTime() - start1;
        System.out.println(strings.length);
        System.out.println("stream -     " + end1/1_000_000_000);


    }

    private static String[] toUpcaseStream(String[] array) {
        String[] collect = Arrays.stream(array).map(String::toUpperCase).toArray(String[]::new);
        return collect;
    }

    private static String[] toUpcase(String[] array) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].toUpperCase();
        }
        return result;
    }
}
