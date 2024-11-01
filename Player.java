/**
 * Class to contain and modify attributes of player inside an object. 
 * 
 * @author Delano Conigy, Daktoa Oudeman, etc
 */

public class Player{ 

    private String gender;
    private int confidence, points, strength, speed, balance; 

    public Player(String gender, int confidence, int strength, int speed, int balance){
        this.gender = gender;
        this.confidence = confidence; 
        this.strength = strength;
        this.speed = speed;
        this.balance = balance;
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
        } else if(confidence >= 100){
            this.confidence = 100;
        } 
        else {
            this.confidence = confidence;
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