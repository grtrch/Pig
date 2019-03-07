=
//Garrett Richardson
/*
 * A program that demonstrates the game "PIG" with system I/O.
 * The program ends when the score is greater than 100 and asks the user 
 * if they wish to play again.
 */
import java.util.*;
import javax.swing.JOptionPane;

public class Assn1_14gsr2 {
		
	// The main starts the game PIG and ends when a game score is greater than 100
	public static void main(String[] args) {
		boolean play = true;
		int HmnGameScore = 0; // Holds the human's overall score
		int CmpeGameScore = 0; // Holds the computer's overall score
		
		// loop allows player to choose to play again
		while (play) {
			JOptionPane.showMessageDialog(null, "Welcome to the game PIG! Press Ok to roll!. ");
			
			// loop allows game to continue until a score is greater than 100
			while (HmnGameScore < 100 && CmpeGameScore < 100) {
				HmnGameScore = humanTurn(HmnGameScore);
				
				// breaks loop after human wins before computer's turn
				if (HmnGameScore >= 100) {
					break;
				} // end if
				
				System.out.println("\n Your turn is over and your game score is: " + HmnGameScore + "\n");
				CmpeGameScore = cmpeTurn(CmpeGameScore);
				System.out.println("\n Computer's turn is over and computer's game score is: " + CmpeGameScore + "\n");
			} // end while
			
				// asks user if they wish to play again if they win
			if (HmnGameScore >= 100) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "You WON! Do you wish to play again","Congratulations", dialogButton);
				// checks if player chooses yes to play again
				if (dialogResult == JOptionPane.YES_OPTION) {
					HmnGameScore = 0;
					CmpeGameScore = 0;
				} // end if
				else {
					play = false;
				}
			} // end if
			// asks user if they wish to play again if they lose
			if (CmpeGameScore >= 100) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "You Lost. Do you wish to play again",
						"You lost.", dialogButton);
				// checks if player chooses to play again
				if (dialogResult == JOptionPane.YES_OPTION) {
					HmnGameScore = 0;
					CmpeGameScore = 0;
				} // end if 
				else {
					play = false;
				}
			} // end if
		} // end while
	}
	
	// method is for computer's roll
	private static int cmpeTurn(int CmpeGameScore) {
		
		System.out.println("\n It's the computer's turn.\n");
		
		int roll1 = randomInteger(); // holds random roll from computer
		int roll2 = randomInteger(); // holds the second random roll from computer
		String Wroll1 = numConverter(roll1); // holds string from first roll
		String Wroll2 = numConverter(roll2); // holds string from second roll
		int cmpeTurnScore = roll1 + roll2; // sums both rolls
		
		System.out.println("Computer rolled a " + Wroll1 + " and a " + Wroll2);
		
		// checks to see if any rolls were one
		while (roll1 != 1 && roll2 != 1) {
			
			System.out.println("The computer's turn score is " + cmpeTurnScore + " and the computer's game score is " + (cmpeTurnScore + CmpeGameScore));
			
			// checks to see if doubles were rolled
			if (roll1 != roll2) {
				// setting the computer logic for it to roll again
				if (cmpeTurnScore > 25) {
					break;
				} // end if
			} // end if
			
			else {
				System.out.println("Computer rolled doubles.");
			}
			
			roll1 = randomInteger(); // holds random roll from computer
			roll2 = randomInteger(); // holds random roll from computer
			System.out.println("Computer rolled a " + numConverter(roll1) + " and a " + numConverter(roll2));
			cmpeTurnScore += roll1 + roll2; // sums up computer's turn score
			
			// checks to see if the turn score added with the
			// game score is greater than 100
			int win = cmpeTurnScore + CmpeGameScore;
				if (win >= 100) {
				return 100;
			} // end if
		} // end wile
			// returns values from method
		return conditions(roll1, roll2, CmpeGameScore, cmpeTurnScore);
	} // end cmpeTurn method
	
	
		// method is for humans roll
	private static int humanTurn(int HmnGameScore) {
		
		System.out.println("\n Your Turn.\n"); 
		
		int roll1 = randomInteger(); // holds random roll from player
		int roll2 = randomInteger(); // holds random roll from player
		int HmnTurnScore = roll1 + roll2; // sums both rolls
		
		System.out.println("You rolled a " + numConverter(roll1) + " and a " + numConverter(roll2));
		
		// checks to see if any rolls were one
		while (roll1 != 1 && roll2 != 1) {
			System.out.println( "Your turn score is " + HmnTurnScore + " and your game score is " + (HmnTurnScore + HmnGameScore));
			
			//checks to see if player rolled doubles 
			if (roll1 != roll2) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Do you wish to roll again", "Your Turn",
						dialogButton);
				//checks to see if player does not want to roll again
				if (dialogResult == JOptionPane.NO_OPTION) {
					break;
				} //end if
			} //end if 
			else{
				System.out.println("You rolled doubles.");
			}
			roll1 = randomInteger(); // holds random roll from player
			roll2 = randomInteger(); // holds random roll from player
			System.out.println("You rolled a " + numConverter(roll1) + " and a " + numConverter(roll2));
			HmnTurnScore += roll1 + roll2; //sums player's turn score from both rolls
			int win = HmnTurnScore + HmnGameScore; // sums up turn score and game score
			
			// checks to see if turn score and game score are greater than 100
			if (win >= 100) {
				return 100;
			} // end if
		} // end while
		
			// returns values from method
		return conditions(roll1, roll2, HmnGameScore, HmnTurnScore);
	} // end humanTurn method
		
	// This method obtains a random integer from 1-6
	public static int randomInteger() {
		Random dice = new Random();
		int Num1 = 1 + dice.nextInt(6); // holds random integer from 1-6
		return Num1;
	} // end RandomInteger method
	
	//Method checks if two ones were rolled or one
	public static int conditions(int roll1, int roll2, int Score, int TurnScore) {
		
		//checks to see if two ones were rolled
		if (roll1 == 1 && roll2 == 1) {
			System.out.println("Rolled two ones. Game score is set to zero and Turn is over. ");
			JOptionPane.showMessageDialog(null, "Two ones were rolled. ");
			Score = 0; //sets game score to 0
			return Score;
		} //ends if 
		
		//if only one 1 was rolled
		if (roll1 == 1 || roll2 == 1) {
			TurnScore = 0; //sets turn score to 0
			System.out.println("Rolled a one. Turn Score is now 0, and turn is over.");
			JOptionPane.showMessageDialog(null, "A one was rolled. ");
		} //ends if
		
		return Score + TurnScore;
		
	} //end conditions method
	
	
	//method takes a number from 1-6 and converts the number to a string
	public static String numConverter(int roll) {
	String Nums[] = {"one", "two", "three", "four", "five", "six"}; //array of strings
	return Nums[roll-1];
	} //end if
	
} 
