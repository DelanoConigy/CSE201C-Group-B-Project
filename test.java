import java.util.*;
/**
 * Test file for Section C Group B
 * 
 * @author Delano Conigy, Rayne Elling, etc
 */

public class test {
	public static void main(String[] args) {
		// Print welcome messages
		Scanner in = new Scanner(System.in);
		System.out.println(
				"Welcome to Go For The Gold! This game tests your skills throughout different gymnastics events.");
		System.out.println(
				"Throughout the game you will be presented with choices. Please type exactly what is displayed in order to give your answer choice.");

		// Start character customization
		System.out.println("It's time to enter the event. Let's customize your character!");
		System.out.println("Please choose a gender: Female or Male");
		String gender = in.next();
		in.nextLine();
		System.out.println("What is your name? Please type your name here: ");
		String name = in.nextLine();
		System.out.println("What country are you competing for? Type the country name here: ");
		String country = in.nextLine();
		System.out.println("Welcome " + name + "! We are so excited to see you here to represent " + country + ".");
		System.out.println("Your gender is " + gender);

		// Start customizing skill levles
		System.out.println(
				"Now it's time to establish your skill level. How confident are you in your abilities? Choose a number 1-10.");
		int confidence = Integer.parseInt(in.next());
		System.out.println("You will now have 20 points to disperse across 3 skills: strength, speed, and balance.");
		System.out.println(
				"The choices you make here will determine how well your athlete performs in certain events. You do not have to use all 20 skill points.");
		int skillPoints = 20;
		System.out
				.println("You have " + skillPoints + " skill points left. How strong are you? Choose a number 0 - 10");
		int tempSkillValue = Integer.parseInt(in.next());
		int strength = tempSkillValue;
		skillPoints -= tempSkillValue;
		System.out.println("You have " + skillPoints + " skill points left. How fast are you? Choose a number 0 - 10");
		tempSkillValue = Integer.parseInt(in.next());
		int speed = tempSkillValue;
		skillPoints -= tempSkillValue;
		System.out.println(
				"You have " + skillPoints + " skill points left. How much balance do you have? Choose a number 0 - 10");
		tempSkillValue = Integer.parseInt(in.next());
		int balance = tempSkillValue;
		skillPoints -= tempSkillValue;
		System.out.println("Your skills are as follows: Strength - " + strength + ", Speed - " + speed + ". Balance - "
				+ balance + ", Confidence: " + confidence);

//		Player p = new Player(gender);

		// Create Player and Rooms
		Player p = new Player(gender, confidence, strength, speed, balance);
		Room pommelRoom = new PommelHorse();
		Room floorRoom = new Floor();
		Room vaultRoom = new Vault();
		Room ringsRoom = new Rings();
		Room unevenBarsRoom = new UnevenBars();
		Room balanceRoom = new BalanceBeam();
		ArrayList<String> rooms = new ArrayList<>();
		rooms.add("Pommel");
		rooms.add("Floor");
		rooms.add("Vault");
		rooms.add("Rings");
		rooms.add("Uneven Bars");
		rooms.add("Balance Beam");

		// Cycle through the rooms
		in.nextLine();
		System.out.print("Now it's time to compete!");
		while (rooms.size() > 0) {
			System.out.println("Pick the event you want to compete in: ");
			for (int i = 0; i < rooms.size() - 1; i++) {
				System.out.print(rooms.get(i) + ", ");
			}
			System.out.println(rooms.get(rooms.size() - 1));
			String room = in.nextLine();
			System.out.println("\n======================================");
			System.out.println("\tStart of " + room);
			System.out.println("======================================");
			if (room.equals("Pommel")) {
				pommelRoom.startRoom(p);
				rooms.remove(rooms.indexOf("Pommel"));
			} else if (room.equals("Floor")) {
				floorRoom.startRoom(p);
				rooms.remove(rooms.indexOf("Floor"));
			} else if (room.equals("Vault")) {
				vaultRoom.startRoom(p);
				rooms.remove(rooms.indexOf("Vault"));
			} else if (room.equals("Rings")) {
				ringsRoom.startRoom(p);
				rooms.remove(rooms.indexOf("Rings"));
			} else if (room.equals("Uneven Bars")) {
				unevenBarsRoom.startRoom(p);
				rooms.remove(rooms.indexOf("Uneven Bars"));
			} else if (room.equals("Balance Beam")) {
				balanceRoom.startRoom(p);
				rooms.remove(rooms.indexOf("Balance Beam"));
			} else {
				System.out.println("Please enter a valid input");
			}
		}

		System.out.println("Congratulations! You have competed in all the events.");
	}
}