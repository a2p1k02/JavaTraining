package nodomain.a2p1k02.java.advanced;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;

public class MyVector<E> extends AbstractList<E> implements List<E>, RandomAccess {

    private static final int MAX_ARRAY_LENGTH = 65535;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULT_ELEMENT_DATA = {};

    private Object[] objectData;

    public MyVector(int size) {
        if (size > 0) {
            this.size = size;
        }
        objectData = new Object[size];
    }

    public MyVector() {
        this.objectData = DEFAULT_ELEMENT_DATA;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Object[] toArray() {
        return Arrays.copyOf(this.objectData, size);
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = objectData.length;
        if (oldCapacity > 0 || objectData != DEFAULT_ELEMENT_DATA) {
            int newCapacity = newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return objectData = Arrays.copyOf(objectData, newCapacity);
        } else {
            return objectData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    public static int newLength(int oldLength, int minGrowth, int prefGrowth) {
        // assert oldLength >= 0
        // assert minGrowth > 0

        int newLength = Math.max(minGrowth, prefGrowth) + oldLength;
        if (newLength - MAX_ARRAY_LENGTH <= 0) {
            return newLength;
        }
        return hugeLength(oldLength, minGrowth);
    }

    private static int hugeLength(int oldLength, int minGrowth) {
        int minLength = oldLength + minGrowth;
        if (minLength < 0) { // overflow
            throw new OutOfMemoryError("Required array length too large");
        }
        if (minLength <= MAX_ARRAY_LENGTH) {
            return MAX_ARRAY_LENGTH;
        }
        return Integer.MAX_VALUE;
    }

    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow(s + 1);
        elementData[s] = e;
        size = s + 1;
    }

    public boolean add(E e) {
        modCount++;
        add(e, objectData, size);
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(objectData[i] == this ? "(this Collection)" : objectData[i]);
            if (objectData[i + 1] == null)
                break;
            sb.append(',').append(' ');
        }
        return sb.append(']').toString();
    }

    @Override
    public E get(int index) {
        return (E) objectData[index];
    }

    @Override
    public int size() {
        return size;
    }
}
