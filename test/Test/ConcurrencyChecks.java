package Test;

import Data.Container;
import Logic.Consumer;
import Logic.Producer;

public class ConcurrencyChecks {

    public static void main(String[] args) throws Exception {
        Container full = new Container();
        full.produce(99);
        boolean producerStopped = stopsWhenInterrupted(new Producer(full));
        boolean consumerStopped = stopsWhenInterrupted(new Consumer("Prueba", new Container()));

        if (!producerStopped || !consumerStopped) {
            throw new AssertionError("Los hilos deben terminar al interrumpir su espera: productor="
                    + producerStopped + ", consumidor=" + consumerStopped);
        }

        Container initialZero = new Container(0);
        Thread consumer = new Thread(() -> {
            try {
                initialZero.consume();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });
        consumer.setDaemon(true);
        consumer.start();
        consumer.join(1000);
        if (consumer.isAlive()) {
            consumer.interrupt();
            consumer.join(1000);
            throw new AssertionError("El constructor con valor debe permitir consumir incluso el cero");
        }
        if (initialZero.getAmount() != 0) {
            throw new AssertionError("El recipiente debe quedar vacio");
        }
        System.out.println("Comprobaciones de concurrencia correctas");
    }

    private static boolean stopsWhenInterrupted(Thread worker) throws Exception {
        worker.setDaemon(true);
        worker.start();
        long deadline = System.nanoTime() + 2_000_000_000L;
        while (worker.getState() != Thread.State.WAITING && worker.isAlive()
                && System.nanoTime() < deadline) {
            Thread.sleep(5);
        }
        if (worker.getState() != Thread.State.WAITING) {
            worker.interrupt();
            throw new AssertionError("El hilo no alcanzo la espera del recipiente");
        }
        worker.interrupt();
        worker.join(500);
        return !worker.isAlive() && worker.isInterrupted();
    }
}
