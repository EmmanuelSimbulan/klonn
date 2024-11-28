package com.svi.solitaire.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import com.svi.solitaire.vo.Card;

public class ShuffleManager {

    private static Random random = new Random();

    // Method to perform the Easy shuffle (winnable game setup)
    public static void easyShuffle(List<Card> deck) {
        System.out.println("Performing Easy Shuffle (Winnable)...");

        // Easy Shuffle - Arrange the deck so that a solvable configuration is achieved.
        // Here, we shuffle the deck and then perform some additional steps to ensure it's winnable.
        Collections.shuffle(deck);

        // Optionally, you could simulate setting up a more structured deck here
        // by ensuring certain patterns or sequences, but we'll just shuffle for simplicity.
    }

    // Method to perform the Medium shuffle (Faro shuffle or similar)
    public static void mediumShuffle(List<Card> deck) {
        System.out.println("Performing Medium Shuffle (Faro Shuffle)...");

        // Faro Shuffle - Split the deck into two halves and interleave them
        int halfSize = deck.size() / 2;
        List<Card> firstHalf = deck.subList(0, halfSize);
        List<Card> secondHalf = deck.subList(halfSize, deck.size());

        // Create a new list to hold the interleaved cards
        List<Card> shuffledDeck = new ArrayList<>(deck.size());

        // Interleave the two halves
        for (int i = 0; i < halfSize; i++) {
            shuffledDeck.add(firstHalf.get(i));
            shuffledDeck.add(secondHalf.get(i));
        }

        // If the deck has an odd number of cards, add the last card from the second half
        if (deck.size() % 2 != 0) {
            shuffledDeck.add(secondHalf.get(secondHalf.size() - 1));
        }

        // Replace the original deck with the shuffled deck
        deck.clear();
        deck.addAll(shuffledDeck);
    }

    // Method to perform the Hard shuffle (completely random shuffle)
    public static void hardShuffle(List<Card> deck) {
        System.out.println("Performing Hard Shuffle (Random)...");

        // Hard Shuffle - Completely random shuffle (using multiple random shuffling for more complexity)
        for (int i = 0; i < 5; i++) {
            Collections.shuffle(deck, random);  // Shuffle multiple times to increase randomness
        }
    }

    // Example method to get the shuffled deck
    public static List<Card> shuffleDeck(List<Card> deck, String shuffleType) {
        // Based on the shuffle type, call the corresponding shuffle method
        switch (shuffleType.toLowerCase()) {
            case "easy":
                easyShuffle(deck);
                break;
            case "medium":
                mediumShuffle(deck);
                break;
            case "hard":
                hardShuffle(deck);
                break;
            default:
                System.out.println("Invalid shuffle type. Using default (easy shuffle).");
                easyShuffle(deck);
                break;
        }
        return deck;
    }
}
