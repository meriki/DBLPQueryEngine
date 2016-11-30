public class journalState implements tagState {
	public publication tagFunction(String value, publication pub) {
		pub.setJournal(value);
		return pub;
	}
}
