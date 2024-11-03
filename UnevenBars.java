import java.util.*;

/**
 * Class to control the Uneven Bars event
 * 
 * @author Delano Conigy
 */

public class UnevenBars extends Room {

	private Player player;
	private String roomDescription;
	private String instructions;
	private String instructions2;
	private String moveChoice;
	private int gripStrength;
	private int moveCount;
	private int maxMoveCount;
	private String[] messages = new String[6];
	private ArrayList<Move> moves = new ArrayList<>();

	private ArrayList<Move> combo1 = new ArrayList<>();
	private ArrayList<Move> combo2 = new ArrayList<>();
	private ArrayList<Move> combo3 = new ArrayList<>();

	/**
	 * A class constructor that will substantiate the room to be playable with
	 * different statistics.
	 * 
	 */
	public UnevenBars() {
		super(); // sets the room score to 0
		this.roomDescription = "Welcome to the Uneven Bars event! Throughout this event you will be provided with choices of which move to make. \n"
				+ "These moves are dependent on your skill (specifically strength) and confidence levels so choose wisely. \n"
				+ "The landing moves are more so based off your balance level. \n"
				+ "Hanging will allow you to increase your grip strength but slighly reduce confidence. "
				+ "Failing a move will decrease your confidence level but completing will increase it";

		this.instructions = "[C] To perform a premade combo, [M] To string together you own moves, [H] to hang, [S] to present room score, or [Q] to quit";
		this.instructions2 = "Please choose the following moves, be wary that each move has a chance of failing!"
				+ "enter [Q] to quit";
		this.gripStrength = 0;
		this.moveCount = 0;
		this.maxMoveCount = 10;
	}

	/**
	 * Will load certain moves into the the different combo ArrayLists.
	 */
	public void loadCombos() {
		// TODO
	}

	/**
	 * Will create and load the possible moves in the an Map. Will be used to
	 * present to user.
	 */
	public void loadMoves() {
		moves.add(new Move("Jump up", 0, "Jump on the bar"));
		moves.add(new Move("Swing Around", 20, "Swing around the bar one time"));
		moves.add(new Move("Swing Forward", 25, "Swing forward to the next bar"));

	}

	/**
	 * A method that determines whether the player has enough of every attribute to
	 * perform a move. There is a random element added but is mostly determined by
	 * player statistics (including grip).
	 * 
	 * @param move The move that is wanting to return.
	 * @return true if move is successfully performed, false otherwise.
	 */
	public boolean performMove(Move move) {
		String successMsg = "Successfully Completed Move";
		String failMsg = "Failed Move";
		Random random = new Random();
		this.moveCount++; // either option uses a move so can be at the top

		if ((this.player.getStrength() + (this.player.getConfidence() / 2) + random.nextInt(0, 11)
				+ this.gripStrength) >= move.getDifficulty()) {
			this.setScore(move.getDifficulty() + this.getScore());
			this.player.setConfidence(player.getConfidence() + 2);
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
		this.moveCount++;
	}

	/**
	 * Prints out every move apart of a specified list with a nice format.
	 * 
	 * @param movesList The list of moves that you would like to print.
	 */
	private void printMoves(ArrayList<Move> movesList) {
		int titleColumnWidth = 20;
		int descriptionColumnWidth = 40;
		int difficultyColumnWidth = 10;
		System.out.printf(
				"%-" + titleColumnWidth + "s %" + descriptionColumnWidth + "s %" + difficultyColumnWidth + "s%n",
				"Number", "Title", "Description", "Difficulty");
		System.out.println("-".repeat(titleColumnWidth + descriptionColumnWidth + difficultyColumnWidth + 2));
		int i = 1;
		for (Move move : movesList) {
			System.out.printf(
					"%-" + titleColumnWidth + "s %" + titleColumnWidth + "s %" + descriptionColumnWidth + "s %"
							+ difficultyColumnWidth + "d%n",
					i, move.getName(), move.getDescription(), move.getDifficulty());
			i++;
		}
	}

	/**
	 * Attempts to perform the combo based on which input is provided.
	 * 
	 * @param option A user integer value that provides the option for what combo
	 *               you want to enter.
	 */
	private void performCombo(int option) {
		switch (option) {
		case 1:
			for (Move move : combo1) {
				if (!(performMove(move))) {
					break;
				}
			}
			break;
		case 2:
			for (Move move : combo2) {
				if (!(performMove(move))) {
					break;
				}
			}
			break;
		case 3:
			for (Move move : combo3) {
				if (!(performMove(move))) {
					break;
				}
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Prints the different combos and prompts the user to enter an input.
	 */
	private void printCombos() {
		System.out.println("There are three different combos, first is easy, second is medium, and third hard: ");
		System.out.println("Press the related number to attempt the combo (ex: 1, 2, or 3)");
		System.out.println("Combo Number 1");
		printMoves(combo1);
		System.out.println("Combo Number 2");
		printMoves(combo2);
		System.out.println("Combo Number 3");
		printMoves(combo3);
		System.out.println("\n Not Interested? Press any other integer to exit");
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
		showMessage(1); // Give the player options on what to do
		String playerChoice = scan.next();
		switch (playerChoice) {
		case "C":
			printCombos();
			performCombo(scan.nextInt());
			System.out.println();
			break;
		case "M":
			printMoves(moves);
			showMessage(2);
			performMove(moves.get(scan.nextInt()));
			System.out.println();
			break;
		case "H":
			hang();
			System.out.println();
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
	 * Prompts the user with certain actions that will help them play through the
	 * room.
	 * 
	 */
	@Override
	public void startRoom(Player player) {
		loadMoves();
		storeMessages();

		Scanner scan = new Scanner(System.in);
		this.player = player;

		showMessage(0); // Shows welcome message
		System.out.println("");
		while (this.moveCount != this.maxMoveCount) {
			playGame(scan);
		}

		player.setPoints(player.getPoints() + (int) this.getScore()); // temporary cast
		System.out.println("Thank you for playing the Uneven Bars Event!");
		System.out.println("Total points over all rooms: " + player.getPoints());

	}
}
