import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class dblp_queryk_handler extends dblp_handler{

	protected static Hashtable<String, Integer> wwwAuthors;
	protected static ArrayList<Integer> pubCount;
	protected ArrayList<String> authorGreatK,authorRep;
	protected int k,hashKey,lineNo = 0;
	ArrayList<author> allAuthors;
	boolean wwwON,isFirst;
	String firstAuthor;
	protected int count=0;
	public dblp_queryk_handler(String searchBy, String searchName,int k) {	//!< Initialise searchcriteria, values
		super(searchBy, searchName);
		allAuthors = new ArrayList<author>();
		wwwAuthors = new Hashtable<String,Integer>();
		pubCount = new ArrayList<Integer>();
		authorGreatK = new ArrayList<String>();
		authorRep = new ArrayList<String>();
		hashKey = 0;
		this.k=k;
	}
	
	
	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {		//!< Process the value of tags
		getReadValue(arg0, arg1, arg2);
		
		if(wwwON && authorON){
			if(isFirst){
				firstAuthor = new String(value);
				isFirst = false;
			}
			wwwAuthors.put(value,hashKey);
		}
	}
	
	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {									//!< Action to do after tag is processed
		if(arg2.equalsIgnoreCase("www")){
			hashKey++;
			authorRep.add(firstAuthor);
			pubCount.add(0);
			wwwON = false;
		}
		if(arg2.equalsIgnoreCase("author")){
			authorON = false;
		}
		if(arg2.equalsIgnoreCase("article")||arg2.equalsIgnoreCase("inproceedings")
				||arg2.equalsIgnoreCase("proceedings")||arg2.equalsIgnoreCase("book")
				||arg2.equalsIgnoreCase("incollection")||arg2.equalsIgnoreCase("phdthesis")
				||arg2.equalsIgnoreCase("masterthesis")){
			articleON = false;
		}
	}
	
	@Override
	public void endDocument() throws SAXException {			//!< Action to do after document read.
		File xml_file = null;
		try{
			xml_file = new File("dblp.xml");
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			finalStep dh = new finalStep("subj","Vinayak Naik");
			sp.parse(xml_file, dh);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {					//!< Action to do when new opening tag encountered.
		
		if(arg2.equalsIgnoreCase("www")){
			wwwON = true;
			isFirst = true;
		}
		if(arg2.equalsIgnoreCase("author")){
			authorON = true;				
		}
		if(arg2.equalsIgnoreCase("article")||arg2.equalsIgnoreCase("inproceedings")
				||arg2.equalsIgnoreCase("proceedings")||arg2.equalsIgnoreCase("book")
				||arg2.equalsIgnoreCase("incollection")||arg2.equalsIgnoreCase("phdthesis")
				||arg2.equalsIgnoreCase("masterthesis")){
			articleON = true;
		}
	}
	
	public static ArrayList<Integer> getPubCount(){return pubCount;}
	
	public static Hashtable<String,Integer> getWwwAuthors(){return wwwAuthors;}
	
	
	
	public ArrayList<String> getAuthorGreatK(){return authorGreatK;}
	
	public int getK(int k){
		int counter = 0;
		for(int f=0;f<pubCount.size();f++){
			if(pubCount.get(f)>k){
				authorGreatK.add(authorRep.get(f));
				counter++;
			}
		}
		return counter;
	}
}

class finalStep extends dblp_handler{
	
	private int lineNo = 0;
	public finalStep(String searchBy, String searchName) {super(searchBy, searchName);}

	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		getReadValue(arg0, arg1, arg2);
		if(articleON && authorON){
			if(dblp_queryk_handler.getWwwAuthors().containsKey(value)){
				dblp_queryk_handler.pubCount.set(dblp_queryk_handler.getWwwAuthors().get(value),
						dblp_queryk_handler.pubCount.get(dblp_queryk_handler.getWwwAuthors().get(value))+1);
			}
		}
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {
		if(arg2.equalsIgnoreCase("article")||arg2.equalsIgnoreCase("inproceedings")
				||arg2.equalsIgnoreCase("proceedings")||arg2.equalsIgnoreCase("book")
				||arg2.equalsIgnoreCase("incollection")||arg2.equalsIgnoreCase("phdthesis")
				||arg2.equalsIgnoreCase("masterthesis")){
			articleON = false;
		}
		if(arg2.equalsIgnoreCase("author")){
			authorON = false;
		}
	}

	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {
		if(arg2.equalsIgnoreCase("article")||arg2.equalsIgnoreCase("inproceedings")
				||arg2.equalsIgnoreCase("proceedings")||arg2.equalsIgnoreCase("book")
				||arg2.equalsIgnoreCase("incollection")||arg2.equalsIgnoreCase("phdthesis")
				||arg2.equalsIgnoreCase("masterthesis")){
			articleON = true;
		}
		if(arg2.equalsIgnoreCase("author")){
			authorON = true;
		}
	}
	
	
}
