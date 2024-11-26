import java.util.Scanner;
import java.util.ArrayList;
/**
 * Class to control the floor room.
 * 
 * @author Dakota Oudeman
 */

public class Floor extends Room {
    /**
     * The total moves left before the end of the room.
     */
    private int movesLeft;

    /**
     * ArrayList of moves that the player can choose from to attempt.
     */
    private ArrayList<Move> skills;


    /**
     * Standard constructor method.
     */
    public Floor() {
        movesLeft = 10;
        skills = loadSkills();
        setScore(25);
    }

    /**
     * Starts the Floor event. This will have the player play through the
     * entire room before returning them back to the main menu.
     * 
     * @param player The player object that contains important attributes.
     */
    public void startRoom(Player player) {
        displayWelcome(player);
        while (movesLeft > 0) {
            System.out.println("You have " + movesLeft + " moves left.");
            Move selectedMove = selectMove();
            boolean successful = attemptMove(player, selectedMove);
            if (successful) {
            	System.out.println("Move completed successfully!");
            	System.out.println();
            } else {
            	System.out.println("Move failed!");
            	System.out.println();
            	setScore(getScore() - 2.5);
            }
            movesLeft--;
        }
        displayGoodbye(player);
        player.setPoints((int) (player.getPoints() + getScore()));
    }

    /**
     * Welcome message displayed upon entering the room.
     * 
     * @param p Player object
     */
    private void displayWelcome(Player p) {
        System.out.print("Welcome to the Floor Room. You will be given 10 chances to make moves.\n"
                        + "Based on your different attributes they will determine whether you\n"
                        + "succesfully complete the move or fail. Successful moves will count\n"
                        + "towards your total score.\n"
                        + "Your Balance: " + p.getBalance() + "\n"
                        + "Your Strength: " + p.getStrength() + "\n"
                        + "Your Speed: " + p.getSpeed() + "\n"
                        + "Your Confidence: " + p.getConfidence() + "\n");
    }

    /**
     * Goodbye message displayed when leaving the room.
     * 
     * @param p Player object
     */
    private void displayGoodbye(Player p) {
        System.out.println("The floor room is now over, goodbye.");
        System.out.println();
    }
    
    /**
     * Will ask the user to select a move from a randomized set of moves
     * 
     * @return The selected move
     */
    private Move selectMove() {
        ArrayList<Move> temp = new ArrayList<>();
        temp.add(skills.get((int) (Math.random() * skills.size())));
        temp.add(skills.get((int) (Math.random() * skills.size())));
        temp.add(skills.get((int) (Math.random() * skills.size())));
        System.out.print("You can choose from one of three of these moves\n"
        			   + "[1] " + temp.get(0).toString(temp.get(0)) + "\n"
        			   + "[2] " + temp.get(1).toString(temp.get(1)) + "\n"
        			   + "[3] " + temp.get(2).toString(temp.get(2)) + "\n");
        Scanner s = new Scanner(System.in);
        int selectedNum = s.nextInt();
        while (selectedNum != 1 && selectedNum != 2 && selectedNum != 3) {
        	System.out.println("Incorrect selection, please select either 1, 2, or 3");
        	selectedNum = s.nextInt();
        }
        Move selectedMove = temp.get(selectedNum - 1);
    	return selectedMove;
    }

    /**
     * Helper method to load the skills ArrayList with various moves.
     * 
     * @return A loaded ArrayList of moves.
     */
    private ArrayList<Move> loadSkills() {
    	ArrayList<Move> moves = new ArrayList<>();
    	moves.add(new Move("Roundoff", 40, "A cartwheel with a landing on both feet, leading into a powerful rebound."));
        moves.add(new Move("Back Handspring", 50, "A quick backward flip with the hands pushing off the ground."));
        moves.add(new Move("Front Tuck", 60, "A forward flip with knees tucked to the chest."));
        moves.add(new Move("Back Layout", 70, "A backward flip with a straight body position."));
        moves.add(new Move("Aerial", 65, "A no-handed cartwheel, requiring strong momentum and control."));
        moves.add(new Move("Double Back Tuck", 85, "Two backward flips performed with knees tucked to the chest."));
        moves.add(new Move("Full Twist", 80, "A backflip with a full 360-degree twist mid-air."));
        moves.add(new Move("Triple Full", 100, "A backflip with three complete twists, requiring exceptional precision."));
        moves.add(new Move("Straddle Jump", 30, "A jump with legs extending into a straddle split in mid-air."));
        moves.add(new Move("Wolf Jump", 35, "A jump where one leg is straight, and the other is bent underneath the body."));
        moves.add(new Move("Switch Leap", 55, "A leap where the legs switch positions mid-air."));
        moves.add(new Move("Double Pike", 90, "A backward double flip with legs in a pike position."));
        moves.add(new Move("Front Layout", 65, "A forward flip with a straight body position."));
        moves.add(new Move("Double Front Tuck", 85, "Two forward flips performed with knees tucked to the chest."));
        moves.add(new Move("Front Handspring", 45, "A forward handspring with a strong push-off from the hands."));
        moves.add(new Move("Side Aerial", 70, "A no-handed side flip with a focus on lateral control."));
        moves.add(new Move("Popa", 50, "A 360-degree straddle jump with strong toe-pointing and rotation."));
        moves.add(new Move("Split Leap", 40, "A leap with legs extending into a full split mid-air."));
        moves.add(new Move("Ring Leap", 55, "A leap where the back leg arches upward to form a ring shape."));
        moves.add(new Move("Shushunova", 80, "A high jump landing in a straddle position before rebounding."));
        moves.add(new Move("Double Arabian", 95, "A double flip where the gymnast begins in a half-twist before flipping."));
        moves.add(new Move("Thomas Salto", 90, "A front tumbling skill with a twist and flip combined."));
        moves.add(new Move("Rudi", 75, "A front flip with a 1.5 twist mid-air."));
        moves.add(new Move("Pike Jump", 30, "A jump where legs are straight and parallel to the floor."));
        moves.add(new Move("Double Twist", 85, "A backflip with two complete twists mid-air."));
        moves.add(new Move("Cat Leap Full", 40, "A cat leap with a full 360-degree turn mid-air."));
        moves.add(new Move("Whip Back", 55, "A quick backward tumbling skill, similar to a back handspring but faster."));
        moves.add(new Move("Double Layout", 95, "A double backward flip in a straight body position."));
        moves.add(new Move("Half-In Half-Out", 90, "A double flip with a half twist on the first and second rotations."));
    	return moves;
    }

    /**
     * Attempts a move specified by the player, based on various attributes like
     * player confidence, strength, speed, and balance.
     * 
     * @param p Player object that has the player attributes.
     * @param m Which move the player will be trying to complete.
     * @return true if skill completed successfully, false otherwise.
     */
    private boolean attemptMove(Player p, Move m) {
        int combinedValue = p.getConfidence() * p.getStrength();
        if (combinedValue < 50) {
        	combinedValue += p.getConfidence() * 1.5;
        }
        if (combinedValue > m.getDifficulty()) {
        	p.setConfidence(p.getConfidence() + 1);
        	return true;
        }
        int newValue = 100 - combinedValue;
        newValue = (newValue * (p.getConfidence() / 10)) / 3;
        combinedValue += newValue;
        if (combinedValue > m.getDifficulty()) {
        	return true;
        }
        p.setConfidence(p.getConfidence() - 1);
    	return false;
    }
}
