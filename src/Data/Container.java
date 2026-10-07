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
    }

    public Container() {
        this.amount = 0;
    }

    public int getAmount() {
        return amount;
    }

    public synchronized void consume() throws InterruptedException {
        // public  void consume() throws InterruptedException {
        if (amount <= 0) {//es innecesaria la segunda condicion eliminar

            while (!producido) {
                System.out.printf("[%s] EN ESPERA | Recipiente vacio; esperando un dato%n",
                        Thread.currentThread().getName());
                wait();
            }
        }
        int previousAmount = this.amount;
        this.amount -= amount;
        System.out.printf("[%s] CONSUMO | Cantidad: %d -> %d%n",
                Thread.currentThread().getName(), previousAmount, this.amount);
        producido = false;
        notifyAll();

    }

    public synchronized void produce(int amount) throws InterruptedException {
        //public void produce(int amount) throws InterruptedException {
        if (this.amount > 0) {
            System.out.printf("[%s] EN ESPERA | Recipiente lleno; esperando que se consuma%n",
                    Thread.currentThread().getName());
            wait();
        }

        while (producido) {
            wait();
        }

        int previousAmount = this.amount;
        this.amount = amount;//cantidad parametro
        System.out.printf("[%s] PRODUCCION | Valor: %d | Cantidad: %d -> %d%n",
                Thread.currentThread().getName(), amount, previousAmount, this.amount);

        notifyAll();

        producido = true;

    }

}
