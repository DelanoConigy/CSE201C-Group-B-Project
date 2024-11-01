import java.util.*;

public class PommelHorse extends Room {
    private Scanner scanner;
    private String position;
    private final List<Move> leftMoves = Arrays.asList(
            new Move("Left Swing", false, "A simple left swing to warm up."),
            new Move("One-Hand Circle", true, "A challenging one-hand circle move."),
            new Move("Scissor", true, "A scissor move requiring strength and balance."),
            new Move("Transition Move", false, "A move that lets you switch positions.")
    );
    private final List<Move> middleMoves = Arrays.asList(
            new Move("Mushroom Spin", false, "An easy spin on the mushroom."),
            new Move("Spindle", true, "A hard spindle move requiring precision."),
            new Move("Russian Swing", true, "A difficult Russian swing for advanced athletes."),
            new Move("Transition Move", false, "A move that lets you switch positions.")
    );
    private final List<Move> rightMoves = Arrays.asList(
            new Move("Right Circle", false, "A simple right circle to stay balanced."),
            new Move("Flare", true, "A hard flare move to impress the judges."),
            new Move("Reverse Circle", true, "A reverse circle move with high difficulty."),
            new Move("Transition Move", false, "A move that lets you switch positions.")
    );

    public PommelHorse() {
        super();
        scanner = new Scanner(System.in);
    }

    @Override
    public void startRoom(Player player) {
        System.out.println("Welcome to the Pommel Horse event!");
        choosePosition();

        System.out.println("Your starting confidence is: " + player.getConfidence());

        // Choose either individual moves or a combo
        System.out.println("Do you want to perform individual moves or a combo move? (type 'individual' or 'combo'):");
        String choiceType = scanner.nextLine().toLowerCase();

        if (choiceType.equals("combo")) {
            System.out.println("You chose to perform a combo move.");
            performComboMove(player);
        } else {
            // Perform five moves as individual choices
            for (int i = 1; i <= 5; i++) {
                System.out.println("\nMove " + i + ": Choose a move from the list below, or type 'transition' to change positions:");
                Move move = chooseMove();
                
                System.out.println("You chose: " + move.getName());
                System.out.println("Description: " + move.getDescription());

                if (move.getName().equalsIgnoreCase("Transition Move")) {
                    System.out.println("You chose to perform a transition move.");
                    choosePosition();  // Allows the player to select a new position
                    continue;  // Move on to the next choice without changing confidence
                }

                executeMove(move, player);  // Execute the chosen move
                System.out.println("Your confidence is now: " + player.getConfidence());

                // Work in progress
                if (player.getConfidence() <= 0) {
                    System.out.println("Oh no! You've lost confidence and fallen off the pommel horse.");
                    break;
                }
            }
        }

        setScore(player.getConfidence());
        System.out.println("You finished the Pommel Horse event with a score of: " + getScore());
    }

    private void choosePosition() {
        System.out.println("Choose your position on the pommel horse (left, middle, right): ");
        position = scanner.nextLine().toLowerCase();

        while (!position.equals("left") && !position.equals("middle") && !position.equals("right")) {
            System.out.println("Invalid position. Please choose left, middle, or right: ");
            position = scanner.nextLine().toLowerCase();
        }
        System.out.println("You are now at the " + position + " position.");
    }

    private Move chooseMove() {
        List<Move> moves;
        switch (position) {
            case "left":
                moves = leftMoves;
                break;
            case "middle":
                moves = middleMoves;
                break;
            case "right":
                moves = rightMoves;
                break;
            default:
                return null;
        }

        System.out.println("Available moves at " + position + " position:");
        for (Move move : moves) {
            System.out.println("- " + move.getName() + " (" + (move.getDifficulty() ? "hard" : "easy") + ")");
        }
        System.out.println("Type the name of the move you want to perform:");

        String moveChoice = scanner.nextLine();
        for (Move move : moves) {
            if (move.getName().equalsIgnoreCase(moveChoice)) {
                return move;
            }
        }

        System.out.println("Invalid move. Please choose a valid move.");
        return chooseMove();
    }

    private void executeMove(Move move, Player player) {
        int confidenceChange;

        if (move.getDifficulty()) {
            System.out.println("This is a hard move and requires a minigame.");
            boolean minigameResult = playMinigame();
            if (minigameResult) {
                System.out.println("Congratulations! You succeeded in the minigame.");
                confidenceChange = 15;  // Reward for succeeding in a hard move minigame
            } else {
                System.out.println("You lost the minigame. Applying base reward.");
                confidenceChange = 5;  // Base reward if minigame is failed
            }
        } else {
            System.out.println("You perform an easy move (" + move.getName() + ").");
            confidenceChange = 5;
        }

        player.addConfidence(confidenceChange);
    }

    public boolean playMinigame() {
        // Randomly select a minigame, for now simply returning success
        return true;
    }

    private void performComboMove(Player player) {
        // Simulated combo move implementation
        System.out.println("Combo moves selected! Performing a series of hard moves.");
        boolean minigameResult = playMinigame();
        if (minigameResult) {
            System.out.println("Combo success! Great job!");
            player.addConfidence(25);  // Higher reward for combo success
        } else {
            System.out.println("Combo failed. Awarding base points.");
            player.addConfidence(10);  // Base reward for failed combo
        }
    }
}
