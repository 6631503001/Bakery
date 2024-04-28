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
    private static String FileBakery = "BakeryStore";
    private static String Filename1;
    private static String Filename2;
    static int Day = 1;
    static int choice, money;
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        boolean Nextday = true;
        readBakeryStore();
        try {
            while (Nextday) {
                Banner();
                System.out.println("Welcome to bakery management");
                boolean Bakesomething = true;
                while (Bakesomething) {
                    System.out.println("We have " + money + " Baht");
                    System.out.println("What do you want to do?");
                    System.out.println(
                            "1.) Edit bakery ingredient\n2.) Show&BuyIngredients in stock\n3.) Produce bakery\n4.) Sell Bakery\n5.) Manage Money\n<input 0 to go to End program>");
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
                                clearScreen();
                                SellBakery();
                                break;
                            case 5:
                                clearScreen();
                                ManageMoney();
                                break;
                            case 0:
                                Day += 1;
                                Bakesomething = false;
                                break;
                            default:
                                clearScreen();
                                Banner();
                                System.out.println("Please only select options 0 - 5");
                                break;
                        }
                        writeBakeryStore(Bakes, Allingredients);
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

    // Subprogram 1-5
    // Subprogram 1
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
                System.out.println("Select number to see their ingredients or <insert 0 to main menu>");
                choices = Integer.parseInt(scan.nextLine());
                if (choices == 0) {
                    break;
                }
                selected = Bakes.get(choices - 1);
                clearScreen();
                subprogram = true;
                while (subprogram) {
                    // to show all the ingredient to use
                    boolean a = true;
                    while (a) {
                        Number = 0;
                        System.out.println(selected.getName() + " Use");
                        for (Ingredient n : selected.Ingredients) {
                            System.out.print(n.getName() + " " + selected.UseIngredient.get(Number) + " ");
                            Number++;
                        }
                        System.out.println("\nDo you want to edit Ingredient?");
                        System.out.println("1 to increase ingredient , 2 to decrease ingredient, 3 to not edit");
                        try {
                            choices = Integer.parseInt(scan.nextLine());
                            a = false;
                        } catch (NumberFormatException e) {
                            clearScreen();
                            System.out.println("Input only number please");
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
                                    clearScreen();
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
                            } else {
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
                            clearScreen();
                            System.out.println("Please select only avaliable number");
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

    // Subprogram 2
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
                clearScreen();
                if (choices == 0) {
                    subprogram = false;
                } else {
                    selected = Allingredients.get(choices - 1);
                    while (true) {
                        try {

                            System.out.println(selected.getName() + " is " + selected.getPrice() + " Baht");
                            System.out.println("We have " + selected.getNumber() + " of " + selected.getName());
                            System.out.println("How many do you want to buy?\n<Or enter 0 to go back>");
                            Number = -1;
                            Number = Integer.parseInt(scan.nextLine());
                            int cost = Number * selected.getPrice();
                            if (Number > 0) {
                                if (money >= cost) {
                                    selected.setNumber(selected.getNumber() + Number);
                                    money -= cost;
                                    System.out.println("Buy succesfully\nYou have " + money + "Baht");
                                    scan.nextLine();
                                    clearScreen();
                                    break;
                                } else {
                                    System.out.println("Buy unsuccesfully\nYou have " + money + "Baht But it cost "
                                            + cost + " Baht");
                                    scan.nextLine();
                                }
                            } else if (Number == 0) {
                                clearScreen();
                                break;
                            } else {
                                System.out.println("Cannot insert less than 0!!");
                            }
                            // clearScreen();
                        } catch (Exception e) {
                            clearScreen();
                            System.out.println("Please Enter only integer");
                        }
                    }
                }
            } catch (Exception e) {
                clearScreen();
                // System.out.println("Please select only avaliable number");

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
                    System.out.println("<input 0 to go to Produce menu>");
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

    // check stock
    private static boolean Checkstock(Bake a, int num) {
        if (a.getNumber() - num < 0) {
            return true;
        }
        return false;
    }

    // subprogram 4
    private static void SellBakery() {
        int i = 1;
        int input = 0;
        Bake Customerchoose = new Bake(null);
        //int price = 0;
        while (true) {
            try {
                input = -1;
                //price = 0;
                i = 1;
                System.out.println("Here is menu ");

                for (Bake a : Bakes) {
                    System.out.println(i + ". " + a.getName() + " have " + a.getNumber() + " piece(s) ");
                    i++;
                }
                System.out.println("please select cutoner menu <input 0 to back to main menu>");

                input = Integer.parseInt(scan.nextLine());
                // System.out.println(input);
                if (input == 0) {
                    clearScreen();
                    break;
                }
                Customerchoose = Bakes.get(input - 1);
                while (true) {
                    input = 0;
                    System.out.println("How many " + Customerchoose.getName() + " do customer want?\n<input 0 to back to menu>");
                    try {
                        input = Integer.parseInt(scan.nextLine());
                        if (input == 0){
                            clearScreen();
                            break;
                        }else if (Checkstock(Customerchoose, input)) {
                            System.out.println("We dont have enough.");
                        } 
                        else if(input<0){
                            System.out.println("Positive number pls.");
                        }else{
                            Customerchoose.setNumber(Customerchoose.getNumber()-input);
                            System.out.println("You got "+input+" pices of "+Customerchoose.getName()+".");
                            System.out.println("Price is "+Customerchoose.getPrice()*input+" Bath.");
                            money += Customerchoose.getPrice()*input;
                            scan.nextLine();
                            clearScreen();
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Number only!!");
                    }
                }
            } catch (NumberFormatException e) {
                clearScreen();
                System.out.println("Number only!");
            } catch (IndexOutOfBoundsException e) {
                clearScreen();
                System.out.println("We dont have that menu.");
            }

        }
        /*
         * 
         * while(Checkstock(Customerchose, input));
         * 
         * Customerchose.setNumber(Customerchose.getNumber()-input);
         * System.out.println("You got "+input+" pices of "+Customerchose.getName()+" ."
         * );
         * System.out.println("Price is "+Customerchose.getPrice()*input+" Bath.");
         * money =money+price;
         * clearScreen();
         */
    }

    // Subprogram 5
    private static void ManageMoney() {
        int choices, deposit;
        while (true) {
            try {
                System.out.println("We have " + money + " Baht");
                System.out.println("1 to add more funding or <input 0 to menu>");
                choices = Integer.parseInt(scan.nextLine());
                clearScreen();
                if (choices == 1) {
                    while (true) {
                        try {
                            System.err.println("Do you want to add more funding");
                            deposit = Integer.parseInt(scan.nextLine());
                            if (deposit == 0) {
                                break;
                            } else if (deposit > 0) {
                                money += deposit;
                                System.out.println("Deposit success!!");
                                scan.nextLine();
                                clearScreen();
                                break;
                            } else if (deposit < 0) {
                                if ((money += deposit) < 0) {
                                    System.out.println("You can't Withdraw more than you have!!");
                                    scan.nextLine();
                                    clearScreen();
                                    break;
                                } else {
                                    money += deposit;
                                    System.out.println("Withdraw success!!");
                                    scan.nextLine();
                                    clearScreen();
                                    break;
                                }
                            }

                        } catch (Exception e) {
                            System.out.println("Please Enter only number");
                        }
                    }
                } else if (choices == 0) {
                    break;
                } else {
                    System.out.println("Please enter only 1 or 0");
                }
            } catch (Exception e) {
                clearScreen();
                System.out.println("Please Enter only number");
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

    // Read&WriteFile

    public static void writeBakeryStore(List<Bake> Bakes, List<Ingredient> Allingredients) {
        try {
            BufferedWriter Store = new BufferedWriter(new FileWriter(FileBakery));
            Store.write(Filename1 + " " + Filename2 + " " + money + " " + Day);
            Store.close();
        } catch (Exception e) {
            System.out.println("SomethingWrongHappen");
        }
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
                addmaterial.write(b.getName() + " " + b.getNumber() + " " + b.getPrice() + "\n");
            }
            addmaterial.close();

        } catch (IOException e) {
            System.out.println("Something happen with your file.");
        }
    }

    public static void readBakeryStore() {
        String line = null;
        // ForReadBakeryStoreFile
        int i;
        try {
            BufferedReader readStore = new BufferedReader(new FileReader(FileBakery));
            i = 0;
            while ((line = readStore.readLine()) != null) {
                String fromStore[] = line.split("\\s+");
                Filename1 = fromStore[0];
                Filename2 = fromStore[1];
                money = Integer.parseInt(fromStore[2]);
                Day = Integer.parseInt(fromStore[3]);
                i++;
            }
            readStore.close();
        } catch (Exception e) {
            System.out.println("SomethingWrongHappen");
        }
        try {
            line = null;
            i = 0;
            // read ingredient
            BufferedReader readingredient = new BufferedReader(new FileReader(Filename2));
            i = 0;
            while ((line = readingredient.readLine()) != null) {
                String fromfile2[] = line.split("\\s+");
                Ingredient addsome = new Ingredient(fromfile2[0], Integer.parseInt(fromfile2[1]),
                        Integer.parseInt(fromfile2[2]));
                Allingredients.add(addsome);
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