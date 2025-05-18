package entrega6;

import us.lsi.aeropuerto.*;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.Duration;

public class PreguntasAeropuertos {

	

	    public static String ciudadAeropuertoMayorFacturacion(LocalDateTime a, LocalDateTime b, List<Vuelo> vuelos) {
	        
	        if (a.isAfter(b) || Duration.between(a, b).toDays() < 1) {
	            throw new IllegalArgumentException("Las fechas deben estar en orden y tener al menos un día de diferencia.");
	        }
	        Map<String, Double> facturacionPorCiudad = vuelos.stream()
	                .filter(v -> !v.fecha().isBefore(a) && !v.fecha().isAfter(b)) // Filtrar vuelos entre las fechas
	                .collect(Collectors.groupingBy(
	                        v -> v.vueloProgramado().ciudadDestino(), // Agrupar por ciudad destino
	                        Collectors.summingDouble(v -> v.vueloProgramado().precio() * v.numPasajeros()) // Sumar facturación
	                ));

	        // Encontrar la ciudad con mayor facturación
	        return facturacionPorCiudad.entrySet().stream()
	                .max(Map.Entry.comparingByValue()) // Buscar el máximo por valor
	                .map(Map.Entry::getKey) // Obtener la ciudad
	                .orElseThrow(() -> new IllegalArgumentException("No hay vuelos en el rango de fechas especificado."));
	    }
	

	
	    public static String ciudadAeropuertoMayorFacturacionImperativo(LocalDateTime a, LocalDateTime b, List<Vuelo> vuelos) {
	        if (a.isAfter(b) || Duration.between(a, b).toDays() < 1) {
	            throw new IllegalArgumentException("Las fechas deben estar en orden y tener al menos un día de diferencia.");
	        }

	        Map<String, Double> facturacionPorCiudad = new HashMap<>();

	        for (Vuelo vuelo : vuelos) {
	            if (!vuelo.fecha().isBefore(a) && !vuelo.fecha().isAfter(b)) {
	                String ciudadDestino = vuelo.vueloProgramado().ciudadDestino();
	                double facturacion = vuelo.vueloProgramado().precio() * vuelo.numPasajeros();
	                facturacionPorCiudad.put(ciudadDestino, facturacionPorCiudad.getOrDefault(ciudadDestino, 0.0) + facturacion);
	            }
	        }

	        String ciudadMayorFacturacion = null;
	        double maxFacturacion = 0.0;

	        for (Map.Entry<String, Double> entry : facturacionPorCiudad.entrySet()) {
	            if (entry.getValue() > maxFacturacion) {
	                maxFacturacion = entry.getValue();
	                ciudadMayorFacturacion = entry.getKey();
	            }
	        }

	        if (ciudadMayorFacturacion == null) {
	            throw new IllegalArgumentException("No hay vuelos en el rango de fechas especificado.");
	        }

	        return ciudadMayorFacturacion;
	    }
	}

