public class urlState implements tagState{
	public publication tagFunction(String value, publication pub) {
//		System.out.println(value);
		pub.setUrl(value);
		return pub;
	}
}
