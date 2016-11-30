import java.util.Comparator;
import java.util.TreeSet;


public abstract class store{

	static int type = 0;
	
	static protected TreeSet<publication> Publications = new TreeSet<publication>();
	static private int count = 0;
	public static void addPublication(publication p){ //!<Adds a new publication to result list
		Publications.add(p);
		count++;
	}
	
	public static void printSet(){					//!< Prints the result set on console
		int i = 0;
		for(publication p: Publications){
			i++;
			System.out.print(i+",[");
			for(author a: p.getAuthors()){
				System.out.print(a.getName()+",");
			}
			System.out.println("],"+p.getTitle()+","+p.getPages()+
					","+p.getYear()+","+p.getVolume()+","+p.getJournal()
					+","+p.getUrl());
//			System.out.println(i+".\t"+p.getRelevance_count());
		}
	}
	
	
	
	

	public static TreeSet<publication> getPub(){	//!< Returns our result set
		
	return Publications;
		}
	
}
