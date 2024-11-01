import java.util.*;

/**
 * Class to control the balance beam event.
 * 
 * @author Rayne Elling
 */


public class UnevenBars extends Room {
    //private Skills[];
    private int elapsedTime;
    //private int comboScore;
    private int gripStrength;
    //private String playerChoice;
    private String roomDescription;
    private String instructions;
    private String moveChoice;
    private String[] messages = new String[6];
    private TreeSet<Move> moves = new TreeSet<>();

    // Could be a class named Combo but their will only be 
    // like three combos and they heavily differ in length
    private ArrayList<Move> combo1 = new ArrayList<>();

    // an array of ArrayLists, each arrayList is a combo, easy medium hard
    private ArrayList<Move>[] combos = new ArrayList[3];


    public UnevenBars(){
        this.roomDescription = "Welcome to the balance beam event! Throughout this event you will be provided with choices of which move to make.These moves are dependent on your skill and confidence levels, so choose wisely.";
        this.instructions = "Please choose the following moves, be wary that each move has a chance of failing!";
        this.moveChoice = ""; // this will just be the user input
        this.gripStrength = 20;
        this.barsScore = 0;
        this.movesLeft = 6; 
        this.elapsedTime = 0; // the amount of time you have to perform each move 
    }

    public void loadMoves(){
        Move jumpUp = new Move("Jump up", 1.0, "They jump!");

    }

    public boolean performSkill(int skillNum){
        return false;
    }

    public void increaseTime(int n){
        this.elapsedTime += n;
    }

    public int getTime(){
        return this.elapsedTime;
    }

    public String getRoomDescription(){
        return roomDescription;
    }

    public String getInstructions(){
        return null;
    }

    public void updateScore(double amount){
        totalBeamScore += amount;
        setScore(totalBeamScore / count);
    }


    private void storeMessages(){
        messages[0] = this.roomDescription;
        messages[1] = this.instructions;
        messages[2] = this.moveChoice;
    }

    private void showMessage(int i){
        System.out.println(messages[i]);
    }
    
    private void displayOptions(Scanner in) {

    }


@Override
    public void startRoom(Player player) {
        storeMessages();
        showMessage(0); // Shows welcome message
        loadMoves();
        showMessage(1); // Shows instructions
        //showMessage(2); // Let's begin. Describe start and first move.
        Scanner scan = new Scanner(System.in);
        displayOptions(scan);
        System.out.println(skills[0].getName() + ", " + skills[0].getDifficulty() + ", " + skills[0].getDescription());
        
        //endRoom();
    }


}
