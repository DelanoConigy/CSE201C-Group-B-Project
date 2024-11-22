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
    private String rules = "First, select a move to perform. Then, choose how long you want \n"
    		+ "to hold it. The longer you hold, the more points you'll get! \n"
    		+ "Watch your stamina, though; the event is over once you run out. Also, \n"
    		+ "the chance of failing a move increases with every second you hold.\n"
    		+ "Though if your confidence is high, that chance is lowered!";

    /**
     * Constructor method for new Rings room.
     * totalRingScore starts at 0, and stamina 100.
     */
    public Rings() {
    	totalRingScore = 0;
    	stamina = 100;
    	moves = new Move[]{
                new Move("Back Lever", 70, "A challenging static hold parallel to the ground."),
                new Move("Iron Cross", 90, "An extremely challenging static hold in a T-pose position."),
                new Move("L-sit", 50, "A core-strength move with your legs straight forward."),
                new Move("Front Tuck", 60, "A tuck position facing forward."),
                new Move("Planche", 85, "A straight-arm hold parallel to the ground ."),
                new Move("Muscle-Up", 60, "A combination of a pull-up and a dip in one fluid motion."),
                new Move("Human Flag", 90, "An advanced bodyweight hold, resembling a flag on a pole."),
                new Move("Maltese", 95, "An extremely challenging straight-arm hold on the rings.")
    	};
    }

    /**
     * Method to choose a move from the private
     * String[] moves
     * @return
     */
    public Move chooseMove() {
    	// print moves
    	System.out.println("Pick a move! The lower the difficulty, the less stamina it'll use.");
    	System.out.println("Enter its corresponding number:");
    	for (int i = 0; i < moves.length; i++) {
            System.out.println((i + 1) + ". " + moves[i].getName() + " (Difficulty: " + 
            		moves[i].getDifficulty() + ") [Description: " + moves[i].getDescription() + "]");
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

        System.out.println("\nYou chose: " + moves[choice - 1].getName() + "\n");
        return moves[choice - 1]; // return index of the chosen move
    }

    /**
     * Method to perform the player-selected move.
     * @param move The move to perform.
     * @param player The player performing the move.
     */
    public void performMove(Move move, Player player) {
        Scanner s = new Scanner(System.in);

        // Calculate the stamina cost for 1 second of holding the move
        int staminaCostPerSecond = Math.max(1, move.getDifficulty() / 4 - player.getStrength() / 10);  // Minimum cost per second is 1
        int maxHoldTime = stamina / staminaCostPerSecond; // Calculate max hold time based on current stamina

        // Ensure the player can hold the move for at least 1 second
        if (stamina < staminaCostPerSecond) {
            System.out.println("Warning: Your stamina is critically low! This is your last move.");
            staminaCostPerSecond = Math.max(1, stamina); // Reduce cost to a minimum of 1
            maxHoldTime = 1; // Force max hold time to 1 second
        }

        System.out.println("You can hold the " + move.getName() + " for a maximum of " + 
        		maxHoldTime + " seconds based on your current stamina.");
        
        // Ask the user how long they want to hold the move
        System.out.println("How many seconds do you want to hold the " + move.getName() + "?");
        int holdTime = 0;

        while (holdTime <= 0 || holdTime > maxHoldTime) {
            try {
            	if (maxHoldTime > 1) {
            		System.out.print("Enter hold time in seconds (1-" + maxHoldTime + "): ");
            	} else {
            		System.out.println("Enter hold time in seconds (1)");
            	}
                holdTime = s.nextInt();
                if (holdTime <= 0 || holdTime > maxHoldTime) {
                    System.out.println("Please enter a number between 1 and " + maxHoldTime + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number between 1 and " + maxHoldTime + ".");
                s.next(); // Clear invalid input
            }
        }

        // Calculate total stamina cost
        int staminaCost = staminaCostPerSecond * holdTime;

        // Determine chance of failure
        double baseFailureChance = Math.min(holdTime * 0.25, 0.9); 
        double confidenceModifier = 1 - (Math.min(player.getConfidence(), 10) / 200.0); 
        double failureChance = baseFailureChance * confidenceModifier;
        
        boolean success = Math.random() > failureChance;

        // Update stamina and score
        stamina -= staminaCost;

        if (success) {
            int pointsGained = move.getDifficulty() * holdTime;
            totalRingScore += pointsGained;
            System.out.println("\nSuccess! You held the " + move.getName() + " for " + holdTime + " seconds.");
            System.out.println("You gained " + pointsGained + " points! Total score: " + totalRingScore + "\n");
            player.addConfidence(1); 
            if (player.getConfidence() < 10) {
            	System.out.println("Your confidence has increased! Current confidence: " + player.getConfidence() + "\n");
            } else {
            	System.out.println("Your confidence was too high to increase! Current confidence: " + player.getConfidence() + "\n");
            }
            
        } else {
            System.out.println("\nOh no! You failed to hold the " + move.getName() + " after " + holdTime + " seconds.");
            System.out.println("No points gained this time.\n");
            player.addConfidence(-3); 
            System.out.println("Your confidence has decreased! Current confidence: " + player.getConfidence() + "\n");
        }

        // Display remaining stamina
        System.out.println("Remaining stamina: " + stamina);
    }

    /**
     * Override of the abstract startRoom() method
     * from the Room class. Shows the Rings rules,
     * and continually allows the player to select and
     * perform moves until they're out of stamina.
     */
    @Override
    public void startRoom(Player player) {
        System.out.println(rules); // Show specific rules for Rings
        System.out.println("\nNote: Your strength skill level reduces stamina usage for each move in this event!");
        System.out.println("Starting stamina: 100\n");
        while (stamina > 0) {
            Move chosenMove = chooseMove();
            performMove(chosenMove, player);
        }
        
        // Convert Rings score to the overall score (max 25 points)
        int convertedScore = Math.min(25, totalRingScore / 15); // Adjust conversion formula if needed
        player.setPoints(convertedScore); // Update player's overall score

        System.out.println("\nRings event is over! Your total converted score is: " + convertedScore);
        System.out.println();
    }

    public static void main(String[] args) {
        Rings rings = new Rings();
        Player player = new Player("Male", 100, 10, 5, 5);
        rings.startRoom(player);
    }
}
