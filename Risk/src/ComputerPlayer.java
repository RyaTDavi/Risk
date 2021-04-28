//Jacob Paluso, Noah Rudolph, Harrison Grogan, Ryan Davis
//Risk

import java.util.Random;

//this class defines how the AI will operate. 
public class ComputerPlayer {
        
        //This method allows the computer to choose a tile if you don't use the
        //  threads to automatically distribute them. The computer will prevent
        //  the player from obtaining a continent unless the computer would
        //  also get a continent. The arguements are tile arrays depicting each
        //  continent and the human and computer player objects. 
	public static Tile countryChooser(Tile[] northAmerica, Tile[] southAmerica, Tile[] europe, Tile[] africa,
			Tile[] asia, Tile[] australia, Player p1, Player p2) {
		int northAmericaWeight = 30;
		int southAmericaWeight = 50;
		int europeWeight = 20;
		int africaWeight = 40;
		int asiaWeight = 10;
		int australiaWeight = 60;

		String p1Name = p1.playerName;
		String p2Name = p2.playerName;

		int p1NorthAmericaCount = 0;
		int p1SouthAmericaCount = 0;
		int p1EuropeCount = 0;
		int p1AfricaCount = 0;
		int p1AsiaCount = 0;
		int p1AustraliaCount = 0;

		int p2NorthAmericaCount = 0;
		int p2SouthAmericaCount = 0;
		int p2EuropeCount = 0;
		int p2AfricaCount = 0;
		int p2AsiaCount = 0;
		int p2AustraliaCount = 0;

		Tile answer = null;

		for (int i = 0; i < northAmerica.length; i++) {
			if (northAmerica[i].getCurrentOwner() != null) {
				if (northAmerica[i].getCurrentOwner().getPlayerName().equals(p1Name)) {
					p1NorthAmericaCount++;
				}

				if (northAmerica[i].getCurrentOwner().getPlayerName().equals(p2Name)) {
					p2NorthAmericaCount++;
				}
			}
		}

		for (int i = 0; i < southAmerica.length; i++) {
			if (southAmerica[i].getCurrentOwner() != null) {
				if (southAmerica[i].getCurrentOwner().getPlayerName().equals(p1Name)) {
					p1SouthAmericaCount++;
				}

				if (southAmerica[i].getCurrentOwner().getPlayerName().equals(p2Name)) {
					p2SouthAmericaCount++;
				}
			}
		}

		for (int i = 0; i < europe.length; i++) {
			if (europe[i].getCurrentOwner() != null) {
				if (europe[i].getCurrentOwner().getPlayerName().equals(p1Name)) {
					p1EuropeCount++;
				}

				if (europe[i].getCurrentOwner().getPlayerName().equals(p2Name)) {
					p2EuropeCount++;
				}
			}
		}

		for (int i = 0; i < africa.length; i++) {
			if (africa[i].getCurrentOwner() != null) {
				if (africa[i].getCurrentOwner().getPlayerName().equals(p1Name)) {
					p1AfricaCount++;
				}

				if (africa[i].getCurrentOwner().getPlayerName().equals(p2Name)) {
					p2AfricaCount++;
				}
			}
		}

		for (int i = 0; i < asia.length; i++) {
			if (asia[i].getCurrentOwner() != null) {
				if (asia[i].getCurrentOwner().getPlayerName().equals(p1Name)) {
					p1AsiaCount++;
				}

				if (asia[i].getCurrentOwner().getPlayerName().equals(p2Name)) {
					p2AsiaCount++;
				}
			}
		}

		for (int i = 0; i < australia.length; i++) {
			if (australia[i].getCurrentOwner() != null) {
				if (australia[i].getCurrentOwner().getPlayerName().equals(p1Name)) {
					p1AustraliaCount++;
				}

				if (australia[i].getCurrentOwner().getPlayerName().equals(p2Name)) {
					p2AustraliaCount++;
				}
			}
		}

		if (p1NorthAmericaCount == 8 || p2NorthAmericaCount == 8) {
			northAmericaWeight += 100;
		}

		if (p1SouthAmericaCount == 3 || p2SouthAmericaCount == 3) {
			southAmericaWeight += 100;
		}

		if (p1EuropeCount == 6 || p2EuropeCount == 6) {
			europeWeight += 100;
		}

		if (p1AfricaCount == 5 || p2AfricaCount == 5) {
			africaWeight += 100;
		}

		if (p1AsiaCount == 11 || p2AsiaCount == 11) {
			asiaWeight += 100;
		}

		if (p1AustraliaCount == 3 || p2AustraliaCount == 3) {
			australiaWeight += 100;
		}

		if (p1NorthAmericaCount + p2NorthAmericaCount == 9) {
			northAmericaWeight = 0;
		}

		if (p1SouthAmericaCount + p2SouthAmericaCount == 4) {
			southAmericaWeight = 0;
		}

		if (p1EuropeCount + p2EuropeCount == 7) {
			europeWeight = 0;
		}

		if (p1AfricaCount + p2AfricaCount == 6) {
			africaWeight = 0;
		}

		if (p1AsiaCount + p2AsiaCount == 12) {
			asiaWeight = 0;
		}

		if (p1AustraliaCount + p2AustraliaCount == 4) {
			australiaWeight = 0;
		}

		int[] continents = { northAmericaWeight, southAmericaWeight, europeWeight, africaWeight, asiaWeight,
				australiaWeight };

		for (int x = 0; x < continents.length; x++) {
			for (int i = 0; i < continents.length - 1; i++) {
				if (continents[i] < continents[i + 1]) {
					int temp = continents[i];
					continents[i] = continents[i + 1];
					continents[i + 1] = temp;
				}
			}
		}

		if (northAmericaWeight == continents[0]) {
			for (int i = 0; i < northAmerica.length; i++) {
				if (northAmerica[i].getCurrentOwner() == null) {
					answer = northAmerica[i];
				}
			}
		} else if (southAmericaWeight == continents[0]) {
			for (int i = 0; i < southAmerica.length; i++) {
				if (southAmerica[i].getCurrentOwner() == null) {
					answer = southAmerica[i];
				}
			}
		} else if (europeWeight == continents[0]) {
			for (int i = 0; i < europe.length; i++) {
				if (europe[i].getCurrentOwner() == null) {
					answer = europe[i];
				}
			}
		} else if (africaWeight == continents[0]) {
			for (int i = 0; i < africa.length; i++) {
				if (africa[i].getCurrentOwner() == null) {
					answer = africa[i];
				}
			}
		} else if (asiaWeight == continents[0]) {
			for (int i = 0; i < asia.length; i++) {
				if (asia[i].getCurrentOwner() == null) {
					answer = asia[i];
				}
			}
		} else {
			for (int i = 0; i < australia.length; i++) {
				if (australia[i].getCurrentOwner() == null) {
					answer = australia[i];
				}
			}
		}

		return answer;
	}
        
