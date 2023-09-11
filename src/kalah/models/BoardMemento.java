package kalah.models;

import java.util.LinkedList;

public class BoardMemento {

    private RectangleBoard board;
    private GameStatus status;
    private int[] houses_Player1;
    private int[] houses_Player2;
    private int store_1;
    private int store_2;

    public BoardMemento(RectangleBoard board, GameStatus status, LinkedList<House> houses_Player1, LinkedList<House> houses_Player2, Store store_1, Store store_2) {
            this.board=board;
            this.houses_Player1 = savePlayerHouse(houses_Player1);
            this.houses_Player2 = savePlayerHouse(houses_Player2);
            this.store_1 = store_1.getSeedAmount();
            this.store_2 = store_2.getSeedAmount();
            this.status = status;
    }


    private int[] savePlayerHouse(LinkedList<House> houses){
        int[] housesState = new int[houses.size()];
            for (int i = 0; i < houses.size(); i++) {
                housesState[i] = houses.get(i).getSeedAmount();
            }
            return housesState;

    }

    public void restore (){
            board.setStatus(status);
            board.setPlayerHouseSeeds(houses_Player1, houses_Player2);
            board.setStoreSeeds(store_1, store_2);
    }
}
