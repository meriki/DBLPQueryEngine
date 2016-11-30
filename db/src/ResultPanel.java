import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * ResultPanel is the panel on the rightmost displaying top20 results
 */
class ResultPanel extends JPanel{    //!< Is the result panel to the right
	JButton next;
	JLabel resultCountLabel,resultLabel;
	public ResultPanel(){
		super();
		JLabel n=new JLabel("Total result count: ");
		n.setBounds(0, 2, 120, 20);
		add(n);
		next = new JButton("Next");
		next.setBounds(400, 500, 60, 30);
		next.addActionListener(new NextAction());
		add(next);
	}
}

