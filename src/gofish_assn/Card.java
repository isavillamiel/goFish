package gofish_assn;

/**
 *  Represents a card that will go into a Deck and will be used by the players.
 *  @author Isabelle Villamiel, Desiree Tang
 *  @version 1.0
 *
 * */
public class Card {
	
	public enum Suits {club, diamond, heart, spade};
	
	static int TOP_RANK = 13; //King
	static int LOW_RANK = 1; //Ace
	
	private int rank;  //1 is an Ace
	private Suits suit;
	
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
	    this.suit = toSuit(s);
	}
	
	public Card(int r, Suits s) {
		rank = r;
		suit = s;
	}
	
	private Suits toSuit(char c) {
	    Suits result;

        if(c == 's'){
           result = Suits.spade;
        }
        else if(c == 'c'){
            result = Suits.club;
        }
        else if(c == 'd'){
            result = Suits.diamond;
        }
        else{
            result = Suits.heart;
        }

        return result;
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
		
		s = s + rankToString(getRank()) + " of " + suitToString(getSuit()) + "s";
		
		return s;
	}
}
