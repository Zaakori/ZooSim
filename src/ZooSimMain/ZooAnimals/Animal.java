package ZooSimMain.ZooAnimals;

public abstract class Animal {

    protected double foodNeed;
    protected double bodyFoodAmount;
    protected double waterNeed;
    protected double bodyWaterAmount;
    protected boolean isSick = false;
    protected double sicknessResistance;
    protected boolean isStuck = false;
    protected boolean isAlive = true;

    protected String sickAnimal = " looks ill";
    protected String curedAnimal = " is cured";


    public double getFoodNeed() {
        return foodNeed;
    }

    public double getBodyFoodAmount() {
        return bodyFoodAmount;
    }

    public void setBodyFoodAmount(double bodyFoodAmount) {
        this.bodyFoodAmount = bodyFoodAmount;
    }

    public void printFoodAmountInDays(){

        String initialString = this.getClass() + " can survive " + (getBodyFoodAmount() / foodNeed) + " day(s) without food.";

        System.out.println(initialString.substring(28));

    }

    public double getWaterNeed() {
        return waterNeed;
    }

    public double getBodyWaterAmount() {
        return bodyWaterAmount;
    }

    public void setBodyWaterAmount(double bodyWaterAmount) {
        this.bodyWaterAmount = bodyWaterAmount;
    }

    public void printWaterAmountInDays(){

        String initialString = this.getClass() + " can survive " + (getBodyWaterAmount() / waterNeed) + " day(s) without water.";

        System.out.println(initialString.substring(28));

    }

    public boolean isStuck() {
        return isStuck;
    }

    public void setStuck(boolean stuck) {
        isStuck = stuck;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public double getSicknessResistance() {
        return sicknessResistance;
    }

    public void setSicknessResistance(double sicknessResistance) {
        this.sicknessResistance = sicknessResistance;
    }

    public void isSick() {
        isSick = true;
    }

    public void isCured(){
        isSick = false;
    }

    public void printIfSick(){

        String initialString = this.getClass() + sickAnimal;

        System.out.println(initialString.substring(28));
    }

    public void printIfCured(){

        String initialString = this.getClass() + curedAnimal;

        System.out.println(initialString.substring(28));
    }




}
