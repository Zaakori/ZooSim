package ZooSimMain.ZooAnimals;

import ZooSimMain.RandomEvents;


public class Sloth extends Animal {

    public Sloth() {
        foodNeed = 0.5;
        waterNeed = 1.5;
        bodyFoodAmount = 2.5;
        bodyWaterAmount = 4.5;
        sicknessResistance = 0.4;
    }

    // every day when sloth is alive, the probability of ´rat invasion´ goes up by 2 points, up to 25 points
    public void ratInvasionUp(RandomEvents randomEvents){

        if(randomEvents.getRatInvasionProbability() >= 25){
            return;
        }

        randomEvents.setRatInvasionProbability(randomEvents.getRatInvasionProbability() + 2);

    }

}
