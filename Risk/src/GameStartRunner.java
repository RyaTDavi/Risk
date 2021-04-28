//Jacob Paluso, Noah Rudolph, Harrison Grogan, Ryan Davis
//Risk

import java.util.Random;

//this class defines what our threads do when opting out of choosing your
//own countries. 
public class GameStartRunner implements Runnable {

	private Tile[] countries;
	private Player player;
	private Random rand = new Random();

	public GameStartRunner(Tile[] x, Player y) {
		countries = x;
		player = y;
	}

	public Tile[] getCountries() {
		return countries;
	}

	public Player getPlayer() {
		return player;
	}

	public void setCountries(Tile[] x) {
		countries = x;
	}

	public void setPlayer(Player x) {
		player = x;
	}

        //randomly assigns unowned countries to players and distributes randomly
        //the starting amount of troops to those countries.
	public void run() {
		int tileNumber = rand.nextInt(countries.length - 1);
		int iterator = 0;

		while (iterator <= (countries.length / 2) - 1) {
			if (!countries[tileNumber].getDone()) {
				countries[tileNumber].setCurrentOwner(player);
				countries[tileNumber].setColor(player.getTeamColor());
				countries[tileNumber].setCurrentSoldierCount(1);
				player.setTeritoryCount(player.getTeritoryCount() + 1);

				player.countriesOwned[iterator] = countries[tileNumber];
				countries[tileNumber].setDone();
				iterator++;
			} else {
				tileNumber = rand.nextInt(countries.length);
			}
		}

		for (int i = 0; i < 20; i++) {
			int randomOwnedTile = rand.nextInt((countries.length / 2) - 1);

			player.getCountriesOwned()[randomOwnedTile]
					.setCurrentSoldierCount(player.getCountriesOwned()[randomOwnedTile].getCurrentSoldierCount() + 1);
		}

	}
}
