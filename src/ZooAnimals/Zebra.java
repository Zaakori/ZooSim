package ZooAnimals;

public class Zebra extends Animal {

    public Zebra() {
        super.foodNeed = 1;
        super.waterNeed = 1.5;
        super.sicknessResistance = 0.4;
    }

    @Override
    public void printIfSick() {
        System.out.println("Zebra is ill.");
    }

    @Override
    public void printIfCured() {
        System.out.println("Zebra is cured.");
    }
}
