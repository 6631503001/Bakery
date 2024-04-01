import java.util.Random;

public class Bread implements Bakery{
    private int Stock;
    private String Name;
    private int Price;
    private String Describe = null;

    public int getStock() {
        return Stock;
    }
    public String getName() {
        return Name;
    }
    public int getPrice() {
        return Price;
    }

    public Bread(String Name,int price){
        this.Name = Name;
        this.Price = price;
    }
    public void SetDescribe(String describe){
        this.Describe = describe;
    }

    @Override
    public void Produce(){
        Random random = new Random();
        Stock = random.nextInt(100);
    }
    @Override
    public String Describe() {
        return Describe;
    }
    @Override
    public void DecreaseStock(int n){
        Stock = Stock-n;
    }
    

}
