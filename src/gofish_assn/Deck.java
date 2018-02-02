package gofish_assn;

import java.util.ArrayList;

import gofish_assn.Card.Suits;
import java.util.Random;

public class Deck {
	ArrayList<Card> deck = new ArrayList<Card> ();
	final int NUM_CARDS = 52;  //for this kind of deck
	
	//creates a new sorted deck
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
	
	public void shuffle() {
		for(int i=0;i<deck.size();i++){
			int rand = (int)(Math.random()*i+1);
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
	
	
	public Card dealCard() {
		
		Card c = new Card();
		
		return c;
		
	}
	

}
