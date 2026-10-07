/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

// @author kecac
public class Container {

    private int amount;
    private boolean producido = false;

    public Container(int amount) {
        this.amount = amount;
        this.producido = true;
    }

    public Container() {
        this.amount = 0;
    }

    public int getAmount() {
        return amount;
    }

    public synchronized void consume(int resta, String name) throws InterruptedException {
        while (!producido) {
           // System.out.printf("[%s] EN ESPERA | Recipiente vacio; esperando un dato%n",
             //       Thread.currentThread().getName());
            wait();
        }
        if ((this.amount - resta) < 0) {
            Thread.sleep(1500);
        } else {
            int previousAmount = this.amount;
            this.amount -= resta;
            System.out.printf("Consumidor " + name + "[%s] CONSUMO | Cantidad: %d -> %d%n",
                    Thread.currentThread().getName(), previousAmount, this.amount);
            producido = false;
            notifyAll();
        }

    }

    public synchronized void produce(int amount) throws InterruptedException {
        while (producido) {
            System.out.printf("[%s] EN ESPERA | Recipiente lleno; esperando que se consuma%n",
                    Thread.currentThread().getName());
            wait();
        }

        int previousAmount = this.amount;
        this.amount += amount;
        System.out.printf("[%s] PRODUCCION | Valor: %d | Cantidad: %d -> %d%n",
                Thread.currentThread().getName(), amount, previousAmount, this.amount);

        producido = true;
        notifyAll();

    }

}
