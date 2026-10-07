/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

// @author kecac
import Data.Container;
import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread {

    private Container container;

    public Producer(Container Container) {
        this.container = Container;
    }

    @Override
    public void run() {
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {

            i++;
            System.out.printf("[%s] Iteracion %d/10 | Intentando producir%n", getName(), i + 1);
            try {
                //  container.produce(i + 1);
                container.produce(ThreadLocalRandom.current().nextInt(1, 4));
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.printf("[%s] Hilo interrumpido; terminando%n", getName());
                return;
            }

            try {
                int pause = ThreadLocalRandom.current().nextInt(700, 1200);
                System.out.printf("[%s] Pausa de %d ms%n", getName(), pause);
                Producer.sleep(pause);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.printf("[%s] Hilo interrumpido; terminando%n", getName());
                return;
            }
        }
        System.out.printf("[%s] Hilo terminado%n", getName());

    }

    public Container getContainer() {
        return container;
    }

}
