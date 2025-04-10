package entrega5.examen;

import java.util.List;
import java.util.stream.Collectors;
import entrega5.Cola;

public class ColaComprasPendientes extends Cola<Compra> {

    // Busca la primera compra cuya descripción contenga la cadena indicada (imperativo)
    public Compra buscarCompraPorDescripcion(String desc) {
        for (Compra compra : this.elementos()) {
            if (compra.getDescripcion().contains(desc)) {
                return compra;
            }
        }
        return null; // Si no se encuentra ninguna compra
    }

    // Filtra las compras pendientes de un cliente que contengan el producto indicado (funcional)
    public List<Compra> filtrarPorClienteYProducto(Cliente cliente, String producto) {
        return this.elementos().stream()
                   .filter(c -> c.getCliente().equals(cliente) && c.getDescripcion().contains(producto))
                   .collect(Collectors.toList());
    }
   
        public static void main(String[] args) {
            // Crear clientes
            Cliente ana = Cliente.of("Ana", 5);
            Cliente juan = Cliente.of("Juan", 2);
            Cliente luis = Cliente.of("Luis", 7);

            // Crear compras
            Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
            Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
            Compra c3 = Compra.of(ana, "Taza con foto", 15.0);
            Compra c4 = Compra.of(luis, "Poster gigante", 80.0);

            // Crear cola de compras pendientes y agregar compras
            ColaComprasPendientes cola = new ColaComprasPendientes();
            cola.add(c1);
            cola.add(c2);
            cola.add(c3);
            cola.add(c4);

            // Probar buscarCompraPorDescripcion
            System.out.println("--------------buscar por compra-------");
            System.out.println("Compra con 'Taza': " + cola.buscarCompraPorDescripcion("Taza"));
            System.out.println("Compra con 'Poster gigante': " + cola.buscarCompraPorDescripcion("Poster gigante"));
            System.out.println("Compra con \"Agenda personalizada\": " + cola.buscarCompraPorDescripcion("Agenda personalizada"));
            System.out.println("Compra con \"Camiseta estampada\": " + cola.buscarCompraPorDescripcion("Camiseta estampada"));
            System.out.println("--------------filtrar por cliente-------");

            // Probar filtrarPorClienteYProducto
            List<Compra> comprasAna = cola.filtrarPorClienteYProducto(ana, "Agenda");
            System.out.println("Compras de Ana con 'Agenda': " + comprasAna);
            List<Compra> comprasjuan = cola.filtrarPorClienteYProducto(juan, "Camiseta estampada");
            System.out.println("Compras de Juan con 'Agenda': " + comprasjuan);
            List<Compra> comprasluis = cola.filtrarPorClienteYProducto(luis, "Poster gigante");
            System.out.println("Compras de Luis con \"Poster gigante\": " + comprasluis);
        }
    }

