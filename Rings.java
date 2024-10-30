import java.util.Scanner;

/**
 * Class to control the rings event.
 * 
 * @author Zak Soryal
 */


public class Rings extends Room {
    private int totalRingScore;
    private int stamina;
    private String[] moves = {"Back Lever", "Iron Cross", "L-sit", "Front Tuck"};
    private String rules = "First, select a move to perform. Then, choose \r\n"
    		+ "how long you want to hold it. The longer you hold, the more points \r\n"
    		+ "you'll get! Watch your stamina, though; the event is over once you\r\n"
    		+ "run out.";

    public Rings() {
    	totalRingScore = 0;
    	stamina = 100;
    }

    public int chooseMove() {
    	System.out.println("Pick a move! Enter its corresponding number.");
    	int i = 1;
        for (String move : moves) {
            System.out.println(i + ". " +move);
            i++;
        }

        Scanner s = new Scanner(System.in);
        int choice = -1;

        while (choice < 1 || choice > moves.length) {
            try {
                System.out.print("Enter your choice (1-4): ");
                choice = s.nextInt();
                if (choice < 1 || choice > moves.length) {
                    System.out.println("Invalid choice. Please select a number between 1 and " + moves.length + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and " + moves.length + ".");
                s.next(); // Clear invalid input
            }
        }

        System.out.println("You chose: " + moves[choice - 1]);
        return choice - 1; // return index of the chosen move
    }

    public void performMove(int move, Player player) {}

    @Override
    public void startRoom(Player player) {
        System.out.println(rules); // Show specific rules for Rings
        //while (stamina > 0) {
            int moveIndex = chooseMove();
            //performMove(moveIndex, player);
        //}
            
            
        //endRoom(totalRingScore);
        //System.out.println("You're out of stamina. Event over! Your total score: " + totalRingScore);
    }

    public static void main(String[] args) {
        Rings rings = new Rings();
        Player player = new Player("Male");
        rings.startRoom(player);
    }
}
