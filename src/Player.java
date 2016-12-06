import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private List<Card> hand;
	private boolean stay;
	
	public Player(String name){
		this.name=name;
		flush();
	}

	
	protected void flush(){
		hand = new ArrayList<Card>();
		stay = false;

	}
	
	public void addCard(Card card){
		
		hand.add(card);

	}
	
	public List<Card> getHand() {
		return hand;
	}
	
	public void setStay() {
		this.stay = true;
	}
	public boolean isStay() {
		return stay;
	}
	
	public String getName() {
		return name;
	}

	public String printHand(){
		StringBuilder sb = new StringBuilder();
		for(Card s : hand){
			sb.append(s.getType() + " ");
		}
		return sb.toString();
	}
	
	
}
