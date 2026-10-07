package org.example.tareasAsincronas;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

public class Practica3 {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Callable<Integer> tarea1 = () -> {
            Thread.sleep(2000);
            return 10;
        };
        Callable<Integer> tarea2 = () -> {
            Thread.sleep(1000);
            return 20;
        };
        Callable<Integer> tarea3 = () -> {
            Thread.sleep(3000);
            return 30;
        };

        Instant inicio = Instant.now();

        Future<Integer> future1 = executor.submit(tarea1);
        Future<Integer> future2 = executor.submit(tarea2);
        Future<Integer> future3 = executor.submit(tarea3);

        try {

            int resultado1 = future1.get();
            int resultado2 = future2.get();
            int resultado3 = future3.get();

            int resultadoTotal = resultado1 + resultado2 + resultado3;
            System.out.println("Resultado total: " + resultadoTotal);

        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido.");
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Una de las tareas lanzó una excepción.");
        } finally {
            executor.shutdown();
        }

        Instant fin = Instant.now();
        long tiempoTotalMs = Duration.between(inicio, fin).toMillis();

        System.out.println("Tiempo total de ejecución: " + tiempoTotalMs + " ms");
    }
}
