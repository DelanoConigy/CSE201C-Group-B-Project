import java.util.*;

/**
 * This is the pommel horse room in Go For The Gold game
 * Player can perform individual or combo moves, transition between positions,
 * and participate in minigames based on move difficulty and player attributes.
 * 
 * Confidence is capped at 10, and the score is scaled to a maximum of 25 points.
 * 
 * @author Jiahao Han
 * 
 * @version 2.0
 */
public class PommelHorse extends Room {
    private Scanner scanner;
    private String position;

    private final List<Move> leftMoves = Arrays.asList(
            new Move("Left Swing", 2, "A simple left swing to warm up."),
            new Move("One-Hand Circle", 4, "A challenging one-hand circle move."),
            new Move("Scissor", 6, "A scissor move requiring strength and balance.")
    );
    private final List<Move> middleMoves = Arrays.asList(
            new Move("Mushroom Spin", 2, "An easy spin on the mushroom."),
            new Move("Spindle", 5, "A hard spindle move requiring precision."),
            new Move("Russian Swing", 7, "A difficult Russian swing for advanced athletes.")
    );
    private final List<Move> rightMoves = Arrays.asList(
            new Move("Right Circle", 2, "A simple right circle to stay balanced."),
            new Move("Flare", 5, "A hard flare move to impress the judges."),
            new Move("Reverse Circle", 7, "A reverse circle move with high difficulty.")
    );

    private final Map<String, int[]> moveRequirements = Map.of(
            "Left Swing", new int[]{2, 0, 0, 0},
            "One-Hand Circle", new int[]{0, 4, 0, 0},
            "Scissor", new int[]{0, 3, 5, 0},
            "Mushroom Spin", new int[]{2, 0, 0, 0},
            "Spindle", new int[]{0, 0, 4, 5},
            "Russian Swing", new int[]{3, 3, 4, 6},
            "Right Circle", new int[]{2, 0, 1, 0},
            "Flare", new int[]{0, 4, 0, 5},
            "Reverse Circle", new int[]{3, 4, 3, 6}
    );
    /**
     * Constructor for PommelHorse. Initializes the scanner for user input.
     */
    public PommelHorse() {
        super();
        scanner = new Scanner(System.in);
    }

    /**
     * Starts the Pommel Horse event, allowing the player to select positions and moves.
     * The event includes minigames based on move difficulty, and scores are calculated
     * proportionally to the player's confidence level.
     *
     * @param player the Player object participating in the event
     */
    @Override
    public void startRoom(Player player) {
        System.out.println("Welcome to the Pommel Horse event!");
        choosePosition();
        System.out.println("Your starting confidence is: " + player.getConfidence());
        System.out.println("Do you want to perform individual moves or a combo move? (type '1' for individual, '2' for combo):");
        int choiceType = Integer.parseInt(scanner.nextLine());

        if (choiceType == 2) {
            performComboMove(player);
        } else {
            for (int i = 1; i <= 5; i++) {
                System.out.println("\nMove " + i + ": Choose a move from the list below:");
                Move move = chooseMove();
                System.out.println("You chose: " + move.getName());
                System.out.println("Description: " + move.getDescription());
                executeMove(move, player);

                if (player.getConfidence() <= 0) {
                    System.out.println("Oh no! You've lost confidence and fallen off the pommel horse.");
                    break;
                }
            }
        }

        int finalConfidence = player.getConfidence();
        double scaledScore = (finalConfidence / 10.0) * 25;
        setScore(scaledScore);
        System.out.println("You finished the Pommel Horse event with a score of: " + Math.min(getScore(), 25));
    }

    /**
     * Prompts the player to choose a position on the pommel horse.
     * Options include Left, Middle, and Right.
     */
    private void choosePosition() {
        System.out.println("Choose your position on the pommel horse:");
        System.out.println("1. Left");
        System.out.println("2. Middle");
        System.out.println("3. Right");

        int choice = Integer.parseInt(scanner.nextLine());
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid choice. Please choose a valid position:");
            choice = Integer.parseInt(scanner.nextLine());
        }

