import ZooAnimals.Animal;
import ZooAnimals.Elephant;
import ZooAnimals.Lion;

import java.util.ArrayList;

public class ZooMain {




    public static void main(String[] args) {

        Player player = new Player();
        Store store = new Store();
        RandomEvents randomEvents = new RandomEvents();



        player.animalsInTheZoo.remove(0);

        System.out.println("--------------------------");

        ArrayList<Animal> someAnimalList;


            for(int i = 0; i < 5; i++){

                someAnimalList = randomEvents.sickness(player.getAnimalsInTheZoo());

                for(Animal a : someAnimalList){
                    System.out.println(a.getClass());
                }

                System.out.println("******************");
            }







    }




}
