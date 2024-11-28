package com.svi.solitaire.main;

//Entry point

import java.util.Scanner;

import com.svi.solitaire.resources.GameInfo;
import com.svi.solitaire.utilities.GameMenu;

public class Main {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		try {
		GameInfo about = new GameInfo(userInput);
        GameMenu menu = new GameMenu(userInput, about); // Pass AboutMenu instance to MainMenu
        menu.start(); // Start the main menu loop
		} finally {
        userInput.close();
		}
	}

}
