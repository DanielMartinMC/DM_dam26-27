package org.example.hilos.bloque2;

public class CocineroCancelable {

    public static void main(String[] args) {
        Runnable tareaCocinero = () -> {
            System.out.println("Cocinero: Empezando a cocinar el plato ");
            for (int i = 1; i <= 10; i++) {
                System.out.println("Cocinero: Cocinando... (segundo " + i + ")");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Cocinero: Plato cancelada");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("Cocinero: ¡Plato terminado!");
        };

        Thread hiloCocinero = new Thread(tareaCocinero);
        hiloCocinero.start();

        try {
            System.out.println("Cliente: Esperando el plato...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Cliente: He cambiado de opinión. ¡Cancela el pedido!");
        hiloCocinero.interrupt();
    }
}