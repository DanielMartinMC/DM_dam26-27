package org.example.hilos.bloque1;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SimuladorVirtual {

    private static void descargaVirtual(int numeroDescargas) {
        Runnable descarga = () -> {
            for (int i = 1; i <= 10; i++) {
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        };

        List<Thread> hilos = new ArrayList<>();
        Instant inicio = Instant.now();

        for (int i = 0; i < numeroDescargas; i++) {
            Thread hilo = Thread.ofVirtual().unstarted(descarga);
            hilos.add(hilo);
            hilo.start();
        }

        Instant fin = Instant.now();
        long tiempoTotal = Duration.between(inicio, fin).toMillis();

        System.out.println("Todas las descargas completadas.");
        System.out.println("Tiempo total de ejecución: " + tiempoTotal + " ms");
    }

    public static void main(String[] args) {
        int numeroDescargas = 10000;
        System.out.println("=== Iniciando prueba con " + numeroDescargas + " Hilos Virtuales ===");
        descargaVirtual(numeroDescargas);
    }



}