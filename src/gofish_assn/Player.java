package gofish_assn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *  Represents the players in the game and their cards in hands and books.
 *  @author Isabelle Villamiel, Desiree Tang
 *  @version 1.0
 *
 * */
public class Player {
	
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> book = new ArrayList<Card>();
	private String name;
	
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

    /**
     *  This function will check a Player's hand for a pair.
     *  If 1 pair is found, it moves the cards to the book
     *  and returns true.
     * */
    public boolean checkHandForBook() {
	    Iterator<Card> it = hand.iterator();
	    Card card1 = new Card();
	    Card card2 = new Card();
	    while(it.hasNext()){
	        card1 = it.next();
            Iterator<Card> itPlusOne = it;
            while(itPlusOne.hasNext()){
                card2 = itPlusOne.next();
                if(card1.getRank()==card2.getRank()){
                    book.add(card1);
                    book.add(card2);
                    removeCardFromHand(card1);
                    removeCardFromHand(card2);
                    return true;

                }
            }
        }
    	return false;
    }

    /**
     *  This function returns whether or not a card's rank
     *  is in the Player's hand.
     *  @param c's rank is the one being compared with the hand.
     * */
    public boolean rankInHand(Card c) {
        Iterator<Card> it = hand.iterator();
        Card card1;
        while(it.hasNext()){
            card1 = it.next();
            if(card1.getRank()==c.getRank()){
                return true;
            }
        }
    	return false;
    }
    /**
	 * Uses some strategy to choose one card from the player's
	 * hand so they can say "Do you have a 4?"
	 * Strategy: get the card from the beginning of Player's hand
	 * @return that CardFromHand
	 */

    public Card chooseCardFromHand() {
		Card CardFromHand = new Card();
		if(hand.size()!=0){
			int rand = (int)(Math.floor(Math.random()*hand.size()));
			CardFromHand = hand.get(rand);
    		hand.remove(rand);
		}
		return CardFromHand;
	}
    
    //Does the player have the card c in her hand?

	/**
	 * Checks whether or not Card c is in the Player's hand
	 * @param c is the card in question
	 * @return true if Card c is in the Player's hand and false if not
	 */
    public boolean cardInHand(Card c) {
		Iterator<Card> it = hand.iterator();
		Card ind;
		while(it.hasNext()){
			ind = it.next();
			if(ind == c) {
				return true;
			}
		}
    	return false;
    }
    

    //OPTIONAL
    // comment out if you decide to not use it    
    //Does the player have a card with the same rank as c in her hand?
    //e.g. will return true if the player has a 7d and the parameter is 7c
    
    public boolean sameRankInHand(Card c) {
    	return false; //stubbed
    }

}
