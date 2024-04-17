import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        int choice;
        Bake bread = new Bake("Bread", 25);
        Bake croissant = new Bake("Croissant", 50);
        bread.Produce();
        croissant.Produce();
        //just try to test
        List<Ingredient> Ingredients = new ArrayList<Ingredient>();

        System.out.println("What do you want to buy?");
        System.out.println("Today we have " + bread.getStock() +" pieces of "+ bread.getName());
        System.out.println("And " + croissant.getStock() +" pieces of "+ croissant.getName());

        System.out.print("1 or 2? :");
        Scanner scan = new Scanner(System.in);
        choice = scan.nextInt();
        scan.close();
        if (choice == 1){
            bread.DecreaseStock(1);
        }
        else if(choice == 2){
            croissant.DecreaseStock(1);
        }


        System.out.println("New stock bread : " + bread.getStock() + "\n" + "croissant : " + croissant.getStock());
    } 
}
