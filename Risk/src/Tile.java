//Jacob Paluso, Noah Rudolph, Harrison Grogan, Ryan Davis
//Risk

import java.awt.Color;

//this class defines the countries in the game
public class Tile {

	public String name;
	public int[][] coords;
	public String[] adjacent;
	// added these properties
	private int currentSoldierCount;
	private Player currentOwner;
	private Color color;
	private Tile[] nextTo;
	private boolean done = false;
	private String continent;

	public Tile(String name, int[][] coords, String[] adjacent) {
		this.name = name;
		this.coords = coords;
		this.adjacent = adjacent;
		currentSoldierCount = 1;
		currentOwner = null;
		color = new Color(205, 183, 142);
	}

	// I created this for testing purposes.
	public Tile(String name, int currentSoldierCount, Player currentOwner) {
		this.name = name;
		this.currentSoldierCount = currentSoldierCount;
		this.currentOwner = currentOwner;
		color = new Color(205, 183, 142);
	}

	//getters and setters
	public String getContinent() {
		return continent;
	}

	public Player getCurrentOwner() {
		return currentOwner;
	}

	public boolean getDone() {
		return done;
	}

	public int getCurrentSoldierCount() {
		return currentSoldierCount;
	}

	public String getName() {
		return name;
	}

	public int[][] getCoords() {
		return coords;
	}

	public String[] getAdjacent() {
		return adjacent;
	}

	public Color getColor() {
		return color;
	}

	public Tile[] getNextTo() {
		return nextTo;
	}

	public void setContinent(String x) {
		continent = x;
	}

	public void setCurrentSoldierCount(int x) {
		currentSoldierCount = x;
	}

	public void setName(String x) {
		name = x;
	}

	public void setCoords(int[][] x) {
		coords = x;
	}

	public void setAdjacent(String[] x) {
		adjacent = x;
	}

	public void setCurrentOwner(Player x) {
		currentOwner = x;
	}

	public void setColor(Color x) {
		color = x;
	}

	public void setDone() {
		done = true;
	}

	public void setNextTo(Tile[] x) {
		nextTo = x;
	}

	public String toString() {
		return "Name: " + name + "\n Current Soldier Count: " + currentSoldierCount + "\n Current Owner: "
				+ currentOwner.getPlayerName();
	}
}