        //This method allows the computer to decide where it wants to attack.
        //  This is based off the current soldier counts on each of the 
        //  countries.
	public static Tile[] countryAttackChooser(Player currentPlayer, Tile[] allTiles) {

		Tile[] currentPlayersTiles = currentPlayer.countriesOwned;
		Tile[] adjacent = new Tile[200];
		Tile[] pairs = new Tile[200];
		Tile[] answer = new Tile[2];

		for (int i = 0; i < currentPlayer.countriesOwned.length; i++) {
			int a = 0;

			if (currentPlayer.countriesOwned[i] != null) {
				for (int x = 0; x < currentPlayer.countriesOwned[i].getAdjacent().length; x++) {
					for (int z = 0; z < allTiles.length; z++) {
						if (currentPlayer.countriesOwned[i].getAdjacent()[x] != null && allTiles[z] != null) {
							if (currentPlayer.countriesOwned[i].getAdjacent()[x].equals(allTiles[z].getName())) {
								if (adjacent[a] == null) {
									adjacent[a] = allTiles[z];
									a++;
								} else {
									a++;
									z--;
								}
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < adjacent.length; i++) {
			for (int z = i + 1; z < adjacent.length; z++) {
				if (adjacent[i] != null && adjacent[z] != null) {
					if (adjacent[i].getName().equals(adjacent[z].getName())) {
						adjacent[z] = null;
					}
				}
			}
			if (adjacent[i] != null) {
				if (adjacent[i].getCurrentOwner().equals(currentPlayer)) {
					adjacent[i] = null;
				} else {

				}
			}
		}

		int a = 0;
		for (int i = 0; i < currentPlayer.countriesOwned.length; i++) {
			for (int x = 0; x < adjacent.length; x++) {
				if (currentPlayer.countriesOwned[i] != null && adjacent[x] != null) {
					for (int z = 0; z < currentPlayer.countriesOwned[i].getAdjacent().length; z++) {
						if (currentPlayer.countriesOwned[i].getAdjacent()[z] != null) {
							if (currentPlayer.countriesOwned[i].getAdjacent()[z].equals(adjacent[x].getName())) {
								pairs[a] = currentPlayer.countriesOwned[i];
								pairs[a + 1] = adjacent[x];
								a += 2;
							}
						}
					}
				}
			}
		}

		int bestRatio = -10000;
		int currentRatio = 0;

		for (int i = 0; i < pairs.length - 1; i += 2) {
			if (pairs[i] != null && pairs[i + 1] != null) {
				currentRatio = pairs[i].getCurrentSoldierCount() - pairs[i + 1].getCurrentSoldierCount();

				if (currentRatio > bestRatio) {
					if (pairs[i].getCurrentSoldierCount() > 1) {
						bestRatio = currentRatio;
						answer[0] = pairs[i];
						answer[1] = pairs[i + 1];
					} else {
						Random rand = new Random();
						int x = rand.nextInt(199);

						if (pairs[x] != null && pairs[x + 1] != null) {
							answer[0] = pairs[x];
							answer[0] = pairs[x + 1];
						} else {
							x = rand.nextInt(199);
						}
					}
				}
			}
		}
		boolean botHasAll = true;
		for (int i = 0; i < allTiles.length; i++) {
			if (allTiles[i].getCurrentOwner() != currentPlayer) {
				botHasAll = false;
			}
		}
		if (botHasAll == false) {
			Random rand1 = new Random();
			while (answer[0] == null && answer[1] == null) {
				int numFrom = rand1.nextInt(41);
				if (allTiles[numFrom].getCurrentOwner() == currentPlayer) {
					answer[0] = allTiles[numFrom];
				}
				if (answer[0] != null) {
					String[] temp = allTiles[numFrom].getAdjacent();
					for (int i = 0; i < allTiles.length; i++) {
						for (int j = 0; j < temp.length; j++) {
							if (allTiles[i].getName().equals(temp[j]) && allTiles[i].getCurrentOwner() != currentPlayer) {
								answer[1] = allTiles[i];
							}
						}
					}
				}
			}
		}

		return answer;
	}

        //This method allows the computer to make a foritfy move at the end of
        //  his turn. Otherwise the AI would not be able to use any forces
        //  that are surrounded by countries the AI owns. 
	public static String computerFortify(Player p1, Tile[] allTiles) {
		Tile[] countriesOwned = new Tile[p1.countriesOwned.length];
		Tile[] adjacent = new Tile[200];

		for (int i = 0; i < countriesOwned.length; i++) {
			countriesOwned[i] = p1.countriesOwned[i];
		}

		for (int i = 0; i < countriesOwned.length; i++) {
			int a = 0;

			if (p1.countriesOwned[i] != null) {
				for (int x = 0; x < p1.countriesOwned[i].getAdjacent().length; x++) {
					for (int z = 0; z < allTiles.length; z++) {
						if (p1.countriesOwned[i].getAdjacent()[x] != null && allTiles[z] != null) {
							if (p1.countriesOwned[i].getAdjacent()[x].equals(allTiles[z].getName())) {
								if (adjacent[a] == null) {
									adjacent[a] = allTiles[z];
									a++;
								} else {
									a++;
									z--;
								}
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < adjacent.length; i++) {
			for (int z = i + 1; z < adjacent.length; z++) {
				if (adjacent[i] != null && adjacent[z] != null) {
					if (adjacent[i].getName().equals(adjacent[z].getName())) {
						adjacent[z] = null;
					}
					if (adjacent[i].getCurrentOwner().equals(p1)) {

					} else {
						adjacent[i] = null;
					}
				}
			}
		}

		Tile[] locked = new Tile[countriesOwned.length];

		for (int i = 0; i < countriesOwned.length; i++) {
			int adjacentCount = 0;
			if (countriesOwned[i] != null) {
				for (int x = 0; x < countriesOwned[i].getAdjacent().length; x++) {
					for (int z = 0; z < adjacent.length; z++) {
						if (countriesOwned[i].getAdjacent()[x] != null && adjacent[z] != null) {
							if (countriesOwned[i].getAdjacent()[x].equals(adjacent[z].getName())) {
								adjacentCount++;
							}
						}
					}
				}
			}
			if (countriesOwned[i] != null) {
				if (adjacentCount == countriesOwned[i].getAdjacent().length) {
					locked[i] = countriesOwned[i];
				}
			}
		}
		for (int x = 0; x < locked.length; x++) {
			for (int i = 0; i < locked.length - 1; i++) {
				if (locked[i] != null && locked[i + 1] != null) {
					if (locked[i].getCurrentSoldierCount() < locked[i + 1].getCurrentSoldierCount()) {
						Tile temp = locked[i];
						locked[i] = locked[i + 1];
						locked[i + 1] = temp;
					}
				}
			}
		}

		boolean firstPosition = false;
		int x = 0;
		while (x < locked.length && firstPosition == false) {
			if (locked[x] == null) {
				x++;
			} else {
				locked[0] = locked[x];
				locked[x] = null;
				firstPosition = true;
			}
		}

		if (locked[0] != null && locked[0].getCurrentSoldierCount() > 1) {
			int amountMoved = locked[0].getCurrentSoldierCount() - 1;
			locked[0].setCurrentSoldierCount(1);

			countryAttackChooser(p1, allTiles)[0].setCurrentSoldierCount(
					countryAttackChooser(p1, allTiles)[0].getCurrentSoldierCount() + amountMoved);

			return "Robbie moved " + amountMoved + " soldiers from " + "" + locked[0].getName().replaceAll("_", " ")
					+ " to " + countryAttackChooser(p1, allTiles)[0].getName().replaceAll("_", " ");
		}

		return "Robbie skips fortify";
	}

        //This allows the computer to turn in a set if it has one. 
	public static String computerCardTurnIn(Player p1, Deck d1, Tile[] allTiles) {
		int wildCounter = p1.getWildCount();
		int soldierCount = p1.getSoldierCount();
		int cavalryCount = p1.getCalvaryCount();
		int cannonCount = p1.getCannonCount();

		Card c1 = p1.playerHand[0];
		Card c2 = p1.playerHand[1];
		Card c3 = p1.playerHand[2];
		Card c4 = p1.playerHand[3];
		Card c5 = p1.playerHand[4];

		Boolean turnedInCards = false;
		String result = "";
		int original = 0;

		if (p1.checkMatch() == true) {
			turnedInCards = true;
			if (wildCounter >= 1) 
                        {
                            if(c1 != null && c2 != null && c3 != null)
                            {
                                if(c1.getUnitType().equals("Wild") || c2.getUnitType().equals("Wild") || c3.getUnitType().equals("Wild"))
                                {
                                    p1.setTurnIn(d1, p1.playerHand[0], p1.playerHand[1], p1.playerHand[2], allTiles);
                                }
                            }
                          
                            if(c1 != null && c2 != null && c4!= null)
                            {
                                if(c1.getUnitType().equals("Wild") || c2.getUnitType().equals("Wild") || c4.getUnitType().equals("Wild"))
                                {
                                    p1.setTurnIn(d1, p1.playerHand[0], p1.playerHand[1], p1.playerHand[3], allTiles);
                                }
                            }
                          
                            if(c1 != null && c2 != null && c5 != null)
                            {
                                if(c1.getUnitType().equals("Wild") || c2.getUnitType().equals("Wild") || c5.getUnitType().equals("Wild"))
                                {
                                    p1.setTurnIn(d1, p1.playerHand[0], p1.playerHand[1], p1.playerHand[4], allTiles);
                                }
                            }
                          
                            if(c2 != null && c3 != null && c4 != null)
                            {
                                if(c2.getUnitType().equals("Wild") || c3.getUnitType().equals("Wild") || c4.getUnitType().equals("Wild"))
                                {
                                    p1.setTurnIn(d1, p1.playerHand[1], p1.playerHand[2], p1.playerHand[3], allTiles);
                                }
                            }
                          
                            if(c2 != null && c3 != null && c5 != null)
                            {
                                if(c2.getUnitType().equals("Wild") || c3.getUnitType().equals("Wild") || c5.getUnitType().equals("Wild"))
                                {
                                    p1.setTurnIn(d1, p1.playerHand[1], p1.playerHand[2], p1.playerHand[4], allTiles);
                                }
                            }
                          
                            if(c3 != null && c4 != null && c5 != null)
                            {
                                if(c3.getUnitType().equals("Wild") || c4.getUnitType().equals("Wild") || c5.getUnitType().equals("Wild"))
                                {
                                    p1.setTurnIn(d1, p1.playerHand[2], p1.playerHand[3], p1.playerHand[4], allTiles);
                                }
                            }
			}
                        
			if (soldierCount >= 3) {
				if (c1 != null && c2 != null && c3 != null) {
					if (c1.getUnitType().equals("Soldier") && c2.getUnitType().equals("Soldier")
							&& c3.getUnitType().equals("Soldier")) {
						p1.setTurnIn(d1, p1.playerHand[0], p1.playerHand[1], p1.playerHand[2], allTiles);
					}
				}
				if (c1 != null && c2 != null && c4 != null) {
					if (c1.getUnitType().equals("Soldier") && c2.getUnitType().equals("Soldier")
							&& c4.getUnitType().equals("Soldier")) {
						p1.setTurnIn(d1, p1.playerHand[0], p1.playerHand[1], p1.playerHand[3], allTiles);
					}
				}
				if (c1 != null && c2 != null & c5 != null) {
					if (c1.getUnitType().equals("Soldier") && c2.getUnitType().equals("Soldier")
							&& c5.getUnitType().equals("Soldier")) {
						p1.setTurnIn(d1, p1.playerHand[0], p1.playerHand[1], p1.playerHand[4], allTiles);
					}
				}
				if (c2 != null && c3 != null && c4 != null) {
					if (c2.getUnitType().equals("Soldier") && c3.getUnitType().equals("Soldier")
							&& c4.getUnitType().equals("Soldier")) {
						p1.setTurnIn(d1, p1.playerHand[1], p1.playerHand[2], p1.playerHand[3], allTiles);
					}
				}
				if (c2 != null && c3 != null && c5 != null) {
					if (c2.getUnitType().equals("Soldier") && c3.getUnitType().equals("Soldier")
							&& c5.getUnitType().equals("Soldier")) {
						p1.setTurnIn(d1, p1.playerHand[1], p1.playerHand[2], p1.playerHand[4], allTiles);
					}
				}
				if (c3 != null && c4 != null && c5 != null) {
					if (c3.getUnitType().equals("Soldier") && c4.getUnitType().equals("Soldier")
							&& c5.getUnitType().equals("Soldier")) {
						p1.setTurnIn(d1, p1.playerHand[2], p1.playerHand[3], p1.playerHand[4], allTiles);
					}
				}

			}
			if (cavalryCount >= 3) {
				if (c1 != null && c2 != null && c3 != null) {
					if (c1.getUnitType().equals("Calvary") && c2.getUnitType().equals("Calvary")
							&& c3.getUnitType().equals("Calvary")) {
						p1.setTurnIn(d1, p1.playerHand[0], p1.playerHand[1], p1.playerHand[2], allTiles);
					}
				}
				if (c1 != null && c2 != null && c4 != null) {
					if (c1.getUnitType().equals("Calvary") && c2.getUnitType().equals("Calvary")
							&& c4.getUnitType().equals("Calvary")) {
						p1.setTurnIn(d1, p1.playerHand[0], p1.playerHand[1], p1.playerHand[3], allTiles);
					}
				}
				if (c1 != null && c2 != null && c5 != null) {
					if (c1.getUnitType().equals("Calvary") && c2.getUnitType().equals("Calvary")
							&& c5.getUnitType().equals("Calvary")) {
						p1.setTurnIn(d1, p1.playerHand[0], p1.playerHand[1], p1.playerHand[4], allTiles);
					}
				}
				if (c2 != null && c3 != null && c4 != null) {
					if (c2.getUnitType().equals("Calvary") && c3.getUnitType().equals("Calvary")
							&& c4.getUnitType().equals("Calvary")) {
						p1.setTurnIn(d1, p1.playerHand[1], p1.playerHand[2], p1.playerHand[3], allTiles);
					}
				}
				if (c2 != null && c3 != null && c5 != null) {
					if (c2.getUnitType().equals("Calvary") && c3.getUnitType().equals("Calvary")
							&& c5.getUnitType().equals("Calvary")) {
						p1.setTurnIn(d1, p1.playerHand[1], p1.playerHand[2], p1.playerHand[4], allTiles);
					}
				}
				if (c3 != null && c4 != null && c5 != null) {
					if (c3.getUnitType().equals("Calvary") && c4.getUnitType().equals("Calvary")
							&& c5.getUnitType().equals("Calvary")) {
						p1.setTurnIn(d1, p1.playerHand[2], p1.playerHand[3], p1.playerHand[4], allTiles);
					}
				}
			}
			if (cannonCount >= 3) {
				if (c1 != null && c2 != null && c3 != null) {
					if (c1.getUnitType().equals("Cannon") && c2.getUnitType().equals("Cannon")
							&& c3.getUnitType().equals("Cannon")) {
						p1.setTurnIn(d1, p1.playerHand[0], p1.playerHand[1], p1.playerHand[2], allTiles);
					}
				}
				if (c1 != null && c2 != null && c4 != null) {
					if (c1.getUnitType().equals("Cannon") && c2.getUnitType().equals("Cannon")
							&& c4.getUnitType().equals("Cannon")) {
						p1.setTurnIn(d1, p1.playerHand[0], p1.playerHand[1], p1.playerHand[3], allTiles);
					}
				}
				if (c1 != null && c2 != null && c5 != null) {
					if (c1.getUnitType().equals("Cannon") && c2.getUnitType().equals("Cannon")
							&& c5.getUnitType().equals("Cannon")) {
						p1.setTurnIn(d1, p1.playerHand[0], p1.playerHand[1], p1.playerHand[4], allTiles);
					}
				}
				if (c2 != null && c3 != null && c4 != null) {
					if (c2.getUnitType().equals("Cannon") && c3.getUnitType().equals("Cannon")
							&& c4.getUnitType().equals("Cannon")) {
						p1.setTurnIn(d1, p1.playerHand[1], p1.playerHand[2], p1.playerHand[3], allTiles);
					}
				}
				if (c2 != null && c3 != null && c5 != null) {
					if (c2.getUnitType().equals("Cannon") && c3.getUnitType().equals("Cannon")
							&& c5.getUnitType().equals("Cannon")) {
						p1.setTurnIn(d1, p1.playerHand[1], p1.playerHand[2], p1.playerHand[4], allTiles);
					}
				}
				if (c3 != null && c4 != null && c5 != null) {
					if (c3.getUnitType().equals("Cannon") && c4.getUnitType().equals("Cannon")
							&& c5.getUnitType().equals("Cannon")) {
						p1.setTurnIn(d1, p1.playerHand[2], p1.playerHand[3], p1.playerHand[4], allTiles);
					}
				}
			}
		}
		if (turnedInCards == true) {
			result = "Robbie turned in 3 cards for a troops bonus,\nhe recieved "
					+ (p1.getReinforcementCount() - original) + " troops";
		} else {
			result = "Robbie did not have a set of cards to turn in this turn";
		}
		return result;
	}

        //This allows the computer to place his troops if the threads are not
        //  used. 
	public static void initialTroopPlacement(Player currentPlayer, Tile[] tiles) {
		int x1 = 19;
		Random rand = new Random();
		for (int i = 0; x1 > 0; i++) {
			int randomNum = rand.nextInt(3) + 1;
			if (tiles[i].getCurrentOwner() == currentPlayer) {
				tiles[i].setCurrentSoldierCount(tiles[i].getCurrentSoldierCount() + randomNum);
				x1 = x1 - randomNum;
			}
			if (i == 41) {
				i = 0;
			}
		}
	}
}
