
import java.util.ArrayList;

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
     * Starts the Floor event. This will have the player play through the entire
     * room before returning them back to the main menu.
     *
     * @param player The player object that contains important attributes.
     */
    public void startRoom(Player player) {
        displayWelcome(player);
        while (movesLeft > 0) {
            // Player display attributes
            Move selectedMove = selectMove();
            attemptMove(player, selectedMove);
            movesLeft--;
        }
        displayGoodbye(player);
    }

    /**
     * Welcome message displayed upon entering the room.
     *
     * @param p Player object
     */
    private void displayWelcome(Player p) {
        System.out.print("Welcome to the Floor Room. You will be given 10 chances to make moves.\n"
                + "Based on your different attributes they will determine whether you\n"
                + "succesfully complete the move or fail. Successful moves will count\n"
                + "towards your total score.\n");
    }

    /**
     * Goodbye message displayed when leaving the room.
     *
     * @param p Player object
     */
    private void displayGoodbye(Player p) {
        System.out.println("The floor room is now over, goodbye.");
    }

    /**
     * Will ask the user to select a move from a randomized set of moves
     *
     * @return The selected move
     */
    private Move selectMove() {
        return null;
    }

    /**
     * Helper method to load the skills ArrayList with various moves.
     *
     * @return A loaded ArrayList of moves.
     */
    private ArrayList<Move> loadSkills() {
        return null;
    }

    /**
     * Attempts a move specified by the player, based on various attributes like
     * player confidence, strength, speed, and balance.
     *
     * @param p Player object that has the player attributes.
     * @param m Which move the player will be trying to complete.
     * @return true if skill completed successfully, false otherwise.
     */
    private boolean attemptMove(Player p, Move m) {
        return false;
    }
}
