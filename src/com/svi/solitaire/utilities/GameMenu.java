package com.svi.solitaire.utilities;

//Main menu and play menu logic

import java.util.Scanner;

import com.svi.solitaire.resources.GameInfo;
import com.svi.solitaire.vo.Card;

public class GameMenu {
	private Scanner userInput;
	private GameInfo about;
	
	public GameMenu(Scanner userInput, GameInfo about) {
		this.userInput = userInput;
		this.about = about;
	}
	
	public void start() {
        boolean running = true;
        while (running) {
            int userChoice = displayMainMenu(); // Call the menu input method
            switch (userChoice) {
                case 1:
                    displayPlayMenu(); // Start the game setup
                    break;
                case 2:
                    about.displayAboutMenu(); // Show about information
                    break;
                case 3:
                    running = false; // Exit
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    System.out.println("\nInvalid Option. Please Try Again!");
            }
        }
    }
	
	public int displayMainMenu() {
	    String prompt = "\n============== MAIN MENU ==============\n"
	                    + "(1) PLAY [KLONDIKE SOLITAIRE]\n"
	                    + "(2) ABOUT [KLONDIKE SOLITAIRE]\n"
	                    + "(3) EXIT";
	    return getValidInput(prompt, 1, 3);
	}
	
	public void displayPlayMenu() {
	    boolean inPlayMenu = true;
	    while (inPlayMenu) { // Keep showing Play Menu until user chooses to return to Main Menu
	        String prompt = "\n========== KLONDIKE SOLITAIRE ==========\n"
	                        + "(1) Klondike Turn 1\n"
	                        + "(2) Klondike Turn 3\n"
	                        + "(3) RETURN BACK";
	        int userChoice = getValidInput(prompt, 1, 3);

	        switch (userChoice) {
	            case 1:
	            	displayShuffleMenu(userChoice); // Call Shuffle Menu
	            	// method to set for Turn 1
	                break;
	            case 2:
	                displayShuffleMenu(userChoice); // Call Shuffle Menu
	                // method to set for Turn 3
	                break;
	            case 3:
	                inPlayMenu = false; // Exit Play Menu
	                break;
	        }
	    }
	}
	
	private void displayShuffleMenu(int turnMode) {
	    boolean inShuffleMenu = true;
	    while (inShuffleMenu) { // Keep showing Shuffle Menu until user chooses to return
	        String prompt = "\n============ KLONDIKE TURN " + (turnMode == 1 ? "1" : "3") + " ============\n"
	                        + "(1) Easy Shuffle (Winnable)\n"
	                        + "(2) Medium Shuffle (Faro)\n"
	                        + "(3) Hard Shuffle (Random)\n"
	                        + "(4) RETURN BACK";
	        int userChoice = getValidInput(prompt, 1, 4);

	        switch (userChoice) {
	            case 1:
	                System.out.println("Starting Easy Shuffle...\n");
	                break;
	            case 2:
	                System.out.println("Starting Medium Shuffle...");
	                break;
	            case 3:
	                System.out.println("Starting Hard Shuffle...");
	                break;
	            case 4:
	                inShuffleMenu = false; // Exit Shuffle Menu to return to Play Menu
	                break;
	        }
	    }
	}

	private int getValidInput(String prompt, int min, int max) {
	    int userChoice = -1;
	    boolean validInput = false;

	    while (!validInput) {
	        System.out.println(prompt);
	        System.out.print("\nEnter Key: ");
	        try {
	            userChoice = userInput.nextInt();
	            if (userChoice >= min && userChoice <= max) {
	                validInput = true;  // Valid input within range
	            } else {
	                System.out.println("\nInvalid Option. Please Try Again!");
	            }
	        } catch (Exception e) {
	            System.out.println("\nInvalid Option. Please Try Again!");
	            userInput.nextLine(); // Clear the invalid input from the scanner
	        }
	    }

	    return userChoice;
	}
}
