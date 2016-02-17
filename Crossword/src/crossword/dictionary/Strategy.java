package crossword.dictionary;
import crossword.Crossword;

public abstract class Strategy {

	public abstract CwEntry findEntry(Crossword cw);
	public abstract void updateBoard(Board b, CwEntry e);
}
