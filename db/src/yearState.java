public class yearState implements tagState{
	public publication tagFunction(String value, publication pub) {
		pub.setYear(Integer.parseInt(value));
		return pub;
	}
}
