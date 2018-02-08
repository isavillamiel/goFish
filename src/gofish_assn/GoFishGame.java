package gofish_assn;

import java.io.FileWriter;
import java.io.IOException;

/**
 *  Represents the entire game of GoFish with two players.
 *  @author Isabelle Villamiel, Desiree Tang
 *  @version 1.0
 *
 * */
public class GoFishGame {
    private String output = "";
    private Deck d = new Deck();

    private Player p1 = new Player("Desiree");
    private Player p2 = new Player("Isabelle");


    public GoFishGame() {
        FileWriter out = null;
        dealCards();
        printPlayerHands();
        while ((p1.getBookSize() + p2.getBookSize()) < 52) {
            player1Turn();
            printPlayerHands();
            printPlayerBooks();
            player2Turn();
            printPlayerHands();
            printPlayerBooks();

            if(!d.isEmpty() && p1.getHandSize() ==0 && p2.getHandSize() == 0){
                Card draw1 = d.dealCard();
                p1.addCardToHand(draw1);
                if(p1.checkHandForBook()){
                    p1.removeCardFromHand(draw1);
                    p1.removeCardFromHand(draw1);
                }

                Card draw2 = d.dealCard();
                p2.addCardToHand(draw2);
                if(p2.checkHandForBook()){
                    p2.removeCardFromHand(draw2);
                    p2.removeCardFromHand(draw2);
                }
            }
        }
        GameOver();
        try{
            out = new FileWriter("output.txt");
            out.write(output);
            out.close();
        }catch(IOException e){
            System.err.println("Caught IOException: "+ e.getMessage());
        }

    }

