//Test file for Section C Group B
import java.util.Scanner;

public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	System.out.println("Welcome to Go For The Gold! This game tests your skills throughout different gymnastics events.");
	System.out.println("Throughout the game you will be presented with choies. Pleas type exactly what is displayed in order to give your answer choice.");
	System.out.println("It's time to enter the event. Would you like to customize your character?");
	System.out.println("Please choose a gender: Female or Male");
	String gender = in.next();
	System.out.println("What is your name? Please type your name here: ");
	String name = in.nextLine();
	System.out.println("What country are you competing for? Type the country name here: ");
	String country = in.nextLine();

	System.out.println("Now it's time to establish your skill level. How confident are you in your abilities? Choose a number 1-10.");
	double confidence = in.next();
	System.out.println("You will now have 20 points to disperse across 3 skills: strength, speed, and balance. The choices you make here will determine how well your athlete performs in certain events. You do not have to use all 20 skill points.")
	int skillPoints = 20;
	System.out.println("You have " + skillPoints + " skill points left. How strong are you? Choose a number 0 - " + skillPoints);
	int tempSkillValue = in.next();
	int strength = tempSkillValue;
	skillPoints -= tempSkillValue;
	System.out.println("You have " + skillPoints + " skill points left. How fast are you? Choose a number 0 - " + skillPoints);
	tempSkillValue = in.next();
	int speed = tempSkillValue;
	skillPoints -= tempSkillValue;
	System.out.println("You have " + skillPoints + " skill points left. How much balance do you have? Choose a number 0 - " + skillPoints);
	tempSkillValue = in.next();
	int balance = tempSkillValue;
	skillPoints -= tempSkillValue;	

	

	Player p = new Player(gender);
	Room balance = new BalanceBeam();

	balance.startRoom();
}