package kalah.commands;

import kalah.models.GameStatus;
import kalah.models.RectangleBoard;

public class QuitCommand implements Command {
    private RectangleBoard board;
    public QuitCommand(RectangleBoard board){
        this.board = board;
    }

    @Override
    public void execute() {
        board.setStatus(GameStatus.FINISHED);
    }
}
