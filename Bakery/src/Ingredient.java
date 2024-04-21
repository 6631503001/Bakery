import java.util.Random;

public class Ingredient implements Stock{
    public String Nameingredient;
    public int Numberingredient = 0;
    private int Price;
   
    //Constructor
    
    public Ingredient(String name,int price){
        this.Nameingredient = name;
        this.Price =price;
    }
    public Ingredient(String name,int number,int price){
        this.Nameingredient = name;
        this.Numberingredient = number;
        this.Price = price;
    }
    public Ingredient(String name){
        this.Nameingredient = name;
    }
    //GetSetField
    public String getName() {
        return Nameingredient;
    }
    public void setNumber(int number) {
        Numberingredient = number;
    }
    public int getNumber() {
        return Numberingredient;
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
        Numberingredient = random.nextInt(100);
    }
    @Override
    public String describe(){
        return (this.Nameingredient + " is Ingredient");
    }
    @Override
    public void decreaseStock(int n) throws OutofStockException{
        if(Numberingredient-n < 0){
            throw new OutofStockException();
        }
        Numberingredient = Numberingredient-n;
    }
    
    
}
