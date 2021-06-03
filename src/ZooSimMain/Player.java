package ZooSimMain;

import ZooSimMain.ZooAnimals.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Player {

    private int energy;                             // stores how many energy points does the player have, in exchange for energy player can do stuff
    private final int DEFAULT_ENERGY = 3;           // determines how many energy points are default, so how many are restored each day
    private int gold = 250;                         // how much gold player has
    private ArrayList<Animal> animalList;           // list of animals that live in players Zoo
    private double foodStorage;                     // amount of food that is stored, is used to feed animals
    private double waterStorage;                    // amount of stored water, is used to give animals water
    private boolean animalHasBeenStolen = true;     // is used to determine whether player has solved StolenAnimal problem or not (if it arises)
    private final String notEnoughEnergy = "Sorry, you don´t have enough energy to do that.";


    public Player() {
        energy = DEFAULT_ENERGY;
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

    public void resetEnergy(){
        energy = DEFAULT_ENERGY;
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

    public boolean isAnimalHasBeenStolen() {
        return animalHasBeenStolen;
    }

    public void setAnimalHasBeenStolen(boolean animalHasBeenStolen) {
        this.animalHasBeenStolen = animalHasBeenStolen;
    }

    // prints out what animals are currently in the zoo
    public void printAnimalsInTheZoo(){
        System.out.println("Animals in the zoo: ");

        for(Animal a : animalList){
            System.out.println(a.getClassAsString());
        }
    }

    // prints out a kind of table that shows how many days (from this point in time) can animals live without food and water
    public void printAnimalWaterAndFoodState(){

        for(Animal a : animalList){
            a.printFoodAndWaterAmountInDays();
        }

    }

    // if lion dies, every alive animals sickness resistance goes down by 20%
    public void moraleDropLION(){

        for(Animal a : animalList){
            a.setSicknessResistance(a.getSicknessResistance() - 0.2);
        }

    }

    // every day when sloth is alive, the probability of ´rat invasion´ goes up by 1 point, up to 25 points
    public void ratInvasionUpSLOTH(RandomEvents randomEvents){

        if(randomEvents.getRatInvasionProbability() >= 25){
            return;
        }

        randomEvents.setRatInvasionProbability(randomEvents.getRatInvasionProbability() + 1);

    }

    // each day on which zebra is alive the probability of ´nothing happened´ goes up by 2 points, up to 25 points
    public void nothingHappenedUpZEBRA(RandomEvents randomEvents){

        if(randomEvents.getNothingHappenedProbability() >= 25){
            return;
        }

        randomEvents.setNothingHappenedProbability(randomEvents.getNothingHappenedProbability() + 2);

    }

    // main method that controls what can a player do in a day, kind of a control panel for all player activities
    public void whatYouWannaDoToday(Scanner scan, Store store, ArrayList<Animal> sickList, boolean animalIsStolen){

        int playerInput = -1;
        boolean alreadySolvedSick = false;
        boolean alreadySolvedIllegalSeller = false;


        System.out.println("What do you want to do today?");
        System.out.println("(type the corresponding number and press enter)");

        while(playerInput != 0){

            // part that displays the options to the player
            System.out.println();
            printEnergyAmount();
            System.out.println();

            System.out.println("0 - do nothing and wait for the next day [costs 0 energy points]");
            System.out.println("1 - check how thirsty and hydrated the animals are [costs 0 energy points] \n");
            System.out.println("2 - go shopping for food and water [costs 1 energy point]");
            System.out.println("3 - give food to the animals [costs 1 energy point]");
            System.out.println("4 - give water to animals [costs 1 energy point] \n");

            if(!sickList.isEmpty() && !alreadySolvedSick){
                System.out.println("5 - cure sick animal [costs 2 energy points]");
            }

            if(animalIsStolen && !alreadySolvedIllegalSeller){
                System.out.println("6 - solve the Illegal Animal Seller problem [30 gold / 2 energy points]");
            }

            // part that analyzes and reacts to players input
                playerInput = scan.nextInt();


            if(playerInput == 1){
                printAnimalWaterAndFoodState();
            } else if(playerInput == 2){
                shopFoodOrWater(scan, store);
            } else if(playerInput == 3){
                feedTheAnimals(scan);
            } else if(playerInput == 4){
                waterTheAnimals(scan);
            } else if(playerInput == 5){

                if(sickList.isEmpty()){
                    System.out.println("There is nobody to cure, everyone is okay.");
                } else if (!sickList.isEmpty() && !alreadySolvedSick){
                   alreadySolvedSick = cureAnimals(scan);
                }
            } else if(playerInput == 6){

                if(!animalIsStolen){
                    System.out.println("Nobody is stolen, chill.");
                } else if(animalIsStolen && !alreadySolvedIllegalSeller) {
                   alreadySolvedIllegalSeller = solveIllegalAnimalSeller(scan);
                }
            }

            if(energy <= 0){
                return;
            }
        }
    }

    // allows player to buy food and/or water
    public void shopFoodOrWater(Scanner scan, Store store){

        int playerInput;
        boolean foodIsBought = false;
        boolean waterIsBought = false;


        if(energy < 1){
            System.out.println(notEnoughEnergy);
            return;
        }

        if(!store.isClosed()){

            while(!foodIsBought){
                System.out.println("How many packs of food do you want to buy? Price for a pack today is " + store.getFoodPrice() + " gold. You have " + gold + " gold.");

                playerInput = scan.nextInt();

                foodIsBought = buyFood(playerInput, store);
            }

        } else {
            System.out.println("No food today for sale. Maybe tomorrow there´s better luck.");
        }

        while(!waterIsBought){
            System.out.println("How many crates of water do you want to buy? Price for a crate today is " + store.getWaterPrice() + " gold. You have " + gold + " gold.");

            playerInput = scan.nextInt();

           waterIsBought = buyWater(playerInput, store);
        }


        energy--;
    }

    // buys food from the store and adds it to players food storage
    private boolean buyFood(int amountOfFood, Store store){

        int totalPrice = store.getFoodPrice() * amountOfFood;

        if(amountOfFood < 1){
            System.out.println("Sorry, you can´t buy " + amountOfFood + " pack(s) of food. Try again.");
            return false;
        }

        if(totalPrice > gold){
            System.out.println("Sorry, you don´t have enough gold to buy " + amountOfFood + " pack(s) of food. Try again.");
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
            System.out.println("Sorry, you can´t buy " + amountOfWater + " crate(s) of water. Try again.");
            return false;
        }

        if(totalPrice > gold){
            System.out.println("Sorry, you don´t have enough gold to buy " + amountOfWater + " crate(s) of water. Try again.");
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

        if(foodStorage < 0.5){
            System.out.println("You don´t have enough food packs to feed anyone. You have " + foodStorage + " food packs.");
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
           if(foodStorage < 0.5){
               System.out.println("You don´t have any food packs left.");
               break;
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

        if(waterStorage < 1){
            System.out.println("You don´t have enough water crates. You have " + waterStorage + " water crates.");
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
            if(waterStorage < 1){
                System.out.println("You don´t have any water crates left.");
                break;
            }
        }
        energy--;
        printEnergyAmount();
    }

    // allows the player to cure the sick animal for 2 energy points
    public boolean cureAnimals(Scanner scan){

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

                        return true;
                    } else {
                        System.out.println("This animal isn´t sick... Do you still want to cure someone or you´re done?");
                    }
                }
            }
        }
        return false;
    }

    // gives opportunity to solve Illegal Animal Seller problem if that problem arises in main game
    public boolean solveIllegalAnimalSeller(Scanner scan){

        int playerInput = 3;

        while(playerInput > 2){

            System.out.println("If you want to pay the Illegal Seller off, TYPE 1 [pay 30 gold].");
            System.out.println("If you want to use force TYPE 2 [use 2 energy points].");
            System.out.println("(if you don´t want/ can´t solve the problem, TYPE 3)");

            playerInput = scan.nextInt();

            if(playerInput == 1){

                if(gold < 30){
                    System.out.println("Oh, you don´t have enough money, you have only " + gold + " gold.");
                } else {
                    gold -= 30;
                    System.out.println("You paid that criminal off... let´s hope he doesn´t come again. You have " + gold + " gold left.");
                    animalHasBeenStolen = false;
                    return true;
                }
            } else if(playerInput == 2){

                if(energy < 2){
                    System.out.println(notEnoughEnergy);
                } else {
                    energy -= 2;
                    System.out.println("You firmly showed this criminal scum the way out... let´s hope he doesn´t come again.");
                    animalHasBeenStolen = false;
                    return true;
                }

            } else if(playerInput == 3){
                return false;
            }
        }
        return false;
    }



}
