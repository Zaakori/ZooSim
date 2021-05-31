package ZooSimMain;

public class Store {

    private int foodPrice = 10;
    private int waterPrice = 5;
    private boolean isClosed = false;
    private boolean isSale = false;

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getWaterPrice() {
        return waterPrice;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean foodForSale) {
        isClosed = foodForSale;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }
}
