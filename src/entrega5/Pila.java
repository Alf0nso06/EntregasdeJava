package entrega5;

import java.util.LinkedList;

public class Pila<E> extends AgregadoLineal<E> {
    private LinkedList<E> elementos = new LinkedList<>();

    @Override
    public void add(E e) {
        elementos.addFirst(e);
    }

    public E top() {
        if (elementos.isEmpty()) {
            return null;
        }
        return elementos.getFirst();
    }
}