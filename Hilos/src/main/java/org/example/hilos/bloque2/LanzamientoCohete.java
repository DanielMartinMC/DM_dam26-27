package org.example.hilos.bloque2;

public class LanzamientoCohete {

    public static void main(String[] args) {
        // 1. Definimos la tarea de la cuenta regresiva
        Runnable tareaCuenta = () -> {
            for (int i = 10; i >= 0; i--) {
                System.out.println("Despegue en: " + i);
                try {
                    Thread.sleep(1000); // Pausa de 1 segundo
                } catch (InterruptedException e) {
                    System.out.println("Secuencia abortada (Interrumpida).");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        };

        Thread hiloCuentaRegresiva = new Thread(tareaCuenta);
        System.out.println("Iniciando secuencia de lanzamiento...\n");
        hiloCuentaRegresiva.start();

        try {
            hiloCuentaRegresiva.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\n🚀 ¡Despegue!");
    }
}