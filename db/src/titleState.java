
public class titleState implements tagState{
	public publication tagFunction(String value, publication pub) {
//		System.out.println("Match encountered");
		pub = new publication();
		pub.setTitle(value);
//		System.out.println("End of fn");
		return pub;
	}
}
