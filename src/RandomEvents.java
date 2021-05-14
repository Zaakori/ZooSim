import ZooAnimals.Animal;

import java.util.List;
import java.util.Random;

public class RandomEvents {


    // sale on food packs, randomly the discount is either 20% or 40%
    public void saleOnFood(Store store){

        Random rand = new Random();
        int random = rand.nextInt(2);

        switch (random){

            case 0: store.setFoodPrice(8);
            break;

            case 1: store.setFoodPrice(6);
            break;

        }

    }

    // it´s a hot day, so in the end of the day from each animal is taken not one
    // amount of water it needs in one day but two amounts
    public void hotDay(List<Animal> animalList){

        for(Animal a : animalList){
            a.setBodyWaterStorage(a.getBodyWaterStorage() - (a.getWaterNeed() * 2));
        }

    }

    // rats invade the food storage, so half of the food is eaten by rats
    public void ratInvasion(Player player){

        if(player.getFoodStorage() != 0){
            player.setFoodStorage(player.getFoodStorage() / 2);
        }

    }

    // nothing happens
    public void nothingHappened(){

    }

    // it rains, so the player doesn´t have to give water to them this day
    public void rain(List<Animal> animalList){

        for(Animal a : animalList){
            a.setBodyWaterStorage(a.getBodyWaterStorage() + a.getWaterNeed());
        }

    }

    // the sickness roams in the zoo, random 3 animals are selected and can get sick
    // the probability of actually getting sick depends on the animals sicknessResistance
    public void sickness(List<Animal> animalList){



    }

    public void peopleFeed(){

    }

    public void foodOutOfStockInStore(){

    }

    public void monkeyGetsStuck(){

    }

    public void activeZebra(){

    }

    public void lionWorksAndPeopleDonateFood(){

    }

    public void somethingBreaks(){

    }




}
