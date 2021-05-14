import ZooAnimals.*;

import java.util.ArrayList;

public class Player {

    private int energy = 2;
    private int gold = 1000;
    private ArrayList<Animal> animalsInTheZoo;
    private int foodStorage;
    private int waterStorage;


    public Player() {
        animalsInTheZoo = new ArrayList<>();
        animalsInTheZoo.add(new Elephant());
        animalsInTheZoo.add(new Lion());
        animalsInTheZoo.add(new Monkey());
        animalsInTheZoo.add(new Sloth());
        animalsInTheZoo.add(new Zebra());

    }

    public int getEnergy() {
        return energy;
    }

    public int getGold() {
        return gold;
    }

    public ArrayList<Animal> getAnimalsInTheZoo() {
        return animalsInTheZoo;
    }

    public int getFoodStorage() {
        return foodStorage;
    }

    public void setFoodStorage(int foodStorage) {
        this.foodStorage = foodStorage;
    }

    public int getWaterStorage() {
        return waterStorage;
    }

    public boolean buyFood(int amountOfFood, Store store){

        int totalPrice = store.getFoodPrice() * amountOfFood;

        if(amountOfFood < 1){
            System.out.println("Sorry, you can´t buy " + amountOfFood + " pack(s) of food.");
            return false;
        }

        if(totalPrice > gold){
            System.out.println("Sorry, you don´t have enough money to buy " + amountOfFood + " pack(s) of food.");
            return false;
        } else {
            gold -= totalPrice;
            foodStorage += amountOfFood;
            System.out.println("You bought " + amountOfFood + " pack(s) of food for total of " + totalPrice + " of gold. You have " + gold + " gold left.");
            return true;
        }

    }

    public boolean buyWater(int amountOfWater, Store store){
        int totalPrice = store.getWaterPrice() * amountOfWater;

        if(amountOfWater < 1){
            System.out.println("Sorry, you can´t buy " + amountOfWater + " crate(s) of water.");
            return false;
        }

        if(totalPrice > gold){
            System.out.println("Sorry, you don´t have enough money to buy " + amountOfWater + " crate(s) of water.");
            return false;
        } else {
            gold -= totalPrice;
            waterStorage += amountOfWater;
            System.out.println("You bought " + amountOfWater + " crate(s) of water for total of " + totalPrice + " of gold. You have " + gold + " gold left.");
            return true;
        }
    }



}
