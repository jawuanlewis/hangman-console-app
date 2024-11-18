package com.github.jawuanlewis.hangmanconsoleapp;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**********
 * Helper functions for playing each round of the game.
 **********/

public class Helper
{
    private static String getRandomWord(String filename, int random) throws IOException
    {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        for (int i = 0; i < random; i++) {
            br.readLine();
        }
        return br.readLine();
    }

    /**
     * Create displays for a word's letter slots that the player must attempt to guess correctly.
     * @param word (String) the word to create a display for in a round of Hangman
     * @param showAnswer (Boolean) determines whether to create empty word or the final result
     * @return (String) either a string of empty slots, or a string displaying the word
     */
    private static String createWordDisplay(String word, boolean showAnswer)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (showAnswer) {
                result.append(curr).append(" ");
            } else {
                if (curr == ' ') {
                    result.append("  ");
                } else {
                    result.append("_ ");
                }
            }
        }
        return result.toString();
    }

    private static int checkGuesses(String word, String tempWord, int numGuesses) 
    {
        StringBuilder newWord = new StringBuilder(tempWord);    // update the word as player plays
        ArrayList<Character> guessedLetters = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        // go until all letters are found, or until all guesses run out
        while (tempWord.contains("_") && numGuesses > 0) {
            System.out.print("\n" + Hangman.getHangman(numGuesses));
            System.out.printf("\n%s\n\nYou have %d attempt(s) remaining.\nMake a guess: ", tempWord, numGuesses);

            String in = scan.nextLine();
            // ensure the player enters a valid guess
            while (in.length() != 1 || 
                   !Character.isLetter(in.charAt(0)) || 
                   guessedLetters.contains(Character.toUpperCase(in.charAt(0)))) 
            {
                if (in.length() == 1 && guessedLetters.contains(Character.toUpperCase(in.charAt(0)))) {
                    System.out.print("You have already guessed this letter: ");
                } else {
                    System.out.print("Please enter a single letter: ");
                }
                in = scan.nextLine();
            }
            char guess = Character.toUpperCase(in.charAt(0));
            guessedLetters.add(guess);

            // correctly guessed letters get revealed in the string
            for (int i = 0; i < word.length(); i++) {
                if (guess == word.charAt(i)) {
                    newWord.setCharAt(i*2, guess);
                }
            }
            if (tempWord.contentEquals(newWord)) {
                numGuesses--;
            } else {
                tempWord = newWord.toString();
            }
        }
        return numGuesses;
    }

    /**
     * Set up and run a new round of Hangman.
     * @param filename (String) input file containing words to choose from
     * @param random (Integer) determines which word to randomly select
     * @param type (String) the type/category of word
     * @throws IOException probably file-reading issues
     */
    public static void playRound(String filename, int random, String type) throws IOException
    {
        String word = getRandomWord(filename, random);
        String tempWord = createWordDisplay(word, false);
        String fullWord = createWordDisplay(word, true);

        // determine if player wins or loses this round
        int numGuesses = checkGuesses(word, tempWord, 6);
        if (numGuesses == 0) {
            System.out.printf("\n%s\nSorry! You have run out of guesses.\nThe %s is:  %s\n\n", Hangman.getHangman(numGuesses), type, fullWord);
        } else {
            System.out.printf("\nCongratulations! You have correctly guessed the %s.\n%s\n\n", type, fullWord);
        }
    }
}
