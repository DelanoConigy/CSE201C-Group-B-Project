
import java.util.*;

/**
 * Class to control the Uneven Bars event
 *
 * @author Delano Conigyy
 */
public class UnevenBars extends Room {

    private Player player;
    private String roomDescription;
    private String instructions;
    private String instructions2;
    private int gripStrength;
    private int moveCount;
    private int maxMoveCount;
    private String[] messages = new String[6];
    private ArrayList<Move> moves = new ArrayList<>();

    private ArrayList<Move> beginnerCombo = new ArrayList<>();
    private ArrayList<Move> intermediateCombo = new ArrayList<>();
    private ArrayList<Move> advancedCombo = new ArrayList<>();

    /**
     * A class constructor that will substantiate the room to be playable with
     * different statistics.
     *
     */
    public UnevenBars() {
        super(); // sets the room score to 0
        this.roomDescription = "Welcome to the Uneven Bars event! Throughout this event you will be provided with choices of which move to make. \n"
                + "These moves are dependent on your skill (specifically strength and grip strength) and confidence levels so choose wisely. \n"
                + "Hanging will allow you to increase your grip strength but slighly reduce confidence. "
                + "Failing a move will decrease your confidence level but completing will increase it. \n "
                + "The max points you can earn in this room is 25 points.";

        this.instructions = "[C] To perform a premade combo, [M] To string together you own moves, [H] to hang, [S] to present room score, or [Q] to quit";
        this.instructions2 = "Please choose the following moves, be wary that each move has a chance of failing!"
                + " Enter [Q] to quit";
        this.gripStrength = 0;
        this.moveCount = 0;
        this.maxMoveCount = 10;
    }

    /**
     * Loads certain moves into the the different combo ArrayLists based of
     * difficulty.
     */
    public void loadCombos() {
        beginnerCombo.add(moves.get(0)); // Kip
        beginnerCombo.add(moves.get(1)); // Handstand
        beginnerCombo.add(moves.get(10)); // Circle

        intermediateCombo.add(moves.get(0)); // Kip
        intermediateCombo.add(moves.get(4)); // Shaposh
        intermediateCombo.add(moves.get(2)); // Tkatchev
        intermediateCombo.add(moves.get(5)); // Pak

        advancedCombo.add(moves.get(11)); // Maloney
        advancedCombo.add(moves.get(12)); // Pak
        advancedCombo.add(moves.get(6)); // Gienger
        advancedCombo.add(moves.get(7)); // Full Twist
    }

    /**
     * Creates and loads the possible moves in the an ArrayList. Will be used to
     * present to user.
     */
    public void loadMoves() {

        moves.add(new Move("Kip", 10, "Basic swing to the bar."));
        moves.add(new Move("Handstand", 15, "Hold a handstand on the bar."));
        moves.add(new Move("Tkatchev", 32, "Release and catch over the bar."));
        moves.add(new Move("Jaeger", 37, "Flip forward and catch."));
        moves.add(new Move("Shaposh", 39, "Fly from low to high bar."));
        moves.add(new Move("Pak", 32, "Fly from high to low bar."));
        moves.add(new Move("Gienger", 36, "Flip with a twist and catch."));
        moves.add(new Move("Full Twist", 39, "Dismount with a flip and twist."));
        moves.add(new Move("Front Flip", 40, "Dismount with two front flips."));
        moves.add(new Move("Turn", 20, "Spin on one bar."));
        moves.add(new Move("Circle", 30, "Swing in a straddle."));
        moves.add(new Move("Maloney", 35, "Toe-on and fly to high bar."));
        moves.add(new Move("Pak", 50, "Pak with a toe-on to high bar."));

    }

    public boolean checkMoves() {
        if (this.moveCount >= this.maxMoveCount) {
            return false;
        }
        return true;
    }

    /**
     * A method that determines whether the player has enough of every attribute
     * to perform a move. There is a random element added but is mostly
     * determined by player statistics (including grip).
     *
     * @param move The move that is wanting to return.
     * @return true if move is successfully performed, false otherwise.
     */
    public boolean performMove(Move move) {
        String successMsg = "Successfully Completed Move";
        String failMsg = "Failed Move";
        if (!checkMoves()) {
            System.out.println("You have run out of moves!");
            return false;
        }

        Random random = new Random();
        this.moveCount++; // either option uses a move so can be at the top

        if ((this.player.getStrength() + (this.player.getConfidence() / 2)
                + random.nextInt(this.gripStrength, 21)
                + this.gripStrength) >= move.getDifficulty()) {
            this.setScore((move.getDifficulty() / 4) + this.getScore());
            this.player.setConfidence(player.getConfidence() + 1);
            System.out.println(successMsg);
            return true;
        }
        this.player.setConfidence(this.player.getConfidence() + 5);

        System.out.println(failMsg);
        return false;
    }

    /**
     * Substantiates the messages array.
     */
    private void storeMessages() {
        messages[0] = this.roomDescription;
        messages[1] = this.instructions;
        messages[2] = this.instructions2;
    }

    /**
     * Prints a certain index of Strings in the message array.
     *
     * @param index The index in the messages array that you want to print.
     */
    private void showMessage(int index) {
        System.out.println(messages[index]);
    }

