package org.example.hilos.bloque1;
public class SimuladorArchivoConRunnable {

    static class DescargaRunnable implements Runnable {
        private String archivo;

        public DescargaRunnable(String archivo) {
            this.archivo = archivo;
        }

        @Override
        public void run() {
            System.out.println("Iniciando descarga de: " + archivo);

            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() +" -> " + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("La descarga de " + archivo + " fue interrumpida.");
                    Thread.currentThread().interrupt();
                    return;
                }
                System.out.println(archivo + " - Progreso: " + i + "%");
            }
            System.out.println("Descarga completada: " + archivo);
        }
    }
    public static void main(String[] args) {
        DescargaRunnable archivo1 = new DescargaRunnable("Documento.pdf");
        DescargaRunnable archivo2 = new DescargaRunnable("Video.mp4");
        DescargaRunnable archivo3 = new DescargaRunnable("Juego.zip");
        DescargaRunnable archivo4 = new DescargaRunnable("Imagen.png");

        Thread a1 = new Thread(archivo1);
        a1.start();
        Thread a2 = new Thread(archivo2);
        a2.start();
        Thread a3 = new Thread(archivo3);
        a3.start();
        Thread a4 = new Thread(archivo4);
        a4.start();


        System.out.println("Todas las descargas han sido iniciadas en segundo plano...\n");
    }
}



