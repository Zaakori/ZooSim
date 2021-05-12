package ZooAnimals;

public class Elephant extends Animal {

    public Elephant() {
        super.foodNeed = 1.5;
        super.waterNeed = 1;
        super.sicknessResistance = 0.8;
    }

    @Override
    public void printIfSick() {
        System.out.println("Elephant seems to be ill.");
    }

    @Override
    public void printIfCured() {
        System.out.println("Elephant is cured.");
    }
}
