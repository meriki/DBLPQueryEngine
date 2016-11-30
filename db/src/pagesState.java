
public class pagesState implements tagState{
	public publication tagFunction(String value, publication pub) {
		pub.setPages(value);
		return pub;
	}
}
