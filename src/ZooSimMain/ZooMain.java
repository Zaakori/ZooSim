package ZooSimMain;

import ZooSimMain.ZooAnimals.Animal;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class ZooMain {

   static boolean lionIsAlive = true;                                   // checks if lion is still alive
   static boolean slothIsAlive = true;                                  // checks if sloth is still alive
   static boolean zebraIsAlive = true;                                  // checks if zebra is still alive
   static boolean animalIsStolen = false;                               // checks if Illegal Animal Seller has come to steal an animal

    public static void main(String[] args) {

        int dayCounter = 1;                                             // counts the days
        int pointCounter = 0;                                           // counts the points, each day the player gets as many points as many animals are still alive
        Scanner scan = new Scanner(System.in);
        Player player = new Player();
        Store store = new Store();
        RandomEvents randomEventsInstance = new RandomEvents();
        ArrayList<Animal> deceasedList = new ArrayList<>();             // list that contains deceased animals of the day, only for displaying purposes
        ArrayList<Animal> stolenAnimalsList = new ArrayList<>();        // list that contains stolen animals, only for displaying purposes


        while(!player.getAnimalList().isEmpty()){

            // part where player gets to know if something has happened the previous night and
            // also more sickness points are added if an animal was sick day before
            System.out.println("//////////////////////////////////////////////////////////////////");
            System.out.println("It´s day " + dayCounter);

            System.out.println();

            if(!deceasedList.isEmpty()){

                for(Animal a : deceasedList){
                    System.out.println(a.getClassAsString() + " passed away.");
                }
             deceasedList.clear();
            }
            if(!stolenAnimalsList.isEmpty()){

                for(Animal a : stolenAnimalsList){
                    System.out.println(a.getClassAsString() + " was stolen.");
                }
                stolenAnimalsList.clear();
            }


            addingSicknessPoints(whoIsSick(player));


            // part where this days´ random events happen and if during one of these events
            // an animal gets sick it is printed out who got sick
            randomEvents(player, store, randomEventsInstance);
            randomEvents(player, store, randomEventsInstance);
            printSickAnimalList(whoIsSick(player));

            System.out.println("--------------------------------------------------------------------");

            // part where finally player can decide what to do today, here we get the input
          player.whatYouWannaDoToday(scan, store, whoIsSick(player), animalIsStolen);

            // part where all kind of every day usual events or consequences happen (like: day points are added,
            // passed animals are deleted from players animal list etc.)
              if(slothIsAlive){
                  player.ratInvasionUpSLOTH(randomEventsInstance);
              }

              if(zebraIsAlive){
                  player.nothingHappenedUpZEBRA(randomEventsInstance);
              }

            pointCounter += givingDayPoints(player.getAnimalList());
            takeFoodFromAnimals(player.getAnimalList());
            takeWaterFromAnimals(player.getAnimalList());
            dayCounter++;
            deceasedList = deletePassedAnimal(player.getAnimalList());
            areLionSlothZebraAlive(player);
            if(animalIsStolen){
                animalIsStolen = player.isAnimalHasBeenStolen();
            }
            if(animalIsStolen){
               stolenAnimalsList.add(stealAnimal(player.getAnimalList()));
            }

            // all things that need to be reset every day are being reset
            player.resetEnergy();
            store.setIsClosed(false);
            store.setSale(false);
            store.setFoodPrice(10);
            animalIsStolen = false;
            player.setAnimalHasBeenStolen(true);
        }

        System.out.println("GAME OVER");
        System.out.println("Your score is: " + pointCounter);

    }


    // in the end of the day takes away one days worth of water
    // from each Animal Object (so it gets thirsty and wants to drink in the next days)
    public static void takeWaterFromAnimals(ArrayList<Animal> animalList){

        if(animalList.isEmpty()){
            return;
        }

    for(Animal a : animalList){
        a.setBodyWaterAmount(a.getBodyWaterAmount() - a.getWaterNeed());
    }


    }

    // in the end of the day takes away one days worth of food
    // from each Animal Object (so it gets hungry and wants to eat in the next days)
    public static void takeFoodFromAnimals(ArrayList<Animal> animalList){

        if(animalList.isEmpty()){
            return;
        }

        for(Animal a : animalList){
            a.setBodyFoodAmount(a.getBodyFoodAmount() - a.getFoodNeed());
        }


    }

    // int the end of the day gives out as many points as many alive animals are there in the zoo
    public static int givingDayPoints(ArrayList<Animal> animalList){

        if((animalList == null) || (animalList.isEmpty())){
            return 0;
        }

        int points = 0;

        for(Animal a : animalList){
            points++;
        }

        return points;
    }

    // its the last method of the day - checks if animal is alive or passed away (by checking if bodyWaterAmount of bodyFoodAmount is 0 or less)
    // if it passed away then it deletes the animal from Players ArrayList
    public static ArrayList<Animal> deletePassedAnimal(ArrayList<Animal> animalList){

        ArrayList<Animal> passedAnimalList = new ArrayList<>();

        for(Animal a : animalList){

            if(a.getBodyFoodAmount() <= 0){
               passedAnimalList.add(a);
            } else if(a.getBodyWaterAmount() <= 0){
               passedAnimalList.add(a);
            } else if(a.getSicknessPoints() >= 2){
                passedAnimalList.add(a);
            }

        }

        if(!passedAnimalList.isEmpty()){

            for(Animal a : passedAnimalList){
                animalList.remove(a);
            }

        }

        return passedAnimalList;
    }

    // turns 'IsAlive' booleans to false if either lion, sloth or zebra die
    public static void areLionSlothZebraAlive(Player player){

        if(!lionIsAlive && !slothIsAlive && !zebraIsAlive){
            return;
        }

        ArrayList<String> aliveList = new ArrayList<>();

        for(Animal a : player.getAnimalList()){
            aliveList.add(a.getClassAsString());
        }

        if(!aliveList.contains("Lion") && lionIsAlive){
            lionIsAlive = false;
            player.moraleDropLION();
        }

        if(!aliveList.contains("Sloth")){
            slothIsAlive = false;
        }

        if(!aliveList.contains("Zebra")){
            zebraIsAlive = false;
        }

    }

    // checks if any of the animals got sick or are still sick
    public static ArrayList<Animal> whoIsSick(Player player){

        ArrayList<Animal> sickList = new ArrayList<>();

        for(Animal a : player.getAnimalList()){

            if(a.getSicknessPoints() > 0){
                sickList.add(a);
            }

        }

        return sickList;
    }

    // actually prints out the list of sick animals
    public static void printSickAnimalList(ArrayList<Animal> sickList){

        if(!sickList.isEmpty()){

            for(Animal a : sickList){
                a.printIfSick();
            }

        }

    }

    // adds sickness points every day to an animal that is sick and hasn´t been cured yet
    public static void addingSicknessPoints(ArrayList<Animal> sickList){

        if(sickList.isEmpty()){
            return;
        }

        for(Animal a : sickList){
            a.setSicknessPoints(a.getSicknessPoints() + 1);
        }

    }

    // actually steals (removes from players animal list) an animal if player hasn´t solved this problem beforehand
    public static Animal stealAnimal(ArrayList<Animal> animalList){

        Random rand = new Random();
        int random = rand.nextInt(animalList.size());
        Animal stolenAnimal = animalList.get(random);

        animalList.remove(random);

        return stolenAnimal;
    }


    // actually generates a random event
    public static void randomEvents(Player player, Store store, RandomEvents randomEvent){

        int randomNumber = randomEvent.getRandomNumberInRightRange();
        int sumOfPrevious = 0;


        if(randomNumber <= sumOfPrevious + randomEvent.getSaleOnFoodProbability()){
            randomEvent.saleOnFood(store);
            return;
        }

        sumOfPrevious += randomEvent.getSaleOnFoodProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getHotDayProbability())){
            randomEvent.hotDay(player.getAnimalList());
            return;
        }

        sumOfPrevious += randomEvent.getHotDayProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getRatInvasionProbability())){
            randomEvent.ratInvasion(player);
            return;
        }

        sumOfPrevious += randomEvent.getRatInvasionProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getNothingHappenedProbability())){
            randomEvent.nothingHappened();
            return;
        }

        sumOfPrevious += randomEvent.getNothingHappenedProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getRainProbability())){
            randomEvent.rain(player.getAnimalList());
            return;
        }

        sumOfPrevious += randomEvent.getRainProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getSicknessProbability())){
            randomEvent.sickness(player.getAnimalList());
            return;
        }

        sumOfPrevious += randomEvent.getSicknessProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getPeopleFeedProbability())){
            randomEvent.peopleFeed(player);
            return;
        }

        sumOfPrevious += randomEvent.getPeopleFeedProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getFoodOutOfStockProbability())){
            randomEvent.foodOutOfStock(store);
            return;
        }

        sumOfPrevious += randomEvent.getFoodOutOfStockProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getIllegalAnimalSellerProbability())){
            randomEvent.illegalAnimalSeller();
            animalIsStolen = true;
            return;
        }

    }


}
