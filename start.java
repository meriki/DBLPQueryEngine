public class start()
{
	static public void main(String[] args) throws Exception {
	    String filename = null;

	    for (int i = 0; i < args.length; i++) {
		filename = args[i];
		if (i != args.length - 1) {
		    usage();
		}
	    }

	    if (filename == null) {
		usage();
	    } 
		GUI console=new GUI();
	}
}
