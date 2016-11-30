import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.xml.sax.SAXException;

public class ResultController implements ActionListener  {
	public static TreeSet<publication> answer=new TreeSet<publication>();
	public int query3answer1,query3answer2,query3answer3,query3answer4,query3answer5;
	public ArrayList<String> query2answer;
	String answer3,one,two;int i,j;
	String query1name,query2year,searchby,selected,kl;
	int query1customyear2,query1customyear1,query1year;
	
	static Iterator it;
	static int counter=1;
	public static int countresult;
	boolean radioselect;
	@Override
	public void actionPerformed(ActionEvent e) {   //!< Defines the action to be performed after pressing sublit button
		GUI.getInstance().midpane.setVisible(true);;
		Thread thread=new Thread(){
			public void run(){

					GUI.getInstance().ans.setText(" Processing Request,  Please wait. "+ "\n" +" After all I'm not Human.");
						if(GUI.getInstance().name!=null)
							query1name=GUI.getInstance().name.getText();
						if(GUI.getInstance().year!=null){
							one=GUI.getInstance().year.getText();
						if(one!=null && !one.equals(""))
							query1year= Integer.parseInt(one);
						else query1year=0;
						}
						
						if(GUI.getInstance().customyear1!=null){
						one=GUI.getInstance().customyear1.getText();
						if(one!=null  && !one.equals(""))
							query1customyear1= Integer.parseInt(one);
						else query1customyear1=0;
						}
						
						if(GUI.getInstance().customyear2!=null){
							one=GUI.getInstance().customyear2.getText();
						if(one!=null  && !one.equals(""))
							query1customyear2= Integer.parseInt(one);
						else query1customyear2=0;
						}
						
					
							searchby=GUI.getInstance().h;			

		if(GUI.getInstance().selected.equals("Query1")){  //!< Query1
			if(GUI.getInstance().rdbtnSortByRelevance.isSelected())
				radioselect=true;
			else
				radioselect=false;
			if(query1name!=null && query1name!="" && query1year==0 && query1customyear1==0 && query1customyear2==0)
			{	
					
						try {
							answer=	mainframe.run(searchby,query1name,radioselect);
						} catch (SAXException | IOException e) {
							e.printStackTrace();
						}
						countresult=answer.size();
					
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								GUI.getInstance().total_count.setText(""+countresult);
								if(countresult==0)
									JOptionPane.showMessageDialog(GUI.getInstance(), "No Results Found");
								else{
									it=answer.iterator();
									GUI.getInstance().ans.setText(print20Results(it,counter));
								}
							};
						});
					
			}	
			else if(query1name!=null && query1name!=""  && query1customyear1==0 && query1customyear2==0){
				
				try {
					answer=	mainframe.run(searchby,query1name,radioselect,query1year);
					countresult=answer.size();
				} catch (SAXException | IOException e1) {
					e1.printStackTrace();
				}
			
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						GUI.getInstance().total_count.setText(""+countresult);
						if(countresult==0)
							JOptionPane.showMessageDialog(GUI.getInstance(), "No Results Found");
						else{
						it=answer.iterator();
						GUI.getInstance().ans.setText(print20Results(it,counter));
						}
					};
				});
			}
			else if(query1name!=null && query1name!="" && query1year==0 ){
				try {
					answer=	mainframe.run(searchby,query1name,radioselect,query1customyear1,query1customyear2);
				} catch (SAXException | IOException e1) {
					e1.printStackTrace();
				}
				countresult=answer.size();
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						GUI.getInstance().total_count.setText(""+countresult);
						if(countresult==0)
							JOptionPane.showMessageDialog(GUI.getInstance(), "No Results Found");
						else{
						it=answer.iterator();
						GUI.getInstance().ans.setText(print20Results(it,counter));
						}
					};
				});
			}
			else{
				JOptionPane.showMessageDialog(GUI.getInstance(), "Please Enter Valid Detilas","ERROR",JOptionPane.ERROR_MESSAGE);
			}
//				
		}
		else if(GUI.getInstance().selected.equals("Query2")){ //!< Query2
			int k=Integer.parseInt((GUI.getInstance().pubno.getText()));
			if(k!=0){
				try {
					query2answer= mainframe.runint("ngdc","sndgv",k);
					GUI.getInstance().total_count.setText(""+query2answer.size());
					it=query2answer.iterator();
					GUI.getInstance().ans.setText(print20Results(it,counter));
				} catch (SAXException | IOException e1) {
					e1.printStackTrace();
				}
			}
			else{				
				JOptionPane.showMessageDialog(GUI.getInstance(), "Please Enter Valid Detilas","ERROR",JOptionPane.ERROR_MESSAGE);
			}//exception
		}
		else{ 					//!< Query3
			int k=Integer.parseInt(GUI.getInstance().pubno.getText());
			try {
//				if(GUI.getInstance().name1.getText().equals(null) || GUI.getInstance().name1.getText().equals("") ||GUI.getInstance().name2.getText().equals(null) || GUI.getInstance().name2.getText().equals("") ||GUI.getInstance().name3.getText().equals(null) || GUI.getInstance().name3.getText().equals("")||GUI.getInstance().name4.getText().equals(null) || GUI.getInstance().name4.getText().equals("")||GUI.getInstance().name5.getText().equals(null) || GUI.getInstance().name5.getText().equals("")   )
				
				query3answer1=mainframe.runk(k,GUI.getInstance().name1.getText());
				query3answer2=mainframe.runk(k,GUI.getInstance().name2.getText());
				query3answer3=mainframe.runk(k,GUI.getInstance().name3.getText());
				query3answer4=mainframe.runk(k,GUI.getInstance().name4.getText());
				query3answer5=mainframe.runk(k,GUI.getInstance().name5.getText());
				String y=mainframe.runks(k, GUI.getInstance().name1.getText(), GUI.getInstance().name2.getText(), GUI.getInstance().name3.getText(), GUI.getInstance().name4.getText(), GUI.getInstance().name5.getText());
				answer3=""+query3answer1+" " + query3answer2+" " + query3answer3+" " + query3answer4+" " + query3answer5;
				
				GUI.getInstance().ans.setText(""+answer3);
			} catch (SAXException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}
		};
	thread.start();

}
	public static String print20Results(Iterator it,int counter){
		
		int i=1;
		String a="";
		while(i<=20 && it.hasNext()){
			a=a+counter+" "+it.next().toString()+"\n\n";
			i++;
			counter++;
		}
		
		return a;
	}

}

class ResetController implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		GUI.getInstance().query_list_cb.setSelectedItem(null);;
		GUI.getInstance().midpane.removeAll();
		GUI.getInstance().midpane.revalidate();
		GUI.getInstance().midpane.repaint();		
//		GUI.getInstance().midpane.setVisible(false);
		
	}
	
}

