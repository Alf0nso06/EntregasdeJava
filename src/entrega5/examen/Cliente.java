package entrega5.examen;
import java.util.Objects;

public final class Cliente {
    private final String nombre;
    private final int antiguedad;

    // Constructor canónico
    public Cliente(String nombre, int antiguedad) {
    	//comprobacion de que ambos campo esten correctos
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        if (antiguedad < 0) {
            throw new IllegalArgumentException("La antigüedad no puede ser negativa.");
        }
        this.nombre = nombre;
        this.antiguedad = antiguedad;
    }

    // Método de factoría
    public static Cliente of(String nombre, int antiguedad) {
        return new Cliente(nombre, antiguedad);
    }

    // Métodos de consulta
    public String getNombre() {
        return nombre;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    // Representación como cadena
    @Override
    public String toString() {
        return "Cliente[nombre=" + nombre + ", antigüedad=" + antiguedad + "]";
    }

    // Métodos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nombre, cliente.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
