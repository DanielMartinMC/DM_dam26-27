package org.example.hilos.bloque6;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProductorCons {

   public  static void main(String[] args) {
       BlockingQueue<Integer> buffer = new ArrayBlockingQueue<Integer>(5);

       Thread productor = new Thread(() -> {
           for (int i = 1; i < 5; i++) {
               try {
                   buffer.put(i);
                   System.out.println("El hilo produce " + i);
                   Thread.sleep(200);
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           }
       });

       Thread consumidor = new Thread(() -> {
           for (int i = 1; i < 5; i++) {
               try {
                   buffer.take();
                   System.out.println("El hilo consume " + i);
                   Thread.sleep(200);
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           }
       });
       productor.start();
       consumidor.start();
   }

}
