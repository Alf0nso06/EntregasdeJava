package entrega5;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Comparator;
import java.util.List;

public class ListaOrdenada<E> extends AgregadoLineal<E> {
    private Comparator<E> comparator;

    public ListaOrdenada(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public static <E> ListaOrdenada<E> of(Comparator<E> comparator) {
        return new ListaOrdenada<>(comparator);
    }

    private int indexOrder(E e) {
        int i = 0;
        while (i < elementos.size() && comparator.compare(e, elementos.get(i)) > 0) {
            i++;
        }
        return i;
    }
    @Override
	public String toString() {
		return "["+ elementos + "]";
	}

    @Override
    public void add(E e) {
        int index = indexOrder(e);
        elementos.add(index, e);
    }
    public static void main(String[] args) {
        ListaOrdenada<Integer> lista = new ListaOrdenada<>(Integer::compareTo);

        lista.add(5);
        lista.add(2);
        lista.add(8);
        lista.add(1);
        lista.add(3);
        System.out.println(lista.elementos());
        System.out.println(lista.size());
        System.out.println(lista);

        lista.remove();
        lista.elementos();
        System.out.println(lista.elementos());
        lista.addAll(Arrays.asList(4, 6, 7));
        lista.elementos();

        lista.removeAll(null);
        lista.elementos();
        lista.isEmpty();

        ListaOrdenada<String> listaStrings = new ListaOrdenada<>(String::compareTo);
        listaStrings.addAll(Arrays.asList("banana", "apple", "date", "cherry"));
        listaStrings.elementos();
        System.out.println(listaStrings);
    }
    //

	
}