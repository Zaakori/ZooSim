package ZooSimMain;

import ZooSimMain.ZooAnimals.Animal;
import java.util.ArrayList;


public class ZooMain {




    public static void main(String[] args) {

        int dayCounter = 1;
        int pointCounter = 0;


        Player player = new Player();
        Store store = new Store();
        RandomEvents randomEvents = new RandomEvents();






        while(dayCounter < 8){

            System.out.println(dayCounter);

         takeWaterFromAnimals(player.getAnimalList());

            deletePassedAnimal(player.getAnimalList());

            pointCounter += givingDayPoints(player.animalsInTheZoo);

            dayCounter++;



            for(Animal a : player.getAnimalList()){
                System.out.println(a);
            }
            System.out.println("**************");
        }

        System.out.println("game over");
        System.out.println(pointCounter);

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
    public static void deletePassedAnimal(ArrayList<Animal> animalList){

        ArrayList<Animal> passedAnimalList = new ArrayList<>();

        for(Animal a : animalList){

            if(a.getBodyFoodAmount() <= 0){
               passedAnimalList.add(a);
            } else if(a.getBodyWaterAmount() <= 0){
               passedAnimalList.add(a);
            }

        }

        if(!passedAnimalList.isEmpty()){

            for(Animal a : passedAnimalList){
                animalList.remove(a);
            }

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

        sumOfPrevious += randomEvent.getFoodOutOfStockProbability();

        if((randomNumber > sumOfPrevious) && (randomNumber <= sumOfPrevious + randomEvent.getSomeoneGetsStuckProbability())){
            randomEvent.someoneGetsStuck(player.getAnimalList());
        }

    }


}
