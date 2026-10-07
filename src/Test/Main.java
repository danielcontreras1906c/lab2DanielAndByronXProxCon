/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Test;

// @author kecac
import Data.Container;
import Logic.Consumer;
import Logic.Producer;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Container container = new Container();

        Consumer consumer1 = new Consumer("1", container);
        Consumer consumer2 = new Consumer("2", container);
        Consumer consumer3 = new Consumer("3", container);
        Producer producer = new Producer(container);

        consumer1.setName("Consumidor");
        producer.setName("Productor");
        System.out.printf("[Principal] Recipiente compartido | Cantidad inicial: %d%n", container.getAmount());

        producer.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();

       // Scanner leer = new Scanner(System.in);

        System.out.println("Presioná Enter para detener.");

        try {
            System.in.read();
        } catch (java.io.IOException ex) {
            System.out.println("Error al leer el teclado.");
        } finally {
            producer.interrupt();
            consumer1.interrupt();
            consumer2.interrupt();
            consumer3.interrupt();
        }

    }

}
