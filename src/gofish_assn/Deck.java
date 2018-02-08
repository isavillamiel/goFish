package gofish_assn;

import java.util.ArrayList;

import gofish_assn.Card.Suits;
import java.util.Random;

/**
 *  Represents the game's deck of cards. One per game.
 *  @author Isabelle Villamiel, Desiree Tang
 *  @version 1.0
 *
 * */
public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card> ();
	final int NUM_CARDS = 52;  //for this kind of deck

	/**
	 * initializes and stores new cards into the deck.
	 * The default order is: 1(s,c,h,d), 2(s,c,h,d), up to 13.
	 */

	public Deck() {
		for(int i = 1; i < 14; i++){
			Card new_Spade = new Card(i,'s');
			deck.add(new_Spade);
			Card new_Club = new Card(i,'c');
			deck.add(new_Club);
			Card new_Heart = new Card(i,'h');
			deck.add(new_Heart);
			Card new_Diamond = new Card(i,'d');
			deck.add(new_Diamond);
		}
	}

	/**
	 * randomizes the deck of Cards
	 */
	public void shuffle() {
		Random rnd = new Random(10000);
		for(int i=0;i<deck.size();i++){
			int rand = rnd.nextInt(52)+1;
			Card temp = deck.get(i);
			deck.set(i,deck.get(rand));
			deck.set(rand,temp);
		}
	}

	/**
	 * prints out all the Cards in a Deck to the console
	 */
	public void printDeck() {
		for(int i=0;i<deck.size();i++){
			System.out.println(deck.get(i));
		}
	}
    public int size (){
	    return deck.size();
    }
	/**
	 * deals a Card to a Player
	 * @return a Card that is dealt from the deck
	 */
	public Card dealCard() {
			Card c = deck.remove(deck.size()-1);
			return c;
	}

	public boolean isEmpty(){
        return deck.isEmpty();
    }
	

}
