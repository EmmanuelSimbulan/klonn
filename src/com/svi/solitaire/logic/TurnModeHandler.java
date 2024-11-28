package com.svi.solitaire.logic;

import com.svi.solitaire.vo.Card;
import com.svi.solitaire.vo.GameState;

import java.util.ArrayDeque;
import java.util.Deque;

public class TurnModeHandler {

    // Enum to represent the draw modes (Turn 1 or Turn 3)
    public enum DrawMode {
        TURN_1, TURN_3
    }

    private DrawMode drawMode;  // Store the current draw mode
    private ArrayDeque<Card> stockPile;  // Reference to the stock pile (talon)
    private GameState gameState;  // Game state to track the current state of the game

    // Constructor to initialize with the game state and stock pile
    public TurnModeHandler(GameState gameState, ArrayDeque<Card> stockPile) {
        this.gameState = gameState;
        this.stockPile = stockPile;
        this.drawMode = DrawMode.TURN_1; // Default to Turn 1 mode
    }

    // Set the draw mode (Turn 1 or Turn 3)
    public void setDrawMode(String mode) {
        switch (mode.toUpperCase()) {
            case "TURN 1":
                this.drawMode = DrawMode.TURN_1;
                System.out.println("Talon draw mode set to Turn 1.");
                break;
            case "TURN 3":
                this.drawMode = DrawMode.TURN_3;
                System.out.println("Talon draw mode set to Turn 3.");
                break;
            default:
                System.out.println("Invalid mode. Defaulting to Turn 1.");
                this.drawMode = DrawMode.TURN_1;
        }
    }

    // Get the current draw mode
    public DrawMode getDrawMode() {
        return drawMode;
    }

    // Method to draw cards from the stock pile (talon) based on the selected mode
    public void drawCard() {
        if (stockPile.isEmpty()) {
            System.out.println("No cards left in the stock pile.");
            return;
        }

        if (drawMode == DrawMode.TURN_1) {
            drawOneCard();
        } else if (drawMode == DrawMode.TURN_3) {
            drawThreeCards();
        }
    }

    // Draw one card from the stock pile (Turn 1 mode)
    private void drawOneCard() {
        Card drawnCard = stockPile.poll(); // Remove and return the top card from stock pile
        if (drawnCard != null) {
            System.out.println("Drawn Card: " + drawnCard);
            // Handle the drawn card (e.g., place in the tableau or foundation)
            // Adjust the game state accordingly
            gameState.addCardToTableau(drawnCard);
        }
    }

    // Draw three cards from the stock pile (Turn 3 mode)
    private void drawThreeCards() {
        if (stockPile.size() < 3) {
            System.out.println("Not enough cards in the stock pile for Turn 3 mode.");
            return;
        }

        // Draw three cards and add them to the tableau
        for (int i = 0; i < 3; i++) {
            Card drawnCard = stockPile.poll(); // Remove and return the top card from stock pile
            if (drawnCard != null) {
                System.out.println("Drawn Card: " + drawnCard);
                gameState.addCardToTableau(drawnCard);  // Adjust game state for each card
            }
        }
    }

    // Reset the stock pile when the player runs out of cards in Turn 1 or Turn 3 mode
    public void resetStockPile() {
        // Convert the talonPile to a List and shuffle it before adding it back to the stockPile
        gameState.resetStockPile(); // The GameState handles the stock reset logic
    }
}
