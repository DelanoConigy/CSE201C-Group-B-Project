/*
* this class if for a event Vault, for right now it just show's you 
*"You are entering the vault event"
* @Author Adash Bhattarai
*/

import java.util.Scanner;


public class Vault extends Room{
	private String rules;
	
	
	//Constructor
	public Vault() {
		rules = "You are entering the vault event";
	}
	

@Override
	public void startRoom(Player player) {
	
	System.out.println(rules);
	
	
	
		
		
		
		
	}

	
}
