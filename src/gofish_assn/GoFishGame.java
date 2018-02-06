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

        //Actual game starts here
        checkPlayersBooks();

        //for(int i =0; i< 10; i++){
        while ((p1.getBookSize() + p2.getBookSize()) != 52) {
           checkPlayersBooks();
           player1Turn();
           player2Turn();
           if (d.isEmpty()) {
               do{
                   checkPlayersBooks();
               }while(p1.getBookSize() + p2.getBookSize() != 52);
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
    // initial starting point of game
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

    //needs work
    private void checkPlayersBooks(){
       p1.checkHandForBook();
       p2.checkHandForBook();
    }
    private void GameOver(){
        String winner = "";
        String loser = "";
        String last_message = "Thanks for playing Go Fish!";

        // end of game: must have a total of 26 pairs in both books
        if (p1.getBookSize() > p2.getBookSize()) {
            winner += p1.getName() + " wins with " + p1.getBookSize() + " booked pairs.";
            //System.out.println("These are " + p1.getName() + "'s pairs");
            //System.out.println(p1.bookToString());
            loser += p2.getName() + "has " + p2.getBookSize() + " booked pairs.";
            //System.out.println("These are " + p2.getName() + "'s pairs");
            //System.out.println(p2.bookToString());

        } else if (p1.getBookSize() < p2.getBookSize()) {
            winner = p2.getName() + " wins with " + p2.getBookSize() + " booked pairs.";
            //System.out.println("These are " + p2.getName() + "'s pairs");
            //System.out.println(p2.bookToString());
            loser = p1.getName() + "has " + p1.getBookSize() + " booked pairs.";
           // System.out.println("These are " + p1.getName() + "'s pairs");
           // System.out.println(p1.bookToString());

        } else if (p1.getBookSize() == p2.getBookSize()) {
            String tie = "Time game!";
            output += "\n" +tie;
            //System.out.println("Tie game!");
            winner += p1.getName() + " has " + p1.getBookSize() + " booked pairs";
            //System.out.println("These are " + p1.getName() + "'s pairs");
            //System.out.println(p1.bookToString());

            loser += p2.getName() + "has " + p2.getBookSize() + " booked pairs.";
            //System.out.println("These are " + p2.getName() + "'s pairs");
            //System.out.println(p2.bookToString());

        }
        output += "\n" + winner + "\n" + loser + "\n" + last_message;
    }
    private void player2Turn(){
        checkPlayersBooks();
        String p2Turn = "";
        String p1Response = "";
        if (p2.getHandSize() == 0 && !d.isEmpty()) {
            Card draw2 = d.dealCard();
            p2.addCardToHand(draw2);
        }
        if (p2.getHandSize() != 0 && !d.isEmpty()) {
            // Player 2's Card
            Card chosen2 = p2.chooseCardFromHand();
            p2Turn += p2.getName() + " asks: Do you have a " + chosen2.getRank() + "?";

            if (p1.rankInHand(chosen2)) {
                p1Response += p1.getName() + " says: Yes. I do have a " + chosen2.getRank();
                Card removed2 = p1.removeCardFromHand(chosen2);
                p2.addCardToHand(removed2);
                p2.checkHandForBook();
                p1Response += "\n" + p2.getName() + " books the " + chosen2.getRank();

            } else {
                p1Response += p1.getName() + " says: Go Fish";
                if (d.isEmpty()) {
                    p1Response += "\n" + "No more cards in deck. Continue to next player's turn.";
                } else {
                    Card nextCard = d.dealCard();
                    p1Response += "\n" + p2.getName() + " draws " + nextCard.toString();
                    p2.addCardToHand(nextCard);
                }
            }
        }
        output += "\n" + p2Turn + "\n" + p1Response;
        System.out.println(p2Turn + "\n" + p1Response + "\n");
    }
    private void player1Turn(){
        String p1Turn = "";
        String p2Response = "";
        checkPlayersBooks();

        if (p1.getHandSize() == 0 && !d.isEmpty()) { // hand is empty so draw card
            Card draw1 = d.dealCard();
            p1.addCardToHand(draw1);
        }

        if (p1.getHandSize() != 0 && !d.isEmpty()) {

            // Player 1's turn
            //if deck is empty and hand is empty -> move on to next player
            Card chosen1 = p1.chooseCardFromHand();
            p1Turn += p1.getName() + " asks: Do you have a " + chosen1.getRank() + "?";

            if (p2.rankInHand(chosen1)) {
                p2Response += "\n" + p2.getName() + " says: Yes. I do have a " + chosen1.getRank();
                Card removed1 = p2.removeCardFromHand(chosen1);
                p1.addCardToHand(removed1);
                p1.checkHandForBook();
                p2Response += "\n" + p1.getName() + " books the " + chosen1.getRank();
            } else {
                p2Response += p2.getName() + " says: Go Fish";
                if (d.isEmpty()) {
                    p2Response += "\n" + "No more cards in deck. Continue to next player's turn."+ "\n";
                } else {
                    Card nextCard = d.dealCard();
                    p2Response += "\n" + p1.getName() + " draws " + nextCard.toString();
                    p1.addCardToHand(nextCard);
                }

            }
            output += "\n" + p1Turn + "\n" + p2Response;
            System.out.println(p1Turn + "\n" + p2Response + "\n");
        }
    }
}
