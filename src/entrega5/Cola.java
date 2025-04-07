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
    public static void main(String[] args) {
        Cola<String> cola = new Cola<>();

        // Añadir elementos
        System.out.println("Añadiendo elementos: 'primero', 'segundo', 'tercero'");
        cola.add("primero");
        cola.add("segundo");
        cola.add("tercero");

        // Mostrar elementos en la cola
        System.out.println("Elementos en la cola: " + cola.elementos);
        System.out.println("Tamaño de la cola: " + cola.elementos.size());

        // Desencolar elementos
        System.out.println("\nDesencolando elementos:");
        try {
            System.out.println("Desencolado: " + cola.remove());
            System.out.println("Cola restante: " + cola.elementos);

            System.out.println("Desencolado: " + cola.remove());
            System.out.println("Cola restante: " + cola.elementos);

            System.out.println("Desencolado: " + cola.remove());
            System.out.println("Cola restante: " + cola.elementos);

            // Intentar desencolar de una cola vacía
            System.out.println("Desencolado: " + cola.remove());
        } catch (IllegalStateException e) {
            System.out.println("Excepción correctamente lanzada al intentar desencolar de una cola vacía: " + e.getMessage());
        }

        // Verificar si la cola está vacía
        System.out.println("\n¿Está vacía? " + cola.isEmpty());
    }
}