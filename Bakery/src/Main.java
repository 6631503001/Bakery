import java.util.ArrayList;
import java.util.List;
//import java.util.NoSuchElementException;
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
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        boolean Nextday = true;
        ReadbakeryrecipesAndIngredient();

        try {
            while (Nextday) {
                Banner();
                System.out.println("Welcome to bakery management");
                boolean Bakesomething = true;

                while (Bakesomething) {
                    System.out.println("What do you want to do?");
                    System.out.println(
                            "1.) Edit bakery ingredient\n2.) Show&BuyIngredients in stock\n3.) Produce bakery\n4.) Sell Bakery\n5.) End program");
                    if (scan.hasNextInt()) {
                        int choice = Integer.parseInt(scan.nextLine());
                        // clearScreen();
                        switch (choice) {
                            case 1:
                                clearScreen();
                                ShowBakery();
                                break;
                            case 2:
                                clearScreen();
                                ShowIngredients();
                                break;
                            case 3:
                                clearScreen();
                                ProduceBakery();
                                break;
                            case 4:
                                
                                break;
                            case 5:
                                Bakesomething = false;
                                break;
                            default:
                                clearScreen();
                                Banner();
                                System.out.println("Please only select options 1 - 6");

                                break;
                        }
                        WritebakeryrecipesAndIngredient(Bakes, Allingredients);
                    } else {

                        clearScreen();
                        Banner();
                        System.out.println("Invalid input. Please enter a number");
                        scan.nextLine();
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

    // clearscreen
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Banner
    private static void Banner() {
        System.out.println("=========== Day " + Day + " ===========");
    }

    // findIngredient
    private static Ingredient findIngredientByNameforadd(String name) {
        for (Bake a : Bakes) {
            for (Ingredient b : a.Ingredients) {
                if (b.getName().equals(name)) {
                    return b;
                }
            }
        }
        for (Ingredient ingredient : Allingredients) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }

    public static Ingredient findusedingredient(String name) {
        for (Bake a : Bakes) {
            for (Ingredient b : a.Ingredients) {
                if (b.getName().equals(name)) {
                    return b;
                }
            }
        }
        return null;
    }

    // Subprogram 1-4
    //Subprogram 1
    private static void ShowBakery() {
        int Number, choices;
        Bake selected;
        String input;
        boolean program = true, subprogram = true;

        while (program) {
            try {
                Number = 1;
                System.out.println("Show Bakery");
                for (Bake a : Bakes) {
                    System.out.println(Number + ". " + a.getName());
                    Number++;

                }

                System.out.println("Select number to see their ingredients or insert 0 to main menu");

                choices = Integer.parseInt(scan.nextLine());
                if (choices == 0) {
                    break;
                }
                selected = Bakes.get(choices - 1);
                clearScreen();
                subprogram = true;
                while (subprogram) {
                    System.out.println(selected.getName() + " Use");
                    // to show all the ingredient to use
                    Number = 0;
                    for (Ingredient n : selected.Ingredients) {
                        System.out.print(n.getName() + " " + selected.UseIngredient.get(Number) + " ");
                        Number++;
                    }
                    System.out.println("\nDo you want to edit Ingredient?");
                    boolean a = true;
                    while (a) {
                        System.out.println("1 to increase ingredient , 2 to decrease ingredient, 3 to not edit");
                        try {
                            choices = Integer.parseInt(scan.nextLine());
                            a = false;
                        } catch (NumberFormatException e) {
                            System.out.println("Input only number pls.");
                        }
                    }
                    switch (choices) {
                        case 1:
                            System.out.println("Please Insert your ingredient to Insert");
                            input = scan.nextLine();
                            Ingredient in = findIngredientByNameforadd(input);
                            if (in != null) {
                                System.out.println("Please enter how many use Ingredient");
                                try {
                                    Number = 0;
                                    Number = Integer.parseInt(scan.nextLine());
                                    clearScreen();

                                } catch (NumberFormatException e) {
                                    System.out.println("Input only number pls.");
                                }
                                if (Number == 0) {
                                    System.out.println("Invalid number of ingredient");
                                } else if (selected.addsameingredient(in, Number)) {
                                    System.out.print("Add success!!");
                                    scan.nextLine();
                                    clearScreen();
                                } else if (selected.addIngredient(in, Number)) {
                                    System.out.print("Add success!!");
                                    scan.nextLine();
                                    clearScreen();
                                } else {
                                    System.out.println("Can't add your ingredient");
                                    scan.nextLine();
                                    clearScreen();
                                }
                            }

                            else {
                                System.out.println("Your Ingredient doesn't exist in our dictory");
                                scan.nextLine();
                                System.out.print("\033[H\033[2J");
                            }
                            clearScreen();
                            break;

                        case 2:
                            System.out.println("Please Insert your ingredient to decreaes");
                            input = scan.nextLine();
                            Ingredient in2 = findusedingredient(input);
                            if (in2 != null) {
                                System.out.println("Please enter how many Ingredient to decrease");
                                try {
                                    Number = Integer.parseInt(scan.nextLine());
                                    clearScreen();
                                    selected.decreaesIngredient(in2, Number);

                                } catch (NumberFormatException e) {
                                    System.out.println("Input only number pls.");
                                }

                            } else {
                                System.out.println("Your Ingredient doesn't exist in our dictory(?)");
                                scan.nextLine();
                                clearScreen();
                            }
                            break;

                        case 3:
                            subprogram = false;
                            clearScreen();
                            break;

                        default:
                            System.out.print("Please select only avaliable number:");
                            break;
                    }
                }

            } catch (Exception e) {
                clearScreen();
                System.out.println("Please select only avaliable number");
            }
        }
        clearScreen();
    }

    //Subprogram 2
    private static void ShowIngredients() {
        int Number, choices;
        boolean subprogram = true;
        Ingredient selected;

        while (subprogram) {
            try {
                Number = 0;
                System.out.println("We have ");
                // System.out.println("===================================");
                for (Ingredient a : Allingredients) {
                    System.out.println((Number + 1) + ". " + a.getName() + " have " + a.getNumber());
                    Number++;
                }
                System.out.println("Enter Number to buy ingredient or 0 to go back");
                choices = Integer.parseInt(scan.nextLine());
                if (choices == 0) {
                    subprogram = false;
                } else {
                    selected = Allingredients.get(choices - 1);
                    while (true) {
                        try {
                            System.out.println("We have " + selected.getNumber() + " " + selected.getName());
                            System.out.println("How many do you want to buy?\nOr enter 0 to go back");
                            Number = -1;
                            Number = Integer.parseInt(scan.nextLine());
                            if (Number >= 0) {
                                selected.setNumber(selected.getNumber() + Number);
                                System.out.println("Buy succesfully");
                                scan.nextLine();
                                break;
                            } else if(Number == 0) {
                                break;
                            }
                            else {
                                System.out.println("Cannot insert less than 0!!");
                            }
                        } catch (Exception e) {
                            clearScreen();
                            System.out.println("Please Enter only integer");
                        }
                    }
                }
            } catch (Exception e) {
                //clearScreen();
                //System.out.println("Please select only avaliable number");

            }
        }
        
    }

    // subprogram 3
    public static void ProduceBakery() {
        boolean produce = true;
        int i = 0;
        int j = 0;
        int choice = 0;
        int number = 0;
        Bake producebake = new Bake(null);
        while (produce) {
            // this for IndexOutOfBoundsException
            while (true) {
                // this for NumberFormatException
                while (true) {
                    i = 0;
                    System.out.println("This is all ingredient that you have in stock");
                    for (Ingredient a : Allingredients) {
                        i++;
                        System.out.print(i + ") " + a.getName() + " " + a.getNumber() + "\n");

                    }
                    System.out.println("And here is your menu.");
                    i = 0;
                    for (Bake a : Bakes) {
                        i++;
                        j = 0;
                        System.out.print(i + ") " + a.getNumber() + " " + a.getName() + " ==> ");
                        for (Ingredient b : a.Ingredients) {
                            System.out.print("[ use " + a.UseIngredient.get(j) + " parts of " + b.getName() + " ] ");
                            j++;
                        }
                        System.out.print("\n");
                    }

                    System.out.println("Which one do you want to produce?");
                    System.out.println("<input 0 to go to Produce menu");
                    try {
                        choice = Integer.parseInt(scan.nextLine());
                        if (choice == 0) {
                            clearScreen();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        clearScreen();
                        System.out.println("Hey input only number pls!");
                    }
                    clearScreen();
                    break;
                }
                try {
                    producebake = Bakes.get(choice - 1);
                    break;
                } catch (IndexOutOfBoundsException e) {
                    clearScreen();
                    System.out.println("We don't have that menu.");
                }
            } // for NumberFormatException
            while (true) {

                System.out.print(producebake.getName() + " ==> ");
                j = 0;
                for (Ingredient a : producebake.Ingredients) {
                    System.out.print("use " + producebake.UseIngredient.get(j) + " parts of " + a.getName());
                    j++;
                }
                System.out.println("\nAnd here is ingredient in your stock that match with recipes.");
                j = 0;
                for (Ingredient a : producebake.Ingredients) {
                    for (Ingredient b : Allingredients) {
                        if (a.getName().equals(b.getName())) {
                            System.out.print(" [ " + a.getName() + " have " + b.getNumber() + " ] ");
                        }
                    }
                    j++;
                }
                System.out.println("\nHow many do you want to produce\n <input 0 to menu>");
                try {
                    number = Integer.parseInt(scan.nextLine());
                    if (number != 0) {
                        clearScreen();
                        producebake.produceStock(number);
                        System.out.println("Produce success!");
                        break;
                    } else if (number == 0) {
                        clearScreen();
                        break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Input number pls.");
                }

            }

        }
    }

    // Decrease method
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
