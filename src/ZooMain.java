import ZooAnimals.Elephant;
import ZooAnimals.Lion;

public class ZooMain {




    public static void main(String[] args) {

        Player player = new Player();
        Store store = new Store();
        RandomEvents randomEvents = new RandomEvents();

        randomEvents.saleOnFood(store);

        player.buyFood(100, store);
        player.buyFood(1, store);
        System.out.println(player.getFoodStorage());


    }




}
