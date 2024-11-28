package com.svi.solitaire.logic;

import com.svi.solitaire.vo.Card;
import com.svi.solitaire.vo.TableauPile;
import com.svi.solitaire.vo.FoundationPile;
import com.svi.solitaire.vo.TalonPile;

public class CardMovementHandler {

    /**
     * Validates if a card can be moved from one pile to another.
     * @param card The card being moved.
     * @param fromPile The pile from which the card is being moved.
     * @param toPile The pile to which the card is being moved.
     * @return true if the move is valid, false otherwise.
     */
    public boolean validateMove(Card card, Object fromPile, Object toPile) {
        if (fromPile instanceof TableauPile && toPile instanceof TableauPile) {
            return validateTableauToTableau((TableauPile) fromPile, (TableauPile) toPile);
        } else if (fromPile instanceof TableauPile && toPile instanceof FoundationPile) {
            return validateTableauToFoundation((TableauPile) fromPile, (FoundationPile) toPile, card);
        } else if (fromPile instanceof TalonPile && toPile instanceof TableauPile) {
            return validateTalonToTableau((TalonPile) fromPile, (TableauPile) toPile, card);
        } else if (fromPile instanceof TalonPile && toPile instanceof FoundationPile) {
            return validateTalonToFoundation((TalonPile) fromPile, (FoundationPile) toPile, card);
        }
        return false; // If the move is not valid for any of the cases
    }

    /**
     * Executes the move of a card from one pile to another.
     * @param card The card being moved.
     * @param fromPile The pile from which the card is being moved.
     * @param toPile The pile to which the card is being moved.
     */
    public void executeMove(Card card, Object fromPile, Object toPile) {
        if (validateMove(card, fromPile, toPile)) {
            if (fromPile instanceof TableauPile && toPile instanceof TableauPile) {
                moveTableauToTableau((TableauPile) fromPile, (TableauPile) toPile, card);
            } else if (fromPile instanceof TableauPile && toPile instanceof FoundationPile) {
                moveTableauToFoundation((TableauPile) fromPile, (FoundationPile) toPile, card);
            } else if (fromPile instanceof TalonPile && toPile instanceof TableauPile) {
                moveTalonToTableau((TalonPile) fromPile, (TableauPile) toPile, card);
            } else if (fromPile instanceof TalonPile && toPile instanceof FoundationPile) {
                moveTalonToFoundation((TalonPile) fromPile, (FoundationPile) toPile, card);
            }
        } else {
            System.out.println("Invalid move!");
        }
    }

    // Validation Methods
    private boolean validateTableauToTableau(TableauPile fromPile, TableauPile toPile) {
        if (toPile.isEmpty()) {
            // Only Kings can go into empty tableau piles
            return fromPile.getTopCard().getRank() == Card.Rank.KING;
        }

        Card fromCard = fromPile.getTopCard();
        Card toCard = toPile.getTopCard();

        // Check if the card can be placed on the tableau pile (alternating colors, descending order)
        return isDescendingAndAlternatingColors(fromCard, toCard);
    }

    private boolean validateTableauToFoundation(TableauPile fromPile, FoundationPile toPile, Card card) {
        if (toPile.isEmpty()) {
            // The foundation pile should start with an Ace
            return card.getRank() == Card.Rank.ACE;
        }

        Card topFoundationCard = toPile.getTopCard();
        // Check if the card can be placed on the foundation (ascending order, same suit)
        return card.getSuit() == topFoundationCard.getSuit() &&
                card.getRank().ordinal() == topFoundationCard.getRank().ordinal() + 1;
    }

    private boolean validateTalonToTableau(TalonPile fromPile, TableauPile toPile, Card card) {
        if (toPile.isEmpty()) {
            // Only Kings can go into empty tableau piles
            return card.getRank() == Card.Rank.KING;
        }

        Card topCard = toPile.getTopCard();
        // Check for alternating colors and descending order for tableau
        return isDescendingAndAlternatingColors(card, topCard);
    }

    private boolean validateTalonToFoundation(TalonPile fromPile, FoundationPile toPile, Card card) {
        if (toPile.isEmpty()) {
            // The foundation pile should start with an Ace
            return card.getRank() == Card.Rank.ACE;
        }

        Card topFoundationCard = toPile.getTopCard();
        // Check for ascending order and same suit for foundation
        return card.getSuit() == topFoundationCard.getSuit() &&
                card.getRank().ordinal() == topFoundationCard.getRank().ordinal() + 1;
    }

    // Utility method for checking descending order and alternating colors
    private boolean isDescendingAndAlternatingColors(Card fromCard, Card toCard) {
        boolean isDescending = fromCard.getRank().ordinal() == toCard.getRank().ordinal() - 1;
        boolean isAlternatingColors = (fromCard.getSuit().isRed() && toCard.getSuit().isBlack()) ||
                                      (fromCard.getSuit().isBlack() && toCard.getSuit().isRed());
        return isDescending && isAlternatingColors;
    }

    // Move Methods
    private void moveTableauToTableau(TableauPile fromPile, TableauPile toPile, Card card) {
        fromPile.removeCard(card);
        toPile.addCard(card);
    }

    private void moveTableauToFoundation(TableauPile fromPile, FoundationPile toPile, Card card) {
        fromPile.removeCard(card);
        toPile.addCard(card);
    }

    private void moveTalonToTableau(TalonPile fromPile, TableauPile toPile, Card card) {
        fromPile.removeCard(card);
        toPile.addCard(card);
    }

    private void moveTalonToFoundation(TalonPile fromPile, FoundationPile toPile, Card card) {
        fromPile.removeCard(card);
        toPile.addCard(card);
    }
}
