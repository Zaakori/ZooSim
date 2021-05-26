package ZooSimMain;

import ZooSimMain.ZooAnimals.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Player {

    private int energy = 2;
    private int gold = 1000;
    private ArrayList<Animal> animalList;
    private double foodStorage;
    private double waterStorage;
    private final String notEnoughEnergy = "Sorry, you don´t have enough energy to do that.";


    public Player() {
        animalList = new ArrayList<>();

        Elephant elephant = new Elephant();
        Lion lion = new Lion();
        Monkey monkey = new Monkey();
        Sloth sloth = new Sloth();
        Zebra zebra = new Zebra();

        animalList.add(elephant);
        animalList.add(lion);
        animalList.add(monkey);
        animalList.add(sloth);
        animalList.add(zebra);

    }

    public int getEnergy() {
        return energy;
    }

    public void printEnergyAmount(){
        System.out.println("You have " + energy + " energy left.");
    }

    public int getGold() {
        return gold;
    }

    public ArrayList<Animal> getAnimalList() {
        return animalList;
    }

    public double getFoodStorage() {
        return foodStorage;
    }

    public void setFoodStorage(double foodStorage) {
        this.foodStorage = foodStorage;
    }

    public double getWaterStorage() {
        return waterStorage;
    }

    // prints out what animals are currently in the zoo
    public void printAnimalsInTheZoo(){
        System.out.println("Animals in the zoo: ");

        for(Animal a : animalList){
            System.out.println(a.getClassAsString());
        }
    }

    // allows player to buy food and/or water
    public void shopFoodOrWater(Scanner scan, Store store){

        int playerInput;


        if(energy < 1){
            System.out.println(notEnoughEnergy);
            return;
        }

        System.out.println("How many packs of food do you want to buy? Price for a pack today is " + store.getFoodPrice() + " gold. You have " + gold + " gold.");

        playerInput = scan.nextInt();

        buyFood(playerInput, store);

        System.out.println("How many crates of water do you want to buy? Price for a crate today is " + store.getWaterPrice() + " gold. You have " + gold + " gold.");

        playerInput = scan.nextInt();

        buyWater(playerInput, store);

        energy--;
        printEnergyAmount();

    }

    // buys food from the store and adds it to players food storage
    private boolean buyFood(int amountOfFood, Store store){

        int totalPrice = store.getFoodPrice() * amountOfFood;

        if(amountOfFood < 1){
            System.out.println("Sorry, you can´t buy " + amountOfFood + " pack(s) of food.");
            return false;
        }

        if(totalPrice > gold){
            System.out.println("Sorry, you don´t have enough gold to buy " + amountOfFood + " pack(s) of food.");
            return false;
        } else {
            gold -= totalPrice;
            foodStorage += amountOfFood;
            System.out.println("You bought " + amountOfFood + " pack(s) of food for total of " + totalPrice + " of gold. You have " + gold + " gold left.");
            return true;
        }

    }

    // buys water from the store and adds bought water to players water storage
    private boolean buyWater(int amountOfWater, Store store){
        int totalPrice = store.getWaterPrice() * amountOfWater;

        if(amountOfWater < 1){
            System.out.println("Sorry, you can´t buy " + amountOfWater + " crate(s) of water.");
            return false;
        }

        if(totalPrice > gold){
            System.out.println("Sorry, you don´t have enough gold to buy " + amountOfWater + " crate(s) of water.");
            return false;
        } else {
            gold -= totalPrice;
            waterStorage += amountOfWater;
            System.out.println("You bought " + amountOfWater + " crate(s) of water for total of " + totalPrice + " of gold. You have " + gold + " gold left.");
            return true;
        }
    }

    // allows player to give food to animals, player can choose who he feeds
    // in the end takes away 1 energy point (only if player had at least 1 energy point to begin with)
    public void feedTheAnimals(Scanner scan){

        String playerInput = "";


        if(energy < 1){
            System.out.println(notEnoughEnergy);
            return;
        }

        printAnimalsInTheZoo();

        while(!playerInput.equals("DONE")){

            System.out.println("\n Who do you want to feed?");
            System.out.println("(if you are done, type 'done')");

           playerInput = scan.nextLine().toUpperCase();

           for(Animal a : animalList){

               String actualAnimal = a.getClassAsString().toUpperCase();

               if(playerInput.contains(actualAnimal)){
                   if(foodStorage >= a.getFoodNeed()){
                      a.setBodyFoodAmount(a.getBodyFoodAmount() + a.getFoodNeed());
                      foodStorage -= a.getFoodNeed();

                       System.out.println("The " + actualAnimal + " has been fed, you have " + foodStorage + " food packs left.");
                       break;
                   }
               }
           }
        }
        energy--;
        printEnergyAmount();
    }

    // allows player to give water to animals, player can choose who he gives water to
    // in the end takes away 1 energy point (only if player had at least 1 energy point to begin with)
    public void waterTheAnimals(Scanner scan){

        String playerInput = "";


        if(energy < 1){
            System.out.println(notEnoughEnergy);
            return;
        }

        printAnimalsInTheZoo();

        while(!playerInput.equals("DONE")){

            System.out.println("\n Who do you want to give water to?");
            System.out.println("(if you are done, type 'done')");

            playerInput = scan.nextLine().toUpperCase();

            for(Animal a : animalList){

                String actualAnimal = a.getClassAsString().toUpperCase();

                if(playerInput.contains(actualAnimal)){
                    if(waterStorage >= a.getWaterNeed()){
                        a.setBodyWaterAmount(a.getBodyWaterAmount() + a.getWaterNeed());
                        waterStorage -= a.getWaterNeed();

                        System.out.println("The " + actualAnimal + " has been given water, you have " + waterStorage + " crates of water left.");
                        break;
                    }
                }
            }
        }
        energy--;
        printEnergyAmount();
    }

    // allows the player to cure the sick animal for 2 energy points
    public void cureAnimals(Scanner scan){

        String playerInput = "";


        while(!playerInput.equals("DONE")){

            System.out.println("Who do you want to cure? (costs 2 energy points per animal, you have " + energy + " points left)");
            System.out.println("(if don´t want to cure, type 'done'");

            playerInput = scan.nextLine().toUpperCase();

            for(Animal a : animalList){

                if(playerInput.contains(a.getClassAsString().toUpperCase())){

                    if(a.getSicknessPoints() > 0){
                        a.setSicknessPoints(0);
                        a.printIfCured();

                        energy -= 2;
                        printEnergyAmount();

                        return;
                    } else {
                        System.out.println("This animal isn´t sick... Do you still want to cure someone or you´re done?");
                    }
                }
            }
        }
    }



}
