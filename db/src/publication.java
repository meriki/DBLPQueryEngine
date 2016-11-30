import java.util.ArrayList;
import java.util.Comparator;

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Comparison;


public class publication implements Comparable<publication>{
	static int sortBy = 0;
	private int year;
	private int relevance_count = 0;
	private String title,pages,volume,journal,url;
	private ArrayList<author> Authors = new ArrayList<author>();
	public publication(int year, String title, String pages, String volume,
			String journal, String url) {									//!<Initialise publication's properties
		this.year = year;
		this.title = title;
		this.pages = pages;
		this.volume = volume;
		this.journal = journal;
		this.url = url;
		Authors = new ArrayList<author>();
	}
	
	@Override
	public String toString() {
		String s = "[";
		for(author a: this.getAuthors()){
			s = s.concat(a.getName()+",");
		}
		s = s.concat("],"+this.getTitle()+","+this.getPages()+
				","+this.getYear()+","+this.getVolume()+","+this.getJournal()
				+","+this.getUrl());
		return s;
	}

	public publication(){
		Authors = new ArrayList<author>();
	}
	
	public int getRelevance_count() {							//!< Returns number of matched words in title
		return relevance_count;
	}

	public void setYear(int year) {								
		this.year = year;
	}

	public void setAuthors(ArrayList<author> authors) {
		Authors = authors;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setRelevance(int count){
		this.relevance_count=count;
	}
	public void addAuthor(author a){
		Authors.add(a);
	}
	public int getYear() {
		return year;
	}
	public String getTitle() {
		return title;
	}
	public String getPages() {
		return pages;
	}
	public String getVolume() {
		return volume;
	}
	public String getJournal() {
		return journal;
	}
	public String getUrl() {
		return url;
	}
	public ArrayList<author> getAuthors() {									//!< returns authors of an article
		return Authors;
	}

	public void setRelevance_count(int relevance_count) {
		this.relevance_count = relevance_count;
	}

	@Override
	public int compareTo(publication arg0) {								//!< Sorting Algorithm for publications based on User's set criteria. Sortby: if 0, Date Sort
		// TODO Auto-generated method stub
		arg0 = (publication)arg0;
		if(publication.sortBy == 1){
			return this.getRelevance_count()<arg0.getRelevance_count()?1:-1;
		}
		else if(publication.sortBy == 2){
			return this.getYear()>arg0.getYear()?1:-1;
		}
		return this.getYear()<arg0.getYear()?1:-1;
	}
	
	
}
