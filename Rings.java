import java.util.Scanner;

/**
 * Class to control the rings event.
 * 
 * @author Zak Soryal
 */
public class Rings extends Room {
    private int totalRingScore;
    private int stamina;
    private String[] moves = {"Back Lever", "Iron Cross", "L-sit", "Front Tuck"};
    private String rules = "First, select a move to perform. Then, choose how long \n"
    		+ "you want to hold it. The longer you hold, the more points \n"
    		+ "you'll get! Watch your stamina, though; the event is over once you\n"
    		+ "run out.\n";

    /**
     * Constructor method for new Rings room.
     * totalRingScore starts at 0, and stamina 100.
     */
    public Rings() {
    	totalRingScore = 0;
    	stamina = 100;
    }

    /**
     * Method to choose a move from the private
     * String[] moves
     * @return
     */
    public int chooseMove() {
    	// print moves
    	System.out.println("Pick a move! Enter its corresponding number.");
    	int i = 1;
        for (String move : moves) {
            System.out.println(i + ". " + move);
            i++;
        }

        Scanner s = new Scanner(System.in);
        int choice = -1;

        // keep getting input until valid
        while (choice < 1 || choice > moves.length) {
            try {
                System.out.print("Enter your choice (1-4): ");
                choice = s.nextInt();
                if (choice < 1 || choice > moves.length) {
                    System.out.println("Invalid choice. Please select a number between 1 and " + moves.length + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and " + moves.length + ".");
                s.next(); // Clear invalid input
            }
        }

        System.out.println("You chose: " + moves[choice - 1]);
        return choice - 1; // return index of the chosen move
    }

    /**
     * Method to perform the player-selected move.
     * @param int move
     * @param Player player
     */
    public void performMove(int move, Player player) {}

    /**
     * Override of the abstract startRoom() method
     * from the Room class. Shows the Rings rules,
     * and continually allows the player to select and
     * perform moves until they're out of stamina.
     */
    @Override
    public void startRoom(Player player) {
        System.out.println(rules); // Show specific rules for Rings
        //while (stamina > 0) {
            int moveIndex = chooseMove();
            //performMove(moveIndex, player);
        //}
            
        //System.out.println("You're out of stamina. Event over! Your total score: " + totalRingScore);
    }
}