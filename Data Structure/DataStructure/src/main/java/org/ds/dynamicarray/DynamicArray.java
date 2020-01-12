package org.ds.dynamicarray;

import org.junit.Assert;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {

    private T[] arr;
    private int capacity = 0; //actual array size
    private int length = 0; //length user thinks array is

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];

    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return size() > 0;
    }

    public T get(int index) {
        return arr[index];
    }

    public void set(int index, T element) {
        arr[index] = element;
    }


    public void add(T element) {
        if (length + 1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2; //double the size

            T[] new_arr = (T[]) new Object[capacity];

            for (int i = 0; i < length; i++) {
                new_arr[i] = arr[i];
            }
            arr = new_arr; // arr has extra nulls padded so assigning to old.
        }
        arr[length++] = element;
    }

    public T removeAt(int rm_index) {
        if (rm_index >= length && rm_index < 0) throw new IndexOutOfBoundsException();
        T data = arr[rm_index];
        T[] new_arr = (T[]) new Object[length - 1];

        for (int i = 0, j = 0; i < length; i++, j++) {
            if (i == rm_index) j--;
            else new_arr[j] = arr[i];
        }
        arr = new_arr;
        capacity = --length;
        return data;
    }


    @Override
    public Iterator<T> iterator() {
        return null;
    }


    public static void main(String[] args) {
        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.add(10);
        arr.add(1);
        arr.removeAt(0);

        Assert.assertEquals(Integer.valueOf(1), arr.get(0));
    }

}
