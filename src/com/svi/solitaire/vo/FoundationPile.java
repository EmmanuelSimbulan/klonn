package com.svi.solitaire.vo;

import java.util.Stack;

public class FoundationPile {

    // The cards in the foundation pile, represented as a stack (only one card can be placed at a time)
    private Stack<Card> cards;
    private Card.Suit suit; // The suit of the foundation pile (e.g., Hearts, Diamonds)

    // Constructor initializes the foundation pile with the given suit
    public FoundationPile(Card.Suit suit) {
        this.cards = new Stack<>();
        this.suit = suit;
    }

    // Adds a card to the foundation pile if the move is valid
    public boolean addCard(Card card) {
        // Check if the card is of the correct suit
        if (card.getSuit() != this.suit) {
            return false; // Invalid move: cards must be of the same suit
        }

        // If the pile is empty, only an Ace can be placed
        if (this.cards.isEmpty()) {
            if (card.getRank() == Card.Rank.ACE) {
                cards.push(card);
                return true;
            }
            return false; // Invalid move: only an Ace can be placed on an empty pile
        }

        // Get the top card of the foundation pile
        Card topCard = this.cards.peek();

        // Check if the card is one rank higher than the top card
        if (card.getRank().ordinal() == topCard.getRank().ordinal() + 1) {
            cards.push(card);
            return true; // Valid move: card placed on top of the stack
        }

        return false; // Invalid move: card does not follow the ascending order
    }

    // Removes and returns the top card of the foundation pile
    public Card removeCard() {
        if (!cards.isEmpty()) {
            return cards.pop();
        }
        return null; // Return null if the pile is empty
    }

    // Returns the top card of the foundation pile
    public Card getTopCard() {
        return cards.isEmpty() ? null : cards.peek();
    }

    // Returns the number of cards in the foundation pile
    public int size() {
        return cards.size();
    }

    // Checks if the foundation pile is empty
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    // Displays the foundation pile as a string (for debugging purposes)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append(" ");
        }
        return sb.toString();
    }

    // Returns the suit of the foundation pile
    public Card.Suit getSuit() {
        return suit;
    }
}
