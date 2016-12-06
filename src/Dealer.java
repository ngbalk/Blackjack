import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dealer extends Player{
	private List<Player> players;
	
	public Dealer(){
		super("Dealer");
		players = new ArrayList<Player>();
	}

	public void play(){
		this.flush();
		this.addCard(Deck.pull());
		for(Player p : players){
			p.flush();
			p.addCard(Deck.pull());
		}
		
		while(true){
			System.out.println("Dealer: "+this.printHand());
			while(!allPlayersStaying()){
				for(Player p : players){
					if(p.isStay()){
						continue;
					}
					System.out.println(p.getName()+": "+p.printHand());
					promptPlayerForAction(p);
					if(isBlackjack(p)){
						System.out.println("Blackjack!");
						System.out.println(p.getName()+": "+p.printHand());
						return;
					}
					if(isBust(p)){
						System.out.println("You busted!");
						System.out.println(p.getName()+": "+p.printHand());
						return;
					}
				}
			}

			
			while(isHit()){
				this.addCard(Deck.pull());
				System.out.println("Dealer: "+this.printHand());
				if(isBlackjack(this)){
					System.out.print("Dealer has blackjack");
					return;
				}
				if(isBust(this)){
					System.out.println("Dealer has busted");
					return;
				}
			}
			
			int dealerScore = calculateNonBlackjackBustScore(this);
			for(Player p : players){
				if(calculateNonBlackjackBustScore(p)>dealerScore){
					System.out.println(p.getName()+" wins");
				}
				if(calculateNonBlackjackBustScore(p)<dealerScore){
					System.out.println(p.getName()+" loses");
				}
				if(calculateNonBlackjackBustScore(p)==dealerScore){
					System.out.println(p.getName()+" pushes");
				}
				return;
			}
			
		}

	}
	
	private int calculateNonBlackjackBustScore(Player player) {
		int total = 0;
		for(Card c : player.getHand()){
			if(c.getType().equals("A")){
				if(total+11<22){
					total+=11;
				}
				else{
					total+=1;
				}
			}
			else{
				total+=c.getVal();
			}
		}
		return total;
	}

	public boolean isHit(){
		int total = 0;
		for(Card c : this.getHand()){
			if(c.getType().equals("A")){
				total+=1;
			}
			else{
				total+=c.getVal();
			}
		}
		for(Card c : this.getHand()){
			if(c.getType().equals("A")){
				if(total+10>17 && total+10<=21){
					return false;
				}
				return true;
			}
		}
		if(total<17){
			return true;
		}
		return false;
	}
	
	public void promptPlayerForAction(Player player){
		Scanner reader = new Scanner(System.in);
		System.out.print("H or S: ");
		
		String n = reader.nextLine();
		while(!n.equals("H") && !n.equals("S")){
			System.out.println("Invalid move");
			System.out.print("H or S: ");
			n = reader.nextLine();
		}
		if(n.equals("H")){
			player.addCard(Deck.pull());
		}
		else{
			player.setStay();
		}
	}
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	public boolean isBlackjack(Player player){
		int total = 0;
		for(Card c : player.getHand()){
			if(c.getType().equals("A")){
				total+=1;
			}
			else{
				total+=c.getVal();
			}
		}
		for(Card c : player.getHand()){
			if(c.getType().equals("A")){
				total+=10;
			}
			if(total==21){
				return true;
			}
		}
		return false;
	}
	
	public boolean isBust(Player player){
		int total = 0;
		for(Card c : player.getHand()){
			if(c.getType().equals("A")){
				total+=1;
			}
			else{
				total+=c.getVal();
			}
		}
		return total>21;
	}
	
	private boolean allPlayersStaying(){
		for(Player p : players){
			if(!p.isStay()){
				return false;
			}
		}
		return true;
	}
	
	
}
