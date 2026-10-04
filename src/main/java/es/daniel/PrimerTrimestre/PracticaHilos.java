package es.daniel.PrimerTrimestre;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class PracticaHilos {

    public static final int NUM_ARCHIVOS = 4;
    public static final int INTERVALO_MS = 200;

    private final CountDownLatch latch = new CountDownLatch(NUM_ARCHIVOS);
    private AtomicInteger contadorDescargasCompletadas = new AtomicInteger(0);
    private AtomicInteger contadorDescargasInterrumpidas = new AtomicInteger(0);
    private final Random rand = new Random();

    public final Semaphore descarga = new Semaphore(NUM_ARCHIVOS);

    public final Semaphore descargaExecutor = new Semaphore(NUM_ARCHIVOS);
    public final ExecutorService executor = Executors.newFixedThreadPool(10);
    public final List<Thread> descargas = new ArrayList<>();

    // Clase que EXTIENDE Thread: solo se encarga de ser el hilo,
    public class DescargaThread extends Thread {

        private final String archivo;

        public DescargaThread(String archivo, String nombreHilo) {
            super(nombreHilo);
            this.archivo = archivo;
        }

        @Override
        public void run() {
            runDescarga(archivo);
        }
    }

    public void runDescarga(Semaphore semaphore)
    {
        //aparcar, esperar un poco y salir
        if (semaphore.tryAcquire())
        {

            try {
                Thread.sleep(rand.nextInt(500)+500);
                int i = 0;
                int j=0;
                // lo que tengo que hacer al final.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            semaphore.release();
            contadorDescargasCompletadas.incrementAndGet();
        }
        else{
            System.out.println("no hay mas descargas disponibles");
            contadorDescargasInterrumpidas.incrementAndGet();
        }
        latch.countDown();
    }

    public void crearDescargas()
    {
        for (int i = 0; i < NUM_ARCHIVOS; i++)
        {
            String archivo = "archivo_" + (i + 1);  // effectively final
            descargas.add(new Thread(() -> this.runDescarga(archivo)));

        }
        descargas.forEach(Thread::start);
    }

    public void esperarFinal() {

        //con HILOS
        descargas.forEach(descarga -> {
            try {
                descarga.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void debug() {

        System.out.println("Descargas");
        System.out.println("ContadorCompletadas: " + contadorDescargasCompletadas.get());
        System.out.println("ContadorInterrumpidas: " + contadorDescargasInterrumpidas.get());
    }


}