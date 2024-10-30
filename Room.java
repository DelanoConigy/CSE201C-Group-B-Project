/**
 * An abstract class to provide a basis for operations and attributes that should be in between each Room. 
 * 
 * @author Delano Conigy, etc
 */


public abstract class Room {
    private double score;

    //Constructor
    public Room() {
        this.score = 0;
    }

    public double getScore(){
        return score;

    }

    public void setScore(double s){
        score = s;
    }

    public void startRoom(Player player) {
    }

    public void endRoom(int roomScore) {
        // adds the individual room score to the overall score
    }












}