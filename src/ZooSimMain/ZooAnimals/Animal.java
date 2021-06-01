package ZooSimMain.ZooAnimals;

public abstract class Animal {

    protected double foodNeed;
    protected double bodyFoodAmount;
    protected double waterNeed;
    protected double bodyWaterAmount;
    protected int sicknessPoints = 0;
    protected double sicknessResistance;

    protected String sickAnimal = " looks ill";
    protected String curedAnimal = " is cured";

    public String getClassAsString(){

        String initialString = this.getClass() + "";

        return initialString.substring(28);

    }

    public double getFoodNeed() {
        return foodNeed;
    }

    public double getBodyFoodAmount() {
        return bodyFoodAmount;
    }

    public void setBodyFoodAmount(double bodyFoodAmount) {

        if(bodyFoodAmount < 0){
            this.bodyFoodAmount = 0;
            return;
        }

        this.bodyFoodAmount = bodyFoodAmount;
    }

    public void printFoodAndWaterAmountInDays(){
        System.out.println(getClassAsString() + " can survive " + (getBodyFoodAmount() / foodNeed) + " day(s) without food" +
                " and " + (getBodyWaterAmount() / waterNeed) + " day(s) without water.");

    }

    public double getWaterNeed() {
        return waterNeed;
    }

    public double getBodyWaterAmount() {
        return bodyWaterAmount;
    }

    public void setBodyWaterAmount(double bodyWaterAmount) {

        if(bodyWaterAmount < 0){
            this.bodyWaterAmount = 0;
            return;
        }

        this.bodyWaterAmount = bodyWaterAmount;
    }

    public double getSicknessResistance() {
        return sicknessResistance;
    }

    public void setSicknessResistance(double sicknessResistance) {
        this.sicknessResistance = sicknessResistance;
    }

    public int getSicknessPoints() {
       return sicknessPoints;
    }

    public void setSicknessPoints(int answer){
        sicknessPoints = answer;
    }

    public void printIfSick(){
        System.out.println(getClassAsString() + sickAnimal);
    }

    public void printIfCured(){
        System.out.println(getClassAsString() + curedAnimal);
    }

}
