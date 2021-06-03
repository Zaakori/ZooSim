package ZooSimMain.ZooAnimals;

public abstract class Animal {

    protected double foodNeed;                 // shows how much food does the animal need/consumes every day
    protected double bodyFoodAmount;           // shows how much food is currently stored in the animals body a.k.a. how hungry it is
    protected double waterNeed;                // shows how much water does the animal need/consumes every day
    protected double bodyWaterAmount;          // shows how much water is currently stored in the animals body a.k.a. how thirsty it is
    protected int sicknessPoints = 0;          // if 0 points, then animal is healthy. amount of points shows how many days the animal has been sick
    protected double sicknessResistance;       // how resistant the animal is to sickness, in percentages

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

    // prints out how many days can the animal survive without food and water (separately)
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
