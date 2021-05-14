package ZooAnimals;

public abstract class Animal {

    protected double foodNeed;
    protected double waterNeed;
    protected double bodyFoodStorage;
    protected double bodyWaterStorage;
    protected boolean isSick = false;
    protected double sicknessResistance;
    protected boolean isAlive = true;

    protected String sickAnimal = " looks ill";
    protected String curedAnimal = " is cured";

    public double getWaterNeed() {
        return waterNeed;
    }

    public double getBodyWaterStorage() {
        return bodyWaterStorage;
    }

    public void setBodyWaterStorage(double bodyWaterStorage) {
        this.bodyWaterStorage = bodyWaterStorage;
    }

    public void isSick() {
        isSick = true;
    }

    public void isCured(){
        isSick = false;
    }

    public void printIfSick(){

        String initialString = this.getClass() + sickAnimal;

        System.out.println(initialString.substring(17));
    }

    public void printIfCured(){

        String initialString = this.getClass() + curedAnimal;

        System.out.println(initialString.substring(17));
    }




}
