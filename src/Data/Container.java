/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Data;

 // @author kecac
 
public class Container {

    private String name;
    private int amount;
    private boolean producido = false;

    public Container(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public Container() {
        this.name = "leche";
        this.amount = 20;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public synchronized void setAmount(int amount) {
        this.amount = amount;
    }
    
    
      public synchronized void consume() throws InterruptedException{
          if (amount <= 0 ) {//es innecesaria la segunda condicion eliminar
              System.out.printf("[%s] CONSUMO SIN REALIZAR | Recipiente vacio%n",
                      Thread.currentThread().getName());
             
              while (!producido) {                  
                  wait();
              }
          }
         int previousAmount = this.amount;
         this.amount -= amount;
         System.out.printf("[%s] CONSUMO | Cantidad: %d -> %d%n",
                 Thread.currentThread().getName(), previousAmount, this.amount);
          producido = false;
          
   }
    
      public synchronized void produce(int amount) throws InterruptedException{
          
          while (producido) {              
              wait();
          }
          
          int previousAmount = this.amount;
          this.amount = amount;
          System.out.printf("[%s] PRODUCCION | Valor: %d | Cantidad: %d -> %d%n",
                  Thread.currentThread().getName(), amount, previousAmount, this.amount);
          notifyAll();
          producido = true;
          
      
      }
    
    
    
}
