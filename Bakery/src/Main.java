import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;

public class Main {
    // + object -Object
    // Exception
    static List<Bake> Bakes = new ArrayList<Bake>();
    static List<Ingredient> Allingredients = new ArrayList<Ingredient>();
    private static final String Filename1 = "Bake";
    private static final String Filename2 = "Ingredient";
    static int Day = 1;
    static int choice;
    public static void main(String[] args) {
        boolean Nextday = true;
        Scanner scan = new Scanner(System.in);

        ReadbakeryrecipesAndIngredient();

        try {
            while (Nextday) {
                Banner();
                System.out.println("Welcome to bakery management");
                boolean Bakesomething = true;

                while (Bakesomething) {
                    System.out.println("What do you want to do?");
                    System.out.println("1.) Show bakery\n2.) Show Ingredients\n3.) Produce bakery\n4.) Buy Ingredients\n5.) Sell Bakery\n6.) End program");

                    if (scan.hasNextInt()) {
                        int choice = scan.nextInt();
                        //clearScreen();
                        switch (choice) {
                            case 1:
                                ShowBakery();
                                break;
                            case 2:
                                //ShowIngredients();
                                break;
                            case 3:
                                //ProduceBakery();
                                break;
                            case 4:
                                //BuyIngredients();
                                break;
                            case 5:
                                //SellBakery();
                                break;
                            case 6:
                                Bakesomething = false;
                                break;
                            default:
                                System.out.println("Please only select options 1 - 6");
                                break;
                        }
                        WritebakeryrecipesAndIngredient(Bakes, Allingredients);
                    } else {
                        clearScreen();
                        System.out.println("Invalid input. Please enter a number.");
                        //scan.nextLine(); 
                        System.out.println(choice);
                    }
                }

                Nextday = false; // Set to false to exit the loop after one iteration
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            scan.close(); // Close the scanner in the finally block
        }
    }

    //clearscreen
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Banner
    private static void Banner() {
        System.out.println("=========== Day " + Day + " ===========");
    }

    // findIngredient
    private static Ingredient findIngredientByName(String name) {
        for (Ingredient ingredient : Allingredients) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }
    
    // Subprogram 1-6
    private static void ShowBakery() {
        int Number, choices;
        Bake selected;
        String input;
        boolean program = true, subprogram = true;
        Scanner scanbakery = new Scanner(System.in);
        Scanner textscanbakery = new Scanner(System.in);

        while (program) {
            try {
                Number = 1;
                System.out.println("Show Bakery");
                for (Bake a : Bakes) {
                    System.out.println(Number + ". " + a.getName());
                    Number++;

                }
                System.out.println("Select number to see their ingredients or insert 0 to main menu");
                choices = scanbakery.nextInt();
                if (choices == 0) {
                    System.out.print("\033[H\033[2J");
                    break;
                }
                selected = Bakes.get(choices - 1);
                System.out.println(selected.getName() + " Use");
                // to show all the ingredient to use
                Number = 0;
                for (Ingredient n : selected.Ingredients) {
                    System.out.print(n.getName() + " " + selected.UseIngredient.get(Number));
                    Number++;
                }
                while (subprogram) {
                    System.out.println("\nDo you want to edit Ingredient?");
                    System.out.println("1 to increase ingredient , 2 to decrease ingredient, 3 to not edit");
                    choices = scanbakery.nextInt();
                    switch (choices) {
                        case 1:
                            System.out.println("Please Insert your ingredient to Insert");
                            input = textscanbakery.nextLine();
                            Ingredient in = findIngredientByName(input);
                            if (in != null) {
                                System.out.println("Please enter how many use Ingredient");
                                Number = scanbakery.nextInt();
                                if (selected.addIngredient(in, Number)) {
                                    System.out.print("Add success!!");
                                    textscanbakery.nextLine();
                                    System.out.print("\033[H\033[2J");
                                } else {
                                    System.out.println("Can't add your ingredient");
                                    textscanbakery.nextLine();
                                    System.out.print("\033[H\033[2J");
                                }
                            } else {
                                System.out.println("Your Ingredient doesn't exist in our dictory(?)");
                                textscanbakery.nextLine();
                                System.out.print("\033[H\033[2J");
                            }
                            break;
                        case 2:

                            break;
                        case 3:
                            subprogram = false;
                            break;
                        default:
                            System.out.print("Please select only avaliable number:");
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.print("Please select only avaliable number:");
            }
        }
        scanbakery.close();
        textscanbakery.close();
    }

    // **ex** Q: What type do you want to decrease? 1.Bakery 2. Ingredient
    // if select1 --> Which one do you want to decrease? [Loop to show list]
    // decrease=scan.nextint(); -->
    // decreaseSomething(Bakes.get(decrease-1),Bakes.get(decrease-1).getname());} if
    // 2 it will be ingredient
    public static boolean decreaseSomething(Object obj, String name) {
        int i = 0;
        if (obj instanceof Bake) {
            Bake objs = new Bake(name);
            objs = (Bake) obj;
            for (Bake a : Bakes) {
                if (a.getName().equals(objs.getName())) {
                    Bakes.remove(i);
                    return true;
                }
                i++;
            }
        } else if (obj instanceof Ingredient) {
            Ingredient objs = new Ingredient(name);
            objs = (Ingredient) obj;
            for (Ingredient a : Allingredients) {
                if (a.getName().equals(objs.getName())) {
                    Allingredients.remove(i);
                    return true;
                }
                i++;
            }
        }
        return false;

    }

    // Q like: Name the manu or ingredient that you want to add?
    // **Addsomething=scan2.nextLine()**
    // How about type? 1.Bakery 2.Ingredent **Type =scan.nextInt()**
    // addSomething(Cake,Type);
    public static boolean addSomething(String name, int num) {
        // int i=0;
        if (num == 1) {
            Bake objs = new Bake(name);

            for (Bake a : Bakes) {
                if (a.getName().equals(objs.getName())) {
                    System.out.println("Sorry but we have this manu already.");
                    return false;
                }

            }
            Bakes.add(objs);

        } else if (num == 2) {
            Ingredient objs = new Ingredient(name);

            for (Ingredient a : Allingredients) {
                if (a.getName().equals(objs.getName())) {
                    System.out.println("Sorry but we have this Ingredients already.");
                    return false;
                }

            }
            Allingredients.add(objs);

        }

        return true;
    }

    // I think I will use this method at the end of the day because it can add new
    // recipes or new ingerdient of that day in file
    // If you take off some bake or ingredaint in filed will take it out too
    // Then the next day that can auto update by read in file *-*zzz
    public static void WritebakeryrecipesAndIngredient(List<Bake> Bakes, List<Ingredient> Allingredients) {

        try {
            // for write file name bake
            BufferedWriter bake = new BufferedWriter(new FileWriter(Filename1));
            for (Bake a : Bakes) {
                bake.write(a.getName() + " " + a.getPrice());
                for (int i = 0; a.Ingredients.size() > i; i++) {
                    bake.write(" " + a.Ingredients.get(i).getName() + " " + a.UseIngredient.get(i));
                }
                bake.write("\n");
            }
            bake.close();

            BufferedWriter addmaterial = new BufferedWriter(new FileWriter(Filename2));
            for (Ingredient b : Allingredients) {
                addmaterial.write(b.getName() + " " + b.getNumber() + "\n");
            }
            addmaterial.close();

        } catch (IOException e) {
            System.out.println("Something happen with your file.");
        }
    }

    // This method will called at the start of day it will make set variable
    // arraylist
    // Then it will clear file to prepare the write method called at the end of the
    // day
    public static void ReadbakeryrecipesAndIngredient() {
        String line = null;
        int i = 0;
        try {

            // read ingredient
            BufferedReader readingredient = new BufferedReader(new FileReader(Filename2));
            i = 0;
            while ((line = readingredient.readLine()) != null) {
                String fromfile2[] = line.split("\\s+");

                for (String a : fromfile2) {
                    Ingredient addsome = new Ingredient(a);
                    try {
                        int number = Integer.parseInt(a);
                        Allingredients.get(i).setNumber(number);
                    } catch (NumberFormatException e) {
                        Allingredients.add(addsome);
                    }
                }
                i++;

            }
            readingredient.close();

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
                        for (Ingredient e : Allingredients) {
                            if (e.getName().equals(ingredientName)) {
                                rebake.addIngredient(Allingredients.get(in), useingredient);
                            }
                            in++;
                        }
                    }
                }
            }
            readbake.close();
        } catch (IOException e) {
            System.out.println("Something happen with your file.");
        }
    }

}
// if you want to creat method clearfile separate from
// ReadbakeryrecipesAndIngredient just tell me
