package ZooAnimals;

import java.util.ArrayList;

public class Lion extends Animal {

    public Lion() {
        foodNeed = 1;
        waterNeed = 1;
        bodyFoodStorage = 5;
        bodyWaterStorage = 3;
        sicknessResistance = 0.4;
    }

    // if lion dies, every alive animals sickness resistance goes down by 20%
    public void moraleDrop(ArrayList<Animal> animalList){

        for(Animal a : animalList){
            a.setSicknessResistance(a.getSicknessResistance() - 0.2);
        }

    }

}
