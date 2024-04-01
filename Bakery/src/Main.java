import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        int choice;
        Bread bread = new Bread("Bread", 25);
        Bread croissant = new Bread("Croissant", 50);
        bread.Produce();
        croissant.Produce();
        //System.out.println("Today we have " + bread.getNumber() +" pieces of "+ bread.getName());
        //System.out.println("Today we have " + croissant.getNumber() +" pieces of "+ croissant.getName());

        System.out.println("What do you want to buy?");
        System.out.println("Today we have " + bread.getStock() +" pieces of "+ bread.getName());
        System.out.println("And " + croissant.getStock() +" pieces of "+ croissant.getName());

        System.out.println("1 or 2? :");
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
