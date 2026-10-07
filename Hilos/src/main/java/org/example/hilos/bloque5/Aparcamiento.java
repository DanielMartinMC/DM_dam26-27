package org.example.hilos.bloque5;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Aparcamiento {

    public static void main(String[] args) {
        Semaphore plazas = new Semaphore(3, true);
        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            int coche = i;
            new Thread(()->{
                try {
                    plazas.acquire();
                    System.out.println("EL coche "+ coche +" ha entrado");
                    System.out.println("Coche " + coche + " ha aparcado. Plazas libres: "
                            + plazas.availablePermits());
                    Thread.sleep(500 + random.nextInt(1500));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println("EL coche" + coche + " ha salido" );
                    plazas.release();
                    System.out.println("Plazas libres tras salir coche " + coche + ": "
                            + plazas.availablePermits());

                }
            }).start();
        }
    }
}
