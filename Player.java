
/**
 * Class to contain and modify attributes of player inside an object.
 *
 * @author Delano Conigy, Daktoa Oudeman, etc
 */
public class Player {

    private String gender;
    private int confidence, points, strength, speed, balance;

    /**
     * Constructs a Player given gender and skill levels.
     *
     * @param gender     of player.
     * @param confidence level.
     * @param strength   level.
     * @param speed      level.
     * @param balance    level.
     */
    public Player(String gender, int confidence, int strength, int speed, int balance) {
        this.gender = gender;
        this.confidence = confidence;
        this.strength = strength;
        this.speed = speed;
        this.balance = balance;
        this.points = 0;
    }

    /**
     * Constructs a Player given only gender.
     *
     * @param gender of player.
     */
    public Player(String gender) {
        this.gender = gender;
        this.confidence = 0;
        this.strength = 0;
        this.speed = 0;
        this.balance = 0;
        this.points = 0;
    }

    /**
     * Gets the gender of the player
     *
     * @return Gender of player.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the speed of the player
     *
     * @return Speed of player.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Gets the strength of the player
     *
     * @return Strength of player.
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Gets the balance of the player
     *
     * @return Balance of player.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Gets the confidence of the player
     *
     * @return Confidence of player.
     */
    public int getConfidence() {
        return confidence;
    }

    /**
     * Gets the number of points for the player
     *
     * @return Points of player.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the number of points for of the player
     *
     * @param Number of points.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Sets the confidence of the player. Confidence cannot go over 10 or under 0.
     *
     * @param Confidence of player.
     */
    public void setConfidence(int confidence) {
        if (confidence <= 0) {
            this.confidence = 0;
        } else if (confidence >= 10) {
            this.confidence = 10;
        } else {
            this.confidence = confidence;
        }
    }

    /**
     * Adjusts the confidence of the player.
     *
     * @param Confidence to adjust by
     */
    public void addConfidence(int confidence) {
        if (this.confidence + confidence >= 10) {
            this.setConfidence(10);
        } else {
            this.setConfidence(this.confidence + confidence);
        }
    }
}
