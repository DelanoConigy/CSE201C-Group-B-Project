import java.util.*;

/**
 * Test file for Section C Group B
 *
 * @author Delano Conigy, Rayne Elling, etc
 */
public class GoForGold {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(
                "Welcome to Go For The Gold!\nThis game tests your skills throughout different gymnastics events.");
        System.out.println(
                "Throughout the game you will be presented with choices. Please type exactly what is displayed in order to give your answer choice.\n");

        // Start character customization
        System.out.println("It's time to enter the event. Let's customize your character!");
        System.out.println("Please choose a gender: Male, Female, or Other");

        String gender = "";
        boolean validGender = false; // Renamed variable for valid gender input

        // Loop until a valid gender input is provided
        while (!validGender) {
            gender = in.next();
            in.nextLine(); // Clear the buffer
            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")
                    || gender.equalsIgnoreCase("Other")) {
                validGender = true; // Exit loop when a valid input is entered
            } else {
                System.out.println("Invalid input. Please choose from: Male, Female, or Other.");
            }
        }
        System.out.println("What is your name? Please type your name here: ");
        String name = in.nextLine();
        System.out.println("What country are you competing for? Type the country name here: ");
        String country = in.nextLine();
        System.out.println("\nWelcome " + name + "! We are so excited to see you here to represent " + country + ".");
        System.out.println("Your gender is " + gender + ".\n");

        // Start customizing skill levels
        System.out.println(
                "Now it's time to establish your skill level. How confident are you in your abilities? Choose a number 1-10.");

        int confidence = 0;
        boolean validConfidence = false;

        // Loop until a valid confidence level (1-10) is entered
        while (!validConfidence) {
            try {
                confidence = Integer.parseInt(in.next());
                if (confidence >= 1 && confidence <= 10) {
                    validConfidence = true; // Valid confidence input, exit loop
                } else {
                    System.out.println("Please enter a number between 1 and 10 for confidence.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number between 1 and 10 for confidence.");
            }
        }

        System.out.println("\nYou will now have 20 points to disperse across 3 skills: strength, speed, and balance.");
        System.out.println(
                "The choices you make here will determine how well your athlete performs in certain events. You do not have to use all 20 skill points.");
        int skillPoints = 20;

        int strength = 0, speed = 0, balance = 0;
        boolean validInput = false;

        // Get strength value
        while (!validInput) {
            System.out.println(
                    "\nYou have " + skillPoints + " skill points left. How strong are you? Choose a number 0 - 10");
            try {
                int tempSkillValue = Integer.parseInt(in.next());
                if (tempSkillValue >= 0 && tempSkillValue <= 10) {
                    strength = tempSkillValue;
                    skillPoints -= tempSkillValue;
                    validInput = true;
                } else {
                    System.out.println(
                            "Invalid input! Strength value cannot exceed 10. Please choose a number between 0 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number between 0 and 10 for strength.");
            }
        }
        validInput = false;

        // Get speed value
        while (!validInput) {
            System.out.println(
                    "\nYou have " + skillPoints + " skill points left. How fast are you? Choose a number 0 - 10");
            try {
                int tempSkillValue = Integer.parseInt(in.next());
                if (tempSkillValue >= 0 && tempSkillValue <= skillPoints && tempSkillValue <= 10) {
                    speed = tempSkillValue;
                    skillPoints -= tempSkillValue;
                    validInput = true;
                } else if (tempSkillValue > 10) {
                    System.out.println(
                            "Invalid input! Speed value cannot exceed 10. Please choose a number between 0 and 10.");
                } else {
                    System.out.println("Invalid input! You can only use " + skillPoints + " skill points. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input! Please enter a valid number between 0 and " + skillPoints + " for speed.");
            }
        }
        validInput = false;

        // Get balance value
        while (!validInput) {
            System.out.println("\nYou have " + skillPoints
                    + " skill points left. How much balance do you have? Choose a number 0 - 10");
            try {
                int tempSkillValue = Integer.parseInt(in.next());
                if (tempSkillValue >= 0 && tempSkillValue <= skillPoints && tempSkillValue <= 10) {
                    balance = tempSkillValue;
                    skillPoints -= tempSkillValue;
                    validInput = true;
                } else {
                    if (tempSkillValue > 10) {
                        System.out.println("Invalid input! The maximum amount of skill points to one category is 10.");
                    } else if (tempSkillValue > 10) {
                        System.out.println(
                                "Invalid input! Balance value cannot exceed 10. Please choose a number between 0 and 10.");
                    } else {
                        System.out.println(
                                "Invalid input! You can only use " + skillPoints + " skill points. Try again.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input! Please enter a valid number between 0 and " + skillPoints + " for balance.");
            }
        }

        System.out
                .println("\nYour skills are as follows: Strength - " + strength + ", Speed - " + speed + ". Balance - "
                        + balance + ", Confidence: " + confidence);

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
        System.out.print("\nNow it's time to compete!\n");
        while (rooms.size() > 0) {
            System.out.println("Your current score is: " + p.getPoints());
            if (p.getPoints() <= 50) {
                System.out.println("You are " + (50 - p.getPoints()) + " points away from winning Bronze!");
            } else if (p.getPoints() <= 100) {
                System.out.println("You are " + (100 - p.getPoints()) + " points away from winning Silver!");
            } else if (p.getPoints() <= 150) {
                System.out.println("You are " + (150 - p.getPoints()) + " points away from winning Gold!");
            }
            // System.out.println("Your skills are as follows: Strength - " + strength + ",
            // Speed - " + speed + ". Balance - "
            // + balance + ", Confidence: " + confidence);
            System.out.println("\nPick the event you want to compete in: ");
            for (int i = 0; i < rooms.size() - 1; i++) {
                System.out.print(rooms.get(i) + ", ");
            }
            System.out.println(rooms.get(rooms.size() - 1));
            String room = in.nextLine();

            // Make the comparison case-insensitive using equalsIgnoreCase
            boolean validRoom = false;

            for (String validRoomName : rooms) {
                if (room.equalsIgnoreCase(validRoomName)) {
                    validRoom = true;
                    break;
                }
            }

            if (validRoom) {
                System.out.println("\n======================================");
                System.out.println("\tStart of " + room);
                System.out.println("======================================");
            }

            // Handle the room selection with case-insensitive comparisons
            if (room.equalsIgnoreCase("Pommel")) {
                pommelRoom.startRoom(p);
                rooms.remove("Pommel");
            } else if (room.equalsIgnoreCase("Floor")) {
                floorRoom.startRoom(p);
                rooms.remove("Floor");
            } else if (room.equalsIgnoreCase("Vault")) {
                vaultRoom.startRoom(p);
                rooms.remove("Vault");
            } else if (room.equalsIgnoreCase("Rings")) {
                ringsRoom.startRoom(p);
                rooms.remove("Rings");
            } else if (room.equalsIgnoreCase("Uneven Bars")) {
                unevenBarsRoom.startRoom(p);
                rooms.remove("Uneven Bars");
            } else if (room.equalsIgnoreCase("Balance Beam")) {
                balanceRoom.startRoom(p);
                rooms.remove("Balance Beam");
            } else {
                System.out.println("Please enter a valid input");
            }
        }

        System.out.println("Congratulations! You have competed in all the events.");

        System.out.println("Your total score is: " + p.getPoints());

        if (p.getPoints() <= 150 && p.getPoints() > 100) {
            System.out.println("Congratulations! You have earned a gold medal for " + country + "!");
        } else if (p.getPoints() <= 100 && p.getPoints() > 50) {
            System.out.println("Congratulations! You have earned a silver medal for " + country + "!");
        } else {
            System.out.println("Congratulations! You have earned a bronze medal for " + country + "!");
        }
    }
}
