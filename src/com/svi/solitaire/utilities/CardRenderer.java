package com.svi.solitaire.utilities;

import com.svi.solitaire.vo.Card;

public class CardRenderer {

    // Method to display the score, stock, and passthrus information
    public static void printGameInfo(int stockCount, int passThrus, int numberOfMoves) {
        String score = "INITIAL DECK\n"
                + "MOVES: [" + numberOfMoves + "]\n"
                + "STOCK: [" + stockCount + "]\n"
                + "PASSTHRUS: [" + passThrus + "]\n";
        System.out.println(score);
    }

    // Method to print the Talon and Foundation areas
    public static void printTalonAndFoundation(Card talonCard, Card[] foundation) {
        System.out.print("Talon\t\t\tFoundation\n");
        // Print Talon card
        printCard(talonCard);
        // Print Foundation cards (up to 4 piles)
        for (int i = 0; i < 4; i++) {
            if (i < foundation.length && foundation[i] != null) {
                printCard(foundation[i]);
            } else {
                System.out.print("╭───╮\n│   │\n╰───╯\t");
            }
        }
        System.out.println();
    }

    // Method to print the tableau (cards stacked in each column)
    public static void printTableau(Card[][] tableau) {
        System.out.println("Tableau");
        int maxRows = 0;

        // Determine the maximum number of rows (this ensures all tableau piles are printed)
        for (Card[] pile : tableau) {
            if (pile != null && pile.length > maxRows) {
                maxRows = pile.length;
            }
        }

        // Iterate over each row in the tableau
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < tableau.length; j++) {
                if (i < tableau[j].length) {
                    // Display the current card in the tableau
                    printCard(tableau[j][i]);
                } else {
                    // Print a blank space if the tableau pile is not deep enough for this row
                    System.out.print("      ");
                }
                // Print a tab between each card for separation
                if (j < tableau.length - 1) {
                    System.out.print("\t");
                }
            }
            // Move to the next line after printing a row of tableau cards
            System.out.println();
        }
    }

    // Helper method to display a single card (whether face-up or face-down)
    private static void printCard(Card card) {
        if (card != null) {
            if (card.isFaceUp()) {
                // Face-up card
                System.out.println("╭───╮");
                System.out.printf("│ %s%s │\n", card.getRank(), card.getSuit());
                System.out.println("╰───╯");
            } else {
                // Face-down card
                System.out.println("╭───╮");
                System.out.println("│   │");
                System.out.println("╰───╯");
            }
        }
    }

    // Method to print the full card layout (Talon, Tableau, and Scoreboard)
    public static void printFullCardLayout(Card[][] tableau, Card talonCard, Card[] foundation, int stockCount, int passThrus, int numberOfMoves) {
        // Print game information first
        printGameInfo(stockCount, passThrus, numberOfMoves);

        // Print the Talon and Foundation areas
        printTalonAndFoundation(talonCard, foundation);

        // Print the Tableau
        printTableau(tableau);
    }
}
