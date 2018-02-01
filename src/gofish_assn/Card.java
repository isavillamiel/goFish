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
	/**
     * This
     * @param r is the rank of the Card
     * @param s is the first letter of the Suit of the Card
     * */
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
		rank = r;
		suit = s;
	}
	
	private Suits toSuit(char c) {
		return Suits.spade; //dummy
	}
	
	private String suitToString(Suits s)
	{
		String res = "";
		res = res + s;
		return res;
	}
	
	private String rankToString(int r)
	{
		String res = "";
		res = res + r;
		return res;
	}

	/**
	 * gets the rank of the card
	 * @return the rank of the card
	 */
	public int getRank() {
		return rank;
	}
	
	public Suits getSuit() {
		return suit;
	}
	
	public String toString() {
		String s = "";
		
		s = s + rankToString(getRank()) + " " + suitToString(getSuit());
		
		return s;
	}
}
