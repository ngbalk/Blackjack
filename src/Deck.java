import java.util.Random;

public class Deck {
	private static String[] cards = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

	public static Card pull(){
		Random rand = new Random();
		return new Card(cards[rand.nextInt(13)]);
	}
}
