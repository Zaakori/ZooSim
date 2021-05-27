package ZooSimMain;

import ZooSimMain.ZooAnimals.Animal;
import java.util.ArrayList;
import java.util.Scanner;


public class ZooMain {


    public static void main(String[] args) {

        int dayCounter = 1;
        int pointCounter = 0;
        Scanner scan = new Scanner(System.in);
        Player player = new Player();
        Store store = new Store();
        ArrayList<Animal> deceasedList = new ArrayList<>();


        while(!player.getAnimalList().isEmpty()){

            System.out.println("//////////////////////////////////////////////////////////////////");
            System.out.println("It´s day " + dayCounter);

            if(!deceasedList.isEmpty()){

                for(Animal a : deceasedList){
                    System.out.println(a.getClassAsString() + " passed away.");
                }
             deceasedList.clear();
            }
            addingSicknessPoints(whoIsSick(player));


            randomEvents(player, store);
            randomEvents(player, store);
            printSickAnimalList(whoIsSick(player));

            System.out.println("--------------------------------------------------------------------");

            player.whatYouWannaDoToday(scan, store, whoIsSick(player));


            pointCounter += givingDayPoints(player.getAnimalList());
            takeFoodFromAnimals(player.getAnimalList());
            takeWaterFromAnimals(player.getAnimalList());
            deceasedList = deletePassedAnimal(player.getAnimalList());

            player.resetEnergy();
            store.setIsFoodForSale(true);
            store.setFoodPrice(10);
            dayCounter++;
        }

        System.out.println("GAME OVER");
        System.out.println(pointCounter);


//        while(dayCounter < 8){
//
//            System.out.println(dayCounter);
//
//         takeWaterFromAnimals(player.getAnimalList());
//
//            deletePassedAnimal(player.getAnimalList());
//
//            pointCounter += givingDayPoints(player.animalList);
//
//            dayCounter++;
//
//
//
//            for(Animal a : player.getAnimalList()){
//                System.out.println(a);
//            }
//            System.out.println("**************");
//        }
//
//        System.out.println("game over");
//        System.out.println(pointCounter);

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

    public static void printSickAnimalList(ArrayList<Animal> sickList){

        if(!sickList.isEmpty()){

            for(Animal a : sickList){
                a.printIfSick();
            }

        }

    }

    public static void addingSicknessPoints(ArrayList<Animal> sickList){

        if(sickList.isEmpty()){
            return;
        }

        for(Animal a : sickList){
            a.setSicknessPoints(a.getSicknessPoints() + 1);
        }

    }


    // actually generates a random event
    public static void randomEvents(Player player, Store store){

        RandomEvents randomEvent = new RandomEvents();
        int randomNumber = randomEvent.getRandomNumberInRightRange();
        int sumOfPrevious = 0;


        if(randomNumber <= sumOfPrevious + randomEvent.getSaleOnFoodProbability()){
            randomEvent.saleOnFood(store);
        }

        sumOfPrevious += randomEvent.getSaleOnFoodProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getHotDayProbability())){
            randomEvent.hotDay(player.getAnimalList());
        }

        sumOfPrevious += randomEvent.getHotDayProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getRatInvasionProbability())){
            randomEvent.ratInvasion(player);
        }

        sumOfPrevious += randomEvent.getRatInvasionProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getNothingHappenedProbability())){
            randomEvent.nothingHappened();
        }

        sumOfPrevious += randomEvent.getNothingHappenedProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getRainProbability())){
            randomEvent.rain(player.getAnimalList());
        }

        sumOfPrevious += randomEvent.getRainProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getSicknessProbability())){
            randomEvent.sickness(player.getAnimalList());
        }

        sumOfPrevious += randomEvent.getSicknessProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getPeopleFeedProbability())){
            randomEvent.peopleFeed(player);
        }

        sumOfPrevious += randomEvent.getPeopleFeedProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getFoodOutOfStockProbability())){
            randomEvent.foodOutOfStock(store);
        }

    }


}
