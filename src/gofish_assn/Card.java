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
	/**
     * This constructor creates a default card
     * with the value: 1 of Spades
     * */
	public Card() {
		rank = 1;
		suit = Suits.spade;
	}
	/**
     * This constructor creates a specific card.
     * @param rank is the rank of the Card
     * @param suit is the first letter of the Suit of the Card
     * */
	public Card(int rank, char suit) {
		this.rank = rank;
	    this.suit = toSuit(suit);
	}
    /**
     * This constructor creates a specific card.
     * @param rank is the rank of the Card
     * @param suit is the actual Suit
     * */
	public Card(int rank, Suits suit) {
		this.rank = rank;
		this.suit = suit;
	}
    /**
     * Correctly changes the character to a Suit
     * @param c is the first letter of the Suit
     * */
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
	/**
     *  This function changes the Suit to a string
     *  Private scope so only this class can use it.
     *  @param suit is the suit in question
     * */
	private String suitToString(Suits suit)
	{
		String res = "";
		res = res + suit;
		return res;
	}
    /**
     *  This function changes an integer to a String
     *  Private scope so only this class can use it.
     *  @param rank is the number in question
     * */
	private String rankToString(int rank)
	{
		String res = "";
		res = res + rank;
		return res;
	}

	/**
	 * gets the rank of the card
	 * @return the rank of the card
	 */
	public int getRank() {
		return rank;
	}
    /**
     * gets the suit of the card
     * @return the suit of the card
     */
	public Suits getSuit() {
		return suit;
	}
    /**
     *  This function returns a string of the
     *  whole card's name
     * @return name of card with rank and suit
     */
	public String toString() {
		String s = "";
		
		s = s + rankToString(getRank()) + " of " + suitToString(getSuit()) + "s";
		
		return s;
	}
}
