package crossword.dictionary;

import java.util.LinkedList;

public class Board {
	
	private BoardCell[][] board;
	private int x;
	private int y;
	
	public Board(int x, int y)
	{
		board = new BoardCell[y][x];
		for(int i=0;i<y;i++)
		{
			for(int j=0;j<x;j++)
			{
				board[i][j] = new BoardCell();
			}
		}
		this.x = x;
		this.y = y;
	}
	
	public int getWidth()
	{
		return x;
	}
	
	public int getHeight()
	{
		return y;
	}
	
	public BoardCell getCell(int x, int y)
	{
		return board[y][x];
	}
	
	public void setCell(int x, int y, BoardCell c)
	{
		board[y][x] = c;
	}
	
	public LinkedList<BoardCell> getStartCells()
	{
		LinkedList<BoardCell> ret = new LinkedList<>();
		for(int i=0;i<getWidth();i++)
		{
			for(int j=0;j<getHeight();j++)
			{
				if((board[i][j].access[0][2] == 1) || (board[i][j].access[1][2]==1))
					ret.add(board[i][j]);
			}
		}
		return ret;
	}

}
