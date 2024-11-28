package com.svi.solitaire.vo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.svi.solitaire.logic.TurnModeHandler;

public class GameState {

    // Game zones (tableau, foundation, stock, talon)
    private ArrayDeque<Card>[] tableauPiles;  // 7 tableau piles
    private ArrayDeque<Card>[] foundationPiles; // 4 foundation piles
    private ArrayDeque<Card> stockPile; // Stock pile (face-down)
    private ArrayDeque<Card> talonPile; // Talon pile (face-up)
    
    private TurnModeHandler.DrawMode drawMode; // Current draw mode (Turn 1 or Turn 3)
    private int currentPlayer; // To track which player is playing (useful for multiplayer)
    private boolean isGameOver; // To check if the game has ended

    // Constructor initializes all game zones
    public GameState() {
        tableauPiles = new ArrayDeque[7];
        foundationPiles = new ArrayDeque[4];
        stockPile = new ArrayDeque<>();
        talonPile = new ArrayDeque<>();
        drawMode = TurnModeHandler.DrawMode.TURN_1;  // Default to Turn 1
        currentPlayer = 1;  // Start with Player 1
        isGameOver = false;

        // Initialize the tableau and foundation piles
        for (int i = 0; i < 7; i++) {
            tableauPiles[i] = new ArrayDeque<>();
        }
        for (int i = 0; i < 4; i++) {
            foundationPiles[i] = new ArrayDeque<>();
        }
    }

    // Set the current draw mode (Turn 1 or Turn 3)
    public void setDrawMode(TurnModeHandler.DrawMode mode) {
        this.drawMode = mode;
    }

    // Get the current draw mode
    public TurnModeHandler.DrawMode getDrawMode() {
        return this.drawMode;
    }

    // Get the current player
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    // Set the current player (useful in multiplayer games)
    public void setCurrentPlayer(int player) {
        this.currentPlayer = player;
    }

    // Reset the stock pile (when the player exhausts the stock and wants to reset it)
    public void resetStockPile() {
        // Convert the talonPile (Deque<Card>) to a List<Card> and shuffle it
        List<Card> stockList = new ArrayList<Card>(talonPile); // Specify Card as the type argument
        Collections.shuffle(stockList);  // Shuffle the list
        stockPile.addAll(stockList);  // Add the shuffled cards back to the stockPile
        talonPile.clear();  // Clear the talon pile
        System.out.println("Stock pile has been reset and shuffled.");
    }

    // Add a card to a tableau pile (usually done during game play when a card is moved)
    public void addCardToTableau(Card card, int pileIndex) {
        if (pileIndex < 0 || pileIndex >= tableauPiles.length) {
            System.out.println("Invalid tableau pile index.");
            return;
        }
        tableauPiles[pileIndex].add(card);
        System.out.println("Card " + card + " added to tableau pile " + (pileIndex + 1));
    }

    // Overloaded method for adding to the first tableau pile (or any default logic)
    public void addCardToTableau(Card card) {
        // For simplicity, default to adding to the first tableau pile (index 0)
        addCardToTableau(card, 0);
    }

    // Move a card to a foundation pile
    public void moveToFoundation(Card card, int foundationIndex) {
        if (foundationIndex < 0 || foundationIndex >= foundationPiles.length) {
            System.out.println("Invalid foundation index.");
            return;
        }
        foundationPiles[foundationIndex].add(card);
        System.out.println("Card " + card + " added to foundation pile " + (foundationIndex + 1));
    }

    // Draw a card from the stock pile (to the talon pile)
    public Card drawCardFromStock() {
        if (stockPile.isEmpty()) {
            System.out.println("Stock pile is empty.");
            return null;
        }
        Card card = stockPile.poll();  // Remove the top card from stock pile
        talonPile.push(card);  // Add it to the talon pile (face-up)
        System.out.println("Card " + card + " drawn from stock and added to talon.");
        return card;
    }

    // Get the tableau piles
    public ArrayDeque<Card>[] getTableauPiles() {
        return tableauPiles;
    }

    // Get the foundation piles
    public ArrayDeque<Card>[] getFoundationPiles() {
        return foundationPiles;
    }

    // Get the stock pile
    public ArrayDeque<Card> getStockPile() {
        return stockPile;
    }

    // Get the talon pile
    public ArrayDeque<Card> getTalonPile() {
        return talonPile;
    }

    // Check if the game is over (when all cards are in the foundation piles)
    public boolean isGameOver() {
        return isGameOver;
    }

    // Set the game over status
    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

    // Save the current game state (for a save game feature)
    public void saveGame() {
        // This can be implemented to save the current state of the game to a file
        System.out.println("Game saved!");
    }

    // Reset the game state (to start a new game)
    public void resetGame() {
        // Reset all game zones and settings
        for (int i = 0; i < 7; i++) {
            tableauPiles[i].clear();
        }
        for (int i = 0; i < 4; i++) {
            foundationPiles[i].clear();
        }
        stockPile.clear();
        talonPile.clear();
        drawMode = TurnModeHandler.DrawMode.TURN_1;
        currentPlayer = 1;
        isGameOver = false;
        System.out.println("Game state has been reset.");
    }
}
