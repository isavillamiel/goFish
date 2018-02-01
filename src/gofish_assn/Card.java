package gofish_assn;

public class Card {
	
	public enum Suits {club, diamond, heart, spade};
	
	static int TOP_RANK = 13; //King
	static int LOW_RANK = 1; //Ace
	
	int rank;  //1 is an Ace
	Suits suit;
	
	public Card() {
		rank = 1;
		suit = Suits.spade;
	}
	
	public Card(int r, char s) {
		rank = r;
		if(s == 's'){
			suit = Suits.spade;
		}
		if(s == 'c'){
			suit = Suits.club;
		}
		if(s == 'd'){
			suit = Suits.diamond;
		}
		if(s == 'h'){
			suit = Suits.heart;
		}
	}
	
	public Card(int r, Suits s) {

	}
	
	private Suits toSuit(char c) {
		return Suits.spade; //dummy
	}
	
	private String suitToString(Suits s)
	{
		return "s"; //dummy
	}
	
	private String rankToString(int r)
	{
		return "A"; //dummy
	}
		
	
	public int getRank() {
		return rank;
	}
	
	public Suits getSuit() {
		return suit;
	}
	
	public String toString() {
		String s = "";
		
		s = s + rankToString(getRank()) + suitToString(getSuit());
		
		return s;
	}
}
