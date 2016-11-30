
public class authorState implements tagState {
	public publication tagFunction(String value, publication pub) {

		pub.addAuthor(new author(value));
		return pub;
	}
}
