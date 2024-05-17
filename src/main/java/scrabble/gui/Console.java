package scrabble.gui;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {
	
	private Console() {
	}

	public static void message(String text) {
		System.out.println(text);
	}

	public static final String SEPARATOR_LINE = "-------------------------------------------------------------";
	private static final Scanner scanner = new Scanner(System.in);
	
	public static void title(String text) {
		separator();
		message(text);
		separator();
	}
	
	public static void separator() {
		message(SEPARATOR_LINE);
	}
	
    public static String askString(String question) {
        Console.message(question);

        String response = null;
        while (response == null) {
            try {
                response = scanner.nextLine();
            } catch (InputMismatchException e) {
                Console.message("Veuillez saisir une valeur");
                scanner.next();
            } catch (NoSuchElementException e) {
                Console.message("Veuillez saisir une valeur");
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
            } catch (InputMismatchException e) {
                Console.message("Veuillez saisir un nombre");
                scanner.next();
            } catch (NoSuchElementException e) {
                Console.message("Veuillez saisir un nombre");
                scanner.next();
            }

            if (response < min || response > max) {
                Console.message("Saisie incorrect veuillez réessayer");
                response = null;
            }
        }


        return response;
    }
}

