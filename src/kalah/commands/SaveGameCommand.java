package kalah.commands;

import kalah.models.RectangleBoard;

public class SaveGameCommand implements Command {

    private RectangleBoard board;

    public SaveGameCommand(RectangleBoard board) {
        this.board= board;
    }

    @Override
    public void execute() {
        this.board.saveState();
    }
}
