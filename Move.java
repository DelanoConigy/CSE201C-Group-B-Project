

public class Move{

    private String name;
    private Boolean difficulty; // True is hard, false is easy
    private String description;

    public Move(String name, Boolean difficulty, String description) {
        this.name = name;
        this.difficulty = difficulty;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Boolean getDifficulty() {
        return difficulty;
    }

    public String getDescription() {
        return description;
    }

}