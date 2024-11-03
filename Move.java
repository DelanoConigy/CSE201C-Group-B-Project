

public class Move{

    private String name;
    private int difficulty; 
    private String description; 

    public Move(String name, int difficulty, String description) {
        this.name = name;
        this.difficulty = difficulty; // scale from 0 to 100
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