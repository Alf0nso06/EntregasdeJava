
package entrega5;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ColaPrioridad<E, P extends Comparable<P>> extends Cola<PriorityElement<E, P>> {

    public static <E, P extends Comparable<P>> ColaPrioridad<E, P> ofPriority() {
        return new ColaPrioridad<>();
    }

    @Override
    public void add(PriorityElement<E, P> element) {
        ListIterator<PriorityElement<E, P>> iterator = elementos.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().priority().compareTo(element.priority()) > 0) {
                iterator.previous();
                break;
            }
        }
        iterator.add(element);
    }

    public void add(E value, P priority) {
        add(new PriorityElement<>(value, priority));
    }

    public void decreasePriority(E value, P newPriority) {
        elementos.removeIf(e -> e.value().equals(value));
        add(value, newPriority);
    }

    public List<E> valuesAsList() {
        List<E> values = new ArrayList<>();
        for (PriorityElement<E, P> element : elementos) {
            values.add(element.value());
        }
        return values;
    }

    public E removeValue() {
        if (elementos.isEmpty()) {
            return null;
        }
        return elementos.remove(0).value();
    }

    public void addAllValues(List<E> values, P priority) {
        for (E value : values) {
            add(value, priority);
        }
    }
}