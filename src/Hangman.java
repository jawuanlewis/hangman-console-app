/**********
 * File: Hangman.java
 * Description: Functionality to create the Hangman display.
 **********/

public class Hangman 
{
    /**
     * Create the corresponding hangman (stick figure), based on the players's remaining lives.
     * @param lives - (Integer) the player's remaining attempts in a round
     * @return - (String) the current stick figure to be displayed
     */
    public static String getHangman(int lives) 
    {
        String hangman;
        switch (lives) {
            case 0:
                hangman = """
                    ________
                    |   |
                    |   O
                    | --|--
                    |  / \\
                    ________
                    """;
                break;
            case 1:
                hangman = """
                    ________
                    |   |
                    |   O
                    | --|--
                    |  /
                    ________
                    """;
                break;
            case 2:
                hangman = """
                    ________
                    |   |
                    |   O
                    | --|--
                    |
                    ________
                    """;
                break;
            case 3:
                hangman = """
                    ________
                    |   |
                    |   O
                    | --|
                    |
                    ________
                    """;
                break;
            case 4:
                hangman = """
                    ________
                    |   |
                    |   O
                    |   |
                    |
                    ________
                    """;
                break;
            case 5:
                hangman = """
                    ________
                    |   |
                    |   O
                    |
                    |
                    ________
                    """;
                break;
            default:
                return "";
        }
        return hangman;
    }
}
