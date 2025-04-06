package entrega5;
import java.util.Comparator;

public class ListaOrdenadaSinRepeticion<E> extends ListaOrdenada<E> {
    
    public ListaOrdenadaSinRepeticion(Comparator<E> comparator) {
        super(comparator);
    }

    public static <E> ListaOrdenadaSinRepeticion<E> of(Comparator<E> comparator) {
        return new ListaOrdenadaSinRepeticion<>(comparator);
    }

    @Override
    public void add(E e) {
        if (!elementos().contains(e)) {
            super.add(e);
        }
    }
}