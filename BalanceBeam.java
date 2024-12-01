import java.util.Scanner;
import java.util.Random;

/**
 * Class to control the balance beam event.
 * 
 * @author Rayne Elling
 */

public class BalanceBeam extends Room {
        private double totalBeamScore;
        private int movesLeft;
        private String roomDescription;
        private String instructions;
        // private String chooseSkill;
        private String[] messages = new String[10];
        private Move[] skills = new Move[21];
        private int count;
        private int comboScore;

        /**
         * Constructs the BalanceBeam room, establishing messages, totalBeamScore,
         * and movesLeft.
         */
        public BalanceBeam() {
                roomDescription = "Welcome to the balance beam event! Throughout this event you will be provided with choices of which move to make. "
                                + "\nThese moves are dependent on your skill and confidence levels, so choose wisely.";
                instructions = "At any point during the room you can type \"Skills\" or \"Score\" to view your skills or score.";
                totalBeamScore = 0;
                movesLeft = 6;
        }

        /**
         * Loads Moves into an array.
         */
        public void loadSkills() {
                Move one = new Move("Leap", 1,
                                "A mount that involves leaping from the ground onto the beam, landing on one foot.");
                Move two = new Move("Cartwheel", 2,
                                "A mount that involves a running start into a cartwheel onto the beam.");
                Move three = new Move("Front tuck", 3,
                                "A mount that involves a running start and a front tuck on to the beam, landing on two feet.");
                Move four = new Move("Cartwheel", 1,
                                "A simple cartwheel across the beam.");
                Move five = new Move("Roundoff", 2,
                                "This move is similar to the cartwheel, but the gymnast lands with both feet together on the beam.");
                Move six = new Move("Free Aerial", 3,
                                "This move is similar to the cartwheel, but the gymnast's hand do not touch the beam.");
                Move seven = new Move("Pike Front Tuck", 1,
                                "This combination of a tuck and a pike has the gymnast flip forward while keeping their legs straight.");
                Move eight = new Move("Tuck Side Salto", 2,
                                "The gymnast starts facing forward and flips sideways, facing the side at the conclusion of the skill.");
                Move nine = new Move("Tucked Arabian Salto", 3,
                                "The gymanst jumps high into the air, performs a half turn, then tucks themselves in to finish the flip.");
                Move ten = new Move("Half Illusion Turn", 1,
                                "The gymnast steps one foot out and while bending down towards that foot swings the other leg above and "
                                                + "around, finishing the turn.");
                Move eleven = new Move("Double turn in tuckstand", 2,
                                "The gymnast crouches down on the beam and performs 2 consecutive turns with one leg and both arms extended.");
                Move twelve = new Move("Triple turn on one foot", 3,
                                "While standing on one foot, the gymnast completes 3 full spins.");
                Move thirteen = new Move("Pike Jump", 1,
                                "Starting on two feet, the gymnast jumps with both legs extended forward.");
                Move fourteen = new Move("Straddle Jump with Half Turn", 2,
                                "The gymnast will rotate 180 degrees while jumping with both legs extended out to opposite sides.");
                Move fifteen = new Move("Split Jump with Body Arched and Head Dropped",
                                3,
                                "The gymnast will jump. While doing so, they extend one leg back and arch their back until their head touches their leg.");
                Move sixteen = new Move("Forward Roll", 1,
                                "The gymnast performs a forward roll on the beam without hand support.");
                Move seventeen = new Move("Back Extension", 2,
                                "Starting from a handstand position, the gymnast moves down to straddle the beam and then performs a back roll "
                                                + "into a handstand.");
                Move eighteen = new Move("Back Handspring", 3,
                                "The gymnast flips backward across the beam, touching their hands to the beam midway through the movement.");
                Move nineteen = new Move("Aerial Walkover", 1,
                                "Stepping forward, the gymnast flips forward with their body extended off of the beam, landing on both feet.");
                Move twenty = new Move("Double Back Tuck", 2,
                                "The gymnast performs two consecutive tucks off of the beam.");
                Move twentyOne = new Move("Arabian Double Front", 3,
                                "The gymnast jumps into the air from a roundoff entry, performs a half turn, "
                                                + "and tucks themselves to complete two forward somersaults.");
                skills[0] = one;
                skills[1] = two;
                skills[2] = three;
                skills[3] = four;
                skills[4] = five;
                skills[5] = six;
                skills[6] = seven;
                skills[7] = eight;
                skills[8] = nine;
                skills[9] = ten;
                skills[10] = eleven;
                skills[11] = twelve;
                skills[12] = thirteen;
                skills[13] = fourteen;
                skills[14] = fifteen;
                skills[15] = sixteen;
                skills[16] = seventeen;
                skills[17] = eighteen;
                skills[18] = nineteen;
                skills[19] = twenty;
                skills[20] = twentyOne;

        }

