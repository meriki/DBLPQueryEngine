import javax.swing.SwingUtilities;

public class DBLPQueryEngine {
	private static GUI engine;
	
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
		      public void run() {
		    	  engine=GUI.getInstance();
		      }
		    });
		
	}

}
