package org.example;

import cl.duoc.models.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        System.out.println("[Zona de carga inicializada]");
        System.out.println();

        //Creación de la zona de carga compartida
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        // Creamos los pedidos
        Pedido pedido1 = new Pedido(1,"Santiago Centro",EstadoPedido.PENDIENTE);
        Pedido pedido2 = new Pedido(2,"Providencia",EstadoPedido.PENDIENTE);
        Pedido pedido3 = new Pedido(3,"Ñuñoa",EstadoPedido.PENDIENTE);
        Pedido pedido4 = new Pedido(4,"Recoleta",EstadoPedido.PENDIENTE);
        Pedido pedido5 = new Pedido(5,"Las Condes",EstadoPedido.PENDIENTE);

        //Agregamos a la zona de carga
        zonaDeCarga.agregarPedido(pedido1);
        zonaDeCarga.agregarPedido(pedido2);
        zonaDeCarga.agregarPedido(pedido3);
        zonaDeCarga.agregarPedido(pedido4);
        zonaDeCarga.agregarPedido(pedido5);
        System.out.println();

        //Acá creamos a los repartidores
        Repartidor camila = new Repartidor("Camila",zonaDeCarga);
        Repartidor luis = new Repartidor("Luis",zonaDeCarga);
        Repartidor pedro = new Repartidor("Pedro", zonaDeCarga);

        //ExecutorServices con 3 hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(camila);
        executor.submit(luis);
        executor.submit(pedro);
        executor.shutdown();

        //Terminan los repartidores
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println();
        System.out.println(
                "Todos los pedidos han sido entregados correctamente"
        );
    }
}