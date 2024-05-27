package scrabble.gui;

import java.util.NoSuchElementException;
import java.util.Scanner;

import scrabble.model.Direction;

public class Console {
	
	public static final String SEPARATOR_LINE = "-------------------------------------------------------------"; 
	
	private static final Scanner scanner = new Scanner(System.in);
	
	private Console() {
	}

	public static void message(String text, boolean withLineReturn) {
	    if (withLineReturn) {
	        System.out.println(text);
	    } else {
	        System.out.print(text);
	    }
	}

	
	
	public static void title(String text) {
		separator();
		message(text, true);
		separator();
	}
	
	public static void space() {
		message(" ", false);
	}
	
	public static void separator() {
		message(SEPARATOR_LINE, true);
	}
	
    public static String askString(String question) {
        Console.message(question, true);

        String response = null;
        while (response == null) {
            try {
                response = scanner.nextLine();
            } catch (NoSuchElementException e) {
                Console.message("Veuillez saisir une valeur", true);
                scanner.next();
            } 
        }
        
        return response;
        
    }
    public static Integer askInt(Integer min, Integer max) {
        Integer response = null;
        while (response == null) {
            try {
                response = scanner.nextInt();
                scanner.nextLine();
            } catch (NoSuchElementException e) {
                Console.message("Veuillez saisir un nombre", true);
                scanner.next();
            } 

            if (response < min || response > max) {
                Console.message("Saisie incorrect veuillez réessayer", true);
                response = null;
            }
        }
        return response;
    }
    
    public static char askJokerLetter() {
        Console.message("Vous avez joué un joker. Veuillez saisir la lettre que vous souhaitez attribuer au joker:", true);
        String input;
        do {
            input = Console.askString("Lettre pour le joker: ").toUpperCase();
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                return input.charAt(0);
            } else {
                Console.message("Veuillez saisir une seule lettre valide.", true);
            }
        } while (true);
    }
    
    public static Direction askDirection() {
        int askDirection = 0;

    	
    	Console.message("Veuillez saisir la direction du mot, horizontal[1] ou vertical[2]", false);
    	askDirection = Console.askInt(1,2);
    	if(askDirection == 1 ) {
    		return Direction.HORIZONTAL;
    	}
   	return Direction.VERTICAL;
    	
    	
    }

	public static void lineReturn() {
		Console.message("", true);
	}
}

