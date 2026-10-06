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

        for (int i = 0; i < 10; i++) {
            System.out.printf("[%s] Iteracion %d/10 | Intentando producir%n", getName(), i + 1);
            try {
                container.produce(i);
            } catch (InterruptedException ex) {
                System.getLogger(Producer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);//quitar esto
            }

            try {
                int pause = ThreadLocalRandom.current().nextInt(1000, 2001);
                System.out.printf("[%s] Pausa de %d ms%n", getName(), pause);
                Producer.sleep(pause);
            } catch (InterruptedException ex) {
                System.getLogger(Producer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        System.out.printf("[%s] Hilo terminado%n", getName());

    }

    public Container getContainer() {
        return container;
    }

}
