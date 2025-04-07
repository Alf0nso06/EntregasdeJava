package entrega5;
import java.util.ArrayList; import java.util.Arrays; import java.util.Comparator; import java.util.List;

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
    public static void main(String[] args) {
        System.out.println("----- Prueba de ListaOrdenadaSinRepeticion -----");

        // Crear una lista ordenada sin repetición con números enteros
        ListaOrdenadaSinRepeticion<Integer> lista = new ListaOrdenadaSinRepeticion<>(Integer::compareTo);

        // Añadir elementos (incluyendo duplicados)
        System.out.println("\nAñadiendo elementos: 5, 2, 8, 1, 3, 5, 2");
        lista.add(5);
        lista.add(2);
        lista.add(8);
        lista.add(1);
        lista.add(3);
        lista.add(5); 
        lista.add(2); 

        // Mostrar elementos en la lista
        System.out.println( lista.elementos());
        System.out.println( lista.size());
      

        
        System.out.println( lista.elementos().get(0));
        lista.elementos().remove(0);

        
        System.out.println( lista.elementos());

        // Añadir elementos en lote (incluyendo duplicados)
        List<Integer> lote = Arrays.asList(4, 6, 7, 4); // 4 repetido
        lista.addAll(lote);
       

        // Mostrar elementos después de añadir en lote
        System.out.println("Elementos después de añadir lote: " + lista.elementos());
        System.out.println("Se espera que el 4 aparezca solo una vez");
    }
}