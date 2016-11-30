public class volumeState implements tagState{
	public publication tagFunction(String value, publication pub) {
		pub.setVolume(value);
		return pub;
	}
}
