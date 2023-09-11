package kalah.models;

public abstract class Pit {

    protected int seedAmount;
    private Pit next;

    public Pit (int seedsNumber){
        this.seedAmount = seedsNumber;
    }

    public int getSeedAmount(){
        return seedAmount;
    }

    public void setSeedAmount(int amount){
        this.seedAmount = amount;
    }


    //Add seeds in the house
    public void addSeed(int numberAdd){
        this.seedAmount+=numberAdd;
    }

    //To check whether the house/store is okay to Add
    public abstract boolean isOkayToAdd(Player player);

    public Pit next() {
        return next;
    }

    //set the next pit for the pit
    public Pit setNext(Pit next) {
        this.next = next;
        return next;
    }

    public boolean isEmpty(){
        return seedAmount==0;
    }
}
