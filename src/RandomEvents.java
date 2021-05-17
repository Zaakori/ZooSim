import ZooAnimals.Animal;
import java.util.*;


public class RandomEvents {

    public double saleOnFoodProbability = 0.1;
    public double hotDayProbability = 0.1;
    public double ratInvasionProbability = 0.1;
    public double nothingHappenedProbability = 0.2;
    public double rainProbability = 0.1;
    public double sicknessProbability = 0.1;
    public double peopleFeedProbability = 0.1;
    public double foodOutOfStockProbability = 0.1;
    public double someoneGetsStuckProbability = 0.1;


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
            a.setBodyWaterStorage(a.getBodyWaterStorage() - (a.getWaterNeed() * 2));
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
            a.setBodyWaterStorage(a.getBodyWaterStorage() + a.getWaterNeed());
        }

    }

    // the sickness roams in the zoo, random 60% animals are selected and can get sick
    // the probability of actually getting sick depends on the animals sicknessResistance
    public ArrayList<Animal> sickness(List<Animal> animalList){

        int sizeOfSickAnimalList = (int) (animalList.size() * 2) / 3;
        ArrayList<Animal> sickAnimalList = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        Random rand = new Random();

        while(set.size() != sizeOfSickAnimalList){

            set.clear();

            for(int i = 0; i < sizeOfSickAnimalList; i++){
              set.add(rand.nextInt(animalList.size()));
            }
        }


        for(int i : set){
            sickAnimalList.add(animalList.get(i));
        }


    return sickAnimalList;

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

    // one animal gets stuck, player has to spend one energy point to free it or
    // after 2 days the animal dies (no matter if it´s being fed and given water or not)
    public void someoneGetsStuck(List<Animal> animalList){

        Random rand = new Random();
        int random = rand.nextInt(animalList.size());

        animalList.get(random).setStuck(true);

    }

}
