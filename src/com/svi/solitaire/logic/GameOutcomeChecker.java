package com.svi.solitaire.logic;

import com.svi.solitaire.vo.FoundationPile;
import com.svi.solitaire.vo.TableauPile;
import com.svi.solitaire.vo.Card;
import com.svi.solitaire.vo.TalonPile;

public class GameOutcomeChecker {

    // Check if the game is won
    public boolean checkWin(FoundationPile[] foundationPiles) {
        // Check if all four foundation piles are complete (Ace to King for each suit)
        for (FoundationPile pile : foundationPiles) {
            if (pile.size() != 13) { // Foundation pile must have 13 cards (Ace to King)
                return false; // Game is not won yet
            }
        }
        return true; // All foundation piles are complete, game is won
    }

    // Check if the game is lost
    public boolean checkLoss(TableauPile[] tableauPiles, TalonPile talon) {
        // Check if there are no valid moves left in the tableau and talon
        boolean noValidMovesLeft = true;

        // Check if any tableau pile has cards that can be moved
        for (TableauPile tableauPile : tableauPiles) {
            if (canMoveCards(tableauPile)) {
                noValidMovesLeft = false;
                break;
            }
        }

        // Check if the talon pile has any cards that can be moved to the tableau or foundation
        if (canMoveFromTalon(talon)) {
            noValidMovesLeft = false;
        }

        return noValidMovesLeft; // If no valid moves are left, game is lost
    }

    // Check if any cards can be moved from the tableau pile (for example, moving a card to a foundation pile)
    private boolean canMoveCards(TableauPile tableauPile) {
        // Check if the top card can be moved to any foundation pile (ascending order, same suit)
        Card topCard = tableauPile.getTopCard();
        if (topCard != null) {
            // Check if it can be moved to any foundation pile (ascending order for each suit)
            for (FoundationPile pile : tableauPile.getFoundationPiles()) {
                if (pile.addCard(topCard)) {
                    return true; // Card can be moved
                }
            }
        }
        return false; // No valid move available for the top card
    }

    // Check if cards can be moved from the talon pile to any tableau or foundation pile
    private boolean canMoveFromTalon(TalonPile talon) {
        Card topCard = talon.getTopCard();
        if (topCard != null) {
            // Check if the top talon card can be moved to any tableau pile or foundation pile
            for (TableauPile tableauPile : talon.getTableauPiles()) {
                if (tableauPile.addCard(topCard)) {
                    return true; // Card can be moved to tableau
                }
            }
            for (FoundationPile foundationPile : talon.getFoundationPiles()) {
                if (foundationPile.addCard(topCard)) {
                    return true; // Card can be moved to foundation
                }
            }
        }
        return false; // No valid move available from the talon pile
    }

    // Provide a status update on the game
    public String getStatusUpdate(FoundationPile[] foundationPiles, TableauPile[] tableauPiles, TalonPile talon) {
        // Check if the game is won
        if (checkWin(foundationPiles)) {
            return "Congratulations! You have won the game!";
        }

        // Check if the game is lost
        if (checkLoss(tableauPiles, talon)) {
            return "Game Over! No valid moves remaining.";
        }

        // Game is still ongoing
        return "The game is still ongoing. Keep going!";
    }
}