    /**
     * Performs the user requested hang move, this is a special move that is not
     * from the Move class. Takes up a move slot but increased your grip and
     * decreased your confidence. Trade off for user.
     */
    private void hang() {
        this.player.setConfidence(this.player.getConfidence() - 1);
        this.gripStrength += 5;
        // makes sure that gripStrength does not go over its bounds for the random
        // aspect
        if (this.gripStrength > 20) {
            this.gripStrength = 20;
        }
        this.moveCount++;
        System.out.println("Hung on the bar for 1 move, your grip is stronger but not feeling as confident!");
    }

    private void movesLeft() {
        System.out.println("\nYou have " + (this.maxMoveCount - this.moveCount) + " moves left! You have performed "
                + this.moveCount + " moves so far!\n");
    }

    /**
     * Prints out every move apart of a specified list with a nice format.
     *
     * @param movesList The list of moves that you would like to print.
     */
    private void printMoves(ArrayList<Move> movesList) {
        int numberColumnWidth = 10; // Width for "Number"
        int titleColumnWidth = 20; // Width for "Title"
        int descriptionColumnWidth = 40; // Width for "Description"
        int difficultyColumnWidth = 10; // Width for "Difficulty"

        // Print header
        System.out.printf(
                "%-" + numberColumnWidth + "s %-" + titleColumnWidth + "s %-" + descriptionColumnWidth + "s %"
                        + difficultyColumnWidth + "s%n",
                "Number", "Title", "Description", "Difficulty");
        System.out.println(
                "-".repeat(numberColumnWidth + titleColumnWidth + descriptionColumnWidth + difficultyColumnWidth + 3));

        // Print each move
        int i = 1;
        for (Move move : movesList) {
            System.out.printf(
                    "%-" + numberColumnWidth + "d %-" + titleColumnWidth + "s %-" + descriptionColumnWidth + "s %"
                            + difficultyColumnWidth + "d%n",
                    i, move.getName(), move.getDescription(), move.getDifficulty());
            i++;
        }
    }

    /**
     * Attempts to perform the combo based on which input is provided.
     *
     * @param option A user integer value that provides the option for what
     *               combo you want to enter.
     */
    private void performCombo(int option) {
        switch (option) {
            case 1:
                singleCombo(beginnerCombo);
                break;
            case 2:
                singleCombo(intermediateCombo);
                break;
            case 3:
                singleCombo(advancedCombo);
                break;
            default:
                break;
        }
    }

    /**
     * A helper method that performs one of the move lists.
     * 
     * @param movesList A specific combo list
     */
    private void singleCombo(ArrayList<Move> movesList) {
        int numPerformed = 1;
        for (Move move : movesList) {
            if (!(performMove(move))) {
                // for the case that we are out of moves before the combo is done
                if (!checkMoves()) {
                    numPerformed--;
                }
                System.out.println("You performed " + numPerformed + "/" + movesList.size()
                        + " moves in the combo.");
                break;
            }
            numPerformed++;
        }
    }

    /**
     * Prints the different combos (the moves, difficult, etc.) and prompts the user
     * to enter an input.
     */
    private void printCombos() {
        System.out.println("There are three different combos, first is easy, second is medium, and third hard: ");
        System.out.println("Press the related number to attempt the combo (ex: 1, 2, or 3)");
        System.out.println("\nCombo Number 1");
        printMoves(this.beginnerCombo);
        System.out.println("\nCombo Number 2");
        printMoves(this.intermediateCombo);
        System.out.println("\nCombo Number 3");
        printMoves(this.advancedCombo);
        System.out.println("\nNot Interested? Press any other integer to exit");
    }

    /**
     * Prints the current points that have been earned in the room.
     */
    private void printScore() {
        System.out.println("Total points earned in this room: " + this.getScore());
    }

    /**
     * A helper method to handle the player inputs and perform actions based on
     * those inputs.
     *
     * @param scan The Scanner object to take player input.
     */
    private void playGame(Scanner scan) {
        showMessage(1); // Gives the player options on what to do
        String playerChoice = scan.next();
        switch (playerChoice) {
            case "C":
                printCombos();
                performCombo(scan.nextInt());
                movesLeft();
                System.out.println();
                break;
            case "M":
                printMoves(moves);
                showMessage(2);
                performMove(moves.get(scan.nextInt() - 1));
                movesLeft();
                System.out.println();
                break;
            case "H":
                hang();
                movesLeft();
                break;
            case "S":
                printScore();
                System.out.println();
                break;
            case "Q":
                this.moveCount = 10; // a temporary way to quit out of the game
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    /**
     * This is basically the main method of the of room and will run the room.
     * Prompts the user with certain actions that will help them play through
     * the room.
     *
     */
    @Override
    public void startRoom(Player player) {
        // puts the neccarry data into different structures
        loadMoves();
        loadCombos();
        storeMessages();
        this.gripStrength = player.getStrength() / 2; // this is needed because of how the game sets up player

        Scanner scan = new Scanner(System.in);
        this.player = player;

        showMessage(0); // Shows welcome message
        System.out.println("");
        while (this.moveCount != this.maxMoveCount) {
            playGame(scan);
        }

        player.setPoints(player.getPoints() + (int) this.getScore()); // temporary cast
        System.out.println(
                "Thank you for playing the Uneven Bars Event! You earned " + this.getScore() + " in this room!");
    }
}
