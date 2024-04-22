import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
public class Main {
    //+ object -Object
    //Exception
    static List<Bake> Bakes = new ArrayList<Bake>();
    static List<Ingredient> Allingredients = new ArrayList<Ingredient>();
    private static final String Filename1 = "Bake";
    private static final String Filename2 = "Ingredient";
    
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
        
        ReadbakeryrecipesAndIngredient();
        //Make Bake object
        /* 
        Bake bread = new Bake("Bread", 25);
        Bakes.add(bread);
        Bake croissant = new Bake("Croissant", 50);
        Bakes.add(croissant);
        Bake test = new Bake("test", 28);
        Bakes.add(test);
        //make Ingredients list and create Ingredient object
        Ingredient flour = new Ingredient("Flour");
        Allingredients.add(flour);
        Ingredient yeet = new Ingredient("Yeet");
        Allingredients.add(yeet);
        
        //Add Ingredient into Bake object
        bread.addIngredient(flour,2);
        croissant.addIngredient(flour, 3);
        */
       //start here 
        while(Nextday)
        {   //set some variable
            Number = 1;
            stillhave = true;
            anwer = null;
            choice = 0;
            //Need to fix produce stock for the future
            //flour.produceStock(1);
            //yeet.produceStock(1);
           
           
            System.out.println("==== Day"+Day+"====");  
            
            
            //I will put the add of decrease right here


            System.out.println("We have ");
          
            for(Ingredient a: Allingredients){
                System.out.println( Number + " . "+ a.getName() + " have "+a.getNumber());
                Number++;        
            }
            System.out.println();
            while (Bakesomething) {
            System.out.println("Which one you want to produce first");
            Number = 1;
            for(Bake a: Bakes){
                System.out.println( Number + " . "+ a.getName() + " have "+a.getNumber() );
                Number++;   
            }     
            choice = scan.nextInt();
            System.out.println("How many you want to make?");
            Bakes.get(choice-1).produceStock(scan.nextInt()); 
            //add check stock for make this line
           
           /*This one tis old version i will make it like above
            /*if(choice == 1){
                //Use 2 Flour to make bread
               
                bread.produceStock(scan.nextInt());
                
               
            }
            else if(choice == 2){
                //Use 3 Flour to make Croissant
                System.out.println("How many you want to make?");
                croissant.produceStock(scan.nextInt());
                
                 //add check stock for make this line
                 
            } */
            
            System.out.println("Want to bake other ?<Y/N>");
            
            anwer = scan2.nextLine();
            //System.out.println(anwer);
            if(anwer.equals("N")){
                Bakesomething= false;
                Nextday = false;
            }
            System.out.println("We have ");
          
            for(Ingredient a: Allingredients){
                System.out.println( Number + " . "+ a.getName() + " have "+a.getNumber());
                Number++;        
            }
            WritebakeryrecipesAndIngredient(Bakes, Allingredients);
        } 
        /* 
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
        */
        scan.close();
        scan2.close();
    }
    
}// **ex** Q: What type do you want to decrease? 1.Bakery  2. Ingredient 
//if select1 --> Which one do you want to decrease?  [Loop to show list]  decrease=scan.nextint(); --> decreaseSomething(Bakes.get(decrease-1),Bakes.get(decrease-1).getname());} if 2 it will be ingredient
        public static boolean decreaseSomething(Object obj,String name){
            int i=0;
            if(obj instanceof Bake){
                Bake objs = new Bake(name);
                objs = (Bake) obj;
                for(Bake a: Bakes){
                    if (a.getName().equals(objs.getName())){
                        Bakes.remove(i);
                        return true;
                    }
                    i++;
                }
            }
            else if(obj instanceof Ingredient){
                Ingredient objs = new Ingredient(name);
                objs = (Ingredient) obj;
                for(Ingredient a: Allingredients){
                    if (a.getName().equals(objs.getName())){
                        Allingredients.remove(i);
                        return true;
                    }
                    i++;
                }
            }
            return false;
           
        }