    /**
     * deals 7 cards to each player in the game
     */
    private void dealCards(){
        d.shuffle();
        for (int i = 0; i < 7; i++) {
            Card c1 = d.dealCard();
            p1.addCardToHand(c1);
            Card c2 = d.dealCard();
            p2.addCardToHand(c2);
        }
    }
    // helper function for debugging
    private void printPlayerHands(){
        System.out.println(p1.getName() + "'s Hand: ");
        System.out.println(p1.handToString());
        System.out.println(p2.getName() + "'s Hand: ");
        System.out.println(p2.handToString());
    }
    // helper function for debugging
    private void printPlayerBooks(){
        System.out.println(p1.getName() + "'s Book: ");
        System.out.println(p1.bookToString());
        System.out.println(p2.getName() + "'s Book: ");
        System.out.println(p2.bookToString());
    }
    private void GameOver(){
        String winner = "";
        String loser = "";
        String last_message = "Thanks for playing Go Fish!";

        // end of game: must have a total of 26 pairs in both books
        if (p1.getBookSize() > p2.getBookSize()) {
            winner += p1.getName() + " wins with " + p1.getBookSize()/2 + " booked pairs.";
            loser += p2.getName() + " has " + p2.getBookSize()/2 + " booked pairs.";

        }
        else if (p1.getBookSize() < p2.getBookSize()) {
            winner = p2.getName() + " wins with " + p2.getBookSize()/2 + " booked pairs.";
            loser = p1.getName() + " has " + p1.getBookSize()/2 + " booked pairs.";

        }
        else if (p1.getBookSize() == p2.getBookSize()) {
            String tie = "Time game!";
            output += "\n" +tie;
            winner += p1.getName() + " has " + p1.getBookSize()/2 + " booked pairs";
            loser += p2.getName() + " has " + p2.getBookSize()/2 + " booked pairs.";
        }
        output += "\n" + winner + "\n" + loser + "\n" + last_message;
        System.out.println(output);

        printPlayerHands();
        printPlayerBooks();

    }
    private void player2Turn(){
        String p2Turn = "";
        String p1Response = "";

        if(p1.getHandSize() >=1 && d.isEmpty()){
            Card chosen2 = p2.chooseCardFromHand();
            p2Turn += p2.getName() + " asks: Do you have a " + chosen2.getRank() + "?";
            if (p2.rankInHand(chosen2)) {
                p1Response += "\n" + p1.getName() + " says: Yes. I do have a " + chosen2.getRank();
                Card removed1 = p1.removeCardFromHand(chosen2);
                p2.addCardToHand(removed1);
                if(p2.checkHandForBook()){
                    p2.removeCardFromHand(chosen2);
                    p2.removeCardFromHand(chosen2);
                }
                p1Response += "\n" + p2.getName() + " books the " + chosen2.getRank();
            }
            else{
                p1Response += p1.getName() + " says: Go Fish";
                p1Response += "\n" + "No more cards in deck. Continue to next player's turn."+ "\n";
            }
        }

        else if (p2.getHandSize() == 0 && !d.isEmpty()) {
            Card draw2 = d.dealCard();
            p2.addCardToHand(draw2);
            if(p2.checkHandForBook()){
                p2.removeCardFromHand(draw2);
                p2.removeCardFromHand(draw2);
            }
        }
        else if (p2.getHandSize() >= 1 && !d.isEmpty()) {

            Card chosen2 = p2.chooseCardFromHand();
            p2Turn += p2.getName() + " asks: Do you have a " + chosen2.getRank() + "?";

            if (p1.rankInHand(chosen2)) {
                p1Response += p1.getName() + " says: Yes. I do have a " + chosen2.getRank();
                Card removed2 = p1.removeCardFromHand(chosen2);
                p2.addCardToHand(removed2);
                if(p2.checkHandForBook()){
                    p2.removeCardFromHand(chosen2);
                    p2.removeCardFromHand(chosen2);
                }
                p1Response += "\n" + p2.getName() + " books the " + chosen2.getRank();

            } else {
                p1Response += p1.getName() + " says: Go Fish";
                Card nextCard = d.dealCard();
                p1Response += "\n" + p2.getName() + " draws " + nextCard.toString();
                p2.addCardToHand(nextCard);

                if(p2.checkHandForBook()){
                    p2.removeCardFromHand(nextCard);
                    p2.removeCardFromHand(nextCard);
                }
            }
        }
        output += "\n" + p2Turn + "\n" + p1Response;
        System.out.println(p2Turn + "\n" + p1Response + "\n");
    }
    private void player1Turn(){
        String p1Turn = "";
        String p2Response = "";

        if(p1.getHandSize() >=1 && d.isEmpty()){
            Card chosen1 = p1.chooseCardFromHand();
            p1Turn += p1.getName() + " asks: Do you have a " + chosen1.getRank() + "?";
            if (p2.rankInHand(chosen1)) {
                p2Response += "\n" + p2.getName() + " says: Yes. I do have a " + chosen1.getRank();
                Card removed1 = p2.removeCardFromHand(chosen1);
                p1.addCardToHand(removed1);
                if(p1.checkHandForBook()){

                    p1.removeCardFromHand(removed1);
                    p1.removeCardFromHand(removed1);
                }
                p2Response += "\n" + p1.getName() + " books the " + chosen1.getRank();
            }
            else{
                p2Response += p2.getName() + " says: Go Fish";
                p2Response += "\n" + "No more cards in deck. Continue to next player's turn."+ "\n";
            }
        }

        else if (p1.getHandSize() == 0 && !d.isEmpty()) { // hand is empty so draw card
            Card draw1 = d.dealCard();
            p1.addCardToHand(draw1);
        }

        else if (p1.getHandSize() >= 1 && !d.isEmpty()) {
            Card chosen1 = p1.chooseCardFromHand();
            p1Turn += p1.getName() + " asks: Do you have a " + chosen1.getRank() + "?";

            if (p2.rankInHand(chosen1)) {
                p2Response += "\n" + p2.getName() + " says: Yes. I do have a " + chosen1.getRank();
                Card removed1 = p2.removeCardFromHand(chosen1);
                p1.addCardToHand(removed1);
                if(p1.checkHandForBook()){
                    p1.removeCardFromHand(removed1);
                    p1.removeCardFromHand(removed1);
                }
                p2Response += "\n" + p1.getName() + " books the " + chosen1.getRank();
            } else {
                p2Response += p2.getName() + " says: Go Fish";
                    Card nextCard = d.dealCard();
                    p2Response += "\n" + p1.getName() + " draws " + nextCard.toString();
                    p1.addCardToHand(nextCard);
                    if(p1.checkHandForBook()){
                        p1.removeCardFromHand(nextCard);
                        p1.removeCardFromHand(nextCard);
                    }
            }
            output += "\n" + p1Turn + "\n" + p2Response;
            System.out.println(p1Turn + "\n" + p2Response + "\n");
        }
    }
}
