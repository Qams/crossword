package crossword.dictionary;

import java.util.LinkedList;
import java.util.Random;

import crossword.Crossword;
import crossword.dictionary.CwEntry.Direction;

public class SimpleStrategy extends Strategy {

	public String passwd;
	private InteliCwDB icwdb;
	private int passwordLength;
	private int countPasswordToGenerate;

	public SimpleStrategy(int passwdLength, InteliCwDB icwdb) {
		passwordLength = passwdLength;
		countPasswordToGenerate = passwdLength;
		try {
			this.icwdb = icwdb;
			passwd = icwdb.getRandom(passwdLength).getWord();
		} catch (NullPointerException ex) {
			passwd = icwdb.getRandom().getWord();
		}
	}

	@Override
	public CwEntry findEntry(Crossword cw) {
		char firstLetter;
		if(passwordLength > cw.getBoard().getHeight())
		{
			Board tmp = cw.getBoardCopy();
			Board newBoard = new Board(cw.getBoard().getWidth(), passwordLength);
			cw.setBoard(newBoard);
			for(int i=0;i<tmp.getHeight();i++)
			{
				for(int j=0;j<tmp.getWidth();j++)
				{
					cw.getBoard().setCell(j, i, tmp.getCell(j, i));
				}
			}
		}
		if (countPasswordToGenerate > 0) {
			firstLetter = passwd.charAt(passwordLength - countPasswordToGenerate);
			int len = cw.getBoard().getWidth()-1;
			LinkedList<Entry> list = icwdb.findAll(firstLetter + ".{1," + len + "}");
			Entry pass = getRandomEntryFromList(list);
			//Entry pass = icwdb.getRandom(firstLetter + "\\w+");
			try {
				CwEntry ret = new CwEntry(pass.getWord(), pass.getClue(), 0, passwordLength - countPasswordToGenerate,
						Direction.HORIZ);
				countPasswordToGenerate--;
				return ret;
			} catch (NullPointerException ex) {
				CwEntry ret = new CwEntry("" + firstLetter, "", 0, passwordLength - countPasswordToGenerate,
						Direction.HORIZ);
				countPasswordToGenerate--;
				return ret;
			}
		}
		return null;
	}
	
	private Entry getRandomEntryFromList(LinkedList<Entry> list)
	{
		Random rand = new Random();
		try
		{
			return list.get(rand.nextInt(list.size()));
		}
		catch(Exception ex)
		{
			return null;
		}
		
	}

	@Override
	public void updateBoard(Board b, CwEntry e) {

			BoardCell bc = null;
			int from = -1;
			for(int i=0;i<b.getHeight();i++)
			{
				if(b.getCell(0, i).getContent().equals(""))
				{
					bc = b.getCell(0, i);
					from = i;
					break;
				}			
			}
			if(bc != null && from != -1)
			{
				for(int j=0;j<e.getWord().length();j++)
				{
					b.setCell(j, from, new BoardCell("" + e.getWord().charAt(j)));
				}
			}
	}

}
