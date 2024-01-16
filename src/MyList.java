import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyList<T>{
    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    private int size;

    public MyList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void add(T element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    public int size() {
        return size;
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                return true;
            }
        }
        return false;
    }
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T removedElement = get(size - 1);
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("elements=[");

        for (int i = 0; i < size; i++) {
            T element = (T) elements[i];
            if (element != null) {
                result.append(element);
                if (i < size - 1) {
                    result.append(", ");
                }
            }
        }

        result.append("]");

        return result.toString();
    }
}
