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
    public boolean addsameingredient(Ingredient in,Integer n){
        int i = 0;
        for(Ingredient b:Ingredients){
            if(b.getName().equals(in.getName())){
                UseIngredient.set(i, UseIngredient.get(i)+n);
                return true;
            }
        }
        return false;
    }
    
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

    
    //You have only remove and decrease stock then i add this one!!
    public void decreaesIngredient(Ingredient in, int num){
       int i = 0;
   
        for(Ingredient a: Ingredients){
            if (in.getName().equals(a.getName())){
                //you can add try catch block if you want
                 if(UseIngredient.get(i)- num<0){
                    System.out.println("Can't less than this.");
                    return ;
                 }
                else if(UseIngredient.get(i)- num==0){
                    removeIngredient(Ingredients.get(i));
                    System.out.println("We remove it already.");
                    return ;
                }
                    
                else{
                    UseIngredient.set(i, UseIngredient.get(i) - num);
                    return;
                }
                
            }
            i++;
        }
       
        return ;
    }
    @Override
    public String describe() {
        return Describe;
    }
    @Override
    public void produceStock(int n){
        try {
        for(int i = 0;i < Ingredients.size();i++){
            Ingredients.get(i).decreaseStock(UseIngredient.get(i)*n);
            
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
