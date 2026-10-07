package org.example.hilos.bloque5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RecursoCom {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        Runnable tarea = () -> {
            String nombre = Thread.currentThread().getName();
            boolean completado = false;

            while (!completado) {
                try {
                    if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(nombre + " ha entrado y está trabajando");
                            Thread.sleep(2000);
                            completado = true;
                            System.out.println(nombre + " ha terminado.");
                        } finally {
                            lock.unlock();
                        }
                    } else {
                        System.out.println(nombre + ": Ocupado, lo intento más tarde.");
                    }

                } catch (InterruptedException e) {
                    System.out.println(nombre + " fue interrumpido durante la espera.");


                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread hilo1 = new Thread(tarea, "Hilo1");
        hilo1.start();
        Thread hilo2 = new Thread(tarea, "Hilo2");
        hilo2.start();
    }
}