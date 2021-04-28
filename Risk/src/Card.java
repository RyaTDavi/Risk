//Jacob Paluso, Noah Rudolph, Harrison Grogan, Ryan Davis
//Risk

//This is the class that define the cards in the game. 
public class Card {

	protected String country;
	protected String unitType;

	public Card(String x, String y) {
		country = x;
		unitType = y;
	}

	public String getCountry() {
		return country;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setCountry(String x) {
		country = x;
	}

	public void setUnitType(String x) {
		unitType = x;
	}

        //this method was created in case the cards ever lost their assignment.
        //Calling this will reset all the cards to their original state.
	public static void assignCardArray(Card[] x) {
		x[0].country = "Alaska";
		x[1].country = "Alberta";
		x[2].country = "Central America";
		x[3].country = "Eastern U.S.";
		x[4].country = "Greenland";
		x[5].country = "Northwest Territory";
		x[6].country = "Ontario";
		x[7].country = "Quebec";
		x[8].country = "Western U.S.";
		x[9].country = "Argentina";
		x[10].country = "Brazil";
		x[11].country = "Peru";
		x[12].country = "Venezuela";
		x[13].country = "Great Britain";
		x[14].country = "Iceland";
		x[15].country = "Northern Europe";
		x[16].country = "Scandinavia";
		x[17].country = "Southern Europe";
		x[18].country = "Ukraine";
		x[19].country = "Western Europe";
		x[20].country = "Congo";
		x[21].country = "East Africa";
		x[22].country = "Egypt";
		x[23].country = "Madagascar";
		x[24].country = "North Africa";
		x[25].country = "South Africa";
		x[26].country = "Afghanistan";
		x[27].country = "China";
		x[28].country = "India";
		x[29].country = "Irkutsk";
		x[30].country = "Japan";
		x[31].country = "Kamchatka";
		x[32].country = "Middle East";
		x[33].country = "Mongolia";
		x[34].country = "Siam";
		x[35].country = "Siberia";
		x[36].country = "Ural";
		x[37].country = "Yakutsk";
		x[38].country = "Eastern Australia";
		x[39].country = "Indonesia";
		x[40].country = "New Guinea";
		x[41].country = "Western Austrailia";
		x[42].country = null;
		x[43].country = null;

		for (int i = 0, j = 14, k = 28; i < 14; i++, j++, k++) {
			x[i].unitType = "Soldier";
			x[j].unitType = "Calvary";
			x[k].unitType = "Cannon";
		}

		x[42].unitType = "Wild";
		x[43].unitType = "Wild";
	}

	public String toString() {
		return country + ", " + unitType;
	}
}
