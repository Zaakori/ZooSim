package ZooSimMain;

import ZooSimMain.ZooAnimals.Animal;
import java.util.ArrayList;


public class ZooMain {




    public static void main(String[] args) {

        int dayCounter = 0;
        int pointCounter = 0;

        Player player = new Player();
        Store store = new Store();
        RandomEvents randomEvents = new RandomEvents();







        while(dayCounter < 8){

            if(dayCounter == 7){
                player.animalsInTheZoo.remove(0);
                player.animalsInTheZoo.remove(1);
            }

            pointCounter += givingDayPoints(player.animalsInTheZoo);

            dayCounter++;
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

        int points = 0;

        for(Animal a : animalList){
            points++;
        }

        return points;
    }

    // !!! AINT WORKING FOR SOME REASON !!!
    // its the last method of the day - checks if animal is alive or passed away (by checking if bodyWaterAmount of bodyFoodAmount is 0 or less)
    // if it passed away then it deletes the animal from Players ArrayList
    public static void deletePassedAnimal(ArrayList<Animal> animalList){

        if(animalList.isEmpty()){
            return;
        }

        for(Animal a : animalList){

            if(a.getBodyWaterAmount() <= 0.0){
                animalList.remove(a);
            } else if(a.getBodyFoodAmount() <= 0.0){
                animalList.remove(a);
            }

        }

    }

    public static void randomEvents(){





    }




}
