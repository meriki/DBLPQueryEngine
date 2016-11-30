import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class dblp_handler extends DefaultHandler{

	protected String value;
	
	protected String searchBy,searchName;
	protected boolean articleON,titleON,pagesON,volumeON,journalON,urlON,yearON,authorON;
	protected boolean matched;
	protected ArrayList<String> tempAuthorList;
	protected int year;
	protected String title,pages,volume,journal,url;
	protected author tempAuthor;
	protected publication tempPublication;
	protected tagState currState;
	protected String[] searchWordsinSearchName;
	protected int numberMatchesinValue;
	protected static int sortBy;
	protected int sinceYear;
	protected int firstyear;
	protected int secondyear;
	protected int querycase;
	protected long artNo = 0;
	protected String author1,author2,author3,author4,author5;

	public dblp_handler(String searchBy, String searchName){ 	//!< Initialise searchcriteria, values
		super();
		this.searchBy = searchBy;
		this.searchName = searchName;
		searchWordsinSearchName=this.searchName.split("\\s");
		querycase=1;
	}
	
	public dblp_handler(String searchBy, String searchName, int year){	//!< Initialise searchcriteria, values
		super();
		this.searchBy = searchBy;
		this.searchName = searchName;
		searchWordsinSearchName=this.searchName.split("\\s");
		sinceYear =year;
		querycase=2;
	}
	
	public dblp_handler(String searchBy, String searchName, int firstyear,int secondyear){	//!< Initialise searchcriteria, values
		super();
		this.searchBy = searchBy;
		this.searchName = searchName;
		searchWordsinSearchName=this.searchName.split("\\s");
		this.firstyear=firstyear;
		this.secondyear=secondyear;
		querycase=3;
	}
	
	public dblp_handler(String string, String author1, String author2, String author3, String author4,
			String author5, int i, int year) {				//!< Initialise searchcriteria, values
		super();
		this.author1=author1;
		this.author2=author2;
		this.author3=author3;
		this.author4=author4;
		this.author5=author5;
		querycase=4;
	}
	
	public String getValue() {							//Return the value
		return value;
	}

	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {		//Overriding processing of characters
//		equateTitle(arg0, arg1, arg2);
		getReadValue(arg0, arg1, arg2);
		currState = null;

		if(titleON){
			numberMatchesinValue=0;
			for(int it=0;it<searchWordsinSearchName.length;it++)
			{
				if(value.contains(searchWordsinSearchName[it])){
					numberMatchesinValue++;
				}
			}
			if(numberMatchesinValue > 0){
				matched = true;
			}
			else{
				matched = false;
			}
			if(matched){
				currState = new titleState();
			}
		}
//		}
		
		if(pagesON && matched)currState = new pagesState();
		if(volumeON && matched)currState = new volumeState();
		if(journalON && matched)currState = new journalState();
		if(urlON && matched)currState = new urlState();
		if(yearON && matched)currState = new yearState();
		if(authorON){
			tempAuthorList.add(value);
		}
		if(currState!=null){
			tempPublication = currState.tagFunction(value, tempPublication);
		}
	}

	public String getReadValue(char[] ch, int start, int length){			//Get the value of the tag from character array
		value = new String("");
		for(int i = start; i<(start+length);i++){
			value = value.concat(""+ch[i]);
		}
		return value;
	}
	
	
	
	@Override
	public void endDocument() throws SAXException {							//Action to occur after document is read
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {											//!< Action to perform after element tag ends
		if(arg2.equalsIgnoreCase("article")||arg2.equalsIgnoreCase("inproceedings")
				||arg2.equalsIgnoreCase("proceedings")||arg2.equalsIgnoreCase("book")
				||arg2.equalsIgnoreCase("incollection")||arg2.equalsIgnoreCase("phdthesis")
				||arg2.equalsIgnoreCase("masterthesis")||arg2.equalsIgnoreCase("www")){
			articleON = false;
			//artNo++;
//			if(artNo%5300000==0){
//				System.out.println(artNo);
//			}
			if(matched){
				for(String s: tempAuthorList){
					tempPublication = (new authorState().tagFunction(s, tempPublication));
				}
				tempPublication.setRelevance(numberMatchesinValue);
				if(querycase==1)
					store.addPublication(tempPublication);
				else if(querycase==2)
				{
					if(tempPublication.getYear()>=sinceYear)
						store.addPublication(tempPublication);
				}
				else if(querycase==3){
					if(tempPublication.getYear()>=firstyear && tempPublication.getYear()<=secondyear)
						store.addPublication(tempPublication);
				}
			}
			matched = false;
			
		}
		titleON = arg2.equalsIgnoreCase("title")?false:titleON;
		pagesON = arg2.equalsIgnoreCase("pages")?false:pagesON;
		volumeON = arg2.equalsIgnoreCase("volume")?false:volumeON;
		journalON = (arg2.equalsIgnoreCase("book")||arg2.equalsIgnoreCase("journal"))?false:journalON;
		urlON = arg2.equalsIgnoreCase("url")?false:urlON;
		yearON = arg2.equalsIgnoreCase("year")?false:yearON;
		pagesON = arg2.equalsIgnoreCase("pages")?false:pagesON;
		authorON = arg2.equalsIgnoreCase("author")?false:authorON;
	}

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {								//!< Action to do when a new element is gonna start
		if(arg2.equalsIgnoreCase("article")||arg2.equalsIgnoreCase("inproceedings")
				||arg2.equalsIgnoreCase("proceedings")||arg2.equalsIgnoreCase("book")
				||arg2.equalsIgnoreCase("incollection")||arg2.equalsIgnoreCase("phdthesis")
				||arg2.equalsIgnoreCase("masterthesis")||arg2.equalsIgnoreCase("www")){
			articleON = true;
			matched = false;
			tempAuthorList = new ArrayList<String>();
		}
		titleON = arg2.equalsIgnoreCase("title")?true:titleON;
		pagesON = arg2.equalsIgnoreCase("pages")?true:pagesON;
		volumeON = arg2.equalsIgnoreCase("volume")?true:volumeON;
		journalON = (arg2.equalsIgnoreCase("book")||arg2.equalsIgnoreCase("journal"))?true:journalON;
		urlON = arg2.equalsIgnoreCase("url")?true:urlON;
		yearON = arg2.equalsIgnoreCase("year")?true:yearON;
		pagesON = arg2.equalsIgnoreCase("pages")?true:pagesON;
		authorON = arg2.equalsIgnoreCase("author")?true:authorON;
	}

	
	
}
