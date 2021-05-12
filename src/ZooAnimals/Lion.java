package ZooAnimals;

public class Lion extends Animal {

    public Lion() {
        super.foodNeed = 1;
        super.waterNeed = 1;
        super.sicknessResistance = 0.4;
    }

    @Override
    public void printIfSick() {
        System.out.println("Lion doesn´t feel that good.");
    }

    @Override
    public void printIfCured() {
        System.out.println("Lion is cured.");
    }
}
