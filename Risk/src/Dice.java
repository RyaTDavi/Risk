//Jacob Paluso, Noah Rudolph, Harrison Grogan, Ryan Davis
//Risk

import java.util.Random;

//this class defines how the dice work in the game
public class Dice {
	private int diceResult;
	Random rand = new Random();

	public Dice() {
		diceResult = -1;
	}

	public int getDiceResult() {
		return diceResult;
	}

	public void setDiceResult(int x) {
		diceResult = x;
	}

	public void rollDice() {
		diceResult = rand.nextInt(6) + 1;
	}

	public String toString() {
		return "This dice rolled a " + diceResult;
	}
}
