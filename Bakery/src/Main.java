public class Main {
    public static void main(String[] args) throws Exception {
        Bread bread = new Bread("bread", 25);
        bread.produce();
        System.out.println("Today we have " + bread.getNumber() +" pieces of "+ bread.getName());
    } 
}
