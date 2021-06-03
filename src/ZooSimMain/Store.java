package ZooSimMain;

public class Store {

    private int foodPrice = 10;                           // price of the food, does sometimes change because of RanomEvents class
    private int waterPrice = 5;                           // price of water, never changes
    private boolean isClosed = false;                     // shows if store is closed or not
    private boolean isSale = false;                       // shows is there a sale on food or not

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
