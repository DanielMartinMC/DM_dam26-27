package org.example.hilos.bloque3;

import java.util.ArrayList;
import java.util.List;

public class SimulacionBancaria {

    static class CuentaBancaria {
        private int saldo = 0;

        public void depositar(int cantidad){

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            this.saldo = saldo + cantidad;
        }

        public int getSaldo(){
            return saldo;
        }

    }

    public void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria();
        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Thread hilo = new Thread(() -> {
                cuenta.depositar(1);
            });
            hilos.add(hilo);
        }

        for (Thread hilo : hilos) {
            hilo.start();
        }


        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Saldo final: " + cuenta.getSaldo());
    }
}
