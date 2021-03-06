package Sorting;

import java.util.Arrays; // Only used for testing
import java.util.Comparator;
import java.util.Random;

public class QuickSort {

    public static <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, Comparator.naturalOrder());
    }

    public static <T> void sort(T[] arr, Comparator<T> comparator) {
        sortRange(arr, 0, arr.length, comparator);
    }

    private static <T> void sortRange(T[] arr, int from, int to, Comparator<T> comparator) {
        if (to - from > 1) {
            int randomPivot = new Random().nextInt(to - from) + from;
            swap(arr, randomPivot, to - 1);

            T pivot = arr[to - 1];
            int middle = from;

            for (int i = from; i < to - 1; i++) {
                if (comparator.compare(arr[i], pivot) < 0) {
                    swap(arr, middle, i);
                    middle++;
                }
            }

            swap(arr, middle, to - 1);

            sortRange(arr, from, middle, comparator);
            sortRange(arr, middle + 1, to, comparator);
        }
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        // Testing sort giving class Integer that has a compareTo method
        Integer[] test = new Integer[]{5, 2, 24, 8, 1, 36, 42, 80, 20, 56};
        sort(test);
        System.out.println(Arrays.deepToString(test));

        System.out.println();

        // Testing sort giving class Dog that does not have a compareTo method
        Dog[] test2 = new Dog[]{
                new Dog("Carl", 3),
                new Dog("Wuffer", 4),
                new Dog("Carl", 4),
                new Dog("Irena", 2)
        };
        sort(test2, (dog1, dog2) -> (dog1.name.compareTo(dog2.name)));
        System.out.println(Arrays.deepToString(test2));
    }

    private static class Dog {
        String name;
        int age;

        Dog(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return name + " " + age;
        }
    }

}
