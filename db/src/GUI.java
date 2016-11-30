import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
public class GUI extends JFrame{
	private static GUI GUIsingleton;
	private static int countObjects = 0;
	protected JComboBox query_list_cb;
	protected JPanel result_pane, midpane,query_pane;
	protected JTextField name,year,customyear1,customyear2,name1,name2,name3,name4,name5;
	protected JRadioButton rdbtnSortByYear,rdbtnSortByRelevance;
	protected String h="Author",selected;
	Border blackline = BorderFactory.createLineBorder(Color.black);
	JLabel total_count=new JLabel(),heading;
	JTextArea ans=new JTextArea();
	JScrollPane scroll=new JScrollPane(ans);;
	protected JTextField pubno;	
	public static GUI getInstance() { //!<Creates an instance of GUI Class.
		if(countObjects == 0){
			countObjects++;
			GUIsingleton= new GUI();
			return GUIsingleton;
		}else
			return GUIsingleton;}		
	private GUI(){ //!<Constructor
		super();
		setSize(new Dimension(1200, 700));		
		heading = new JLabel("DBLP Query Engine", SwingConstants.CENTER);
		heading.setBorder(blackline);
		heading.setFont(heading.getFont().deriveFont(30.0f));
		result_pane=new ResultPanel();
		query_pane = new JPanel();
		query_pane.setLayout(null);		
		query_list_cb= new Cobox();		
		query_pane.add(query_list_cb);
		query_pane.setLayout(null);
		midpane=new JPanel();						
		midpane.setLayout(null);
		query_list_cb.addItemListener(new ItemListener() {			
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					if(((String)((JComboBox)arg0.getSource()).getSelectedItem()).equals("Query1")){
						if(midpane.getComponentCount()>0){
							midpane.removeAll();
							midpane.revalidate();
				            midpane.repaint();							
						}
						else{
							midpane.setBounds(1,70,query_pane.getWidth()-11,330);
						}
						selected="Query1";
						String[] options={"Author name","Title"};
						JComboBox select=new JComboBox(options);
						select.addItemListener(new ItemListener() {
							public void itemStateChanged(ItemEvent e) {
								h=((String)((JComboBox)arg0.getSource()).getSelectedItem());
							}
						});
						midpane.add(select);						
						select.setBounds(100, 10, 100, 30);
						name = new JTextField();
						name.setBounds(200,70,80,30);name.setToolTipText("enter a string");
						midpane.add(name);
						year = new JTextField();
						year.setBounds(200,110,80,30);year.setToolTipText("enter an integer");
						midpane.add(year);
						customyear1 = new JTextField();
						customyear1.setBounds(140,150,80,30);customyear1.setToolTipText("enter an integer");
						midpane.add(customyear1);
						customyear2 = new JTextField();
						customyear2.setBounds(260,150,80,30);customyear2.setToolTipText("enter an integer");
						midpane.add(customyear2);
						JLabel namel = new JLabel("Name/Title Tag");
						namel.setBounds(20,70,100,30);
						midpane.add(namel);
						JLabel yearl = new JLabel("Since year");
						yearl.setBounds(20,110,100,30);
						midpane.add(yearl);
						JLabel cyearl = new JLabel("Custom Range");
						cyearl.setBounds(20,150,100,30);
						midpane.add(cyearl);
						ButtonGroup bng=new ButtonGroup();
						rdbtnSortByYear = new JRadioButton("Sort by year");
						rdbtnSortByYear.setBounds(30,200,180,30);
						bng.add(rdbtnSortByYear);						
						rdbtnSortByRelevance = new JRadioButton("Sort by Relevance");
						rdbtnSortByRelevance.setBounds(30,240,180,30);
						bng.add(rdbtnSortByRelevance);
						midpane.add(rdbtnSortByYear);
						midpane.add(rdbtnSortByRelevance);
						rdbtnSortByYear.setSelected(true);}
					else if(((String)((JComboBox)arg0.getSource()).getSelectedItem()).equals("Query2")){
						if(midpane.getComponentCount()>0){
							
							midpane.removeAll();
							midpane.revalidate();
				            midpane.repaint();				
						}
						else{
							midpane.setBounds(1,70,query_pane.getWidth()-11,330);
						}
						selected="Query2";
						
						pubno = new JTextField();pubno.setToolTipText("enter an integer");
						pubno.setBounds(200,70,80,30);
						midpane.add(pubno);
						year=null;customyear1=null;customyear2=null;
						JLabel namel = new JLabel("No. publications");
						namel.setBounds(20,70,100,30);
						midpane.add(namel);
						midpane.revalidate();
			            midpane.repaint();}
					else if(((String)((JComboBox)arg0.getSource()).getSelectedItem()).equals("Query3")){
						if(midpane.getComponentCount()>0){
							
							midpane.removeAll();
							midpane.revalidate();
				            midpane.repaint();							
						}
						else{
							midpane.setBounds(1,70,query_pane.getWidth()-11,330);
						}
						selected="Query3";
						name=null;year=null;customyear1=null;customyear2=null;
						name1 = new JTextField(100);
						name1.setBounds(140,30,200,30);name1.setToolTipText("enter a string");
						midpane.add(name1);
						name2 = new JTextField(100);
						name2.setBounds(140,80,200,30);name2.setToolTipText("enter a string");
						midpane.add(name2);
						name3 = new JTextField(100);
						name3.setBounds(140,130,200,30);name3.setToolTipText("enter a string");
						midpane.add(name3);
						name4 = new JTextField(100);
						name4.setBounds(140,180,200,30);name4.setToolTipText("enter a string");
						midpane.add(name4);
						name5 = new JTextField(100);
						name5.setBounds(140,230,200,30);name5.setToolTipText("enter a string");
						midpane.add(name5);
						
						JLabel lname1 = new JLabel("Author1");
						lname1.setBounds(20,30,100,30);
						midpane.add(lname1);
						JLabel lname2 = new JLabel("Author2");
						lname2.setBounds(20,80,100,30);
						midpane.add(lname2);
						JLabel lname3 = new JLabel("Author3");
						lname3.setBounds(20,130,100,30);
						midpane.add(lname3);
						JLabel lname4 = new JLabel("Author4");
						lname4.setBounds(20,180,100,30);
						midpane.add(lname4);
						JLabel lname5 = new JLabel("Author5");
						lname5.setBounds(20,230,100,30);
						midpane.add(lname5);						
						JLabel yearl = new JLabel("Till Year");
						yearl.setBounds(20,280,100,30);
						midpane.add(yearl);						
						pubno = new JTextField();pubno.setToolTipText("enter an integer");
						pubno.setBounds(200,280,80,30);
						midpane.add(pubno);
			}}}});		
		midpane.setVisible(true);
		query_pane.add(midpane);		
		JButton btnSubmit = new JButton("Submit");
		JButton btnReset = new JButton("Reset");
		btnSubmit.setBounds(30, 400, 100, 30);
		btnSubmit.addActionListener(new ResultController());btnReset.setToolTipText("Click to erase pun");
		btnReset.addActionListener(new ResetController());btnSubmit.setToolTipText("Click to submit query");
		query_pane.add(btnSubmit);
		btnReset.setBounds(200, 400, 100, 30);
		query_pane.add(btnReset);		
		query_pane.setVisible(true);
		setLayout(null);
		heading.setBounds(0, 0,getWidth(), 80);
		query_pane.setBounds(0, heading.getHeight(), 350, getHeight()-heading.getHeight());
//		query_pane.setBorder(blackline);
		total_count.setBounds(130,2,50,20);
		result_pane.add(total_count);
		result_pane.setLayout(null);
		result_pane.setBounds(query_pane.getWidth(), heading.getHeight(), getWidth()-query_pane.getWidth(), query_pane.getHeight());
		result_pane.setBorder(blackline);
		scroll.setBounds(0,30,result_pane.getWidth(),result_pane.getHeight()-160);
		result_pane.add(scroll);
		ans.setEditable(false);
		result_pane.setVisible(true);
		add(heading);
		add(query_pane);
		add(result_pane);
		setDefaultCloseOperation(super.EXIT_ON_CLOSE);
		setVisible(true);
	}}

