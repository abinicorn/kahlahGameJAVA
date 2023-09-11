package kalah.models;

import java.util.LinkedList;
import static kalah.Kalah.NUM_OF_HOUSES;
import static kalah.models.Player.PLAYER_INDEX_1;
import static kalah.models.Player.PLAYER_INDEX_2;

public class RectangleBoard {
    private LinkedList<House> houses_Player1, houses_Player2;
    private Store store_1, store_2;
    private Player player1, player2;
    private GameStatus status;
    private int winnerIndex;
    private BoardMemento memento;

    public RectangleBoard (int seedsNumber, int houseNumber){
        initBoard(seedsNumber,houseNumber);
    }

    public void initBoard(int seedsNumber, int houseNumber){
        status = GameStatus.PLAYER1_TURN;
        setUpHousesAndStores(seedsNumber, houseNumber);
        player1 = new Player(PLAYER_INDEX_1, houses_Player1, store_1);
        player2 = new Player(Player.PLAYER_INDEX_2, houses_Player2, store_2);
        store_1.setStoreOwner(player1);
        store_2.setStoreOwner(player2);
        this.memento = null;
    }

    public void setUpHousesAndStores( int seedsNumber, int houseNumber){
        houses_Player1 = House.buildHouses(PLAYER_INDEX_1, seedsNumber, houseNumber);
        houses_Player2 = House.buildHouses(PLAYER_INDEX_2, seedsNumber, houseNumber);
        House.setOppositeHouse(houses_Player1, houses_Player2);
        store_1 = new Store(player1);
        store_2 = new Store(player2);
        Player.connectHousesAndStores(houses_Player1, store_1, houses_Player2, store_2);
    }

    public GameStatus getStatus(){
        return status;
    }

    public void setStatus(GameStatus status){
        this.status = status;
    }

    public Player getPlayer(int playerIndex){
        return playerIndex == PLAYER_INDEX_1 ? player1 : player2;
    }

    public int getCurrentPlayerIndex(){
        int currentPlayerIndex=0;
        if (status == GameStatus.PLAYER1_TURN)
            currentPlayerIndex=1;
        else if (status == GameStatus.PLAYER2_TURN){
            currentPlayerIndex=2;
        }
        return currentPlayerIndex;
    }

    public Player getCurrentPlayer(){
        return getPlayer(getCurrentPlayerIndex());
    }

    public void togglePlayer(){
        status = status == GameStatus.PLAYER1_TURN ? GameStatus.PLAYER2_TURN : GameStatus.PLAYER1_TURN;
    }
    public void setWinner(int playerIndex){
        this.winnerIndex =playerIndex;
    }

    public int getWinner(){
        return this.winnerIndex;
    }

    public void setPlayerHouseSeeds(int[] player1Houses, int[] player2Houses) {
        for (int i = 0; i < NUM_OF_HOUSES; i++) {
            this.houses_Player1.get(i).setSeedAmount(player1Houses[i]);
            this.houses_Player2.get(i).setSeedAmount(player2Houses[i]);
        }
    }

    public void setStoreSeeds(int store_1, int store_2){
        this.store_1.setSeedAmount(store_1);
        this.store_2.setSeedAmount(store_2);
    }

    public void saveState(){
        memento = new BoardMemento(this, this.status, this.houses_Player1, this.houses_Player2, this.store_1, this.store_2);
    }

    public boolean loadState(){
        boolean loadSuccess = false;
        if(memento != null){
            memento.restore();
            loadSuccess = true;
        }
        return loadSuccess;
    }
}
