package ZooAnimals;

public abstract class Animal {

    protected double foodNeed;
    protected double waterNeed;
    protected int daysWithoutFood;
    protected int daysWithoutWater;
    protected boolean isSick;
    protected double sicknessResistance;
    protected boolean isAlive;

    public void setSick(boolean sick) {
        isSick = sick;
    }

    public abstract void printIfSick();

    public abstract void printIfCured();


}
