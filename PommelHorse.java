/**
 * PommelHorse room.
 * 
 * Players can select moves,perform actions based on their skill levels, and complete minigames if they don't meet
 * specific skill requirements for moves.
 * 
 * Author: Norman Han
 * Version: 1.0
 */

 import java.util.*;

 /**
  * PommelHorse class where players can perform individual moves or combo moves. 
  * This class extends Room and includes move requirements and minigame triggers based on the player’s attributes.
  */
 public class PommelHorse extends Room {
     private Scanner scanner; // Scanner for user input
     private String position; // Current position on the pommel horse (left, middle, right)
 
     // Define available moves for each position on the pommel horse
     private final List<Move> leftMoves = Arrays.asList(
             new Move("Left Swing", 2, "A simple left swing to warm up."),
             new Move("One-Hand Circle", 4, "A challenging one-hand circle move."),
             new Move("Scissor", 6, "A scissor move requiring strength and balance."),
             new Move("Transition Move", 1, "A move that lets you switch positions.")
     );
     private final List<Move> middleMoves = Arrays.asList(
             new Move("Mushroom Spin", 2, "An easy spin on the mushroom."),
             new Move("Spindle", 5, "A hard spindle move requiring precision."),
             new Move("Russian Swing", 7, "A difficult Russian swing for advanced athletes."),
             new Move("Transition Move", 1, "A move that lets you switch positions.")
     );
     private final List<Move> rightMoves = Arrays.asList(
             new Move("Right Circle", 2, "A simple right circle to stay balanced."),
             new Move("Flare", 5, "A hard flare move to impress the judges."),
             new Move("Reverse Circle", 7, "A reverse circle move with high difficulty."),
             new Move("Transition Move", 1, "A move that lets you switch positions.")
     );
 
     // Skill requirements for each move in terms of speed, strength, balance, and confidence
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
      * Constructor for PommelHorse, initializes the scanner.
      */
     public PommelHorse() {
         super();
         scanner = new Scanner(System.in);
     }
 
     /**
      * Starts the pommel horse event, allowing the player to select positions and moves.
      * Triggers minigames if the player does not meet the skill requirements for moves.
      * 
      * @param player the Player object participating in the event
      */
     @Override
     public void startRoom(Player player) {
         System.out.println("Welcome to the Pommel Horse event!");
         choosePosition();
 
         System.out.println("Your starting confidence is: " + player.getConfidence());
 
         // Prompt for move type selection: individual or combo
         System.out.println("Do you want to perform individual moves or a combo move? (type 'individual' or 'combo'):");
         String choiceType = scanner.nextLine().toLowerCase();
 
         if (choiceType.equals("combo")) {
             System.out.println("You chose to perform a combo move.");
             performComboMove(player);
         } else {
             // Perform up to five individual moves
             for (int i = 1; i <= 5; i++) {
                 System.out.println("\nMove " + i + ": Choose a move from the list below, or type 'transition' to change positions:");
                 Move move = chooseMove();
 
                 System.out.println("You chose: " + move.getName());
                 System.out.println("Description: " + move.getDescription());
 
                 // Allow player to change position using a transition move
                 if (move.getName().equalsIgnoreCase("Transition Move")) {
                     System.out.println("You chose to perform a transition move.");
                     choosePosition(); 
                     continue;
                 }
 
                 executeMove(move, player); 
                 System.out.println("Your confidence is now: " + player.getConfidence());
 
                 // End the event if the player loses all confidence
                 if (player.getConfidence() <= 0) {
                     System.out.println("Oh no! You've lost confidence and fallen off the pommel horse.");
                     break;
                 }
             }
         }
 
         setScore(player.getConfidence());
         System.out.println("You finished the Pommel Horse event with a score of: " + getScore());
     }
 
     /**
      * Prompts the player to choose a position on the pommel horse (left, middle, right).
      */
     private void choosePosition() {
         System.out.println("Choose your position on the pommel horse (left, middle, right): ");
         position = scanner.nextLine().toLowerCase();
 
         while (!position.equals("left") && !position.equals("middle") && !position.equals("right")) {
             System.out.println("Invalid position. Please choose left, middle, or right: ");
             position = scanner.nextLine().toLowerCase();
         }
         System.out.println("You are now at the " + position + " position.");
     }
 
     /**
      * Allows the player to choose a move based on their current position. Displays available moves and prompts the player to select one.
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
 
         System.out.println("Available moves at " + position + " position:");
         for (Move move : moves) {
             System.out.println("- " + move.getName() + " (Difficulty: " + move.getDifficulty() + ")");
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
 
     /**
      * Executes the selected move and checks if the player meets skill requirements.
      * If requirements are not met, a minigame is triggered.
      * 
      * @param move the selected Move object
      * @param player the Player object attempting the move
      */
     private void executeMove(Move move, Player player) {
         int confidenceChange;
         int[] requirements = moveRequirements.getOrDefault(move.getName(), new int[]{0, 0, 0, 0});
 
         // Check if player's skills meet move requirements; trigger minigame if not
         if (player.getSpeed() < requirements[0] || 
             player.getStrength() < requirements[1] || 
             player.getBalance() < requirements[2] || 
             player.getConfidence() < requirements[3]) {
             
             System.out.println("This move is challenging based on your current skills and requires a minigame.");
             boolean minigameResult = playRandomMinigame(player);
             if (minigameResult) {
                 System.out.println("Congratulations! You succeeded in the minigame.");
                 confidenceChange = 15;
             } else {
                 System.out.println("You lost the minigame. Applying base reward.");
                 confidenceChange = 5;
             }
         } else {
             System.out.println("You perform the move (" + move.getName() + ") without needing a minigame.");
             confidenceChange = 5;
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
      * Number guessing minigame where the player has three attempts to guess a randomly generated number between 1 and 5.
      * 
      * @return true if the player guesses the correct number, false otherwise
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
                 System.out.println("Correct! You won the minigame.");
                 return true;
             } else if (guess < correctNumber) {
                 System.out.println("Too low.");
             } else {
                 System.out.println("Too high.");
             }
         }
 
         System.out.println("You've used all attempts. The correct number was " + correctNumber + ".");
         return false;
     }
 
     /**
      * Reaction time minigame where the player must type "GO!" as fast as they can after a randomized delay.
      * 
      * @param player the Player object participating in the minigame
      * @return true if the player reacts within the allowed time, false otherwise
      */
     private boolean playReactionTimeMinigame(Player player) {
         System.out.println("Minigame: Type 'GO!' as fast as you can after the prompt.");
         System.out.println("Get ready...");
         
         try {
             Thread.sleep(1000 + new Random().nextInt(2000));
         } catch (InterruptedException e) {
             System.out.println("An error occurred.");
         }
 
         System.out.println("GO!");
 
         long startTime = System.currentTimeMillis();
         String response = scanner.nextLine();
         long reactionTime = System.currentTimeMillis() - startTime;
 
         if (response.equalsIgnoreCase("GO!") && reactionTime < 2000 - (player.getSpeed() * 10)) {
             System.out.println("Great reaction! You won the minigame.");
             return true;
         } else {
             System.out.println("Too slow or incorrect input. You lost the minigame.");
             return false;
         }
     }
 
     /**
      * Performs a challenging combo move with a predefined sequence. Awards higher confidence upon successful completion.
      * 
      * @param player the Player object performing the combo move
      */
     private void performComboMove(Player player) {
         System.out.println("Combo moves selected! Performing a challenging sequence including The Busnari, The Magyar, and The Tong Fei.");
         boolean minigameResult = playRandomMinigame(player);
         if (minigameResult) {
             System.out.println("Combo success! Great job!");
             player.addConfidence(25);
         } else {
             System.out.println("Combo failed. Awarding base points.");
             player.addConfidence(10);
         }
     }
 }
