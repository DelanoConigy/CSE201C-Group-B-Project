/**
 * Class to contain and modify attributes of player inside an object. 
 * 
 * @author Delano Conigy, etc
 */

public class Player{

    private final int DEFAULT_CONFIDENCE = 20; 

    private String gender;
    private int confidence; 
    private int points; 

    public Player(String gender){
        this.gender = gender;
        this.confidence = DEFAULT_CONFIDENCE; 
        this.points = 0;
    }

    public String getGender(){
        return this.gender;
    }

    public int getConfidence(){
        return this.confidence;
    }

    public void setConfidence(int confidence){
        if (this.confidence + confidence <= 0){
            this.confidence = 0;
        } else {
            this.confidence += confidence;
        }
    }

    public void addConfidence(int confidence){
        if (this.confidence + confidence >= 100){
            this.setConfidence(100);
        } else{
            this.setConfidence(this.confidence + confidence);
        }
    }
}