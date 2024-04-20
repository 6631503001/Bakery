import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    //+ object -Object
    //Exception

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        boolean Nextday= true;
        boolean Bakesomething= true;
        boolean stillhave = true;
        int Day = 1;
        int Number ;
        int wantsome;
        int choice;
        String anwer = null;

        //Make Bake object
        Bake bread = new Bake("Bread", 25);
        Bake croissant = new Bake("Croissant", 50);

        //make Ingredients list and create Ingredient object
        List<Ingredient> Ingredients = new ArrayList<Ingredient>();
        Ingredient flour = new Ingredient("Flour");
        Ingredients.add(flour);
        Ingredient yeet = new Ingredient("Yeet");
        Ingredients.add(yeet);

        //Add Ingredient into Bake object
        bread.addIngredient(flour,2);
        croissant.addIngredient(flour, 3);

       //start here 
        while(Nextday)
        {   //set some variable
            Number = 1;
            stillhave = true;
            anwer = null;
            choice = 0;
            //Need to fix produce stock for the future
            flour.produceStock(1);
            yeet.produceStock(1);
           
           
            System.out.println("==== Day"+Day+"====");  
             System.out.println("We have ");
          
            for(Ingredient a: Ingredients){
                System.out.println( Number + " . "+ a.getName() + " have "+a.getNumber());
                Number++;        
            }
            System.out.println();
            while (Bakesomething) {
            System.out.println("Which one you want to produce first");
            System.out.println(1 +" Bread");
            System.out.println(2 +" croissant");
            choice = scan.nextInt();
            
            if(choice == 1){
                //Use 2 Flour to make bread
                System.out.println("How many you want to make?");
                bread.produceStock(scan.nextInt());
                
                //add check stock for make this line
            }
            else if(choice == 2){
                //Use 3 Flour to make Croissant
                System.out.println("How many you want to make?");
                croissant.produceStock(scan.nextInt());
                
                 //add check stock for make this line
                 
            } 
            
            System.out.println("Want to bake other ?<Y/N>");
            
            anwer = scan2.nextLine();
            //System.out.println(anwer);
            if(anwer.equals("N")){
                Bakesomething= false;
            }
            
        } 
        
        System.out.println("Lets open this store!!");
        //start sell
        while (stillhave)
        {   //set some variable
            wantsome=0;
        
        System.out.println("Which one do you want to buy we still have,\n1.) Bread " + bread.getNumber()+" picese\n2.) Croissant " + croissant.getNumber()+" picese");
        choice = scan.nextInt();
        
        if(choice == 1){
            System.out.println("We still have Bread "+bread.getNumber()+ " \n How many do you want?" );
             wantsome =scan.nextInt();
             
            bread.decreaseStock(wantsome);
            System.out.println("Price is "+bread.getPrice()*wantsome+" Bath");
            
        }
        else if(choice == 2){
           
            System.out.println("We still have Croissant "+croissant.getNumber()+ " \n How many do you want?" );
            wantsome =scan.nextInt();
            
            croissant.decreaseStock(wantsome);
            System.out.println("Price is "+croissant.getPrice()*wantsome+" Bath");
        }   
        System.out.println("Wanna go home?<Y/N>");
        anwer = scan2.nextLine();
         
         if(anwer.equals("Y")){
           stillhave= false;
        }
        scan.close();
        scan2.close();
        }

      
    }
    
}
}
