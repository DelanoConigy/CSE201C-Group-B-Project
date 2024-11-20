
import java.util.Scanner;

/**
 * Class to control the rings event.
 *
 * @author Zak Soryal
 */
public class Rings extends Room {

    private int totalRingScore;
    private int stamina;
    private Move[] moves;
    private String rules = "First, select a move to perform. Then, choose how long \n"
            + "you want to hold it. The longer you hold, the more points \n"
            + "you'll get! Watch your stamina, though; the event is over once you\n"
            + "run out.\n";

    /**
     * Constructor method for new Rings room. totalRingScore starts at 0, and
     * stamina 100.
     */
    public Rings() {
        totalRingScore = 0;
        stamina = 100;
        moves = new Move[]{
            new Move("Back Lever", 70, "A challenging static hold."),
            new Move("Iron Cross", 90, "An advanced position on the rings."),
            new Move("L-sit", 50, "A core-strength move."),
            new Move("Front Tuck", 60, "A tuck position facing forward.")
        };
    }

    /**
     * Method to choose a move from the private String[] moves
     *
     * @return
     */
    public int chooseMove() {
        // print moves
        System.out.println("Pick a move! Enter its corresponding number.");
        for (int i = 0; i < moves.length; i++) {
            System.out.println((i + 1) + ". " + moves[i].getName() + " (Difficulty: " + moves[i].getDifficulty() + ")");
        }

        Scanner s = new Scanner(System.in);
        int choice = -1;

        // keep getting input until valid
        while (choice < 1 || choice > moves.length) {
            try {
                System.out.print("Enter your choice (1-" + moves.length + "): ");
                choice = s.nextInt();
                if (choice < 1 || choice > moves.length) {
                    System.out.println("Invalid choice. Please select a number between 1 and " + moves.length + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and " + moves.length + ".");
                s.next(); // Clear invalid input
            }
        }

        System.out.println("You chose: " + moves[choice - 1].getName());
        return choice - 1; // return index of the chosen move
    }

    /**
     * Method to end the room.
     */
    public void endRoom() {
        System.out.println("\nUh-oh, you're out of stamina! The Rings event is over.");
        System.out.println("Final Rings score: " + totalRingScore);
    }

    /**
     * Method to perform the player-selected move.
     *
     * @param int move
     * @param Player player
     */
    public void performMove(int move, Player player) {

    }

    /**
     * Method to hold the user-chosen move.
     *
     * @param int time Time in seconds to hold
     * @param Move move The move to hold
     */
    public void holdMove(int time, Move move) {

    }

    /**
     * Override of the abstract startRoom() method from the Room class. Shows
     * the Rings rules, and continually allows the player to select and perform
     * moves until they're out of stamina.
     */
    @Override
    public void startRoom(Player player) {
        System.out.println(rules); // Show specific rules for Rings
        //while (stamina > 0) {
        int moveIndex = chooseMove();
        //performMove(moveIndex, player);
        //}
        endRoom();
    }
}
