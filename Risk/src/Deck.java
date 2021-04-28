//Jacob Paluso, Noah Rudolph, Harrison Grogan, Ryan Davis
//Risk

import java.util.Random;

//this class defines the deck in the game that holds all the cards
public class Deck {
	private static Card[] cards = new Card[44];
	private static boolean cardDrawn = false;

	public Deck(Card c0, Card c1, Card c2, Card c3, Card c4, Card c5, Card c6, Card c7, Card c8, Card c9, Card c10,
			Card c11, Card c12, Card c13, Card c14, Card c15, Card c16, Card c17, Card c18, Card c19, Card c20,
			Card c21, Card c22, Card c23, Card c24, Card c25, Card c26, Card c27, Card c28, Card c29, Card c30,
			Card c31, Card c32, Card c33, Card c34, Card c35, Card c36, Card c37, Card c38, Card c39, Card c40,
			Card c41, Card c42, Card c43) {
		cards[0] = c0;
		cards[1] = c1;
		cards[2] = c2;
		cards[3] = c3;
		cards[4] = c4;
		cards[5] = c5;
		cards[6] = c6;
		cards[7] = c7;
		cards[8] = c8;
		cards[9] = c9;
		cards[10] = c10;
		cards[11] = c11;
		cards[12] = c12;
		cards[13] = c13;
		cards[14] = c14;
		cards[15] = c15;
		cards[16] = c16;
		cards[17] = c17;
		cards[18] = c18;
		cards[19] = c19;
		cards[20] = c20;
		cards[21] = c21;
		cards[22] = c22;
		cards[23] = c23;
		cards[24] = c24;
		cards[25] = c25;
		cards[26] = c26;
		cards[27] = c27;
		cards[28] = c28;
		cards[29] = c29;
		cards[30] = c30;
		cards[31] = c31;
		cards[32] = c32;
		cards[33] = c33;
		cards[34] = c34;
		cards[35] = c35;
		cards[36] = c36;
		cards[37] = c37;
		cards[38] = c38;
		cards[39] = c39;
		cards[40] = c40;
		cards[41] = c41;
		cards[42] = c42;
		cards[43] = c43;
	}

	public Card[] getDeck() {
		return cards;
	}

	public void setDeckCard(Card c1) {
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] == null) {
				cards[i] = c1;
			}
		}
	}

	public static Card drawCard(Player p) {
		Random rand = new Random();
		Card drawnCard = new Card(null, null);

		int x = rand.nextInt(44);
		cardDrawn = false;

		while (cardDrawn == false) {
			if (cards[x] == null) {
				x = rand.nextInt(44);
			} else {
				drawnCard = cards[x];
				cards[x] = null;
				cardDrawn = true;
			}
		}

		int i = 0;

		while (i < 5) {
			if (p.playerHand[i] != null) {
				i++;
			} else {
				p.playerHand[i] = drawnCard;
				p.setCardCount(p.getCardCount() + 1);
				i += 10;

				if (drawnCard.unitType == "Soldier") {
					p.setSoldierCount(p.getSoldierCount() + 1);
				} else if (drawnCard.unitType == "Calvary") {
					p.setCalvaryCount(p.getCalvaryCount() + 1);
				} else if (drawnCard.unitType == "Cannon") {
					p.setCannonCount(p.getCannonCount() + 1);
				} else {
					p.setWildCount(p.getWildCount() + 1);
				}
			}
		}

		return drawnCard;
	}

	public String toString() {
		for (int i = 0; i < cards.length; i++) {
			System.out.println("Country: " + cards[i].country);
			System.out.println("Unit Type: " + cards[i].unitType);
			System.out.println("");
		}
		return " ";
	}
}
