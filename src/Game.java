
public class Game {

	public static void main(String[] args) {
		Dealer dealer = new Dealer();
		dealer.addPlayer(new Player("Nick"));
		dealer.play();
	}

}
