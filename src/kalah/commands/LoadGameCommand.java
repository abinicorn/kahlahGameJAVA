package kalah.commands;
import com.qualitascorpus.testsupport.IO;
import kalah.models.RectangleBoard;

public class LoadGameCommand implements Command {
    private RectangleBoard board;
    private IO io;
    public LoadGameCommand(RectangleBoard board, IO io) {
        this.board = board;
        this.io = io;
    }


    @Override
    public void execute() {
        boolean loadSuccess = this.board.loadState();
        if(!loadSuccess){
            io.println("No saved game");
        }
    }
}
