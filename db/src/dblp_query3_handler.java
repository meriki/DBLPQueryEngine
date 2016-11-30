import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class dblp_query3_handler {
	TreeSet<publication> pubs; 
	int year,actual;
	public dblp_query3_handler(int year, String author1,String author2,String author3,String author4, String author5) {		//!< Initialise searchcriteria, values
		File xml_file = null;
		actual = 0;
		pubs = new TreeSet<publication>();
		try{
			xml_file = new File("dblp.xml");
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			dblp_handler dh = new dblp_query11_handler("subj",author1,1600,year);
			sp.parse(xml_file, dh);
			pubs = store.getPub();
			this.year = year;
//			System.out.println("Prediction= "+predict()+" ;Actual = "+actual);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public dblp_query3_handler(int year, String author1) {																	//!< Initialise searchcriteria, values
		File xml_file = null;
		actual = 0;
		pubs = new TreeSet<publication>();
		try{
			xml_file = new File("dblp.xml");
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			dblp_handler dh = new dblp_query11_handler("subj",author1,1600,year);
			sp.parse(xml_file, dh);
			pubs = store.getPub();
			this.year = year;
//			System.out.println("Prediction= "+predict()+" ;Actual = "+actual);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public int predict(){					//!< Predict the next year value using database
		ArrayList<Float> set = new ArrayList<Float>();
		for(int i = year;i>=1600;i--){
			set.add(0f);
		}
		for(publication p: pubs){
			int yearHere = p.getYear();
			if(yearHere<=year){
				set.set(yearHere-1600, set.get(yearHere-1600)+1);
			}
			if(yearHere == year+1){
				actual++;
			}
		}
		float answer = new predictor().predict(set, year-1600);
		return (int)answer;
	}
}
