package entrega5.examen;

import java.util.List;

import java.util.ArrayList;

import entrega5.ListaOrdenada;

public class ClientesPorAntiguedad extends ListaOrdenada<Cliente> {

	 // Constructor: Orders clients by descending antigüedad
    public ClientesPorAntiguedad() {
        super((c1, c2) -> Integer.compare(c2.getAntiguedad(), c1.getAntiguedad()));
    }

    // Método imperativo para obtener los n clientes con mayor antigüedad
    public List<Cliente> topClientes(int n) {
        List<Cliente> topClientes = new ArrayList<>();
        List<Cliente> elementos = this.elementos();

        for (int i = 0; i < n && i < elementos.size(); i++) {
            topClientes.add(elementos.get(i));
        }

        return topClientes;
    }
    public static void main(String[] args) {
        // Create Cliente instances
        Cliente ana = Cliente.of("Ana", 5);
        Cliente juan = Cliente.of("Juan", 2);
        Cliente luis = Cliente.of("Luis", 7);

        //no se usa en este ejemplo
        Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
        Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
        Compra c3 = Compra.of(ana, "Taza con foto", 15.0);

        // Create ClientesPorAntiguedad instance and add clients
        ClientesPorAntiguedad clientesPorAntiguedad = new ClientesPorAntiguedad();
        clientesPorAntiguedad.add(ana);
        clientesPorAntiguedad.add(juan);
        clientesPorAntiguedad.add(luis);

        // Retrieve the top 2 clients by seniority
        List<Cliente> topClientes = clientesPorAntiguedad.topClientes(2);

        // Print the results
        System.out.println("Top 2 clients by seniority:");
        for (Cliente cliente : topClientes) {
            System.out.println(cliente);
        }
    }
}