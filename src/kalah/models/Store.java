package kalah.models;

public class Store extends Pit {

    private Player storeOwner;

    public Store(Player storeOwner){
        super(0);
        this.storeOwner = storeOwner;
    }

    //only when the store owner is the player, the store can be sown seeds.
    @Override
    public boolean isOkayToAdd(Player player) {
        return storeOwner.equals(player);
    }

    public void setStoreOwner(Player player){
        storeOwner = player;
    }
}
