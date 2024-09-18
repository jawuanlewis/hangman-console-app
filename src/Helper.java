/**********
 * File: Helper.java
 * Description: Helper functions for playing each round of the game.
 **********/

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Helper
{
    /**
     * Randomly choose a word for the user to guess in a round of Hangman.
     * @param filename - (String) input file containing words to choose from
     * @param random - (int) determines which word to randomly select
     * @return - (String) random word from the input file
     * @throws IOException - file-reading issues
     */
    public static String getRandomWord(String filename, int random) throws IOException
    {
        // file-reading tools
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        for (int i = 0; i < random; i++) {
            br.readLine();
        }
        return br.readLine();
    }

    /**
     * Create displays for a word's letter slots that the user must attempt to guess correctly.
     * @param word - (String) the word to create a display for in a round of Hangman
     * @param showAnswer - (boolean) determines whether to create empty word or the final result
     * @return - (String) either a string of empty slots, or a string displaying the word
     */
    public static String createWordDisplay(String word, boolean showAnswer)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (showAnswer) {
                // create a string that reveals the word
                result.append(curr).append(" ");
            } else {
                // create empty letter slots for the user to guess
                if (curr == ' ') {
                    result.append("  ");
                } else {
                    result.append("_ ");
                }
            }
        }
        return result.toString();
    }

    /**
     * Set up a round of Hangman and prompt the user to guess letters in the given mystery word.
     * @param filename - (String) input file containing words to choose from
     * @param random - (int) determines which word to randomly select
     * @param type - (String) the type/category of word
     * @throws IOException - probably file-reading issues
     */
    public static void guess(String filename, int random, String type) throws IOException
    {
        int numGuesses = 6;
        String word = getRandomWord(filename, random);
        String tempWord = createWordDisplay(word, false);
        String fullWord = createWordDisplay(word, true);
        StringBuilder newWord = new StringBuilder(tempWord);    // update the word as user plays
        Scanner scan = new Scanner(System.in);

        // allow user to guess letters until all letters have been found,
        // or until all guesses have been used up
        while (tempWord.contains("_") && numGuesses > 0) {
            System.out.printf("\n%s\nYou have %d guesses remaining.\nMake a guess: ", tempWord, numGuesses);

            String in = scan.nextLine();
            while (in.length() != 1 || !Character.isLetter(in.charAt(0))) {
                System.out.print("Please enter a single letter: ");
                in = scan.nextLine();
            }
            char guess = Character.toUpperCase(in.charAt(0));

            // if user guesses a correct letter, it will appear in the string
            for (int i = 0; i < word.length(); i++) {
                if (guess == word.charAt(i)) {
                    newWord.setCharAt(i*2, guess);
                }
            }
            // lose guesses if no new letters found
            if (tempWord.contentEquals(newWord)) {
                numGuesses--;
            } else {
                tempWord = newWord.toString();
            }
        }
        // print win or lose message
        if (numGuesses == 0) {
            System.out.printf("\nSorry! You have run out of guesses.\nThe %s is: %s\n\n", type, fullWord);
        } else {
            System.out.printf("\nCongratulations! You have correctly guessed the %s.\n%s\n\n", type, tempWord);
        }
    }
}
