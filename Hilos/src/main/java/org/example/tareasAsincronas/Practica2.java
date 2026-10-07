package org.example.tareasAsincronas;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Practica2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> future = executor.submit(() -> {

            Thread.sleep(5000);
            return 100;

        });

        while (!future.isDone()) {
            System.out.println("Esperando... ");

            Thread.sleep(500);
        }

        System.out.println("Resultado" + future.get());
        executor.shutdown();
    }
}
