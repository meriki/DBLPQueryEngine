import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class mainframe {
	/**
	 * @param args
	 * @return 
	 * @throws SAXException 
	 * @throws IOException 
	 */
	public static TreeSet<publication> run(String searchby,String name,boolean sort) throws SAXException, IOException {
		
		File xml_file = null;
		try{
			
			xml_file = new File("dblp.xml");
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			//dblp_handler dh = new dblp_query11_handler("subj","Vinayak Naik",2005,2007);
			if(sort)
				publication.sortBy=1;
			if(searchby.equals("Author")){
				dblp_query11_handler dh = new dblp_query11_handler(searchby,name);
				sp.parse(xml_file, dh);
				}
			else{
				dblp_handler dh = new dblp_handler(searchby,name);
				sp.parse(xml_file, dh);
			}
			
			publication.sortBy=0;
			return store.getPub();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
//		new GUI();
		return null;
	}
	public static TreeSet<publication> run(String searchby,String name,boolean sort,int year) throws SAXException, IOException {
		
		File xml_file = null;
		try{
			xml_file = new File("dblp.xml");
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			
			if(sort)
				publication.sortBy=1;
			if(searchby.equals("Author")){
				dblp_query11_handler dh = new dblp_query11_handler(searchby,name,year);
				sp.parse(xml_file, dh);
				}
			else{
				dblp_handler dh = new dblp_handler(searchby,name,year);
				sp.parse(xml_file, dh);
			}
			
			publication.sortBy=0;
		}
		catch(Exception e){
			e.printStackTrace();			
		}
		
		return store.getPub();

	}
public static TreeSet<publication> run(String searchby,String name,boolean sort,int year1,int year2) throws SAXException, IOException {
		
		File xml_file = null;
		try{
			xml_file = new File("dblp.xml");
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			
			if(sort)
				publication.sortBy=1;
			if(searchby.equals("Author")){
				dblp_query11_handler dh = new dblp_query11_handler(searchby,name,year1,year2);
				sp.parse(xml_file, dh);
				}
			else{
				dblp_handler dh = new dblp_handler(searchby,name,year1,year2);
				sp.parse(xml_file, dh);
			}
			
			publication.sortBy=0;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return store.getPub();
	}

public static ArrayList<String> runint(String searchby,String name,int k) throws SAXException, IOException {
	
	File xml_file = null;
	try{
		xml_file = new File("dblp.xml");
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		
		
		dblp_queryk_handler dh = new dblp_queryk_handler(searchby,name,k);
		
		sp.parse(xml_file, dh);

		dh.getK(k);
		return dh.getAuthorGreatK();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;

}

public static int runk(int k,String name1) throws SAXException, IOException {
	
	File xml_file = null;
	try{
		xml_file = new File("dblp.xml");
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		
		dblp_query3_handler dh = new dblp_query3_handler(k,name1);
//		dblp_query3_handler dh = new dblp_query3_handler(k,name1,name2,name3,name4,name5);
		
	//write retrun 	
		return dh.predict();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return 0;

}

public static String runks(int k,String name1,String name2,String name3, String name4, String name5) throws SAXException, IOException {
	
	File xml_file = null;
	try{
		xml_file = new File("dblp.xml");
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		
		dblp_query3_handler dh = new dblp_query3_handler(k,name1,name2,name3,name4,name5);
//		dblp_query3_handler dh = new dblp_query3_handler(k,name1,name2,name3,name4,name5);
		
	//write retrun 	
//		return dh.predict();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;

}

}