import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

class NextAction implements ActionListener{   //!<Is the Action Listener class of Next Button
	
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable(){ //!<Defines what needs to be done after next id pressed

			@Override
			public void run() {
				
				
				if(!ResultController.it.hasNext())
					JOptionPane.showMessageDialog(GUI.getInstance(), "No new entries","ERROR",JOptionPane.ERROR_MESSAGE);
				else{
				String a=ResultController.print20Results(ResultController.it,ResultController.counter);
				GUI.getInstance().ans.setText(a);
	
				}
			}
			
		});
		
	}	
}