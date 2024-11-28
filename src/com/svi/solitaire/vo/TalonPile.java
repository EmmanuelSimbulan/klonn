package com.svi.solitaire.vo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class TalonPile {
    // A deque to hold the cards in the talon (stockpile)
    private Deque<Card> talonCards;

    // Constructor: Initializes the talon with an empty deque
    public TalonPile() {
        talonCards = new ArrayDeque<>();
    }

    // Add a card to the talon (for initialization or after a reshuffle)
    public void addCard(Card card) {
        talonCards.push(card); // Add card to the top of the stack
    }

    // Draw a card from the talon (returns null if no cards are left)
    public Card drawCard() {
        if (talonCards.isEmpty()) {
            return null; // No cards left to draw
        }
        return talonCards.pop(); // Remove and return the top card from the talon
    }

    // Peek at the top card of the talon (without removing it)
    public Card getTopCard() {
        return talonCards.peek(); // Returns the top card without removing it
    }

    // Check if the talon is empty (no more cards to draw)
    public boolean isEmpty() {
        return talonCards.isEmpty();
    }

    // Get the number of cards left in the talon
    public int size() {
        return talonCards.size();
    }

    // Shuffle the talon cards (used when reshuffling or restocking)
    public void shuffle() {
        // Add your shuffle logic here, e.g., using Collections.shuffle() or your custom logic
        // For simplicity, using a simple reshuffle technique.
        ArrayList<Card> shuffledList = new ArrayList<>(talonCards);
        Collections.shuffle(shuffledList);
        talonCards.clear();
        talonCards.addAll(shuffledList);
    }

    // Reshuffle the talon if it's empty and we want to reuse the discarded cards
    public void reshuffle(Deque<Card> discardPile) {
        // Move discarded cards to the talon (reshuffling)
        talonCards.addAll(discardPile);
        discardPile.clear();
        shuffle(); // Optional: Shuffle the talon after reshuffling
    }

    // Return a string representation of the talon pile (for debugging or printing)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Talon Pile: ");
        for (Card card : talonCards) {
            sb.append(card.toString()).append(" ");
        }
        return sb.toString();
    }
}
