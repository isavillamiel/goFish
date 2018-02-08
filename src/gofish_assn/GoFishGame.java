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
            player2Turn();

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
    /**
     * helper function that prints out all the cards in each player's hand
     */
    private void printPlayerHands(){
        System.out.println(p1.getName() + "'s Hand: ");
        System.out.println(p1.handToString());
        System.out.println(p2.getName() + "'s Hand: ");
        System.out.println(p2.handToString());
    }

    /**
     * helper function that prints out all the cards in each player's book
     */
    private void printPlayerBooks(){
        System.out.println(p1.getName() + "'s Book: ");
        System.out.println(p1.bookToString());
        System.out.println(p2.getName() + "'s Book: ");
        System.out.println(p2.bookToString());
    }

    /**
     * prints out who the winner is with how many pairs
     * and the prints out the pairs in each player's book
     */
    private void GameOver(){
        String winner = "";
        String loser = "";
        String last_message = "Thanks for playing Go Fish!\n";

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
            String tie = "\nTie game!";
            output += "\n" +tie;
            winner += p1.getName() + " has " + p1.getBookSize()/2 + " booked pairs";
            loser += p2.getName() + " has " + p2.getBookSize()/2 + " booked pairs.";
        }
        output += "\n" + winner + "\n" + loser + "\n" + last_message;
        System.out.println(output);

        printPlayerHands();
        printPlayerBooks();

    }

    /**
     * player 2's turn to ask a question or draw a card if no cards in hand
     */
    private void player2Turn(){
        String p2Turn = "";
        String p1Response = "";

        if(p1.getHandSize() >=1 && d.isEmpty()){
            Card chosen2 = p2.chooseCardFromHand();
            p2Turn += p2.getName() + " asks: Do you have a " + chosen2.getRank() + "?";
               p1Response = p2.playTurn(p1,chosen2,d);
        }

        else if (p2.getHandSize() == 0 && !d.isEmpty()) { //when you draw a card, your turn is over and goes to next player's turn
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
            p1Response += p2.playTurn(p1,chosen2,d);
        }
        output += "\n" + p2Turn + "\n" + p1Response;
        System.out.println(p2Turn + "\n" + p1Response + "\n");
    }

    /**
     * player 1's turn to ask a question or draw a card if no cards in hand
     */
    private void player1Turn(){
        String p1Turn = "";
        String p2Response = "";

        if(p1.getHandSize() >=1 && d.isEmpty()){
            Card chosen1 = p1.chooseCardFromHand();
            p1Turn += p1.getName() + " asks: Do you have a " + chosen1.getRank() + "?";

            p2Response += p1.playTurn(p2,chosen1,d);

        }
        //when you draw a card, your turn is over and goes to next player's turn
        else if (p1.getHandSize() == 0 && !d.isEmpty()) {
            Card draw1 = d.dealCard();
            p1.addCardToHand(draw1);
        }

        else if (p1.getHandSize() >= 1 && !d.isEmpty()) {
            Card chosen1 = p1.chooseCardFromHand();
            p1Turn += p1.getName() + " asks: Do you have a " + chosen1.getRank() + "?";
            p2Response += p1.playTurn(p2,chosen1,d);

        }
        output += "\n" + p1Turn + "\n" + p2Response;
        System.out.println(p1Turn + "\n" + p2Response + "\n");
    }
}
