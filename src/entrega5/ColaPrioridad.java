
package entrega5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


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
    public static void main(String[] args) {
        System.out.println("----- Prueba de ColaPrioridad -----");

        // Crear una cola de prioridad
        ColaPrioridad<String, Integer> colaPrioridad = new ColaPrioridad<>();

        // Añadir elementos con prioridad
        System.out.println("\nAñadiendo elementos con prioridad:");
        colaPrioridad.add("Crítico", 1);
        colaPrioridad.add("Normal", 3);
        colaPrioridad.add("Urgente", 2);
        colaPrioridad.add("Bajo", 4);

        // Mostrar elementos ordenados por prioridad
        System.out.println("\nElementos en la cola por prioridad: " + colaPrioridad.valuesAsList());
        System.out.println("Elementos con sus prioridades: " + colaPrioridad.elementos());
        System.out.println("Tamaño de la cola: " + colaPrioridad.size());

        // Cambiar la prioridad de "Normal" de 3 a 1
        System.out.println("\nCambiando la prioridad de 'Normal' de 3 a 1:");
        colaPrioridad.decreasePriority("Normal", 1);
        System.out.println("Elementos con prioridad actualizada: " + colaPrioridad.valuesAsList());

        // Desencolando elementos según prioridad
        System.out.println("\nDesencolando elementos por prioridad:");
        try {
            while (!colaPrioridad.isEmpty()) {
                System.out.println("Desencolado: " + colaPrioridad.removeValue());
                System.out.println("Cola restante: " + colaPrioridad.valuesAsList());
            }

            System.out.println("\n¿Está vacía? " + colaPrioridad.isEmpty());

            // Intentar eliminar de una cola vacía (debería lanzar excepción)
            System.out.println("\nIntentando desencolar de una cola vacía...");
            colaPrioridad.removeValue();
        } catch (NoSuchElementException e) {
            System.out.println("Excepción correctamente lanzada al intentar desencolar de una cola vacía: " + e.getMessage());
        }

        // Prueba con addAll
        System.out.println("\nPrueba con addAll:");
        List<String> tareas = Arrays.asList("Tarea A", "Tarea B", "Tarea C");
        colaPrioridad.addAllValues(tareas, 2);
        System.out.println("Elementos añadidos en lote con prioridad 2: " + colaPrioridad.valuesAsList());

        colaPrioridad.add("Tarea Urgente", 1);
        System.out.println("Después de añadir 'Tarea Urgente' con prioridad 1: " + colaPrioridad.valuesAsList());
    }
}