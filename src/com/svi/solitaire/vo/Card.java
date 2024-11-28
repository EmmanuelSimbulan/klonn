package com.svi.solitaire.vo;

public class Card {
    private Rank rank;
    private Suit suit;
    private boolean faceUp; // Flag to indicate if the card is face-up or face-down

    // Constructor to initialize the card with a rank, suit, and initial face-up state
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.faceUp = false; // Cards are face-down by default
    }

    // Getter method for the rank
    public Rank getRank() {
        return rank;
    }

    // Getter method for the suit
    public Suit getSuit() {
        return suit;
    }

    // Getter for face-up state
    public boolean isFaceUp() {
        return faceUp;
    }

    // Method to flip the card (toggle between face-up and face-down)
    public void flip() {
        this.faceUp = !this.faceUp;
    }

    // Returns a string representation of the card, considering whether it is face-up or face-down
    @Override
    public String toString() {
        if (faceUp) {
            return rank + " of " + suit;
        } else {
            return "[Face Down]"; // You could display any representation for face-down cards
        }
    }
}
