import java.util.ArrayList;


public class author {
	private String name;
	private String url;
	private ArrayList<String> alias;
	int num_publications = 1;
	public author(String name){				//!< Initialise Author with representative name
		this.name = name;
		alias = new ArrayList<String>();
	}
	public ArrayList<String> getAlias() {	//!< Get Aliases of an Author
		return alias;
	}
	public void setAlias(ArrayList<String> alias) {	//!<Set An Alias with a Alias Array
		this.alias = alias;
	}
	public void increment(){				//!< Increment Person's Number of Publications when found
		num_publications++;
	}
	public void increment(int x){			//!< Used to add one author's number of publcation to another if they resolve to the same entity
		num_publications += x;
	}
	public String getName(){				//!< Get Representative Name
		return name;
	}
	public int getPublications(){			//!< Get an author's num of publications
		return num_publications;
	}
	@Override
	public boolean equals(Object obj) {		//!< Overriding equal function. Two authors are equal if represntative names are same
		author tbc = (author)obj;
		if(tbc==null){
			return false;
		}
		if(this.getName().equals(tbc.getName())){
			return true;
		}
		else{
			return false;
		}
	}
	
}
