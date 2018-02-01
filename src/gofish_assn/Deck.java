package gofish_assn;

import java.util.ArrayList;

import gofish_assn.Card.Suits;
import java.util.Random;

public class Deck {
	ArrayList<Card> deck = new ArrayList<Card> ();
	final int NUM_CARDS = 52;  //for this kind of deck
	
	//creates a new sorted deck
	public Deck() {

	}
	
	public void shuffle() {
		
	}

	/**
	 * prints out all the Cards in a Deck to the console
	 */
	public void printDeck() {
		for(int i=0;i<deck.size();i++){
			System.out.println(deck.get(i));
		}
	}
	
	
	public Card dealCard() {
		
		Card c = new Card();
		
		return c;
		
	}
	

}
