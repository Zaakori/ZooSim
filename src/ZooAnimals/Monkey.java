package ZooAnimals;

public class Monkey extends Animal {

    public Monkey() {
        super.foodNeed = 1;
        super.waterNeed = 1;
        super.sicknessResistance = 0.4;
    }

    @Override
    public void printIfSick() {
        System.out.println("Monkey looks ill.");
    }

    @Override
    public void printIfCured() {
        System.out.println("Monkey is cured");
    }
}
