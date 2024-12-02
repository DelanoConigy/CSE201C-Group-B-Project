
/**
 * A class to provide a basis for operations and attributes for each Move which
 * will be utilized by multiple rooms.
 *
 * @author Delano Conigy, Dakota Oudeman, etc
 */
public class Move {

    private String name;
    private int difficulty;
    private String description;

    /**
     * Constructs a Move given the name, difficulty, and description.
     *
     * @param name of the move.
     * @param difficulty of the move.
     * @param description of the move.
     */
    public Move(String name, int difficulty, String description) {
        this.name = name;
        this.difficulty = difficulty; // scale from 0 to 100
        this.description = description;
    }

    /**
     * Gets the name of the move.
     *
     * @return Name of the move.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the difficulty of the move.
     *
     * @return Difficulty of the move.
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Gets the description of the move.
     *
     * @return Description of the move.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a standardized toString statment of a given move.
     *
     * @param Move to print.
     * @return Standardized string of the move.
     */
    public String toString(Move move) {
        String result = "";
        result += "Name: " + move.getName() + " Difficulty: " + move.getDifficulty() + " Description: "
                + move.getDescription();

        return result;
    }

}
