package crossword;

import java.util.Iterator;
import java.util.LinkedList;
import crossword.dictionary.*;

public class Crossword {

	private LinkedList<CwEntry> entries;
	private Board b;
	private InteliCwDB cwdb;
	private final long ID = -1;
	
	public Crossword(int x, int y, String filename)
	{
		entries = new LinkedList<>();
		b = new Board(x, y);
		this.cwdb = new InteliCwDB(filename);
	}
	
	public Iterator<CwEntry> getEntryIter()
	{
		return entries.iterator();
	}
	
	public Board getBoardCopy()
	{
		Board ret = new Board(b.getWidth(), b.getHeight());
		for(int i=0;i<b.getHeight();i++)
		{
			for(int j=0;j<b.getWidth();j++)
			{
				ret.setCell(j, i, b.getCell(j, i));
			}
		}
		
		return ret;
	}
	
	public void setCwDB(InteliCwDB cwdb)
	{
		this.cwdb = cwdb;
	}
	
	public InteliCwDB getCwDB()
	{
		return cwdb;
	}
	
	public boolean contains(String word)
	{
		for(CwEntry cwe : entries)
		{
			if(cwe.getWord().equals(word))
				return true;
		}
		return false;
	}
	
	public final void addCwEntry(CwEntry cwe, Strategy s)
	{
		entries.add(cwe);
		s.updateBoard(b,cwe);
	}
	
	public final void generate(Strategy s)
	{
		CwEntry e = null;
		while((e = s.findEntry(this))!=null){
			addCwEntry(e, s);
		}
	}
	
	public Board getBoard()
	{
		return b;
	}
	
	public void setBoard(Board b)
	{
		this.b = b;
	}
	
	void printEntries()
	{
		for(CwEntry en : entries)
		{
			System.out.println(en.getWord() + " X: " + en.getX() + " Y: " + en.getY() + " CLUE: " + en.getClue());
		}
	}
	
	void printBoard()
	{
		for(int i=0;i<b.getHeight();i++)
		{
			for(int j=0;j<b.getWidth();j++)
			{
				if(!b.getCell(j, i).getContent().equals(""))
					System.out.print(b.getCell(j, i).getContent());
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		/*Crossword cr = new Crossword(3,2,"cwdb.txt");
		cr.b.getCell(1, 1).setContent("A");
		cr.b.getCell(1, 1).access[0][2] = 1;
		Board b2 = cr.getBoardCopy(); 
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<2;j++)
			{
				System.out.print(b2.getCell(i, j).getContent());
			}
			System.out.println();
		}
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.print(b2.getCell(1, 1).access[i][j]);
			}
			System.out.println();
		}*/
		Crossword cw = new Crossword(15, 5, "cwdb.txt");
		SimpleStrategy sim = new SimpleStrategy(9, cw.getCwDB());
		System.out.println("Haslo " + sim.passwd);
		cw.generate(sim);
		cw.printEntries();
		cw.printBoard();
	}
	
	//TODO zmiana maksymalnej dlugosci hasla dla simple
	//TODO updateBoard dla simple
	
}
