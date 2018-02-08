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
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Card> book = new ArrayList<Card>();
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
	 *  This function removes a card with the same rank from the Player's hand
	 *  and returns it.
	 * @param c is found and removed from Player's hand
	 * */
	public Card removeCardFromHand(Card c) {
		Iterator<Card> it = hand.iterator();
		Card removeCard = new Card();
		while(it.hasNext()){
				removeCard = it.next();
				if(removeCard.getRank()==c.getRank()){
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

		if(hand.size() == 1 || hand.size() == 0){return false;}

		for(int i=0; i<hand.size();i++){
			for(int j=i+1; j<hand.size();j++){
				if(hand.get(i).getRank() == hand.get(j).getRank()){
					book.add(hand.get(i));
					book.add(hand.get(j));
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
    	int rand = (int)(Math.floor(Math.random()*hand.size()));
    	Card CardFromHand = hand.get(rand);
    	//hand.remove(rand);
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

    public int sameRankInHand(Card c,int location) {
    	int finding = c.getRank();
    	int cur_location = location;
    	for(Card it : hand.subList(location,hand.size())){
    		if (it.getRank() == finding){
    			return cur_location;
			}
			cur_location++;
		}
    	return -1;
    }
	public String playTurn(Player playerResponding, Card chosenOne, Deck d) {
    	String result = "";
    	if(playerResponding.rankInHand(chosenOne)){
    		result += playerResponding.getName() + " says: Yes. I do have a " + chosenOne.getRank() + "\n";
    		Card removed = playerResponding.removeCardFromHand(chosenOne);
    		this.addCardToHand(removed);
    		if(this.checkHandForBook()) {
				this.removeCardFromHand(removed);
				// two removes because it only removes 1 at a time
				this.removeCardFromHand(removed);
				}
			result += this.getName() + " books the " + chosenOne.getRank() + "\n";
		}
		else if(!playerResponding.rankInHand(chosenOne) && d.isEmpty()){
    		result += playerResponding.getName() + " says: Go Fish.\n";
    		result += "No more cards in deck. Continue to next player's turn. \n";
		}
		else if(!playerResponding.rankInHand(chosenOne) && !d.isEmpty()){
    		result += playerResponding.getName() + " says: Go Fish.\n";
    		Card nextCard = d.dealCard();
    		result += this.getName() + " draws " + nextCard.toString() + "\n";
    		this.addCardToHand(nextCard);
    		if(this.checkHandForBook()){
    			this.removeCardFromHand(nextCard);
    			this.removeCardFromHand(nextCard);
			}
		}
    	return result;
	}
}
