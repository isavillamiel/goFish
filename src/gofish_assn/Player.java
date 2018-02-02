package gofish_assn;

import java.util.ArrayList;
import java.util.Iterator;


public class Player {
	
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> book = new ArrayList<Card>();
	String name;
	
	public Player(String name) {
		this.name = name;
	}
	/**	This function adds a new card onto the Player's hand
	 * @param c is added onto the Player's hand
	 * */
	public void addCardToHand(Card c) {
		hand.add(c);
	}
	/**
	 *  This function removes the card from the Player's hand
	 *  and returns it.
	 * @param c is found and removed from Player's hand
	 * */
	public Card removeCardFromHand(Card c) {
		Iterator<Card> it = hand.iterator();
		Card removeCard = new Card();
		while(it.hasNext()){
				removeCard = it.next();
				if(removeCard==c){
					it.remove();
					break;
				}
			}
		return removeCard;
	}
	
	public String getName() {
		return name;
	}
	/**
     *  This function creates a string
     *  with all the card from the Player's hand
     *  and returns it.
     *  @return s is the string
     * */
	public String handToString() {
		String s = new String();
        Iterator<Card> it = hand.iterator();
        while(it.hasNext()){
            s += it.next().toString() + "\n";
        }
		return s;
	}
    /**
     *  This function creates a string
     *  with all the card from the Player's book
     *  and returns it.
     *  @return s is the string
     * */
	public String bookToString() {
        String s = new String();
        Iterator<Card> it = book.iterator();
        while(it.hasNext()){
            s += it.next().toString() + "\n";
        }
        return s;
	}
	/**
     *  This function returns the size of the Player's hand
     *  using ArrayList's native size function.
     * */
	public int getHandSize() {
        return hand.size();
	}
    /**
     *  This function returns the size of the Player's book
     *  using ArrayList's native size function.
     * */
	public int getBookSize() {
		return book.size();
	}
	
	
	//Here are som ideas for additional functionality that you may want/need
	//OPTIONAL
    // comment out if you decide to not use it
    //this function will check a players hand for a pair. 
    //If a pair is found, it moves the cards to the book and returns true

    public boolean checkHandForBook() {
    	return false; //stubbed
    }

    //OPTIONAL
    // comment out if you decide to not use it    
    //Does the player have a card with the same rank as c in her hand?
    public boolean rankInHand(Card c) {
    	return false; //stubbed
    }
    
    //uses some strategy to choose one card from the player's
    //hand so they can say "Do you have a 4?"
    public Card chooseCardFromHand() {
    	Card c = new Card();
    	
    	return c;
    }
    
    //Does the player have the card c in her hand?
    public boolean cardInHand(Card c) {
    	return false; //stubbed
    }
    

    //OPTIONAL
    // comment out if you decide to not use it    
    //Does the player have a card with the same rank as c in her hand?
    //e.g. will return true if the player has a 7d and the parameter is 7c
    
    public boolean sameRankInHand(Card c) {
    	return false; //stubbed
    }

}
