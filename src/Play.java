/**********
 * Author: Jawuan Lewis
 * Personal Project: Hangman (Console App)
 * Description: Main file to run for playing Hangman in the console. Player can choose between
 *              4 different themes for words: sports, movies, video games, and fun phrases.
 **********/

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Play
{
    public static void main(String[] args)
    {
        char input;
        String line;
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println("-----HANGMAN-----\n");

            do {
                displayMenu();
                line = scan.next();
                input = Character.toUpperCase(line.charAt(0));

                if (line.length() == 1) {
                    switch (input) {
                        case 'A':
                            Helper.playRound("sports.txt", randomNum(), "sport");
                            break;
                        case 'B':
                            Helper.playRound("movies.txt", randomNum(), "movie");
                            break;
                        case 'C':
                            Helper.playRound("games.txt", randomNum(), "video game");
                            break;
                        case 'D':
                            Helper.playRound("phrases.txt", randomNum(), "phrase");
                            break;
                        case 'Q':
                            // quit game
                            System.out.println("\nGoodbye, thank you for playing!");
                            break;
                        default:
                            System.out.print("\nUnknown action\n");
                    }
                } else {
                    System.out.print("\nUnknown action\n");
                }
            } while (input != 'Q');
        }
        catch (FileNotFoundException exception) {
            System.out.print("The file was not found\n");
        }
        catch (IOException exception) {
            System.out.print("IO Exception\n");
        }
    }

    /**
     * Display the opening menu for a round of Hangman.
     */
    private static void displayMenu()
    {
        System.out.print("""
            A)\tSports
            B)\tMovies
            C)\tVideo Games
            D)\tFun Phrases
            Q)\tQuit
            \nChoose a level!
        """);
    }

    /**
     * Generate random number to use in selecting a random word.
     * @return - random positive number under 20
     */
    private static int randomNum()
    {
        Random rand = new Random();
        return rand.nextInt(20);
    }
}
