import java.util.Scanner;
import java.util.Random;

/**
 * Class to control the balance beam event.
 * 
 * @author Rayne Elling
 */

public class BalanceBeam extends Room {
    private int totalBeamScore;
    private int movesLeft;
    private String roomDescription;
    private String instructions;
    private String chooseSkill;
    private String[] messages = new String[6];
    private Move[] skills = new Move[6];
    private int count;
    private int comboScore;

    /**
     * Constructs the BalanceBeam room, establishing messages, totalBeamScore,
     * and movesLeft
     */
    public BalanceBeam() {
        roomDescription = "Welcome to the balance beam event! Throughout this event you will be provided with choices of which move to make.These moves are dependent on your skill and confidence levels, so choose wisely.";
        instructions = "Please choose the following.";
        chooseSkill = "You walk up to the beam. You have the choice to perform a cartwheel to mount. Do you choose to perform this event? Please type Yes or No";
        totalBeamScore = 0;
        movesLeft = 6;
    }

    /**
     * Loads Moves into an array.
     */
    public void loadSkills() {
        Move one = new Move("cartwheel", 15.0, "A desciption of a cartwheel");
        Move two = new Move("back handspring", 20.0,
                "A desciption of a back handspring");
        skills[0] = one;
        skills[1] = two;
    }

    /**
     * Performs the given skill.
     * 
     * @param index of skill/Move
     * @return true if move completed successfully, false otherwise
     */
    public boolean performSkill(int skillNum) {
        int performance = calculatePerformance();
        if (performance >= skills[skillNum].getDifficulty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates the performance of the athlete for a given skill.
     * 
     * @return the score calculated for the skill.
     */
    public int calculatePerformance() {
        Random rand = new Random();
        int randomNum = rand.nextInt(30);
        return randomNum;
    }

    /**
     * Updates the number of moves attempted.
     */
    public void updateCount() {
        count++;
    }

    /**
     * Updates the score for the event.
     * 
     * @param amount to update by.
     */
    public void updateScore(double amount) {
        totalBeamScore += amount;
        setScore(totalBeamScore / count);
    }

    /**
     * Stores the messages in an array.
     */
    private void storeMessages() {
        messages[0] = roomDescription;
        messages[1] = instructions;
        messages[2] = chooseSkill;
    }

    /**
     * Displays the given message.
     * 
     * @param Index of message.
     */
    private void showMessage(int i) {
        System.out.println(messages[i]);
    }

    /**
     * Displays options for user to see their score, skills, or move on to
     * choosing a skill.
     * 
     * @param Scanner to take input.
     */
    private void displayOptions(Scanner in) {
        String input;
        boolean valid = true;
        do {
            showMessage(2);
            input = in.next();
            if (input.equals("Score")) {
                // showScore();
                valid = false;
            } else if (input.equals("Skills")) {
                // showSkills();
                valid = false;
            } else if (input.equals("Yes")) {
                performSkill(count);
                valid = false;
            } else if (input.equals("No")) {
                count++;
                valid = false;
            } else {
                System.out.println("Please enter a valid input");
            }
        } while (valid);
    }

    @Override
    /**
     * Establishes the sequence of events for Balance Beam room.
     * 
     * @param Player who is competing in the event.
     */
    public void startRoom(Player player) {
        loadSkills();
        System.out.println("Welcome to the Balance Beam event!");
        System.out.println(
                "Please choose a skill to complete: cartwheel or back handspring");
        Scanner in = new Scanner(System.in);
        boolean notCorrectInput = true;
        boolean success = false;
        String userChoice = "move";
        while (notCorrectInput) {
            userChoice = in.nextLine();
            if (userChoice.equals("cartwheel")) {
                success = performSkill(0);
                notCorrectInput = false;
            } else if (userChoice.equals("back handspring")) {
                success = performSkill(1);
                notCorrectInput = false;
            } else {
                System.out.println("Please enter a valid input");
            }
        }
        if (success) {
            System.out.println(
                    "You successfully completed the " + userChoice + "!");
        } else {
            System.out.println("You failed the " + userChoice + ".");
        }
        System.out.println("Now it's time to try a different event");

        // storeMessages();
        // showMessage(0); // Shows welcome message
        // loadSkills();
        // showMessage(1); // Shows instructions
        // showMessage(2); // Let's begin. Describe start and first move.
        // Scanner in = new Scanner(System.in);
        // displayOptions(in);
        // System.out.println(skills[0].getName() + ", " +
        // skills[0].getDifficulty() + ", " + skills[0].getDescription());

    }

}
