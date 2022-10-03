package com.lab1.task.sorting;

import com.lab1.task.Entities.Book;

import java.util.Arrays;

public class Sorting {
    Book[] array;

    public Sorting(Book[] array) {
        this.array = array;
    }


    public void swap(Book[] array, int ind1, int ind2) {
        Book tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    static void invertUsingFor(Book[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            Book temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }



    public void selectionSort(Book[] array){
        System.out.println(Arrays.toString(array));
        for (int left = 0; left < array.length; left++) {
            int minInd = left;
            for (int i = left; i < array.length; i++) {
                if (array[i].getRating() < array[minInd].getRating()) {
                    minInd = i;
                }
            }
            swap(array, left, minInd);
        }
        invertUsingFor(array);
        System.out.println(Arrays.toString(array));
    }

    public void shuttleSort(Book[] array){
        System.out.println(Arrays.toString(array));
        for (int i = 1; i < array.length; i++) {
            if (array[i].getRating() < array[i - 1].getRating()) {
                swap(array, i, i - 1);
                for (int z = i - 1; (z - 1) >= 0; z--) {
                    if (array[z].getRating() < array[z - 1].getRating()) {
                        swap(array, z, z - 1);
                    } else {
                        break;
                    }
                }
            }
        }
        invertUsingFor(array);
        System.out.println(Arrays.toString(array));
    }

    public void shellSort(Book[] array){
        System.out.println(Arrays.toString(array));
        int gap = array.length / 2;
        // Пока разница между элементами есть
        while (gap >= 1) {
            for (int right = 0; right < array.length; right++) {
                // Смещаем правый указатель, пока не сможем найти такой, что
                // между ним и элементом до него не будет нужного промежутка
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (array[c].getRating() > array[c + gap].getRating()) {
                        swap(array, c, c + gap);
                    }
                }
            }
            // Пересчитываем разрыв
            gap = gap / 2;
        }
        invertUsingFor(array);
        System.out.println(Arrays.toString(array));
    }


    public void quickSort(Book[] array, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        Book pivot = array[(leftMarker + rightMarker) / 2];
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (array[leftMarker].getRating() < pivot.getRating()) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (array[rightMarker].getRating() > pivot.getRating()) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    Book tmp = array[leftMarker];
                    array[leftMarker] = array[rightMarker];
                    array[rightMarker] = tmp;
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            quickSort(array, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(array, leftBorder, rightMarker);
        }

    }
    public void print(){
        invertUsingFor(array);
        System.out.println(Arrays.toString(array));
    }
}
