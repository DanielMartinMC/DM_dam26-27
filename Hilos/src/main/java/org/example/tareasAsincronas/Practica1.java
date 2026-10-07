package org.example.tareasAsincronas;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Practica1 {

    public void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> tarea = () -> {
            Thread.sleep(2000);
            return 42;
        };

        Future<Integer> resultado = executor.submit(tarea);

        System.out.println("Tarea enviada");
        System.out.println("Esperando resultado...");

        try {
            int resultadoFinal = resultado.get();
            System.out.println("Resultado: " + resultadoFinal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();
    }
}
