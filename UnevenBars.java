import java.util.*;

/**
 * Class to control the Uneven Bars event
 * 
 * @author Delano Conigy 
 */


public class UnevenBars extends Room {
    //private Skills[];
    private int elapsedTime;
    //private int comboScore;
    private int gripStrength;
    //private String playerChoice;
    private String roomDescription;
    private String instructions;
    private String instructions2;
    private String moveChoice;
    private int barsScore;
    private int moveCount;
    private String[] messages = new String[6];
    private TreeMap<String, Move> moves = new TreeMap<>();

    // Could be a class named Combo but their will only be 
    // like three combos and they heavily differ in length
    private ArrayList<Move> combo1 = new ArrayList<>();
    private ArrayList<Move> combo2 = new ArrayList<>();
    private ArrayList<Move> combo3 = new ArrayList<>();


    public UnevenBars(){
        this.roomDescription = "Welcome to the balance beam event! Throughout this event you will be provided with choices of which move to make. These moves are dependent on your skill, confidence levels, and grip strength so choose wisely.";
        this.instructions = "Please choose the following moves, be wary that each move has a chance of failing!";
        this.instructions2 = "[1] to perform a premade combo, [2] to string  together you own moves, or [3] to hang";
        this.moveChoice = ""; // this will just be the user input
        this.gripStrength = 20;
        this.barsScore = 0;
        this.moveCount = 0;
        this.elapsedTime = 0; // the amount of time you have to perform each move 
    }

    public void displayCombo(ArrayList<Move> combo, int comboNum){
        System.out.println("Moves in Combo " + comboNum + ":");
        for (int i = 0; i < combo.size(); i++){
            System.out.print(combo.get(i).toString());
        }
        System.out.println(); // adds a new line
    }

    public void loadMoves(){
        Move jumpUp = new Move("Jump up", 1, "They jump!");

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
        return instructions;
    }

    public void updateScore(double amount){
        barsScore += amount;
        setScore(barsScore / moveCount);
    }


    private void storeMessages(){
        messages[0] = this.roomDescription;
        messages[1] = this.instructions;
        messages[2] = this.instructions2;
        messages[3] = this.moveChoice;
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
        
        //endRoom();
    }


}
