package entrega5.examen;

import java.util.Objects;

public final class Compra {
    private final Cliente cliente;
    private final String descripcion;
    private final double importe;

    // Constructor canónico
    public Compra(Cliente cliente, String descripcion, double importe) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía.");
        }
        if (importe < 0) {
            throw new IllegalArgumentException("El importe no puede ser negativo.");
        }
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.importe = importe;
    }

    // Método de factoría
    public static Compra of(Cliente cliente, String descripcion, double importe) {
        return new Compra(cliente, descripcion, importe);
    }

    // Métodos de consulta
    public Cliente getCliente() {
        return cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getImporte() {
        return importe;
    }

    // Representación como cadena
    @Override
    public String toString() {
        return "Compra [Nombre de cliente=" + cliente.getNombre() +
               ", descripción=" + descripcion +
               ", importe=" + String.format("%.2f", importe) + " €]";
    }

    // Métodos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return Double.compare(compra.importe, importe) == 0 &&
               Objects.equals(cliente, compra.cliente) &&
               Objects.equals(descripcion, compra.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, descripcion, importe);
    }
}
