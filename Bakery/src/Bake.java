import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Bake implements Stock{
    private String Name;
    private int Price;
    private int Number = 0;
    private String Describe = null;
    public  List<Ingredient> Ingredients = new ArrayList<Ingredient>();
    public  List<Integer> UseIngredient = new ArrayList<>();
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
    public boolean addIngredient(Ingredient in,Integer n){
        for(Ingredient b:Ingredients){
            if(b.getName() == in.getName()){
                return false;
            }
        }
        Ingredients.add(in);
        UseIngredient.add(n);
        return true;
    }
    public boolean removeIngredient(Ingredient in){
        int i = 0; 
        for(Ingredient b:Ingredients){
            if(b.getName() == in.getName()){
                Ingredients.remove(b);
                UseIngredient.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }
    @Override
    public String describe() {
        return Describe;
    }
    @Override
    public void produceStock(int n){
        try {
        int i = 0;
        for(Ingredient a : Ingredients){
            a.decreaseStock(UseIngredient.get(i)*n);
            i++;
        }
        Number += n;
    }
         catch (OutofStockException e){
        System.out.println("You don't have enough ingredients to produce.");
    }
    }
    @Override
    public void decreaseStock(int n) throws OutofStockException{
        if(Number-n < 0){
            throw new OutofStockException();
        }
        Number = Number-n;
    }
    

}
