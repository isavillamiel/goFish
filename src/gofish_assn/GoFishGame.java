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

		//Actual game starts here

        while(p1.checkHandForBook()){
            System.out.println(p1.getName() + " books initial pairs");
            System.out.println(p1.getName() + "'s Hand without Pairs: ");
            System.out.println(p1.handToString());

        }

        while(p2.checkHandForBook()){
            System.out.println(p2.getName() + " books initial pairs");
            System.out.println(p2.getName() + "'s Hand without Pairs: ");
            System.out.println(p2.handToString());
        }

        for(int i =0; i< 10; i++){
		//while((p1.getBookSize() + p2.getBookSize() )!= 23){

            // Player 1's turn
			Card chosen1 = p1.chooseCardFromHand();
            System.out.println(p1.getName() + " asks: Do you have a " + chosen1.getRank() + "?");

            if(p2.rankInHand(chosen1)){
                System.out.println(p2.getName()+ " says: Yes. I do have a " + chosen1.getRank());
                Card removed1 = p2.removeCardFromHand(chosen1);
                p1.addCardToHand(removed1);
                p1.checkHandForBook();
                System.out.println(p1.getName() + " books the " + chosen1.getRank());
            }
            else{
                System.out.println(p2.getName() + " says: Go Fish");
                if(d.isEmpty()){
                    System.out.println("No more cards in deck. Continue to next player's turn.");
                }
                else{
                    Card nextCard = d.dealCard();
                    System.out.println(p1.getName() + " draws " + nextCard.toString());
                }

            }

            // Player 2's Card
            Card chosen2 = p2.chooseCardFromHand();
            System.out.println(p2.getName() + " asks: Do you have a " + chosen2.getRank() + "?");

            if(p1.rankInHand(chosen2)){
                System.out.println(p1.getName()+ " says: Yes. I do have a " + chosen2.getRank());
                Card removed2 = p1.removeCardFromHand(chosen2);
                p2.addCardToHand(removed2);
                p2.checkHandForBook();
                System.out.println(p2.getName() + " books the " + chosen2.getRank());
            }
            else{
                System.out.println(p1.getName() + " says: Go Fish");
                if(d.isEmpty()){
                    System.out.println("No more cards in deck. Continue to next player's turn.");
                }
                else{
                    Card nextCard = d.dealCard();
                    System.out.println(p1.getName() + " draws " + nextCard.toString());
                }
            }

		}


	}

}
