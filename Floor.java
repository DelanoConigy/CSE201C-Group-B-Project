import java.util.Scanner;
import java.io.ArrayList;
/**
 * Class to control the floor room.
 * 
 * @author Dakota Oudeman
 */

public class Floor extends Room {
    /**
     * The total moves left before the end of the room.
     */
    private int movesLeft;

    /**
     * ArrayList of moves that the player can choose from to attempt.
     */
    private ArrayList<Move> skills;


    /**
     * Standard constructor method.
     */
    public Floor() {
        movesLeft = 10;
        skills = loadSkills();
    }

    /**
     * Starts the Floor event. This will have the player play through the
     * entire room before returning them back to the main menu.
     * 
     * @param Player player The player object that contains important attributes.
     */
    public void startRoom(Player player) {
        displayWelcome();
        while (movesLeft > 0) {
            Move selectedMove = selectMove();
            //Something that displays available moves and has user choose which moves
            attemptMove(player, selectedMove);
        }
    }

    /**
     * Welcome message displayed upon entering the room.
     */
    private void displayWelcome(Player p) {
        System.out.print("Welcome to the Floor Room. You will be given 10 chances to make moves.\n"
                        + "Based on your different attributes they will determine whether you\n"
                        + "succesfully complete the move or fail. Successful moves will count\n"
                        + "towards your total score.\n");
    }
    
    /**
     * Will ask the user to select a move from a randomized set of moves
     */
    private Move selectMove() {
        return null;
    }

    /**
     * Helper method to load the skills ArrayList with various moves.
     * 
     * @return ArrayList<Move> A loaded ArrayList of moves.
     */
    private ArrayList<Move> loadSkills() {
        return null;
    }

    /**
     * Attempts a move specified by the player, based on various attributes like
     * player confidence, strength, speed, and balance.
     * 
     * @param Player p Player object that has the player attributes.
     * @param Move m Which move the player will be trying to complete.
     * @return boolean true if skill completed succesfully, false otherwise.
     */
    private boolean attemptMove(Player p, Move m) {
        return false;
    }
}