public class Store {

    private int foodPrice = 10;
    private int waterPrice = 5;
    private boolean isFoodForSale = true;

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getWaterPrice() {
        return waterPrice;
    }

    public boolean isFoodForSale() {
        return isFoodForSale;
    }

    public void setIsFoodForSale(boolean foodForSale) {
        isFoodForSale = foodForSale;
    }
}
