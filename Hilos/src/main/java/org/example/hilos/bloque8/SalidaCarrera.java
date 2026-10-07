package org.example.hilos.bloque8;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class SalidaCarrera {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latchListos = new CountDownLatch(5);
        CountDownLatch latchSalida = new CountDownLatch(1);
        Random random = new Random();


        for (int i = 0; i < 5; i++) {
            Thread corredor = new Thread(() -> {
                try {
                    System.out.println("Calentando");
                    Thread.sleep(random.nextInt(1000));
                    System.out.println("Hilo Listo");
                    latchListos.countDown();
                    latchSalida.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();;
                }
            });
            corredor.start();
        }

        latchListos.await();

        System.out.println("Los 5 corredores están listos en la línea de salida!");
        System.out.println(" Preparados... Listos...");

        Thread.sleep(1000);

        System.out.println("Disparo de salida");

        latchSalida.countDown();

    }
}
