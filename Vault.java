/*
* This class is for the Vault event, for right now it just show's you 
*"You are entering the vault event"
* @Author Adash Bhattarai
*/

import java.util.*;

public class Vault extends Room{
	private String rules;
	
	
	//Constructor
	public Vault() {
		rules = "You are entering the vault event";
	}
	
	/**
	 * The method that will the run the room and call methods that handle cases.
	 */
	@Override
	public void startRoom(Player player) {
	
		System.out.println(rules);

		System.out.println("You are exiting the Vault event");
	}
}
