import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class populate_aliases extends DefaultHandler{

	private String searchName, name1,name2,name3,name4,name5,value;
	private ArrayList<String> aliases, aliases1,aliases2,aliases3,aliases4,aliases5;
	private boolean wwwON=false,authorON=false,matched = false;
	private ArrayList<author> finalAuthors;
	
	public populate_aliases(String searchName) {		//!< Initialise searchcriteria, values
		this.searchName = searchName;
		aliases = new ArrayList<String>();
		finalAuthors = new ArrayList<author>();
	}
	
	public populate_aliases(String name1, String name2,String name3,String name4,String name5) {	//!< Initialise searchcriteria, values
		this.name1= name1;
		this.name2= name2;
		this.name3= name3;
		this.name4= name4;
		this.name5= name5;
		aliases1 = new ArrayList<String>();
		aliases2 = new ArrayList<String>();
		aliases3 = new ArrayList<String>();
		aliases4 = new ArrayList<String>();
		aliases5 = new ArrayList<String>();
		finalAuthors = new ArrayList<author>();
	}
	
	@Override
	public void endDocument() throws SAXException {
		//System.out.println("pa parsed");
	}

	@Override
	public void startDocument() throws SAXException {
		//System.out.println("pa parsing");
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {			//!< Process tag values
		getReadValue(ch, start, length);
		if(wwwON && authorON){
			aliases.add(value);
		}
	}
	
	public ArrayList<author> getAuthorList(){	//!< Get final authors list with aliases
		if(finalAuthors.size()==0){
			author a = new author(searchName);
			aliases = new ArrayList<String>();
			aliases.add(searchName);
			a.setAlias(aliases);
			finalAuthors.add(a);
		}
		return finalAuthors;
	}
	
	public String getReadValue(char[] ch, int start, int length){	//!< Get tag value from character array
		value = new String("");
		for(int i = start; i<(start+length);i++){
			value = value.concat(""+ch[i]);
		}
		return value;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {					//!< Action to after tag elements ends

		if(qName.equalsIgnoreCase("www")){
			wwwON = false;
			for(String s: aliases){
				if(s.contains(searchName)){
					matched = true;
				}
			}
			//System.out.println("matched: "+matched);
			if(matched){
				author a = new author(aliases.get(0));
				a.setAlias(aliases);
				finalAuthors.add(a);
			}
			matched = false;
		}
		if(qName.equalsIgnoreCase("author")){
			authorON = false;
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {	//!< Action to do when new tag element encountered.
		if(qName.equalsIgnoreCase("www")){
			wwwON = true;
			aliases = new ArrayList<String>();
		}
		if(qName.equalsIgnoreCase("author")){
			authorON = true;
		}
	}

	
	
}
