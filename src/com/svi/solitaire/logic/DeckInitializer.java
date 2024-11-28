package com.svi.solitaire.logic;

import com.svi.solitaire.vo.Card;
import com.svi.solitaire.vo.Rank;
import com.svi.solitaire.vo.Suit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class DeckInitializer {

    private List<Card> deck; // List to hold all 52 cards
    private ArrayDeque<Card>[] tableauPiles; // 7 tableau piles (ArrayDeque for easy addition/removal)
    private ArrayDeque<Card> stockPile; // Stock pile
    private ArrayDeque<Card>[] foundationPiles; // 4 foundation piles (one for each suit)

    // Constructor
    public DeckInitializer() {
        this.deck = new ArrayList<>();
        this.tableauPiles = new ArrayDeque[7]; // Initialize 7 tableau piles
        this.stockPile = new ArrayDeque<>();
        this.foundationPiles = new ArrayDeque[4]; // Initialize 4 foundation piles

        // Initialize tableau piles and foundation piles
        for (int i = 0; i < 7; i++) {
            tableauPiles[i] = new ArrayDeque<Card>(); // Specify the type parameter
        }
        for (int i = 0; i < 4; i++) {
            foundationPiles[i] = new ArrayDeque<Card>(); // Specify the type parameter
        }
    }

    // Initialize the deck of 52 cards
    public void initializeDeck() {
        // Creating 52 cards for the deck
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(rank, suit);
                deck.add(card);
            }
        }
    }

    // Shuffle the deck based on the selected difficulty level
    public void shuffleDeck(String shuffleType) {
        switch (shuffleType) {
            case "Easy":
                ShuffleManager.easyShuffle(deck); // Easy shuffle (winnable)
                break;
            case "Medium":
                ShuffleManager.mediumShuffle(deck); // Medium shuffle (Faro)
                break;
            case "Hard":
                ShuffleManager.hardShuffle(deck); // Hard shuffle (Random)
                break;
            default:
                System.out.println("Invalid shuffle type. Defaulting to Easy shuffle.");
                ShuffleManager.easyShuffle(deck); // Default to Easy shuffle if invalid
                break;
        }
    }

    // Distribute cards to tableau piles and stock pile
    public void distributeCards() {
        int cardIndex = 0;

        // Distribute cards to tableau piles (7 piles)
        for (int i = 0; i < 7; i++) {
            // Each tableau pile gets i+1 cards (first pile gets 1 card, second gets 2, etc.)
            for (int j = 0; j <= i; j++) {
                tableauPiles[i].add(deck.get(cardIndex++));
            }
        }

        // Remaining cards go to the stock pile
        while (cardIndex < deck.size()) {
            stockPile.add(deck.get(cardIndex++));
        }
    }

    // Getters for piles
    public ArrayDeque<Card>[] getTableauPiles() {
        return tableauPiles;
    }

    public ArrayDeque<Card> getStockPile() {
        return stockPile;
    }

    public ArrayDeque<Card>[] getFoundationPiles() {
        return foundationPiles;
    }
}
