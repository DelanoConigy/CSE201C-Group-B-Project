/**
 * Class to control the rings event.
 * 
 * @author Zak Soryal
 */


public class Rings extends Room {
    private int totalRingScore = 0;
    private int stamina = 100;
    private String[] moves = {"Back Lever", "Iron Cross", "L-sit", "Front Tuck"}

    public Rings() {
        super("Rings");
    }

    public void rules() {
        System.out.println("First, select a move to perform. Then, choose 
        how long you want to hold it. The longer you hold, the more points 
        you'll get! Watch your stamina, though; the event is over once you
        run out.");
    }

    public int chooseMove() {}

    public void performMove(int move, Player player) {}

    @Override
    public void startRoom(Player player) {
        rules(); // Show specific rules for Rings
        while (stamina > 0) {
            int moveIndex = chooseMove();
            performMove(moveIndex, player);
        }
        //endRoom(totalRingScore);
        //System.out.println("You're out of stamina. Event over! Your total score: " + totalRingScore);
    }
}
