import java.util.Random;

public class Ingredient implements Stock{
    private String Name;
    private int Number = 0;
    private int Price;
   
    //Constructor
    
    public Ingredient(String name,int price){
        this.Name = name;
        this.Price =price;
    }
    public Ingredient(String name,int number,int price){
        this.Name = name;
        this.Number = number;
        this.Price = price;
    }
    public Ingredient(String name){
        this.Name = name;
    }
    //GetSetField
    public String getName() {
        return Name;
    }
    public void setNumber(int number) {
        Number = number;
    }
    public int getNumber() {
        return Number;
    }
    public void setPrice(int price) {
        Price = price;
    }
    public int getPrice() {
        return Price;
    }
    //Methods(produce is still have work to do)
    @Override
    public void produceStock(int n){
        Random random = new Random();
        Number = random.nextInt(100);
    }
    @Override
    public String describe(){
        return (this.Name + " is Ingredient");
    }
    @Override
    public void decreaseStock(int n) throws OutofStockException{
        if(Number-n < 0){
            throw new OutofStockException();
            
        }
        Number = Number-n;
        
    }
    
    
}
