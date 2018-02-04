package gofish_assn;

public class GoFishGame {

	public GoFishGame() {

		Deck d = new Deck();
		d.shuffle();

		Player p1 = new Player("Desiree");
		System.out.println("Player One: " + p1.getName());

		Player p2 = new Player("Isabelle");
		System.out.println("Player Two: " + p2.getName());

		for(int i =0; i < 7; i++){
			Card c1 = d.dealCard();
			p1.addCardToHand(c1);
			Card c2 = d.dealCard();
			p2.addCardToHand(c2);
		}

		System.out.println(p1.getName() + "'s Hand: ");
		System.out.println(p1.handToString());
		System.out.println(p2.getName() + "'s Hand: ");
		System.out.println(p2.handToString());

		while((p1.getBookSize() + p2.getBookSize() )!= 23){
			System.out.println("");

		}


	}

}
