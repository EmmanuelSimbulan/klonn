package com.svi.solitaire.vo;

import java.util.ArrayDeque;
import java.util.Deque;

public class TableauPile {

    // The cards in the tableau pile, represented as a Deque for efficient removal and addition
    private Deque<Card> cards;

    // Constructor initializes the tableau pile with an empty deck
    public TableauPile() {
        this.cards = new ArrayDeque<>();
    }

    // Adds a card to the tableau pile
    public void addCard(Card card) {
        cards.push(card);
    }

    // Removes the top card from the tableau pile
    public Card removeCard(Card card) {
        if (!cards.isEmpty() && cards.peek().equals(card)) {
            return cards.pop();
        }
        return null; // Return null if card is not the top card
    }

    // Returns the top card of the tableau pile
    public Card getTopCard() {
        return cards.isEmpty() ? null : cards.peek();
    }

    // Returns the number of cards in the tableau pile
    public int size() {
        return cards.size();
    }

    // Checks if the tableau pile is empty
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    // Displays the tableau pile as a string (for debugging purposes)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append(" ");
        }
        return sb.toString();
    }

    // Checks if the tableau pile can accept a card based on game rules
    public boolean canAcceptCard(Card card) {
        // If the tableau pile is empty, only a King can be placed
        if (this.isEmpty()) {
            return card.getRank() == Card.Rank.KING;
        }

        // Get the top card of the tableau pile
        Card topCard = this.getTopCard();

        // The card must be one rank lower than the top card and of the opposite color
        return isDescendingAndAlternatingColors(card, topCard);
    }

    // Checks if a card can be placed on top of another card (descending order and alternating colors)
    private boolean isDescendingAndAlternatingColors(Card fromCard, Card toCard) {
        boolean isDescending = fromCard.getRank().ordinal() == toCard.getRank().ordinal() - 1;
        boolean isAlternatingColors = (fromCard.getSuit().isRed() && toCard.getSuit().isBlack()) ||
                                      (fromCard.getSuit().isBlack() && toCard.getSuit().isRed());
        return isDescending && isAlternatingColors;
    }
}