//Q like: Name the manu or ingredient that you want to add? **Addsomething=scan2.nextLine()** 
//How about type? 1.Bakery  2.Ingredent **Type =scan.nextInt()**  addSomething(Cake,Type); 
        public static boolean addSomething(String name, int num){
            //int i=0;
            if(num == 1){
                Bake objs = new Bake(name);
              
                for(Bake a: Bakes){
                    if (a.getName().equals(objs.getName())){
                        System.out.println("Sorry but we have this manu already.");
                        return false;
                    }
                    
                }
                Bakes.add(objs);
              
            }
            else if(num == 2){
                Ingredient objs = new Ingredient(name);
               
                for(Ingredient a: Allingredients){
                    if (a.getName().equals(objs.getName())){
                        System.out.println("Sorry but we have this Ingredients already.");
                        return false;
                    }
                 
                }
                Allingredients.add(objs);
           
            }
            
            return true;
        }
         
        //I think I will use this method at the end of the day because it can add new recipes or new ingerdient of that day in file
        //If you take off some bake or ingredaint in filed will take it out too 
        //Then the next day that can auto update by read in file *-*zzz  
        public static void WritebakeryrecipesAndIngredient(List<Bake> Bakes, List<Ingredient> Addingredients){
          
            try{
                //for write file name bake
            BufferedWriter bake = new BufferedWriter(new FileWriter(Filename1));
            for(Bake a:Bakes){
                bake.write(a.getName()+" "+a.getPrice()+" ");
                for(  int i =0;a.Ingredients.size()>i;i++){
                    bake.write(" "+a.Ingredients.get(i).getName()+" " + a.UseIngredient.get(i));
                }
                bake.write("\n");
            }
                bake.close();

                BufferedWriter material = new BufferedWriter(new FileWriter(Filename2));
                for(Ingredient b:Addingredients){
                    material.write(b.getName()+" "+b.getNumber()+"\n");
                }
                material.close();

            }
            catch(IOException e){
                System.out.println("Something happen with your file.");
            }
       }
       
       //This method will called at the start of day it will make set variable arraylist
       //Then it will clear file to prepare the write method called at the end of the day
       public static void ReadbakeryrecipesAndIngredient(){
        String line = null;
        int i =0;
        int j =0;
        /*for(Bake a:Bakes){ 
            a.Ingredients.clear();
            a.UseIngredient.clear();
        }    
               Bakes.clear();
               Allingredients.clear();
               */
        try{
            
        
        //read ingredient
        BufferedReader readingredient = new BufferedReader(new FileReader(Filename2));
        i=0;
        while ((line = readingredient.readLine()) != null)
            {  
                 String fromfile2[] = line.split("\\s+");
           
                for(String a:fromfile2)
                 {  Ingredient addsome = new Ingredient(a);
                 try {
                    int number = Integer.parseInt(a);
                    Allingredients.get(i).setNumber(number);
                }catch (NumberFormatException e){
                    Allingredients.add(addsome);
                }
                }
                i++;
    
            }readingredient.close();

        BufferedReader readbake = new BufferedReader(new FileReader(Filename1));
        while ((line = readbake.readLine()) != null) {
            String[] fromfile = line.split("\\s+");
            if (fromfile.length > 1) {
                String name = fromfile[0];
                int price = Integer.parseInt(fromfile[1]);
                Bake rebake = new Bake(name, price);
                Bakes.add(rebake);

                for (int o = 2; o < fromfile.length; o += 2) {
                    String ingredientName = fromfile[o];
                    int useingredient = Integer.parseInt(fromfile[o + 1]);
                    int in = 0;
                    for(Ingredient e:Allingredients){
                        if(e.getName().equals(ingredientName)){
                        rebake.addIngredient(Allingredients.get(in), useingredient);}
                        in++;
                    }
                }
            }
        }
        readbake.close(); 
    } catch(IOException e) {
        System.out.println("Something happen with your file.");
    }
}


       
       }
//if you want to creat method clearfile separate from ReadbakeryrecipesAndIngredient just tell me


