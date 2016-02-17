package crossword.dictionary;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class CwDB {
	
	protected LinkedList<Entry> dict;
	
	public CwDB(String filename)
	{
		dict = new LinkedList<>();
		createDB(filename);
	}
	
	public void add(String word, String clue)
	{
		dict.add(new Entry(word,clue));
	}
	
	public Entry get(String word)
	{
		for(Entry e : dict)
		{
			if(e.getWord().equals(word))
			{
				return e;
			}
		}
		return null;
	}
	
	public void remove(String word)
	{
		for(Entry e : dict)
		{
			if(e.getWord().equals(word))
				dict.remove(e);
		}
	}
	
	public void saveDB(String filename)
	{
		
	}
	
	public int getSize()
	{
		return dict.size();
	}
	
	protected void createDB(String filename)
	{
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
			String line = null;
			while((line = br.readLine()) != null)
			{
				String line2 = br.readLine();
				add(line, line2);
				System.out.println(line2);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Podana nazwa pliku nie istnieje");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
