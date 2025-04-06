package entrega5;

import java.util.ArrayList;

public class Cola<E> extends AgregadoLineal<E> {

    public Cola() {
        super();
    }

    public static <E> Cola<E> of() {
        return new Cola<>();
    }

    @Override
    public void add(E e) {
        elementos.add(e);
    }

    public E remove() {
        if (elementos.isEmpty()) {
            return null;
        }
        return elementos.remove(0);
    }
}