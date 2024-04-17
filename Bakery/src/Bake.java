import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Bake implements Stock{
    private String Name;
    private int Price;
    private int Number = 0;
    private String Describe = null;
    private List<Ingredient> Ingredients = new ArrayList<Ingredient>();
    //Constuctorfield
    public Bake(String name,int number,int price){
        this.Name = name;
        this.Number = number;
        this.Price = price;
    }
    public Bake(String name,int price){
        this.Name = name;
        this.Price = price;
    }
    public Bake(String name){
        this.Name = name;
    }
    //GetSetfield
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
    public void SetDescribe(String describe){
        this.Describe = describe;
    }
    //Methods(Produce doesn't success)
    @Override
    public String describe() {
        return Describe;
    }
    @Override
    public void produceStock(){
        
    }
    @Override
    public void decreaseStock(int n) throws OutofStockException{
        if(Number-n < 0){
            throw new OutofStockException();
        }
        Number = Number-n;
    }
    

}
