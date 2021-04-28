//Jacob Paluso, Noah Rudolph, Harrison Grogan, Ryan Davis
//Risk

import java.awt.Color;

//this class defines the player and implements all the functionality of a player.
public class Player {

	private Color teamColor;
	private boolean turn;
	private int cardCount;
	private int territoryCount;
	private int reinforcementCount;
	private static boolean match;
	protected Card[] playerHand = new Card[5];
	protected Tile[] countriesOwned = new Tile[42];
	private static int soldierCount;
	private static int calvaryCount;
	private static int cannonCount;
	private static int wildCount;
	boolean phase1;
	boolean phase2;
	boolean phase3;
	String playerName;

	boolean northAmerica;
	boolean southAmerica;
	boolean africa;
	boolean europe;
	boolean asia;
	boolean australia;

	public Player(String name) {
		teamColor = new Color(205, 183, 142);
		turn = false;
		cardCount = 0;
		territoryCount = 0;
		reinforcementCount = 0;
		match = false;
		soldierCount = 0;
		calvaryCount = 0;
		cannonCount = 0;
		wildCount = 0;
		phase1 = false;
		phase2 = false;
		phase3 = false;
		playerName = name;

		for (int i = 0; i < playerHand.length; i++) {
			playerHand[i] = null;
		}

		for (int i = 0; i < countriesOwned.length; i++) {
			countriesOwned[i] = null;
		}
	}
        
        //getters and setters
	public String getPlayerName() {
		return playerName;
	}

	public Color getTeamColor() {
		return teamColor;
	}

	public boolean IsTurn() {
		return turn;
	}

	public boolean isPhase1() {
		return phase1;
	}

	public boolean isPhase2() {
		return phase2;
	}

	public boolean isPhase3() {
		return phase3;
	}

	public int getCardCount() {
		return cardCount;
	}

	public int getTeritoryCount() {
		return territoryCount;
	}

	public int getReinforcementCount() {
		return reinforcementCount;
	}

	public boolean hasMatch() {
		return match;
	}

	public int getSoldierCount() {
		return soldierCount;
	}

	public int getCalvaryCount() {
		return calvaryCount;
	}

	public int getCannonCount() {
		return cannonCount;
	}

	public int getWildCount() {
		return wildCount;
	}

	public Card[] getPlayerHand() {
		return playerHand;
	}

	public Tile[] getCountriesOwned() {
		return countriesOwned;
	}

	public void setTeamColor(Color color) {
		teamColor = color;
	}

	public void setIsTurn(boolean x) {
		turn = x;
	}

	public void setIsPhase1(boolean x) {
		phase1 = x;
	}

	public void setIsPhase2(boolean x) {
		phase2 = x;
	}

	public void setPlayerName(String x) {
		playerName = x;
	}

	public void setIsPhase3(boolean x) {
		phase3 = x;
	}

	public void setCardCount(int cardAmount) {
		cardCount = cardAmount;
	}

	public void setTeritoryCount(int teritoryAmount) {

		territoryCount = teritoryAmount;
	}

