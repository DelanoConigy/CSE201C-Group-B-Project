/*
* Purpose: this calls reperesent the vault event in a game. Players can perform flips of varying difficylt,
earning points based on their performance. The class handles event instrucations, scoring, and logic
for flip success or failure using a dice role. 
* @Author Adash Bhattarai
*/

import java.util.Random;
import java.util.Scanner;

public class Vault extends Room {
    private Player player;
    private String roomDescription;
    private String instructions;
    private String instructions2;
    private int flipCount;
    private int maxFlipCount;
    private Random random;
    private int totalPoints;

    // Array of possible flips for the event
    private String[] flips = {"Easy Flip", "Medium Flip", "Hard Flip"};
    /**
     * Constructor for the Vault class.
     * Initializes the room with a description, instructions for the player,
     * and settings for the number of flips, maximum points, and random number generation.
     * Key Features:
     * Room Description: Provides an overview of the Vault event.
     * Instructions: Explains how to perform flips and the required dice rolls.
     * Game Settings: Limits the number of flips and initializes total points to 0.
     * Random Dice Rolls: Generates random values to determine success for flips.
     */
    public Vault() {
        super(); // Inherited constructor to initialize the score for the room
        this.roomDescription = "Welcome to the Vault event! You will attempt a series of flips, each with varying difficulty levels.";
        this.instructions = "[1] To perform the Easy Flip, [2] To perform the Medium Flip, [3] To perform the Hard Flip, or [Q] to quit.";
        this.instructions2 = "The flips vary in difficulty. Easy requires a roll of 3 or higher, Medium 5 or higher, and Hard requires a 6.";
        this.flipCount = 0;
        this.maxFlipCount = 3; // Limit the number of flips to 5
        this.random = new Random();
        this.totalPoints = 0;
    }

    /**
     * the dice roll for the flip.
     * @return A random number between 1 and 6
     */
    private int rollDice() {
        return random.nextInt(12) + 1;
    }

    /**
     * Perform a flip based on user selection and update points.
     * @param flipChoice The type of flip chosen (1, 2, or 3)
     */
    private void performFlip(int flipChoice) {
        int requiredRoll = 0;
        int pointsEarned = 0;

        switch (flipChoice) {
            case 1:
                requiredRoll = 3;
                pointsEarned = 5;
                System.out.println("You chose the Easy Flip.");
                break;
            case 2:
                requiredRoll = 6;
                pointsEarned =8;
                System.out.println("You chose the Medium Flip.");
                break;
            case 3:
                requiredRoll = 9;
                pointsEarned = 12;
                System.out.println("You chose the Hard Flip.");
                break;
            default:
                System.out.println("Invalid choice, defaulting to Easy Flip.");
                requiredRoll = 12;
                pointsEarned = 5;
                break;
        }

        // Roll the dice
        int diceRoll = rollDice();
        System.out.println("You rolled a " + diceRoll);

        // Check if the flip was successful
        if (diceRoll >= requiredRoll) {
            System.out.println("Congratulations! You landed the flip and earned " + pointsEarned + " points.");
            this.totalPoints += pointsEarned;
        } else {
            int halfPoints = pointsEarned / 2; // Earn half points if flip fails
            System.out.println("Unfortunately, you didn't land the flip. You earned " + halfPoints + " points instead.");
            this.totalPoints += halfPoints;
        }
        
        if(this.totalPoints > 25 ) {
        	this.totalPoints = 25;
        }

        System.out.println("Your total points are now: " + this.totalPoints);
    }

    /**
     * Prints the instructions and handles user input for performing flips.
     * @param scan Scanner object to take player input
     */
    private void playGame(Scanner scan) {
        System.out.println(this.roomDescription);
        System.out.println(this.instructions);
        System.out.println(this.instructions2);

        // Handle player input
        String playerChoice = scan.next();

        switch (playerChoice) {
            case "1":
            case "2":
            case "3":
                performFlip(Integer.parseInt(playerChoice)); // Perform the flip based on user's choice
                this.flipCount++; // Increment flip count after each action
                break;
            case "Q":
                System.out.println("You chose to quit the Vault event.");
                this.flipCount = this.maxFlipCount; // End the game early if the player chooses to quit
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    /**
     * This is the main method for the Vault event, which runs the event.
     * @param player The player who is participating in the Vault event
     */
    @Override
    public void startRoom(Player player) {
        this.player = player;

    
        Scanner scan = new Scanner(System.in);

        // Game loop to handle player actions
        while (this.flipCount < this.maxFlipCount) {
            playGame(scan);
        }

        // Update the player's points after the event
        this.player.setPoints(this.player.getPoints() + this.totalPoints);
        System.out.println("Thank you for playing the Vault Event!");
        System.out.println("Total points earned in this room: " + this.totalPoints);
        System.out.println("Total points across all rooms: " + player.getPoints());
    }
}
