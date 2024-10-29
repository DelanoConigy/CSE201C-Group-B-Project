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
	private String playerChoice;
	private String roomDescription;
	private String instructions;
	private String[] messages = new String[6];
	private Move[] skills = new Move[6];


	public BalanceBeam(){
		roomDescription = "Welcome to the balance beam event! Throughout this event you will be provided with choices of which move to make.These moves are dependent on your skill and confidence levels, so choose wisely.";
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

	public updateScore(double amount){
		totalBeamScore += amount;
		score = totalBeamScore / count;
	}


	private void storeMessages(){
		messages[0] = roomDescription;
	}

	private void showMessage(int i){
		System.out.println(messages[i]);
	}

@Override
    public void startRoom(Player player) {
		storeMessages();
        showMessage(0); // Shows welcome message
        //loadSkills();
		showMessage(1); // Shows instructions
		showMessage(2); // Let's begin. Describe start and first move.
		Scanner in = new Scanner(System.in);
		String input = in.next;
		boolean valid = true;
		while(valid){
			if(input.equals("Score")){
				showScore();
			} else if (input.equals("Skills")){
				showSkills();
			} else if(input.equals("Yes")){
				performSkill(count);
			} else if (input.equals("No")){
				count++;
			} else {
				System.out.println("Please enter a valid input");
			}
		}
		
		
		


        //endRoom(totalRingScore);
	}










}
