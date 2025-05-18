package entrega6;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import us.lsi.centro.Alumno;
import us.lsi.centro.Alumnos;
import us.lsi.centro.Profesor;
import us.lsi.centro.Profesores;

import us.lsi.centro.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PreguntasCentro {
	
	private Centro centro;

    public PreguntasCentro(Centro centro) {
        this.centro = centro;
        
        
    }
	
    public Double promedioEdadProfesoresImperativo(String dniAlumno) {
	    double totalEdad = 0;
	    int contador = 0;

	    // Iterate through all matriculas
	    for (Matricula matricula : Matriculas.of().todas()) {
	        if (matricula.dni().equals(dniAlumno)) {
	            // Iterate through all assignments
	            for (Asignacion asignacion : centro.asignaciones().todas()) {
	                if (asignacion.grupo().equals(matricula.grupo())) {
	                    // Iterate through all professors
	                    for (Profesor profesor : centro.profesores().todos()) {
	                        if (profesor.dni().equals(asignacion.dni())) {
	                            totalEdad += profesor.edad();
	                            contador++;
	                        }
	                    }
	                }
	            }
	        }
	    }
	    // Calculate and return the average
	    if (contador == 0) {
	        return 0.0; // Return 0 if no professors are found
	    } else {
	        return totalEdad / contador;
	    }
	}
	    
	
	public Double promedioEdadProfesoresFuncional (String dniAlumno) {
	
		return Matriculas.of().todas().stream()
		        .filter(m -> m.dni().equals(dniAlumno)) // Filtrar solo las matrículas del alumno
		        .flatMap(m -> centro.asignaciones().todas().stream()
		            .filter(a -> a.grupo().equals(m.grupo())) // Buscar asignaciones del mismo grupo
		            .map(a -> centro.profesores().profesor(a.dni())) // Obtener el profesor por su DNI
		        )
		        .filter(p -> p != null) // Evitar nulls por seguridad
		        .mapToInt(Profesor::edad) // Obtener la edad de cada profesor
		        .average() // Calcular promedio
		        .orElse(0.0); // Devolver 0.0 si no hay profesores
	}
	public Grupo grupoMayorDiversidadEdadImperativo() {
	    // Map to store the ages of students in each group
	    Map<Grupo, List<Integer>> edadesGrupo = new HashMap<>();

	    // Populate the edadesGrupo map
	    for (Matricula matricula : Matriculas.of().todas()) {
	        Grupo grupo = matricula.grupo();
	        for (Alumno alumno : centro.alumnos().todos()) {
	            if (alumno.dni().equals(matricula.dni())) {
	                edadesGrupo.computeIfAbsent(grupo, k -> new ArrayList<>()).add(alumno.edad());
	            }
	        }
	    }

	    // Map to store the age diversity (max - min) for each group
	    Map<Grupo, Integer> diferenciaEdades = new HashMap<>();
	    for (Map.Entry<Grupo, List<Integer>> entry : edadesGrupo.entrySet()) {
	        List<Integer> edades = entry.getValue();
	        int min = Integer.MAX_VALUE;
	        int max = Integer.MIN_VALUE;
	        for (int edad : edades) {
	            if (edad < min) min = edad;
	            if (edad > max) max = edad;
	        }
	        diferenciaEdades.put(entry.getKey(), max - min);
	    }

	    // Find the group with the maximum age diversity
	    Grupo grupoMayor = null;
	    int maxDiferencia = Integer.MIN_VALUE;
	    for (Map.Entry<Grupo, Integer> entry : diferenciaEdades.entrySet()) {
	        if (entry.getValue() > maxDiferencia) {
	            maxDiferencia = entry.getValue();
	            grupoMayor = entry.getKey();
	        }
	    }

	    return grupoMayor;
	}
	public static Grupo grupoMayorDiversidadEdadFuncional() {
	    return Matriculas.of().todas().stream()
	            .collect(Collectors.groupingBy(
	                    Matricula::grupo,
	                    Collectors.mapping(m -> Alumnos.of().alumno(m.dni()).edad(), Collectors.toList())
	            ))
	            .entrySet().stream()
	            .max(Comparator.comparing(e -> Collections.max(e.getValue()) - Collections.min(e.getValue())))
	            .map(Map.Entry::getKey)
	            .orElse(null);
	}
	public static Alumno alumnoMasMatriculasFuncional() {
	    return Matriculas.of().todas().stream()
	            .collect(Collectors.groupingBy(Matricula::dni, Collectors.counting()))
	            .entrySet().stream()
	            .max(Comparator.comparingLong(Map.Entry::getValue))
	            .map(entry -> Alumnos.of().alumno(entry.getKey()))
	            .orElse(null);
	}
	public static Map<String, List<String>> rangosEdadPorAlumnoImperativo(String rangoEdad) {
	    if (rangoEdad == null || rangoEdad.isEmpty()) {
	        throw new IllegalArgumentException("El rango de edad no puede ser nulo o vacío");
	    }

	    Map<String, List<String>> resultado = new HashMap<>();
	    String[] rangos = rangoEdad.split(",");

	    for (String r : rangos) {
	        String[] edades = r.split("-");
	        if (edades.length != 2) {
	            throw new IllegalArgumentException("El rango de edad debe tener el formato 'min-max'");
	        }

	        try {
	            int edadMin = Integer.parseInt(edades[0].strip());
	            int edadMax = Integer.parseInt(edades[1].strip());
	            if (edadMin >= edadMax) {
	                throw new IllegalArgumentException("El rango de edad debe estar ordenado de menor a mayor, sin solaparse");
	            }

	            List<String> alumnosEnRango = new ArrayList<>();
	            for (Alumno alumno : Alumnos.of().todos()) {
	                if (alumno.edad() >= edadMin && alumno.edad() <= edadMax) {
	                    alumnosEnRango.add(alumno.nombre());
	                }
	            }
	            resultado.put(r.strip(), alumnosEnRango);
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("El rango de edad debe contener números enteros");
	        }
	    }

	    return resultado;
	}
	public static Map<String, List<String>> rangosEdadPorAlumnoFuncional(String rangoEdad) {
	    if (rangoEdad == null || rangoEdad.isBlank()) {
	        throw new IllegalArgumentException("El rango de edad no puede ser nulo o vacío");
	    }

	    return Arrays.stream(rangoEdad.split(","))
	            .map(String::strip)
	            .collect(Collectors.toMap(
	                    rango -> rango,
	                    rango -> {
	                        String[] partes = rango.split("-");
	                        int inicio = Integer.parseInt(partes[0].strip());
	                        int fin = Integer.parseInt(partes[1].strip());
	                        if (inicio >= fin) throw new IllegalArgumentException("El rango debe ir de menor a mayor");
	                        return Alumnos.of().todos().stream()
	                                .filter(alumno -> alumno.edad() >= inicio && alumno.edad() <= fin)
	                                .map(Alumno::nombre)
	                                .toList();
	                    }
	            ));
	}
	public static String nombreProfesorMasGruposImperativo(Integer min, Integer max) {
	    if (max < min) {
	        throw new IllegalArgumentException("El rango de edad es incorrecto");
	    }

	    String nombreProfesor = null;
	    int maxAsignaciones = 0;

	    for (Profesor profesor : Profesores.of().todos()) {
	        if (profesor.edad() >= min && profesor.edad() <= max) {
	            int asignaciones = 0;
	            for (Asignacion asignacion : Asignaciones.of().todas()) {
	                if (asignacion.dni().equals(profesor.dni())) {
	                    asignaciones++;
	                }
	            }
	            if (asignaciones > maxAsignaciones) {
	                maxAsignaciones = asignaciones;
	                nombreProfesor = profesor.nombre();
	            }
	        }
	    }

	    return nombreProfesor;
	}
	public static String nombreProfesorMasGruposFuncional(Integer min, Integer max) {
	    if (min > max) {
	        throw new IllegalArgumentException("El rango de edad es incorrecto");
	    }

	    return Asignaciones.of().todas().stream()
	            .collect(Collectors.groupingBy(Asignacion::dni, Collectors.counting()))
	            .entrySet().stream()
	            .max(Map.Entry.comparingByValue())
	            .map(entry -> Profesores.of().profesor(entry.getKey()).nombre())
	            .orElse(null);
	}
	public static List<String> nombresAlumnosMayorNotaImperativo(Integer n, Integer a) {
	    if (a == null || n == null) {
	        throw new IllegalArgumentException("Datos inválidos");
	    }
	    if (n < 1 || n > 10) {
	        throw new IllegalArgumentException("nº de alumnos inválido");
	    }

	    List<Alumno> filtro = new ArrayList<>();
	    for (Alumno alumno : Alumnos.of().todos()) {
	        if (alumno.fechaDeNacimiento().getYear() > a) {
	            filtro.add(alumno);
	        }
	    }

	    filtro.sort((a1, a2) -> Double.compare(a2.nota(), a1.nota()));

	    List<String> resultado = new ArrayList<>();
	    for (int i = 0; i < n && i < filtro.size(); i++) {
	        resultado.add(filtro.get(i).nombre());
	    }

	    return resultado;
	}
	public static List<String> nombresAlumnosMayorNotaFuncional(Integer n, Integer a) {
	    if (n <= 0 || n > 10 || a == null) {
	        throw new IllegalArgumentException("Datos inválidos");
	    }

	    return  Alumnos.of().todos().stream()
	            .filter(alumno -> alumno.fechaDeNacimiento().getYear() > a)
	            .sorted(Comparator.comparingDouble(Alumno::nota).reversed())
	            .limit(n)
	            .map(Alumno::nombre)
	            .toList();
	}
	public static void main(String[] args) {
	    // Create an instance of PreguntasCentro
	     // Replace with actual initialization
	    

	    // Test methods using the instance
	    System.out.println("Grupo mayor diversidad edad (Funcional): " + grupoMayorDiversidadEdadFuncional());
	    System.out.println("Alumno con más matrículas (Funcional): " + alumnoMasMatriculasFuncional());
	    System.out.println("Rangos de edad por alumno (Imperativo): " + rangosEdadPorAlumnoImperativo("18-25,26-30"));
	    System.out.println("Rangos de edad por alumno (Funcional): " + rangosEdadPorAlumnoFuncional("18-25,26-30"));
	    System.out.println("Profesor con más grupos (Imperativo): " + nombreProfesorMasGruposImperativo(30, 50));
	    System.out.println("Profesor con más grupos (Funcional): " + nombreProfesorMasGruposFuncional(30, 50));
	    System.out.println("Nombres alumnos mayor nota (Imperativo): " + nombresAlumnosMayorNotaImperativo(5, 2000));
	    System.out.println("Nombres alumnos mayor nota (Funcional): " + nombresAlumnosMayorNotaFuncional(5, 2000));
	}
}
