package ZooAnimals;

public class Sloth extends Animal {

    public Sloth() {
        super.foodNeed = 0.5;
        super.waterNeed = 1.5;
        super.sicknessResistance = 0.4;
    }

    @Override
    public void printIfSick() {
        System.out.println("Sloth is sick.");
    }

    @Override
    public void printIfCured() {
        System.out.println("Sloth is cured.");
    }
}
