package ZooSimMain;

import ZooSimMain.ZooAnimals.Animal;
import java.util.*;


public class RandomEvents {

    private int saleOnFoodProbability = 10;
    private int hotDayProbability = 10;
    private int ratInvasionProbability = 10;
    private int nothingHappenedProbability = 20;
    private int rainProbability = 10;
    private int sicknessProbability = 10;
    private int peopleFeedProbability = 10;
    private int foodOutOfStockProbability = 10;
    private int someoneGetsStuckProbability = 10;

    public int getSaleOnFoodProbability() {
        return saleOnFoodProbability;
    }

    public int getHotDayProbability() {
        return hotDayProbability;
    }

    public int getRatInvasionProbability() {
        return ratInvasionProbability;
    }

    public void setRatInvasionProbability(int ratInvasionProbability) {
        this.ratInvasionProbability = ratInvasionProbability;
    }

    public int getNothingHappenedProbability() {
        return nothingHappenedProbability;
    }

    public void setNothingHappenedProbability(int nothingHappenedProbability) {
        this.nothingHappenedProbability = nothingHappenedProbability;
    }

    public int getRainProbability() {
        return rainProbability;
    }

    public int getSicknessProbability() {
        return sicknessProbability;
    }

    public int getPeopleFeedProbability() {
        return peopleFeedProbability;
    }

    public int getFoodOutOfStockProbability() {
        return foodOutOfStockProbability;
    }

    public int getSomeoneGetsStuckProbability() {
        return someoneGetsStuckProbability;
    }

    // used for getting a random number in a correct range
    public int getRandomNumberInRightRange(){

        Random rand = new Random();
        int random;

        int total = saleOnFoodProbability + hotDayProbability + ratInvasionProbability +
                nothingHappenedProbability + rainProbability + sicknessProbability + peopleFeedProbability +
                foodOutOfStockProbability + someoneGetsStuckProbability;

        random = rand.nextInt(total) + 1;

        return random;
    }


    // sale on food packs, randomly packs are discounted to 8 or 6 gold
    public void saleOnFood(Store store){

        Random rand = new Random();
        int random = rand.nextInt(2);

        switch (random){

            case 0: store.setFoodPrice(8);
            break;

            case 1: store.setFoodPrice(6);
            break;

        }

    }

    // it´s a hot day, so in the end of the day from each animal is taken not one
    // amount of water it needs in one day but two amounts
    public void hotDay(List<Animal> animalList){

        for(Animal a : animalList){
            a.setBodyWaterAmount(a.getBodyWaterAmount() - (a.getWaterNeed() * 2));
        }

    }

    // rats invade the food storage, so half of the food is eaten by rats
    public void ratInvasion(Player player){

        if(player.getFoodStorage() != 0){
            player.setFoodStorage(player.getFoodStorage() / 2);
        }

    }

    // nothing happens
    public void nothingHappened(){

    }

    // it rains, so the player doesn´t have to give water to them this day
    public void rain(List<Animal> animalList){

        for(Animal a : animalList){
            a.setBodyWaterAmount(a.getBodyWaterAmount() + a.getWaterNeed());
        }

    }

    // the sickness roams in the zoo, random 60% animals are selected and can get sick, animals that actually got sick are returned in ArrayList
    // the probability of actually getting sick depends on the animals sicknessResistance
    public void sickness(List<Animal> animalList){

        int sizeOfSickAnimalList = (int) (animalList.size() * 2) / 3;
        ArrayList<Animal> potentiallySickAnimalList = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        Random rand = new Random();

        while(set.size() != sizeOfSickAnimalList){

            set.clear();

            for(int i = 0; i < sizeOfSickAnimalList; i++){
              set.add(rand.nextInt(animalList.size()));
            }
        }


        for(int i : set){
            potentiallySickAnimalList.add(animalList.get(i));
        }

        for(Animal a : potentiallySickAnimalList){

            int randomNumber = rand.nextInt(100) + 1;
            double usefulRandomNumber = (double) randomNumber / 100;

            if(usefulRandomNumber >= a.getSicknessResistance()){
                a.setSicknessPoints(1);
            }

        }

    }

    // a random amount of food is being donated to the zoo
    // it goes to the food storage
    public void peopleFeed(Player player){

        Random rand = new Random();
        int random = rand.nextInt(11) + 5;

        player.setFoodStorage(player.getFoodStorage() + random);

    }

    // there is no food in the store at that day
    public void foodOutOfStock(Store store){

        store.setIsFoodForSale(false);

    }


}