        if (choice == 1) {
            position = "left";
        } else if (choice == 2) {
            position = "middle";
        } else if (choice == 3) {
            position = "right";
        }

        System.out.println("You are now at the " + position + " position.");
    }

    /**
     * Allows the player to choose a move based on their current position.
     * Displays available moves as numbered options.
     *
     * @return the selected Move object
     */
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

        System.out.println("Available moves:");
        for (int i = 0; i < moves.size(); i++) {
            System.out.println((i + 1) + ". " + moves.get(i).getName() + " (Difficulty: " + moves.get(i).getDifficulty() + ")");
        }

        System.out.println("Choose a move by number:");
        int choice = Integer.parseInt(scanner.nextLine()) - 1;

        while (choice < 0 || choice >= moves.size()) {
            System.out.println("Invalid choice. Please select a valid move:");
            choice = Integer.parseInt(scanner.nextLine()) - 1;
        }

        return moves.get(choice);
    }

    /**
     * Executes the selected move and adjusts confidence based on success.
     * Triggers a minigame if the player does not meet the skill requirements for the move.
     *
     * @param move   the selected Move object
     * @param player the Player object attempting the move
     */
    private void executeMove(Move move, Player player) {
        int confidenceChange;
        int[] requirements = moveRequirements.getOrDefault(move.getName(), new int[]{0, 0, 0, 0});

        if (player.getSpeed() < requirements[0] || 
            player.getStrength() < requirements[1] || 
            player.getBalance() < requirements[2] || 
            player.getConfidence() < requirements[3]) {
            boolean minigameResult = playRandomMinigame(player);
            if (minigameResult) {
                confidenceChange = 3;
            } else {
                confidenceChange = 1;
            }
        } else {
            confidenceChange = 2;
        }

        player.addConfidence(confidenceChange);
    }

    /**
     * Randomly selects and initiates a minigame for the player.
     *
     * @param player the Player object participating in the minigame
     * @return true if the player wins the minigame, false otherwise
     */
    private boolean playRandomMinigame(Player player) {
        Random random = new Random();
        int minigameType = random.nextInt(2);
        if (minigameType == 0) {
            return playNumberGuessingMinigame();
        } else {
            return playReactionTimeMinigame(player);
        }
    }

    /**
     * Number-guessing minigame where the player guesses a number between 1 and 5.
     *
     * @return true if the player guesses correctly, false otherwise
     */
    private boolean playNumberGuessingMinigame() {
        Random random = new Random();
        int correctNumber = random.nextInt(5) + 1;
        int attempts = 3;
        System.out.println("Minigame: Guess a number between 1 and 5. You have " + attempts + " attempts.");
        for (int i = 0; i < attempts; i++) {
            System.out.print("Enter your guess: ");
            int guess = Integer.parseInt(scanner.nextLine());
            if (guess == correctNumber) {
                return true;
            } else if (guess < correctNumber) {
                System.out.println("Too low.");
            } else {
                System.out.println("Too high.");
            }
        }
        return false;
    }

    /**
     * Reaction time minigame where the player must type "GO!" after a randomized delay.
     *
     * @param player the Player object participating in the minigame
     * @return true if the player reacts quickly enough, false otherwise
     */
    private boolean playReactionTimeMinigame(Player player) {
        System.out.println("Minigame: Type 'GO!' as fast as you can after the prompt.");
        try {
            Thread.sleep(1000 + new Random().nextInt(2000));
        } catch (InterruptedException e) {
            System.out.println("An error occurred.");
        }
        System.out.println("GO!");
        long startTime = System.currentTimeMillis();
        String response = scanner.nextLine();
        long reactionTime = System.currentTimeMillis() - startTime;
        return response.equalsIgnoreCase("GO!") && reactionTime < 2000 - (player.getSpeed() * 100);
    }

    /**
     * Perform combo move with a predefined sequence of high-difficulty moves.
     * Adjusts confidence based on the success or failure of minigame.
     * 
     * @param player the Player object performing the combo move
     */
    private void performComboMove(Player player) {
        boolean minigameResult = playRandomMinigame(player);
        if (minigameResult) {
            player.addConfidence(4);
        } else {
            player.addConfidence(2);
        }
    }
}
