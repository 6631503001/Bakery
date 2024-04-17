public interface Stock {
    public void produceStock();
    public String describe();
    public void decreaseStock(int n) throws OutofStockException;
}
