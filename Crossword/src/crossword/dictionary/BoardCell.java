package crossword.dictionary;

public class BoardCell {
	
	public int[][] access = {{0,0,1},{0,1,1},{1,0,1},{1,1,1},{2,0,1},{2,1,1}};
	private String content = "";
	//private static final int COMBINATIONS = 6;
	//private static final int DEP = 3;
	
	/**
	 * Column 0 Start(0), Inner(1), End(2)
	 * Column 1 Horiz(0), Vert(1)
	 * Column 0 disable(0), enable(1) 
	 */
	
	public BoardCell()
	{
		content = "";
	}
	
	public BoardCell(String content)
	{
		this.content = content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public String getContent()
	{
		return content;
	}
	
}
