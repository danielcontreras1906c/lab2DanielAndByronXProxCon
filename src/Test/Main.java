/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package Test;

// @author kecac

import Data.Container;
import Logic.Consumer;
import Logic.Producer;

 
public class Main {

    
    public static void main(String[] args) {
      
        Container container = new  Container("Leche", 16);
        
        Consumer consumer = new Consumer("humano", container);
        Producer producer = new Producer(container);
        producer.setName("Productor");
        consumer.setName("Consumidor");
        System.out.printf("[Principal] Recipiente compartido | Cantidad inicial: %d%n", container.getAmount());
        
        producer.start();
        consumer.start();
        
        
        
    }

}
