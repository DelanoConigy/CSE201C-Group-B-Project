
/**
 * An abstract class to provide a basis for operations and attributes that
 * should be in between each Room.
 *
 * @author Delano Conigy, Dakota Oudeman, etc
 */

public abstract class Room {

    /**
     * Score acquired in the room.
     */
    private double score;

    /**
     * Constructor for room
     */
    public Room() {
        this.score = 0;
    }

    /**
     * Gets the score for the room.
     *
     * @return total score
     */
    public double getScore() {
        return score;
    }

    /**
     * Sets the score for the room. The max a score can get for each room is 50.
     *
     * @param s score
     */
    public void setScore(double s) {
        if (s > 50) {
            score = 50;
            return;
        }
        this.score = s;
    }

    /**
     * Adds the score
     *
     * @param s score
     */
    public void addScore(double s) {
        score += s;
    }

    /**
     * Subtracts the score
     *
     * @param s score
     */
    public void subtractScore(double s) {
        score -= s;
    }

    /**
     * Starts the room and allows the player to play through the room
     *
     * @param player Player class
     */
    public void startRoom(Player player) {

    }
}
