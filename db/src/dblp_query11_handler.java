import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class dblp_query11_handler extends dblp_handler{
	
	private author queriedAuthor;
	private ArrayList<String> authorNames;
	public populate_aliases pa;
	
	
	
	public dblp_query11_handler(String searchBy, String searchName, int fromyear, int toyear){	//!< Initialise searchcriteria, values
		super(searchBy, searchName, fromyear, toyear);
		_init();
	}
	
	public dblp_query11_handler(String searchBy, String searchName, int year){					//!< Initialise searchcriteria, values
		super(searchBy, searchName, year);
		_init();
	}
	
	public dblp_query11_handler(String searchBy, String searchName) {							//!< Initialise searchcriteria, values
		super(searchBy, searchName);
		_init();
	}
	
	public void _init(){																		//!< Initialise values and begin processing for alias names of authors
		queriedAuthor = new author(searchName);
		authorNames = new ArrayList<String>();
		pa = new populate_aliases(searchName);
		authorNames = new ArrayList<String>();
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			sp.parse("dblp.xml", pa);
			ArrayList<author> tempAuthors = new ArrayList<author>();
			tempAuthors.addAll(pa.getAuthorList());
			for(author a: tempAuthors){
				for(String s: a.getAlias()){
					authorNames.add(s);
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {		//Process Values of tags
		getReadValue(arg0, arg1, arg2);
		currState = null;
		if(authorON){
			tempAuthorList.add(value);
		}
		if(titleON)currState = new titleState();
		if(pagesON)currState = new pagesState();
		if(volumeON)currState = new volumeState();
		if(journalON)currState = new journalState();
		if(urlON)currState = new urlState();
		if(yearON)currState = new yearState();
		if(currState!=null){
			tempPublication = currState.tagFunction(value, tempPublication);
		}
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {											//!< Action to do after element ends 
		if(arg2.equalsIgnoreCase("article")||arg2.equalsIgnoreCase("inproceedings")
				||arg2.equalsIgnoreCase("proceedings")||arg2.equalsIgnoreCase("book")
				||arg2.equalsIgnoreCase("incollection")||arg2.equalsIgnoreCase("phdthesis")
				||arg2.equalsIgnoreCase("masterthesis")){
				
			for(String s1: authorNames){
				for(String s2: tempAuthorList){
					if(s1.equalsIgnoreCase(s2)){
						matched = true;
					}
				}
			}
		}
		super.endElement(arg0, arg1, arg2);
	}

	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {							//!< Action to do when element starts
		
		super.startElement(arg0, arg1, arg2, arg3);
	}

	@Override
	public void startDocument() throws SAXException {
		
	}
	
	
	
}
