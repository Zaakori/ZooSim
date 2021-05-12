import ZooAnimals.Animal;
import java.util.ArrayList;

public class Player {

    private int energy = 2;
    private int gold = 1000;
    private ArrayList<Animal> animalsInTheZoo;
    private int foodStorage;
    private int waterStorage;


    public boolean buyFood(int amountOfFood, Store store){

        int totalPrice = store.getFoodPrice() * amountOfFood;

        if(amountOfFood < 1){
            System.out.println("Sorry, you can´t buy " + amountOfFood + " packs of food.");
            return false;
        }

        if(totalPrice > gold){
            System.out.println("Sorry, you don´t have enough money to buy " + amountOfFood + " packs of food.");
            return false;
        } else {
            gold =- totalPrice;
            foodStorage =+ amountOfFood;
            System.out.println("You bought " + amountOfFood + " packs of food for total of " + totalPrice + " of gold. You have " + gold + " gold left.");
            return true;
        }

    }

    public boolean buyWater(int amountOfWater, Store store){
        int totalPrice = store.getWaterPrice() * amountOfWater;

        if(amountOfWater < 1){
            System.out.println("Sorry, you can´t buy " + amountOfWater + " crates of water.");
            return false;
        }

        if(totalPrice > gold){
            System.out.println("Sorry, you don´t have enough money to buy " + amountOfWater + " crates of water.");
            return false;
        } else {
            gold =- totalPrice;
            waterStorage =+ amountOfWater;
            System.out.println("You bought " + amountOfWater + " crates of water for total of " + totalPrice + " of gold. You have " + gold + " gold left.");
            return true;
        }
    }



}
