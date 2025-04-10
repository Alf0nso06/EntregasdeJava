package entrega5.examen;

import java.util.List;
import java.util.stream.Collectors;
import entrega5.Pila;

public class HistorialCompras extends Pila<Compra> {

    // Calcula el total gastado por un cliente específico
    public double totalGastadoPor(Cliente cliente) {
        return this.elementos().stream()
                   .filter(c -> c.getCliente().equals(cliente))
                   .mapToDouble(Compra::getImporte)
                   .sum();
    }

    // Devuelve las compras cuyo importe supera la cantidad indicada
    public List<Compra> comprasMayoresA(double cantidad) {
        return this.elementos().stream()
                   .filter(c -> c.getImporte() > cantidad)
                   .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        // Create Cliente instances
        Cliente ana = Cliente.of("Ana", 5);
        Cliente juan = Cliente.of("Juan", 2);
        Cliente luis = Cliente.of("Luis", 7);

        // Create Compra instances
        Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
        Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
        Compra c3 = Compra.of(ana, "Taza con foto", 15.0);
        Compra c4 = Compra.of(luis, "Poster gigante", 80.0);

        // Create HistorialCompras instance and add purchases
        HistorialCompras historial = new HistorialCompras();
        historial.add(c1);
        historial.add(c2);
        historial.add(c3);
        historial.add(c4);

        // Test totalGastadoPor
        System.out.println("Total gastado por Ana: " + historial.totalGastadoPor(ana));
        System.out.println("Total gastado por Juan: " + historial.totalGastadoPor(juan));
        System.out.println("Total gastado por Luis: " + historial.totalGastadoPor(luis));

        // Test comprasMayoresA
        double cantidad = 50.0;
        List<Compra> comprasMayores = historial.comprasMayoresA(cantidad);
        System.out.println("Compras mayores a " + cantidad + ":");
        System.out.println( comprasMayores );
    }
}