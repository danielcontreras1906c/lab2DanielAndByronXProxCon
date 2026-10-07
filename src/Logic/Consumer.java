/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

// @author kecac
import Data.Container;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer extends Thread {

    private String name;
    private Container container;

    public Consumer(String name, Data.Container container) {
        this.name = name;
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
         int i = 0;

        while (!Thread.currentThread().isInterrupted()) {
            i++;
            System.out.printf("Consumer "+name +"[%s] Iteracion %d/10 | Intentando consumir%n", getName(), i + 1);
            try {
                container.consume(ThreadLocalRandom.current().nextInt(1, 3), name);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.printf("[%s] Hilo interrumpido; terminando%n", getName());
                return;
            }
            try {
                int pause = ThreadLocalRandom.current().nextInt(1000, 2001);
                System.out.printf("[%s] Pausa de %d ms%n", getName(), pause);
                Consumer.sleep(pause);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.printf("[%s] Hilo interrumpido; terminando%n", getName());
                return;
            }
        }
        System.out.printf("[%s] Hilo terminado%n", getName());

    }

}
