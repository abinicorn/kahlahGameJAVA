package kalah.models;

import com.qualitascorpus.testsupport.IO;

import static kalah.models.Player.*;

public class Printer {

    private RectangleBoard board;

    public Printer(RectangleBoard board){
        this.board = board;
    }

    public void printBoard(IO io, RectangleBoard board){
        Player player1 = board.getPlayer(PLAYER_INDEX_1);
        Player player2 = board.getPlayer(PLAYER_INDEX_2);

        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.println(String.format("| P2 | 6[%2d] | 5[%2d] | 4[%2d] | 3[%2d] | 2[%2d] | 1[%2d] | %2d |",
                player2.getHouse(6).getSeedAmount(),
                player2.getHouse(5).getSeedAmount(),
                player2.getHouse(4).getSeedAmount(),
                player2.getHouse(3).getSeedAmount(),
                player2.getHouse(2).getSeedAmount(),
                player2.getHouse(1).getSeedAmount(),
                player1.getStore().getSeedAmount()));
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println(String.format("| %2d | 1[%2d] | 2[%2d] | 3[%2d] | 4[%2d] | 5[%2d] | 6[%2d] | P1 |",
                player2.getStore().getSeedAmount(),
                player1.getHouse(1).getSeedAmount(),
                player1.getHouse(2).getSeedAmount(),
                player1.getHouse(3).getSeedAmount(),
                player1.getHouse(4).getSeedAmount(),
                player1.getHouse(5).getSeedAmount(),
                player1.getHouse(6).getSeedAmount()));
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    public String getUserChoice(IO io, RectangleBoard board){
        io.println(String.format("Player P%d", board.getCurrentPlayerIndex()));
        io.println("    (1-6) - house number for move");
        io.println("    N - New game");
        io.println("    S - Save game");
        io.println("    L - Load game");
        io.println("    q - Quit");
        String userChoice = io.readFromKeyboard("Choice:");
        return userChoice;
    }

    public void printWinner(IO io, RectangleBoard board){
        if(board.getWinner() != 0) {
            io.println(String.format("\tplayer 1:%d", board.getPlayer(PLAYER_INDEX_1).countAllSeeds()));
            io.println(String.format("\tplayer 2:%d", board.getPlayer(PLAYER_INDEX_2).countAllSeeds()));
            switch (board.getWinner()) {
                case PLAYER_INDEX_1:
                    io.println("Player 1 wins!");
                    break;
                case PLAYER_INDEX_2:
                    io.println("Player 2 wins!");
                    break;
                case WINNER_TIE_INDEX:
                    io.println("A tie!");
                    break;
            }
        }
    }


}
