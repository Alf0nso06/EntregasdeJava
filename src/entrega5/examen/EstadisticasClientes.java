package entrega5.examen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EstadisticasClientes {

    
    public static Map<Cliente, List<Compra>> agruparComprasPorClienteImperativa(List<Compra> compras) {
        Map<Cliente, List<Compra>> resultado = new HashMap<>();
        for (Compra compra : compras) {
            Cliente cliente = compra.getCliente();
            if (!resultado.containsKey(cliente)) {
                resultado.put(cliente, new ArrayList<>());
            }
            resultado.get(cliente).add(compra);
        }
        return resultado;
    }
 // Versión funcional
    public static Map<Cliente, List<Compra>> agruparComprasPorClienteFuncional(List<Compra> compras) {
        return compras.stream().collect(Collectors.groupingBy(Compra::getCliente));
    }
    public static void main(String[] args) {
        // Datos de prueba
        Cliente ana = Cliente.of("Ana", 5);
        Cliente juan = Cliente.of("Juan", 2);
        Cliente luis = Cliente.of("Luis", 7);

        List<Compra> compras = Arrays.asList(
            Compra.of(ana, "Agenda personalizada", 25.5),
            Compra.of(juan, "Camiseta estampada", 60.0),
            Compra.of(ana, "Taza con foto", 15.0),
            Compra.of(luis, "Poster gigante", 80.0)
        );

        // Pruebas
        System.out.println("Funcional: " + EstadisticasClientes.agruparComprasPorClienteFuncional(compras));
        System.out.println("Imperativa: " + EstadisticasClientes.agruparComprasPorClienteImperativa(compras));
    }
}
