package kalah.models;

import java.util.LinkedList;

public class House extends Pit {

    private int playerIndex;
    private House oppositeHouse;

    @Override
    public boolean isOkayToAdd(Player player) {
        return true;
    };

    public House (int playerIndex, int seedsAmount){
        super(seedsAmount);
        this.playerIndex = playerIndex;
    }

    public House getOppositeHouse(){
        return oppositeHouse;
    }

    public void setOppositeHouse(House oppositeHouse){
        this.oppositeHouse = oppositeHouse;
    }

    public int removeSeeds() {
        int seedsNumber=this.seedAmount;
        this.seedAmount=0;
        return seedsNumber;
    }

    public boolean isPlayerHouse(int playerIndex){
        return this.playerIndex == playerIndex;
    }

    public static LinkedList<House> buildHouses(int playerIndex, int seedNumber, int houseLength) {
        LinkedList<House> houses= new LinkedList<>();
        houses.addLast(new House(playerIndex, seedNumber));
        while(houses.size()<houseLength){
            House house= new House(playerIndex, seedNumber);
            houses.addLast(house);
        }
        return houses;
    }

    public static void setOppositeHouse(LinkedList<House> houseOne, LinkedList<House> houseTwo){
        for (int i = 0; i < houseOne.size(); i++) {
            House chosen=houseOne.get(i);
            House opposite=houseTwo.get(houseOne.size()-i-1);
            chosen.setOppositeHouse(opposite);
            opposite.setOppositeHouse(chosen);
        }
    }

}
