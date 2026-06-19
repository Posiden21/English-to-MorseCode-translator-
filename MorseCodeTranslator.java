/*
 * Name: Alex Alvarez
 * Course: 605.201 Introduction to Programming Using Java
 * Assignment: Assignment 5 - Morse Code Translator
 * References:
 *   - Assignment5.pdf, "Morse code" assignment prompt.
 *   - Oracle Java Documentation, Scanner class:
 *     https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html
 *   - Oracle Java Documentation, String class:
 *     https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html
 */

import java.util.Scanner;

public class Assignment5 {
    private static final int ENGLISH_INDEX = 0;
    private static final int MORSE_INDEX = 1;

    private static final String[][] MORSE_TABLE = {
        {"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."}, {"E", "."},
        {"F", "..-."}, {"G", "--."}, {"H", "...."}, {"I", ".."}, {"J", ".---"},
        {"K", "-.-"}, {"L", ".-.."}, {"M", "--"}, {"N", "-."}, {"O", "---"},
        {"P", ".--."}, {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
        {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"}, {"Y", "-.--"},
        {"Z", "--.."}, {"1", ".----"}, {"2", "..---"}, {"3", "...--"},
        {"4", "....-"}, {"5", "....."}, {"6", "-...."}, {"7", "--..."},
        {"8", "---.."}, {"9", "----."}, {"0", "-----"}
    };

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        displayMenu();
        String translationChoice = keyboard.nextLine().trim();

        if (translationChoice.equals("1")) {
            System.out.print("Enter English text: ");
            String englishText = keyboard.nextLine();
            System.out.println("Morse code: " + translateEnglishToMorse(englishText));
        } else if (translationChoice.equals("2")) {
            System.out.print("Enter Morse code: ");
            String morseCode = keyboard.nextLine();
            System.out.println("English text: " + translateMorseToEnglish(morseCode));
        } else {
            System.out.println("Invalid selection. Please run the program again.");
        }

        keyboard.close();
    }

    private static void displayMenu() {
        System.out.println("Morse Code Translator");
        System.out.println("1. Translate English to Morse code");
        System.out.println("2. Translate Morse code to English");
        System.out.print("Enter your choice: ");
    }

    private static String translateEnglishToMorse(String englishText) {
        StringBuilder translatedText = new StringBuilder();

        for (int index = 0; index < englishText.length(); index++) {
            char currentCharacter = Character.toUpperCase(englishText.charAt(index));

            if (currentCharacter == ' ') {
                appendWithSpacing(translatedText, "|");
            } else if (Character.isLetterOrDigit(currentCharacter)) {
                String morseCode = findByEnglishCharacter(String.valueOf(currentCharacter));
                appendWithSpacing(translatedText, morseCode);
            }
        }

        return translatedText.toString();
    }

    private static String translateMorseToEnglish(String morseCode) {
        StringBuilder translatedText = new StringBuilder();
        String[] morseWords = morseCode.trim().split("\\s*\\|\\s*");

        for (int wordIndex = 0; wordIndex < morseWords.length; wordIndex++) {
            String[] morseCharacters = morseWords[wordIndex].trim().split("\\s+");

            for (String morseCharacter : morseCharacters) {
                if (!morseCharacter.isBlank()) {
                    translatedText.append(findByMorseCode(morseCharacter));
                }
            }

            if (wordIndex < morseWords.length - 1) {
                translatedText.append(" ");
            }
        }

        return translatedText.toString();
    }

    private static String findByEnglishCharacter(String englishCharacter) {
        for (String[] morseEntry : MORSE_TABLE) {
            if (morseEntry[ENGLISH_INDEX].equals(englishCharacter)) {
                return morseEntry[MORSE_INDEX];
            }
        }

        return "";
    }

    private static String findByMorseCode(String morseCode) {
        for (String[] morseEntry : MORSE_TABLE) {
            if (morseEntry[MORSE_INDEX].equals(morseCode)) {
                return morseEntry[ENGLISH_INDEX];
            }
        }

        return "";
    }

    private static void appendWithSpacing(StringBuilder text, String value) {
        if (!value.isEmpty()) {
            if (text.length() > 0) {
                text.append(" ");
            }

            text.append(value);
        }
    }
}
