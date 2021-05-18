package ZooSimMain.ZooAnimals;

import ZooSimMain.RandomEvents;


public class Zebra extends Animal {

    public Zebra() {
        foodNeed = 1;
        waterNeed = 1.5;
        bodyFoodAmount = 5;
        bodyWaterAmount = 4.5;
        sicknessResistance = 0.4;
    }

    // each day on which zebra is alive the probability of ´nothing happened´ goes up by 2 points, up to 30 points
    public void nothingHappenedUp(RandomEvents randomEvents){

        if(randomEvents.getNothingHappenedProbability() >= 30){
            return;
        }

        randomEvents.setNothingHappenedProbability(randomEvents.getNothingHappenedProbability() + 2);

    }

}
