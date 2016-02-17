package crossword.dictionary;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Pattern;

public class InteliCwDB extends CwDB implements Comparator<Entry> {
	
	private Random rand;
	private ListIterator<Entry> iterator;
	
	public InteliCwDB(String filename)
	{
		super(filename);
		rand = new Random();
		iterator = dict.listIterator();
	}
	
	public LinkedList<Entry> findAll(String pattern)
	{
		LinkedList<Entry> ret = new LinkedList<>();
		iterator = dict.listIterator();
		while(iterator.hasNext())
		{
			Entry tmp = iterator.next();
			if(Pattern.matches(pattern, tmp.getWord()))
			{
				ret.add(tmp);
			}
		}
		return ret;
	}
	
	public Entry getRandom()
	{
		int index = rand.nextInt(getSize());
		Entry ret = dict.get(index);
		return ret;
	}
	
	public Entry getRandom(int length)
	{
		int count = dict.size();
		int it = 0;
		int index = rand.nextInt(count);
		iterator = dict.listIterator(index);
		while(it<count)
		{
			Entry ret = iterator.next();
			if(ret.getWord().length()==length)
				return ret;
			if(isEnd())
			{
				iterator = dict.listIterator(0);
			}
			it++;
		}
		return null;
	}
	
	public Entry getRandom(String pattern)
	{
		int count = dict.size();
		int index = rand.nextInt(count);
		int it=0;
		iterator = dict.listIterator(index);
		while(it<count)
		{
			Entry ret = iterator.next();
			if(Pattern.matches(pattern, ret.getWord()))
				return ret;
			if(isEnd())
			{
				iterator = dict.listIterator(0);
			}
			it++;
			
		}
		return null;
	}
	
	private boolean isEnd()
	{
		if(iterator.hasNext()==false)
			return true;
		else
			return false;
	}
	
	public void add(String word, String clue)
	{
		for(Entry e : dict)
		{
			if(e.getWord().toUpperCase().compareTo(word.toUpperCase())>0)
			{
				int index = dict.indexOf(e);
				dict.add(index, new Entry(word,clue));
				return;
			}
		}
		dict.add(new Entry(word,clue));
	}

	@Override
	public int compare(Entry o1, Entry o2) {
		String tmp1 = o1.getWord().toUpperCase();
		String tmp2 = o2.getWord().toUpperCase();
		return tmp1.compareTo(tmp2);
	}
	
	public static void main(String[] args) {
		
		InteliCwDB k = new InteliCwDB("cwdb.txt");
		System.out.println(k.getRandom("\\w{5}a"));
	}


}
