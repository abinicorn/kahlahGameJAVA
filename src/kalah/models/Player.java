package kalah.models;

import java.util.LinkedList;
import java.util.List;
import static kalah.Kalah.NUM_OF_HOUSES;

public class Player {

    private int playerIndex;

    private List<House> houses;
    private Store store;

    public static final int PLAYER_INDEX_1 = 1;
    public static final int PLAYER_INDEX_2 = 2;
    public static final int WINNER_TIE_INDEX = 3;


    public Player(int playerIndex, List<House> houses, Store store) {
        this.playerIndex = playerIndex;
        this.houses=houses;
        this.store=store;

    }
    public House getHouse(int houseNum) {
        if (houseNum<1 || houseNum>this.houses.size()){
            throw new IllegalArgumentException("House number must be between 1 and " + this.houses.size());
        }
        return this.houses.get(houseNum - 1);
    }

    public Store getStore() {
        return store;
    }

    public int countHouseSeeds(){
        int sum=0;
        for (int i = 0; i <NUM_OF_HOUSES; i++) {
            sum += getHouse(i+1).getSeedAmount();
        }
        return sum;
    }
    public int countAllSeeds(){
        return countHouseSeeds()+store.getSeedAmount();
    }
    public static void connectHousesAndStores(LinkedList<House> player1Houses, Store player1Store, LinkedList<House> player2Houses, Store player2Store){
        for (int i = 0; i < player1Houses.size()-1; i++) {
            player1Houses.get(i).setNext(player1Houses.get(i+1));
            player2Houses.get(i).setNext(player2Houses.get(i+1));
        }
        player1Houses.getLast().setNext(player1Store);
        player1Store.setNext(player2Houses.getFirst());
        player2Houses.getLast().setNext(player2Store);
        player2Store.setNext(player1Houses.getFirst());
    }

}
