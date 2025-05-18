package entrega6;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.biblioteca.Prestamos;


import us.lsi.biblioteca.Libro;
import us.lsi.biblioteca.Libros;

public class Bibliotecapreguntas {
	public static Optional<Entry<Integer, Long>> masVecesPrestado() {
        return Prestamos.of().todos().stream().collect(Collectors.groupingBy(p->p.codigo(),Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByKey());
		
	}
	public static Map.Entry<String, Integer> masVecesPrestadoImperativo() {
        Map<String, Integer> contador = new HashMap<>();

        for (var prestamo : Prestamos.of().todos()) {
            String codigoLibro = prestamo.isbn();
            contador.put(codigoLibro, contador.getOrDefault(codigoLibro, 0) + 1);
        }

        Map.Entry<String, Integer> resultado = null;

        for (Map.Entry<String, Integer> entry : contador.entrySet()) {
            if (resultado == null || entry.getValue() > resultado.getValue()) {
                resultado = entry;
            }
        }

        if (resultado == null) {
            throw new IllegalArgumentException("No hay registros de préstamos.");
        }

        return resultado;
	}
	public static Map<String, Set<String>> librosPorAutor(Libros libros, List<String> nombres) {
        return Libros.of().todos().stream().filter(l->nombres.contains(l.autor())).collect(Collectors.groupingBy(Libro::autor,Collectors.mapping(Libro::titulo, Collectors.toSet())));


            
}
	public static Map<String, Set<String>> librosPorAutorImperativo(Libros libros, List<String> nombres) {
	    Map<String, Set<String>> resultado = new HashMap<>();

	    for (Libro libro : libros.todos()) {
	        String autor = libro.autor();
	        String titulo = libro.titulo();

	        if (nombres == null || nombres.contains(autor)) {
	            resultado.computeIfAbsent(autor, k -> new HashSet<>()).add(titulo);
	        }
	    }

	    return resultado;
	}
	public static void main(String[] args) {
	    // Test para masVecesPrestado
	  System.out.println(masVecesPrestado());

	    // Test para masVecesPrestadoImperativo
	    try {
	        Map.Entry<String, Integer> libroMasPrestadoImperativo = masVecesPrestadoImperativo();
	        System.out.println("Libro más veces prestado (imperativo): " + libroMasPrestadoImperativo);
	    } catch (Exception e) {
	        System.out.println("Error en masVecesPrestadoImperativo: " + e.getMessage());
	    }

	    // Test para librosPorAutor
	   System.out.println(librosPorAutor(Libros.of(), List.of("Mateo Coll","Amor Roberta Pont Riquelme")));

	    // Test para librosPorAutorImperativo
	    try {
	        List<String> autores = List.of("Mateo Coll","Amor Roberta Pont Riquelme");
	        Map<String, Set<String>> librosPorAutorImperativo = librosPorAutorImperativo(Libros.of(), autores);
	        System.out.println("Libros por autor (imperativo): " + librosPorAutorImperativo);
	    } catch (Exception e) {
	        System.out.println("Error en librosPorAutorImperativo: " + e.getMessage());
	    }
	}
	}