        /**
         * Performs the given skill.
         * 
         * @param index of skill/Move.
         * @return True if move completed successfully, false otherwise.
         */
        public boolean performSkill(int skillNum, Player p) {
                int difficulty = skills[skillNum].getDifficulty();
                int skillLevel = p.getStrength() + p.getBalance();
                int confidence = p.getConfidence();
                int total = calculatePerformance(difficulty, skillLevel, confidence);

                if (difficulty == 3) {
                        return total >= 25;
                } else if (difficulty == 2) {
                        return total >= 20;
                } else {
                        return total >= 15;
                }

        }

        /**
         * Calculates the performance of the athlete for a given skill.
         * 
         * @param difficulty of the skill chosen.
         * @param skill Level of the skill chosen.
         * @return the score calculated for the skill.
         */
        public int calculatePerformance(int difficulty, int skillLevel,
                        int confidence) {
                Random rand = new Random();
                int randomNum = rand.nextInt(10);
                int total = skillLevel + confidence + randomNum;
                // System.out.println("total: " + skillLevel + " + " + confidence + " +
                // " + randomNum + " = " + total);
                return total;
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
                messages[2] = "\nYou walk up to the beam. Please choose the skill you would like to mount the beam with: ";
                messages[3] = "You are now on the beam. Which skill will you perform next? ";
                messages[4] = "It's time to wow the judges. Which skill would you like to perform?";
                messages[5] = "Now let's add a spin! Select one of the following skills.";
                messages[6] = "It's time to add some flare. Which jump skill would you like to attempt?";
                messages[7] = "Let's move across the beam. Which skill will you attempt next?";
                messages[8] = "It's time to dismount. Choose your final skill.";
        }

        /**
         * Updates the score for the room.
         * 
         * @param difficulty of skill.
         */
        private void updateScore(int difficulty) {
                if (difficulty == 3) {
                        totalBeamScore += 3.6;
                } else if (difficulty == 2) {
                        totalBeamScore += 2.6;
                } else {
                        totalBeamScore += 1.6;
                }
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
         * @param Player who is competing in the event.
         */
        private void displayOptions(Scanner in, Player p) {
                boolean valid = true;
                String input;
                int skillNum = -1;
                while (valid) {
                        showMessage(count + 2);
                        System.out.println(
                                        "Choose one of the following skills to complete: (enter the number)");
                        System.out.println("1. " + skills[count * 3].getName() + "\n2. "
                                        + skills[(count * 3) + 1].getName() + "\n3. "
                                        + skills[(count * 3) + 2].getName() + "\n"
                                        + "4. More Info");
                        input = in.nextLine();
                        if (input.equals("1")) {
                                skillNum = (count * 3);
                                valid = false;
                        } else if (input.equals("2")) {
                                skillNum = (count * 3) + 1;
                                valid = false;
                        } else if (input.equals("3")) {
                                skillNum = (count * 3) + 2;
                                valid = false;
                        } else if (input.equals("4")) {
                                System.out.println();
                                System.out.println(skills[count * 3].getName() + ": "
                                                + skills[count * 3].getDescription()
                                                + "\nThis move has a difficulty of: "
                                                + skills[(count * 3)].getDifficulty());
                                System.out.println(skills[(count * 3) + 1].getName() + ": "
                                                + skills[(count * 3) + 1].getDescription()
                                                + "\nThis move has a difficulty of: "
                                                + skills[(count * 3) + 1].getDifficulty());
                                System.out.println(skills[(count * 3) + 2].getName() + ": "
                                                + skills[(count * 3) + 2].getDescription()
                                                + "\nThis move has a difficulty of: "
                                                + skills[(count * 3) + 2].getDifficulty() + "\n");
                        } else if (input.equals("Skills")) {
                                System.out.println("Your skills are as follows: Strength - "
                                                + p.getStrength() + ", Speed - " + p.getSpeed()
                                                + ", Balance - "
                                                + p.getBalance() + ", Confidence: " + p.getConfidence()
                                                + "\n");
                        } else if (input.equals("Score")) {
                                System.out.println(
                                                "Your total balance beam score is: " + totalBeamScore);
                                System.out.println(
                                                "Your overall score is: " + p.getPoints() + "\n");
                        } else {
                                System.out.println(input);
                                System.out.println("Please enter a valid input");
                        }
                }

                if (performSkill(skillNum, p)) {
                        System.out.println("\nSuccess!");
                        if (!(p.getConfidence() >= 10)) {
                                p.addConfidence(1);
                        }
                        updateScore(skills[skillNum].getDifficulty());
                } else {
                        System.out.println("\nFailure.");
                        if (p.getConfidence() != 1) {
                                p.addConfidence(-2);
                        }

                }

                System.out
                                .println("Your new confidence level is: " + p.getConfidence());
                System.out.println(
                                "Your current score is: " + (int) totalBeamScore + "\n");
                count++;
                movesLeft--;

        }

        @Override
        /**
         * Establishes the sequence of events for Balance Beam room.
         * 
         * @param Player who is competing in the event.
         */
        public void startRoom(Player player) {
                storeMessages();
                loadSkills();
                showMessage(0);
                showMessage(1);
                Scanner in = new Scanner(System.in);
                while (movesLeft > -1) {
                        displayOptions(in, player);
                }

                player.setPoints(player.getPoints() + (int) totalBeamScore);
                System.out.println("Heading back to the main event...\n");

        }

}
