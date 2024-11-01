

public class Move{

    private String name;
    private int difficulty; 
    // I feel like changing the difficulty to a number can help determine random events better. 
    // For instance, if you have a confidence of 50 and the moves difficulty is 75, you could
    // add the confidence plus a random chance variable to determine whether the move is successful
    // or not. I think easy and hard is to vague and will make harder implementations when working with
    // change events.  
    private String description; 

    public Move(String name, int difficulty, String description) {
        this.name = name;
        this.difficulty = difficulty;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getDescription() {
        return description;
    }

    public String toString(Move move) {
        String result = "";
        result += "Name: " + move.getName() + " Difficulty: " + move.getDifficulty() + " Description: " + move.getDescription();

        return result;
    }

}