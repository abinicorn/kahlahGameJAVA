package kalah.commands;

import kalah.Kalah;
import kalah.models.RectangleBoard;

public class NewGameCommand implements Command {
    private RectangleBoard board;

    public NewGameCommand(RectangleBoard board){
        this.board = board;
    }

    @Override
    public void execute() {
        board.initBoard(4, Kalah.NUM_OF_HOUSES);
    }
}
