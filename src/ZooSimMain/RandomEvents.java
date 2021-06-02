package ZooSimMain;

import ZooSimMain.ZooAnimals.Animal;
import java.util.*;


public class RandomEvents {

    private int saleOnFoodProbability = 10;
    private int hotDayProbability = 10;
    private int ratInvasionProbability = 10;
    private int nothingHappenedProbability = 20;
    private int rainProbability = 10;
    private int sicknessProbability = 100;
    private int peopleFeedProbability = 10;
    private int foodOutOfStockProbability = 10;
    private int illegalAnimalSellerProbability = 10;

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

    public int getIllegalAnimalSellerProbability() {
        return illegalAnimalSellerProbability;
    }

    // used for getting a random number in a correct range
    public int getRandomNumberInRightRange(){

        Random rand = new Random();
        int random;

        int total = saleOnFoodProbability + hotDayProbability + ratInvasionProbability +
                nothingHappenedProbability + rainProbability + sicknessProbability + peopleFeedProbability +
                foodOutOfStockProbability + illegalAnimalSellerProbability;

        random = rand.nextInt(total) + 1;

        return random;
    }


    // sale on food packs, randomly packs are discounted to 8 or 6 gold
    public void saleOnFood(Store store){

        if(store.isClosed()){
            System.out.println("Nothing happened, no sale for today.");
            return;
        }

        Random rand = new Random();
        int random = rand.nextInt(2);

        switch (random){

            case 0: store.setFoodPrice(8);
            break;

            case 1: store.setFoodPrice(6);
            break;

        }

        if(random == 0){
            System.out.println("There is a sale on food! Today a food pack costs 8 gold.");
        } else if(random == 1){
            System.out.println("There is a sale on food! Today a food pack costs 6 gold.");
        }

    }

    // it´s a hot day, so in the end of the day from each animal is taken not one
    // amount of water it needs in one day but two amounts
    public void hotDay(List<Animal> animalList){

        for(Animal a : animalList){

            if(a.getBodyWaterAmount() <= 1){
                a.setBodyWaterAmount(1);
            } else {
                a.setBodyWaterAmount(a.getBodyWaterAmount() - (a.getWaterNeed() * 2));
            }

        }

        System.out.println("Today is a hot day! All animals are two times more thirsty than usual.");
    }

    // rats invade the food storage, so half of the food is eaten by rats
    public void ratInvasion(Player player){

        if(player.getFoodStorage() > 1){

            double ratInvasionRaw = player.getFoodStorage() / 2;
            double ratInvasionResult = Math.round(ratInvasionRaw);

            System.out.println("There was a rat invasion in your food storage! You had " + player.getFoodStorage() +
                    " packs of food and now you have " + ratInvasionResult + " packs left.");

            player.setFoodStorage(ratInvasionResult);

        } else {
            System.out.println("Rats came looking for food but didn´t find much. So nothing happened.");
        }

    }

    // nothing happens
    public void nothingHappened(){

        System.out.println("Huh, nothing happened.");
    }

    // it rains, so the player doesn´t have to give water to them this day
    public void rain(List<Animal> animalList){

        for(Animal a : animalList){
            a.setBodyWaterAmount(a.getBodyWaterAmount() + a.getWaterNeed());
        }

        System.out.println("Rain day! Every animal got their day´s share of water.");
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

        System.out.println("Sickness roams in the zoo...");
    }

    // a random amount of food is being donated to the zoo
    // it goes to the food storage
    public void peopleFeed(Player player){

        Random rand = new Random();
        int random = rand.nextInt(11) + 5;

        player.setFoodStorage(player.getFoodStorage() + random);

        System.out.println("Some nice people donated food to the zoo! They donated " + random +
                " food packs and now you have " + player.getFoodStorage() + " packs of food in storage.");

    }

    // there is no food in the store at that day
    public void foodOutOfStock(Store store){

        if(store.isSale()){
            System.out.println("Nothing happened, shop is still open.");
            return;
        }

        store.setIsClosed(true);

        System.out.println("Sadly the shop doesn´t have any food for sale today. Maybe tomorrow?");
    }

    public void illegalAnimalSeller(){

        System.out.println("An Illegal Animal Seller threatens to steal one of your animals, pay him 30 gold immediately or " +
                "use your muscles to show him who is the boss.");
    }

}
