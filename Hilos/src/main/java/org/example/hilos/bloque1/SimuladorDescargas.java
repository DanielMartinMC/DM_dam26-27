package org.example.hilos.bloque1;

public class SimuladorDescargas {
    static class Descarga extends Thread {
        private String archivo;
    
        public Descarga(String archivo) {
            this.archivo = archivo;
        }

        public void run() {
            System.out.println("Iniciando descarga de: " + archivo);
    
            for (int i = 1; i <= 10; i++) {
                System.out.println(getName() + " -> " + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("La descarga de " + archivo + " fue interrumpida.");
                    Thread.currentThread().interrupt();
                    return;
                }
                System.out.println(archivo + " - Progreso: " + i + "%");
            }
            System.out.println("✅ ¡Descarga completada: " + archivo + "!");
        }
    }

    public static void main(String[] args) {
        Descarga archivo1 = new Descarga("Documento.pdf");
        Descarga archivo2 = new Descarga("Video.mp4");
        Descarga archivo3 = new Descarga("Juego.zip");
        Descarga archivo4 = new Descarga("Imagen.png");

        archivo1.start();
        archivo2.start();
        archivo3.start();
        archivo4.start();

        System.out.println("Todas las descargas han sido iniciadas en segundo plano...\n");
    }
}
