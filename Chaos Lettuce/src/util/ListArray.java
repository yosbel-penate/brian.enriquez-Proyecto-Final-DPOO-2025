package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import exception.EmptyListException;

public class ListArray<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int size;

    // Constructor
    @SuppressWarnings("unchecked")
    public ListArray() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    
 // Método que verifica si la lista contiene el elemento especificado
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? data[i] == null : element.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

   // Método que elimina la primera ocurrencia del elemento especificado de la lista
   public boolean remove(T element) throws EmptyListException {
        if (size == 0) {
            throw new EmptyListException("Cannot remove from an empty list");
        }

        for (int i = 0; i < size; i++) {
            if (element == null ? data[i] == null : element.equals(data[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    // Método para agregar un elemento al final
    public void add(T element) {
        if (size == data.length) {
            resize(2 * data.length);
        }
        data[size++] = element;
    }

    // Método para insertar un elemento en una posición específica
    public void insert(int index, T element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }

        if (size == data.length) {
            resize(2 * data.length);
        }

        // Desplazar elementos para hacer espacio
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = element;
        size++;
    }

    // Método para obtener el tamaño de la lista
    public int length() {
        return size;
    }

    // Método para eliminar un elemento en una posición específica
    public T remove(int index) throws EmptyListException, IndexOutOfBoundsException {
        if (size == 0) {
            throw new EmptyListException("Cannot remove from an empty list");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }

        T removedElement = data[index];

        // Desplazar elementos para llenar el espacio
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[--size] = null; // Limpiar la última referencia

        // Reducir el tamaño del arreglo si está muy vacío
        if (size > 0 && size == data.length / 4) {
            resize(data.length / 2);
        }

        return removedElement;
    }

    // Método para obtener un elemento en una posición específica
    public T get(int index) throws EmptyListException, IndexOutOfBoundsException {
        if (size == 0) {
            throw new EmptyListException("Cannot get from an empty list");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }
        return data[index];
    }

    // Método para modificar un elemento en una posición específica
    public void set(int index, T element) throws EmptyListException, IndexOutOfBoundsException {
        if (size == 0) {
            throw new EmptyListException("Cannot set in an empty list");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }
        data[index] = element;
    }

    // Método interno para redimensionar el arreglo
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    //Implementación del iterador para permitir for-each
    @Override
    public Iterator<T> iterator() {
        return new ListArrayIterator();
    }
    
    private class ListArrayIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove no está soportado");
        }
    }
}
