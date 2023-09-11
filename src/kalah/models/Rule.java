package kalah.models;

import com.qualitascorpus.testsupport.IO;

import static kalah.models.Player.*;

public class Rule {
    private IO io;

    public Rule(IO io){
        this.io = io;
    }

    public void apply(RectangleBoard board, int userChoice) {
        Pit currentPit = ruleSowSeeds(board, board.getCurrentPlayer().getHouse(userChoice));
        currentPit = ruleCheckLast(board, currentPit);
        checkWinner(board);
    }

    private Pit ruleSowSeeds(RectangleBoard board, Pit currentPit){
        Integer seeds = ((House)currentPit).removeSeeds();
        while (seeds > 0) {
            currentPit = currentPit.next();
            if (currentPit.isOkayToAdd(board.getCurrentPlayer())){
                currentPit.addSeed(1);
                seeds--;
            }
        }
        return currentPit;
    }

    private Pit ruleCheckLast(RectangleBoard board, Pit currentPit){
        if (!isPlayerStore(board, currentPit)) {
            if (checkLastEmpty(board, currentPit)) {
                board.getCurrentPlayer().getStore().addSeed(((House) currentPit).removeSeeds());
                board.getCurrentPlayer().getStore().addSeed(((House) currentPit).getOppositeHouse().removeSeeds());
            }
            board.togglePlayer();
        }
        return currentPit;
    }

    private boolean checkLastEmpty(RectangleBoard board, Pit currentPit){
        boolean isHouse = currentPit instanceof House;
        boolean isPlayerHouse = ((House) currentPit).isPlayerHouse(board.getCurrentPlayerIndex());
        return isHouse && isPlayerHouse && currentPit.getSeedAmount() == 1 && ((House)currentPit).getOppositeHouse().getSeedAmount() != 0;
    }

    private boolean isPlayerStore(RectangleBoard board, Pit currentPit){

        return board.getCurrentPlayer().getStore().equals(currentPit);
    }


    private void checkWinner(RectangleBoard board){
        if(board.getCurrentPlayer().countHouseSeeds() == 0){
            board.setStatus(GameStatus.FINISHED);
            int player1AllSeeds=board.getPlayer(PLAYER_INDEX_1).countAllSeeds();
            int player2AllSeeds=board.getPlayer(PLAYER_INDEX_2).countAllSeeds();
            if (player1AllSeeds>player2AllSeeds){
                board.setWinner(PLAYER_INDEX_1);
            }
            else if(player1AllSeeds<player2AllSeeds){
                board.setWinner(PLAYER_INDEX_2);
            }
            else board.setWinner(WINNER_TIE_INDEX);
        }
    }
}
