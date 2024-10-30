import java.util.Scanner;

/**
 * Class to control the balance beam event.
 * 
 * @author Rayne Elling
 */


public class BalanceBeam extends Room {
    //private Skills[];
    private int totalBeamScore;
    //private int comboScore;
    private int movesLeft;
    //private String playerChoice;
    private String roomDescription;
    private String instructions;
    private String chooseSkill;
    private String[] messages = new String[6];
    private Move[] skills = new Move[6];
    private int count;


    public BalanceBeam(){
        roomDescription = "Welcome to the balance beam event! Throughout this event you will be provided with choices of which move to make.These moves are dependent on your skill and confidence levels, so choose wisely.";
        instructions = "Please choose the following.";
        chooseSkill = "You walk up to the beam. You have the choice to perform a cartwheel to mount. Do you choose to perform this event? Please type Yes or No";
        totalBeamScore = 0;
        movesLeft = 6; 
    }

    public void loadSkills(){
        Move one = new Move("Jump up", 1.0, "They jump!");
        skills[0] = one;

    }

    public boolean performSkill(int skillNum){
        return false;
    }

    public int calculatePerformance(){
        return 0;
    }

    public void updateCount(int n){
        count += n;
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
        messages[0] = roomDescription;
        messages[1] = instructions;
        messages[2] = chooseSkill;
    }

    private void showMessage(int i){
        System.out.println(messages[i]);
    }
    
    private void displayOptions(Scanner in) {
        String input;
        boolean valid = true;
        do {
            showMessage(2);
            input = in.next();
            if(input.equals("Score")){
                //showScore();
                valid = false;
            } else if (input.equals("Skills")){
                //showSkills();
                valid = false;
            } else if(input.equals("Yes")){
                performSkill(count);
                valid = false;
            } else if (input.equals("No")){
                count++;
                valid = false;
            } else {
                System.out.println("Please enter a valid input");
            }
        } while(valid);
    }

@Override
    public void startRoom(Player player) {
        storeMessages();
        showMessage(0); // Shows welcome message
        loadSkills();
        showMessage(1); // Shows instructions
        //showMessage(2); // Let's begin. Describe start and first move.
        Scanner in = new Scanner(System.in);
        displayOptions(in);
        System.out.println(skills[0].getName() + ", " + skills[0].getDifficulty() + ", " + skills[0].getDescription());
        
        //endRoom();
    }


}
