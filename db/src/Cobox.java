import javax.swing.JComboBox;

public class Cobox extends JComboBox{
	public Cobox(){
		super();
		this.addItem("Query1");
		this.addItem("Query2"); 
		this.addItem("Query3");
		this.setBounds(100,30,100,30);
		this.setSelectedItem(null);
	}
}

