package kalah;
import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.commands.*;
import kalah.models.*;

public class Kalah {
	public static int NUM_OF_HOUSES = 6;

	private Rule rule;
	private Invoker invoker = new Invoker();

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
		RectangleBoard board = new RectangleBoard(4,NUM_OF_HOUSES);
		Printer printer = new Printer(board);
		rule = new Rule(io);
		NewGameCommand newGameCommand = new NewGameCommand (board);
		QuitCommand quitCommand = new QuitCommand(board);
		LoadGameCommand loadGameCommand = new LoadGameCommand(board, io);
		SaveGameCommand saveGameCommand = new SaveGameCommand(board);

		printer.printBoard(io,board);
		while(board.getStatus() != GameStatus.FINISHED){
			String userChoice = printer.getUserChoice(io,board);
			switch (userChoice) {
				case "n":
					invoker.setCommand(newGameCommand);
					invoker.execute();
					printer.printBoard(io,board);
					break;
				case "s":
					invoker.setCommand(saveGameCommand);
					invoker.execute();
					printer.printBoard(io,board);
					break;
				case "l":
					invoker.setCommand(loadGameCommand);
					invoker.execute();
					printer.printBoard(io,board);
					break;
				case "q":
					invoker.setCommand(quitCommand);
					invoker.execute();
			}

			if(isNumeric(userChoice)){
				if (board.getCurrentPlayer().getHouse(Integer.parseInt(userChoice)).isEmpty()) {
					io.println("House is empty. Move again.");
				}
				else{
					rule.apply(board, Integer.parseInt(userChoice));
				}
				printer.printBoard(io,board);
			}
		}
		io.println("Game over");
		printer.printBoard(io,board);
		printer.printWinner(io,board);
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public void play(IO io, boolean vertical, boolean bmf) {
		// DO NOT CHANGE. Only here for backwards compatibility
		play(io);
	}
}