	public void setTeritoryCount(Tile[] tiles) {
		territoryCount = 0;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i].getCurrentOwner() == this) {
				territoryCount++;
			}
		}
	}

	public void setReinforcementCount(int reinforcementAmount) {
		reinforcementCount = reinforcementAmount;
	}

	public void setMatch(boolean x) {
		match = x;
	}

	public void setSoldierCount(int x) {
		soldierCount = x;
	}

	public void setCalvaryCount(int x) {
		calvaryCount = x;
	}

	public void setCannonCount(int x) {
		cannonCount = x;
	}

	public void setWildCount(int x) {
		wildCount = x;
	}

	public void setCountriesOwned(Tile x) {
		int iterator = 0;

		while (iterator <= 41) {
			if (countriesOwned[iterator] == null) {
				countriesOwned[iterator] = x;
			} else {
				iterator++;
			}
		}
	}

	// This method will initiate an attack from one Tile to another
        //attackingFrom: Tile that the current player is attacking from
        //attackingAt: Tile that the current player is attacking. 
        //5 dice objects
        //the number of dice the attacking chooses to use from 1 to 3
	public void attack(Tile attackingFrom, Tile attackingAt, Dice d1, Dice d2, Dice d3, Dice d4, Dice d5,
			int diceAttackAmount) {
		String af = attackingFrom.getName().replaceAll("_", " ");
		String aa = attackingAt.getName().replaceAll("_", " ");
		RiskMenu.consoleText.append("\n" + getPlayerName() + " attacked " + aa + " from " + af);
		int result = -1;

		// resets the dice to a prerolled state
		d1.setDiceResult(-1);
		d2.setDiceResult(-1);
		d3.setDiceResult(-1);
		d4.setDiceResult(-1);
		d5.setDiceResult(-1);

		// checks that there are enough attacking soldiers for 1 2 or 3 dice to be
		// rolled
		if (attackingFrom.getCurrentSoldierCount() > 3) {
			if (diceAttackAmount == 1) {
				d1.rollDice();
			} else if (diceAttackAmount == 2) {
				d1.rollDice();
				d2.rollDice();
			} else {
				d1.rollDice();
				d2.rollDice();
				d3.rollDice();
			}
		} // checks to see if there are enough attacking soldiers for up to 2 dice to be
			// rolled
		else if (attackingFrom.getCurrentSoldierCount() == 3) {
			if (diceAttackAmount > 2) {
				diceAttackAmount = 2;
			}

			if (diceAttackAmount == 1) {
				d1.rollDice();
			} else {
				d1.rollDice();
				d2.rollDice();
			}
		} // Only enough attackers for one dice to be rolled
		else if (attackingFrom.getCurrentSoldierCount() == 2) {
			if (diceAttackAmount > 1) {
				diceAttackAmount = 1;
			}

			d1.rollDice();
		} // catches someone who attempts to attack from a place with only 1 soldier
			// in case this isn't handled somewhere else.
		else {
			System.out.println("Not enough soldiers to attack");
		}

		// checks to see if there are enough defending soldiers for either 1 or 2 dice.
		if (attackingAt.getCurrentSoldierCount() >= 2) {
			d4.rollDice();
			d5.rollDice();
		} else if (attackingAt.getCurrentSoldierCount() == 1) {
			d4.rollDice();
		} // No defenders so no defending dice are rolled
		else {

		}

		// ensures there are enough soldiers to fight before calling the method that
		// will calculate the dice result
		if (attackingAt.getCurrentSoldierCount() > 0 && attackingFrom.getCurrentSoldierCount() > 1) {
			result = calculateDiceResult(d1, d2, d3, d4, d5);
		}

		// removes 2 soldiers from the attackers
		if (result == 0) {
			attackingFrom.setCurrentSoldierCount(attackingFrom.getCurrentSoldierCount() - 2);
			RiskMenu.consoleText.append("\nbut lost 2 soldiers.");
		} // removes 1 soldier from attackers and defenders
		else if (result == 1) {
			attackingFrom.setCurrentSoldierCount(attackingFrom.getCurrentSoldierCount() - 1);
			attackingAt.setCurrentSoldierCount(attackingAt.getCurrentSoldierCount() - 1);
			RiskMenu.consoleText.append("\nand defeated 1 soldier but lost 1 as well.");
		} // removes 2 solders from defenders
		else if (result == 2) {
			attackingAt.setCurrentSoldierCount(attackingAt.getCurrentSoldierCount() - 2);
			RiskMenu.consoleText.append("\nand defeated 2 soldiers.");
		} // removes 1 solder from defenders
		else if (result == 3) {
			attackingAt.setCurrentSoldierCount(attackingAt.getCurrentSoldierCount() - 1);
			RiskMenu.consoleText.append("\nand defeated 1 soldier.");
		} // removes 1 soldier from the attackers
		else {
			attackingFrom.setCurrentSoldierCount(attackingFrom.getCurrentSoldierCount() - 1);
			RiskMenu.consoleText.append("\nbut lost 1 soldier.");
		}

		// this changes the ownership of a tile and its color to the new owners
		// teamcolor
		if (attackingAt.getCurrentSoldierCount() <= 0) {

			attackingAt.setColor(attackingFrom.getCurrentOwner().getTeamColor());
			attackingAt.setCurrentSoldierCount(diceAttackAmount);
			attackingFrom.setCurrentSoldierCount(attackingFrom.getCurrentSoldierCount() - diceAttackAmount);

			for (int i = 0; i < countriesOwned.length; i++) {
				if (attackingAt.getCurrentOwner().countriesOwned[i] != null) {
					if (attackingAt.getCurrentOwner().countriesOwned[i].getName().equals(attackingAt.getName())) {
						attackingAt.getCurrentOwner().countriesOwned[i] = null;
					}
				}
			}

			attackingAt.setCurrentOwner(attackingFrom.getCurrentOwner());
		}
	}
        
        //this is a helper method for the attack method.
        //This method calculates the result of the dice rolls based on the rules of risk
	private int calculateDiceResult(Dice x1, Dice x2, Dice x3, Dice x4, Dice x5) {
		int result = -1;
		Dice[] attacker = { x1, x2, x3 };

		// sorts the dice in decending order
		for (int i = 0; i < 2; i++) {
			if (attacker[i].getDiceResult() < attacker[i + 1].getDiceResult()) {
				Dice temp = new Dice();
				temp = attacker[i];
				attacker[i] = attacker[i + 1];
				attacker[i + 1] = temp;
			}
		}

		int highDefenseDieRoll = -1;
		int lowDefenseDieRoll = -1;

		// determines which defender die was higher
		if (x4.getDiceResult() > x5.getDiceResult() && x5.getDiceResult() != -1) {
			highDefenseDieRoll = x4.getDiceResult();
			lowDefenseDieRoll = x5.getDiceResult();
		} else if (x4.getDiceResult() < x5.getDiceResult() && x5.getDiceResult() != -1) {
			highDefenseDieRoll = x5.getDiceResult();
			lowDefenseDieRoll = x4.getDiceResult();
		} else {
			highDefenseDieRoll = x4.getDiceResult();
		}

		// determines how many dice were rolled and determines result
		if (attacker[1].getDiceResult() != -1 && lowDefenseDieRoll != -1) {
			if (attacker[0].getDiceResult() > highDefenseDieRoll && attacker[1].getDiceResult() > lowDefenseDieRoll) {
				// defenders lose 2
				result = 2;
			} else if ((attacker[0].getDiceResult() <= highDefenseDieRoll
					&& attacker[1].getDiceResult() > lowDefenseDieRoll)
					|| (attacker[0].getDiceResult() > highDefenseDieRoll
							&& attacker[1].getDiceResult() <= lowDefenseDieRoll)) {
				// attackers lose 1 and defenders lose 1
				result = 1;
			} else {
				// attackers lose 2
				result = 0;
			}
		} else {
			if (attacker[0].getDiceResult() > highDefenseDieRoll) {
				// defender loses 1
				result = 3;
			} else {
				// attackers lose 1
				result = 4;
			}
		}
		return result;
	}
        
        //this method checks to see if the player it is called on has a matching
        //  set to turn in from the cards in his hand.
	public static boolean checkMatch() {
		match = false;

		if (soldierCount >= 3 || calvaryCount >= 3 || cannonCount >= 3
				|| (soldierCount >= 1 && calvaryCount >= 1 && cannonCount >= 1)) {
			match = true;
		} else if (wildCount == 1 && (soldierCount >= 2 || calvaryCount >= 2 || cannonCount >= 2)) {
			match = true;
		} else if (wildCount == 1 && (soldierCount >= 1 && calvaryCount >= 1) || (soldierCount >= 1 && cannonCount >= 1)
				|| (calvaryCount >= 1 && cannonCount >= 1)) {
			match = true;
		} else if (wildCount == 2 && (soldierCount >= 1 || calvaryCount >= 1 || cannonCount >= 1)) {
			match = true;
		} else {
			match = false;
		}

		return match;
	}
        
        //this checks to see if a player has any of the continent bonuses that
        //  are in the game.
	public void checkContinentBonus() {
		int northAmericaCount = 0;
		int southAmericaCount = 0;
		int africaCount = 0;
		int europeCount = 0;
		int australiaCount = 0;
		int asiaCount = 0;

		for (int i = 0; i < countriesOwned.length; i++) {
			if (countriesOwned[i] != null) {
				if (countriesOwned[i].getContinent().equals("North_America")) {
					northAmericaCount++;
				}

				if (countriesOwned[i].getContinent().equals("South_America")) {
					southAmericaCount++;
				}

				if (countriesOwned[i].getContinent().equals("Europe")) {
					europeCount++;
				}

				if (countriesOwned[i].getContinent().equals("Africa")) {
					africaCount++;
				}

				if (countriesOwned[i].getContinent().equals("Asia")) {
					asiaCount++;
				}

				if (countriesOwned[i].getContinent().equals("Australia")) {
					australiaCount++;
				}
			}

			northAmerica = northAmericaCount == 9;
			southAmerica = southAmericaCount == 4;
			europe = europeCount == 7;
			africa = africaCount == 6;
			asia = asiaCount == 12;
			australia = australiaCount == 4;

		}
	}

        //this method calculates the proper amount of reinforcements a player
        //  gets at the start of his turn.
        //the argument is an array of tiles that holds all the tiles in the game
	public int calculateReinforcements(Tile[] tiles) {
		setTeritoryCount(tiles);
		int iterator = 0;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i].getCurrentOwner() == this) {
				countriesOwned[iterator] = tiles[i];
				iterator++;
			}
		}
		checkContinentBonus();
		int reinforcementCount = 0;
		reinforcementCount += territoryCount / 3;

		if (northAmerica) {
			reinforcementCount += 5;
		}

		if (southAmerica) {
			reinforcementCount += 2;
		}

		if (europe) {
			reinforcementCount += 5;
		}

		if (africa) {
			reinforcementCount += 3;
		}

		if (asia) {
			reinforcementCount += 7;
		}

		if (australia) {
			reinforcementCount += 2;
		}

		if (reinforcementCount < 3) {
			reinforcementCount = 3;
		}

		return reinforcementCount;

	}

	// arguments attackingFrom is the attacking country, attackingAt is the
	// country being attacked, x is the number of troops the player wishes to
	// move after winning.
	public void victoryTroopMove(Tile attackingFrom, Tile attackingAt, int x) {
		if (attackingFrom.getCurrentSoldierCount() > 1 && attackingFrom.getCurrentSoldierCount() - x >= 1) {
			attackingFrom.setCurrentSoldierCount(attackingFrom.getCurrentSoldierCount() - x);

			attackingAt.setCurrentSoldierCount(attackingAt.getCurrentSoldierCount() + x);
		}
	}

        //this method turns in a set of cards if you have one and gives you
        //the proper increase to reinforcements. 
        //the cards passed here are the cards that are turned in. 
	public void setTurnIn(Deck d1, Card c1, Card c2, Card c3, Tile[] allTiles) {
		if (c1.getUnitType().equals("Soldier")) {
			soldierCount--;
		}
		if (c2.getUnitType().equals("Soldier")) {
			soldierCount--;
		}
		if (c3.getUnitType().equals("Soldier")) {
			soldierCount--;
		}

		if (c1.getUnitType().equals("Calvary")) {
			calvaryCount--;
		}
		if (c2.getUnitType().equals("Calvary")) {
			calvaryCount--;
		}
		if (c3.getUnitType().equals("Calvary")) {
			calvaryCount--;
		}

		if (c1.getUnitType().equals("Cannon")) {
			cannonCount--;
		}
		if (c2.getUnitType().equals("Cannon")) {
			cannonCount--;
		}
		if (c3.getUnitType().equals("Cannon")) {
			cannonCount--;
		}
		if (c1.getUnitType().equals("Wild")) {
			wildCount--;
		}
		if (c2.getUnitType().equals("Wild")) {
			wildCount--;
		}
		if (c3.getUnitType().equals("Wild")) {
			wildCount--;
		}

		d1.setDeckCard(c1);
		d1.setDeckCard(c2);
		d1.setDeckCard(c3);

		cardCount -= 3;

		for (int i = 0; i < playerHand.length; i++) {
			if (playerHand[i] != null) {
				if (playerHand[i].equals(c1)) {
					playerHand[i] = null;
				}
			}
			if (playerHand[i] != null) {
				if (playerHand[i].equals(c2)) {
					playerHand[i] = null;
				}
			}
			if (playerHand[i] != null) {
				if (playerHand[i].equals(c3)) {
					playerHand[i] = null;
				}
			}
		}

		reinforcementCount += 8;

		for (int i = 0; i < allTiles.length; i++) {
			if (c1.getUnitType().equals("Wild") && c2.getUnitType().equals("Wild") && c3.getUnitType().equals("Wild")) {

			} else if (c1.getUnitType().equals("Wild") && c2.getUnitType().equals("Wild")) {
				if (c3.getCountry().equals(allTiles[i].getName())
						&& allTiles[i].getCurrentOwner().getPlayerName().equals(playerName)) {
					allTiles[i].setCurrentSoldierCount(allTiles[i].getCurrentSoldierCount() + 2);
				}
			} else if (c1.getUnitType().equals("Wild") && c3.getUnitType().equals("Wild")) {
				if (c2.getCountry().equals(allTiles[i].getName())
						&& allTiles[i].getCurrentOwner().getPlayerName().equals(playerName)) {
					allTiles[i].setCurrentSoldierCount(allTiles[i].getCurrentSoldierCount() + 2);
				}
			} else if (c2.getUnitType().equals("Wild") && c3.getUnitType().equals("Wild")) {
				if (c1.getCountry().equals(allTiles[i].getName())
						&& allTiles[i].getCurrentOwner().getPlayerName().equals(playerName)) {
					allTiles[i].setCurrentSoldierCount(allTiles[i].getCurrentSoldierCount() + 2);
				}
			} else if (c1.getUnitType().equals("Wild")) {
				if (c2.getCountry().equals(allTiles[i].getName())
						&& allTiles[i].getCurrentOwner().getPlayerName().equals(playerName)) {
					allTiles[i].setCurrentSoldierCount(allTiles[i].getCurrentSoldierCount() + 2);
				}
				if (c3.getCountry().equals(allTiles[i].getName())
						&& allTiles[i].getCurrentOwner().getPlayerName().equals(playerName)) {
					allTiles[i].setCurrentSoldierCount(allTiles[i].getCurrentSoldierCount() + 2);
				}
			} else if (c2.getUnitType().equals("Wild")) {
				if (c1.getCountry().equals(allTiles[i].getName())
						&& allTiles[i].getCurrentOwner().getPlayerName().equals(playerName)) {
					allTiles[i].setCurrentSoldierCount(allTiles[i].getCurrentSoldierCount() + 2);
				}
				if (c3.getCountry().equals(allTiles[i].getName())
						&& allTiles[i].getCurrentOwner().getPlayerName().equals(playerName)) {
					allTiles[i].setCurrentSoldierCount(allTiles[i].getCurrentSoldierCount() + 2);
				}
			} else if (c3.getUnitType().equals("Wild")) {
				if (c1.getCountry().equals(allTiles[i].getName())
						&& allTiles[i].getCurrentOwner().getPlayerName().equals(playerName)) {
					allTiles[i].setCurrentSoldierCount(allTiles[i].getCurrentSoldierCount() + 2);
				}
				if (c2.getCountry().equals(allTiles[i].getName())
						&& allTiles[i].getCurrentOwner().getPlayerName().equals(playerName)) {
					allTiles[i].setCurrentSoldierCount(allTiles[i].getCurrentSoldierCount() + 2);
				}
			} else {
				if (c1.getCountry().equals(allTiles[i].getName())
						&& allTiles[i].getCurrentOwner().getPlayerName().equals(playerName)) {
					allTiles[i].setCurrentSoldierCount(allTiles[i].getCurrentSoldierCount() + 2);
				}
				if (c2.getCountry().equals(allTiles[i].getName())
						&& allTiles[i].getCurrentOwner().getPlayerName().equals(playerName)) {
					allTiles[i].setCurrentSoldierCount(allTiles[i].getCurrentSoldierCount() + 2);
				}
				if (c3.getCountry().equals(allTiles[i].getName())
						&& allTiles[i].getCurrentOwner().getPlayerName().equals(playerName)) {
					allTiles[i].setCurrentSoldierCount(allTiles[i].getCurrentSoldierCount() + 2);
				}
			}
		}
	}

	public String toString() {
		System.out.println("Team Color: " + teamColor);
		System.out.println("Card Count: " + cardCount);
		System.out.println("Teritory Count: " + territoryCount);
		System.out.println("Soldier Card Amount: " + soldierCount);
		System.out.println("Calvary Card Amount: " + calvaryCount);
		System.out.println("Cannon Card Amount: " + cannonCount);
		System.out.println("Wild Card Amount: " + wildCount);

		for (int i = 0; i < playerHand.length; i++) {
			System.out.println(playerHand[i]);
		}

		return "Reinforcement Count: " + reinforcementCount;
	}
}
