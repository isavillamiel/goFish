package gofish_assn;



public class Main {
	
	public static void main(String args[]) {
		System.out.println("Hello World!");


		Card c = new Card(5, Card.Suits.club);
		Card c2 = new Card(4,'d');

		System.out.println("Two cards made");

		System.out.println(c.rank + " " + c.suit);

		System.out.println();
		System.out.println(c.toString());
			
		Deck d = new Deck();
		System.out.println("Deck below");
		
		d.printDeck();
		System.out.println();
		
		d.shuffle();
		
		System.out.println();
		System.out.println("Shuffled deck below");
		d.printDeck();

		
		c = d.dealCard();
		System.out.println(c.toString());

        c = d.dealCard();
        System.out.println(c.toString());
		
		Player p1 = new Player("Jane");
		System.out.println(p1.getName());
		
		
	}

}
