public interface Stock {
    public void produceStock(int n);
    public String describe();
    public void decreaseStock(int n) throws OutofStockException;
}
