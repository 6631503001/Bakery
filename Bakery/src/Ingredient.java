import java.util.Random;

public class Ingredient implements Bakery{
    private String Name;
    private int Number;

    public int getNumber() {
        return Number;
    }
    public void setNumber(int number) {
        Number = number;
    }

    public Ingredient(String name,int number){
        this.Name = name;
        this.Number = number;
    }
    public Ingredient(String name){
        this.Name = name;
    }

    @Override
    public void Produce(){
        Random random = new Random();
        Number = random.nextInt(100);
    }
    @Override
    public String Describe(){
        return (this.Name + " is Ingredient");
    }
    @Override
    public void DecreaseStock(int n){
        Number = Number-n;
    }
}
