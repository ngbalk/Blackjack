import java.util.Arrays;
import java.util.List;

public class Card {
	private int val;
	private String type;
	public Card(String type){
		List<String> numbers = Arrays.asList("2","3","4","5","6","7","8","9","10");
		List<String> face = Arrays.asList("J","Q","K");
		if(numbers.contains(type)){
			this.val=Integer.parseInt(type);
			this.type=type;
		}
		if(face.contains(type)){
			this.val=10;
			this.type=type;
		}
		if(type=="A"){
			this.val=11;
			this.type=type;
		}
	}
	public String getType() {
		return type;
	}
	public int getVal() {
		return val;
	}
}
