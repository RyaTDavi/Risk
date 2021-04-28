//Jacob Paluso, Noah Rudolph, Harrison Grogan, Ryan Davis
//Risk

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class RiskMenu extends JFrame {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenHeight = screenSize.height - 30;
	int screenWidth = screenSize.width + 20;
	int applyX = 1280;
	int applyY = 720;
	int troops = 0;
	String reinforceString = "";
	String reinforceCountry = "";
	String attackingArmy = "";
	String countryToAttack = "";
	String movingArmy = "";
	String receivingArmy = "";
	String troopsToMove = "0";
	String card1ComboBoxSelection = "0";
	String card2ComboBoxSelection = "0";
	String card3ComboBoxSelection = "0";
	int x = 1;
	int index = -1;
	int movingIndex = -1;
	int receivingIndex = -1;
	int roundNum = 0;
	int cardsThisTurn = 0;
	int troopsToMoveInt = 0;
	boolean attackPopup = true;

	int attackingArmyIndex = -1;
	int countryToAttackIndex = -1;

	private JLabel l1 = new JLabel();
	private JLabel l2 = new JLabel();
	private JLabel l3 = new JLabel();
	private JLabel l4 = new JLabel();
	private JLabel colorLabel = new JLabel("Color: ");

	// view cards labels
	private JLabel selectCards = new JLabel("Select 3 Cards");
	private JLabel message = new JLabel("Error/Points");
	private JLabel cardsToTurnIn = new JLabel("You have cards to turn in");
	private JLabel blank = new JLabel("");
	private JLabel card1Header = new JLabel("Card1");
	private JLabel card2Header = new JLabel("Card2");
	private JLabel card3Header = new JLabel("Card3");
	private JLabel card4Header = new JLabel("Card4");
	private JLabel card5Header = new JLabel("Card5");
	private JLabel cardType = new JLabel("Card Type");
	private JLabel cardCountry = new JLabel("Card Country");
	private JLabel card1Type = new JLabel("Card1Type");
	private JLabel card2Type = new JLabel("Card2Type");
	private JLabel card3Type = new JLabel("Card3Type");
	private JLabel card4Type = new JLabel("Card4Type");
	private JLabel card5Type = new JLabel("Card5Type");
	private JLabel card1Country = new JLabel("Card1Country");
	private JLabel card2Country = new JLabel("Card2Country");
	private JLabel card3Country = new JLabel("Card3Country");
	private JLabel card4Country = new JLabel("Card4Country");
	private JLabel card5Country = new JLabel("Card5Country");

	// attack pop up labels
	private JLabel winMessage = new JLabel("You won!");
	private JLabel conquerMessage = new JLabel(
			"You conquered placeholderCountryAttacked with placeholderCountryAttackWith");
	private JLabel troopsMessage = new JLabel(
			"placeholderCountryAttackWith now has placeholder troops. placeholderCountryAttacked has placeholder troops");
	private JLabel questionMessage = new JLabel("How many troops would you want to move?");
	private JLabel updateCountryAttackedWithArmy = new JLabel(
			"placeholderCountryAttackWith will have placeholder troops.");
	private JLabel updateCountryAttackedArmy = new JLabel("placeholderCountryAttacked will have placeholder troops.");

	private JPanel panel = new JPanel();
	private JPanel mapSidePanel = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel leftPanel = new JPanel();

	private JPanel consolePanel = new JPanel(null);

	public static JTextArea consoleText = new JTextArea();
	JScrollPane scroll = new JScrollPane(consoleText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	// view cards panels
	private JPanel mainCardPanel = new JPanel();
	private JPanel sideCardPanel = new JPanel();
	private JPanel topCardPanel = new JPanel();
	private JPanel bottomCardPanel = new JPanel();

	// attack popup panels
	private JPanel mainAttackPanel = new JPanel();
	private JPanel topAttackPanel = new JPanel();
	private JPanel middleAttackPanel = new JPanel();
	private JPanel bottomAttackPanel = new JPanel();

	// view cards frame
	private JFrame cardFrame = new JFrame();

	// attack pop up frame
	private JFrame attackPopupFrame = new JFrame();

	private JButton b1 = new JButton("Single Player");
	private JButton b2 = new JButton("Multiplayer");
	private JButton b3 = new JButton("Settings");
	private JButton b4 = new JButton("View Cards");

	private JButton b5 = new JButton("Place Troops");
	private JButton b6 = new JButton("Done");
	private JButton b7 = new JButton("Help");

	// view cards buttons
	private JButton turnInCards = new JButton("Turn in Cards");
	private JButton info = new JButton("Info");
	private JButton close = new JButton("Close");

	// attack popup buttons
	private JButton moveTroops = new JButton("Move Troops");

	private JCheckBox startPos = new JCheckBox("Choose starting positions");

	private JButton fullScreen = new JButton("Full Screen: OFF");

	String[] resolutions = new String[] { "720p", "1080p" };
	private JComboBox resolutionList = new JComboBox(resolutions);
	private JComboBox sideBox = new JComboBox();
	private JComboBox comboBox = new JComboBox();

	String[] colors = new String[] { "Red", "Blue", "Green", "Yellow" };
	private JComboBox color = new JComboBox(colors);

	// view cards comboboxes
	private JComboBox card1 = new JComboBox();
	private JComboBox card2 = new JComboBox();
	private JComboBox card3 = new JComboBox();

	// attack popup comboboxes
	private JComboBox moveAttackingArmyTroops = new JComboBox();

	public static boolean windowed = true;
	private boolean wd;
	private boolean mAA;
	private boolean mAM;
	private boolean mASFX;

	private boolean muteAllAudio = false;
	private boolean muteAllMusic = false;
	private boolean muteAllSFX = false;
	private boolean applied = false;
	private boolean isChoosing = false;

	private String gameState = "Main Menu";
	private String doneState = "Reinforcement";

	private int cardsDrawn = 0;

	private JFileChooser fc = new JFileChooser();

	private Tile[] tiles;
	private Tile[] northAmerica = new Tile[9];
	private Tile[] southAmerica = new Tile[4];
	private Tile[] europe = new Tile[7];
	private Tile[] africa = new Tile[6];
	private Tile[] asia = new Tile[12];
	private Tile[] australia = new Tile[4];

	private int na = 0;
	private int sa = 0;
	private int eur = 0;
	private int afr = 0;
	private int asi = 0;
	private int aus = 0;
	private MapGeneration map;

	private JComponent riskLogo;

	private Color b = new Color(0, 100, 210);

	private JTextField tf = new JTextField("Name", 10);

	private int iterator = 0;

	private Player[] players;

	private Dice d1 = new Dice();
	private Dice d2 = new Dice();
	private Dice d3 = new Dice();
	private Dice d4 = new Dice();
	private Dice d5 = new Dice();

	Card c0 = new Card("Alaska", "Soldier");
	Card c1 = new Card("Alberta", "Soldier");
	Card c2 = new Card("Central America", "Soldier");
	Card c3 = new Card("Eastern U.S.", "Soldier");
	Card c4 = new Card("Greenland", "Soldier");
	Card c5 = new Card("Northwest Territory", "Soldier");
	Card c6 = new Card("Ontario", "Soldier");
	Card c7 = new Card("Quebec", "Soldier");
	Card c8 = new Card("Western U.S.", "Soldier");
	Card c9 = new Card("Argentina", "Soldier");
	Card c10 = new Card("Brazil", "Soldier");
	Card c11 = new Card("Peru", "Soldier");
	Card c12 = new Card("Venezuela", "Soldier");
	Card c13 = new Card("Great Britain", "Soldier");
	Card c14 = new Card("Iceland", "Calvary");
	Card c15 = new Card("Northern Europe", "Calvary");
	Card c16 = new Card("Scandinavia", "Calvary");
	Card c17 = new Card("Southern Europe", "Calvary");
	Card c18 = new Card("Ukraine", "Calvary");
	Card c19 = new Card("Western Europe", "Calvary");
	Card c20 = new Card("Congo", "Calvary");
	Card c21 = new Card("East Africa", "Calvary");
	Card c22 = new Card("Egypt", "Calvary");
	Card c23 = new Card("Madagascar", "Calvary");
	Card c24 = new Card("North Africa", "Calvary");
	Card c25 = new Card("South Africa", "Calvary");
	Card c26 = new Card("Afghanistan", "Calvary");
	Card c27 = new Card("China", "Calvary");
	Card c28 = new Card("India", "Cannon");
	Card c29 = new Card("Irkutsk", "Cannon");
	Card c30 = new Card("Japan", "Cannon");
	Card c31 = new Card("Kamchatka", "Cannon");
	Card c32 = new Card("Middle East", "Cannon");
	Card c33 = new Card("Mongolia", "Cannon");
	Card c34 = new Card("Siam", "Cannon");
	Card c35 = new Card("Siberia", "Cannon");
	Card c36 = new Card("Ural", "Cannon");
	Card c37 = new Card("Yakutsk", "Cannon");
	Card c38 = new Card("Eastern Australia", "Cannon");
	Card c39 = new Card("Indonesia", "Cannon");
	Card c40 = new Card("New Guinea", "Cannon");
	Card c41 = new Card("Western Australia", "Cannon");
	Card c42 = new Card(null, "Wild");
	Card c43 = new Card(null, "Wild");

	Deck deck = new Deck(c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20,
			c21, c22, c23, c24, c25, c26, c27, c28, c29, c30, c31, c32, c33, c34, c35, c36, c37, c38, c39, c40, c41,
			c42, c43);

	public RiskMenu() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
				| IllegalAccessException ex) {
		}
		ActionListener buttonListener = (ActionEvent e1) -> {
			String cmd = e1.getActionCommand();
			switch (cmd) {
			case "Single Player":
				panel2.add(leftPanel, BorderLayout.CENTER);
				leftPanel.setVisible(true);
				riskLogo.setVisible(false);
				b2.setVisible(true);
				b1.setText("Confirm Single Player Game");
				b2.setText("Back");
				b3.setVisible(false);
				break;
			case "Confirm Single Player Game":
				iterator = 0;
				players = new Player[2];
				String name = "Player 1";
				if (tf.getText().equals("Name")) {
					players[0] = new Player(name);
				} else {
					name = tf.getText();
				}
				players[0] = new Player(name);
				l1.setText(name + " it is your turn");
				players[1] = new Player("Robbie");
				switch (color.getSelectedItem().toString()) {
				case "Red":
					players[0].setTeamColor(new Color(200, 20, 20));
					break;
				case "Blue":
					players[0].setTeamColor(new Color(20, 20, 200));
					break;
				case "Green":
					players[0].setTeamColor(new Color(20, 200, 20));
					break;
				case "Yellow":
					players[0].setTeamColor(new Color(220, 220, 20));
					break;
				}
				players[1].setTeamColor(new Color(0, 220, 220));
				if (startPos.isSelected()) {
					doneState = "Choosing";
					comboBox.setVisible(false);
					l2.setText("Choose a tile to occupy");
					l3.setText("");
					l3.setVisible(true);
					l4.setVisible(true);
					l4.setText("");
					b5.setText("Choose Tile");
					b6.setVisible(false);
				} else {
					b6.setVisible(false);
					roundNum++;
					GameStartRunner thread1 = new GameStartRunner(tiles, players[0]);
					thread1.run();
					GameStartRunner thread2 = new GameStartRunner(tiles, players[1]);
					thread2.run();
					name = players[0].getPlayerName();
					players[0].setReinforcementCount(players[0].calculateReinforcements(tiles));
					troops = players[0].getReinforcementCount();
					comboBox.removeAllItems();
					for (int i = 0; i < troops + 1; i++) {
						if (i == 0) {
							comboBox.addItem("Choose a number of troops");
						} else {
							comboBox.addItem(Integer.toString(i));
						}
					}
					l1.setText(name + " it is your turn");
					l2.setText("Phase Reinforcement");
					l3.setText("You have " + players[0].getReinforcementCount() + " troops left");
					l4.setText("Choose a country to reinforce");
					b5.setText("Place Troops");
					b5.setEnabled(true);
					consoleText.append("\nChoose a number of troops");
					comboBox.setVisible(true);

					doneState = "Attack";
				}
				leftPanel.setVisible(false);
				mapSidePanel.setVisible(true);

				panel2.add(scroll, BorderLayout.WEST);
				scroll.createVerticalScrollBar();
				scroll.revalidate();
				consoleText.setEditable(false);
				consoleText.setColumns(30);

				panel.setBackground(b);
				gameState = "Game";
				b1.setText("Settings");
				b2.setText("Return to Main Menu");
				b3.setVisible(false);
				b4.setVisible(true);

				scroll.setVisible(true);
				consoleText.setVisible(true);
				break;
			case "Choose Tile":
				Random rand = new Random();
				if (l3.getText().equals("")) {
					l4.setText("Please choose a tile!");
				} else {
					if (iterator == tiles.length - 2) {
						for (Tile tile : tiles) {
							String tt = tile.getName().replaceAll("_", " ");
							if (tt.equals(l3.getText())) {
								if (tile.getCurrentOwner() != null) {
									isChoosing = true;
									if (tile.getCurrentOwner().getPlayerName().equals(players[0].getPlayerName())) {
										l4.setText("You already own this tile!");
									} else {
										l4.setText("Another player owns this tile!");
									}
								} else {
									l4.setText("");
									isChoosing = false;
									tile.setColor(players[0].getTeamColor());
									tile.setCurrentOwner(players[0]);
									map.repaint();
									iterator++;
								}
								int pick = rand.nextInt(tiles.length);
								while (!isChoosing) {
									Tile a = ComputerPlayer.countryChooser(northAmerica, southAmerica, europe, africa,
											asia, australia, players[0], players[1]);
									a.setCurrentOwner(players[1]);
									a.setColor(players[1].getTeamColor());
									iterator++;
									isChoosing = true;
								}
								players[0].setReinforcementCount(players[0].calculateReinforcements(tiles));
								troops = players[0].getReinforcementCount();
								troops += 19;
								comboBox.removeAllItems();
								for (int i = 0; i < troops + 1; i++) {
									if (i == 0) {
										comboBox.addItem("Choose a number of troops");
									} else {
										comboBox.addItem(Integer.toString(i));
									}
								}
								doneState = "Reinforcement";
								b5.setVisible(false);
								b6.setVisible(true);
							}
						}
					} else if (iterator < tiles.length - 2) {
						for (Tile tile : tiles) {
							String tt = tile.getName().replaceAll("_", " ");
							if (tt.equals(l3.getText())) {
								if (tile.getCurrentOwner() != null) {
									isChoosing = true;
									if (tile.getCurrentOwner().getPlayerName().equals(players[0].getPlayerName())) {
										l4.setText("You already own this tile!");
									} else {
										l4.setText("Another player owns this tile!");
									}
								} else {
									isChoosing = false;
									tile.setColor(players[0].getTeamColor());
									tile.setCurrentOwner(players[0]);
									iterator++;
								}
							}
						}
						int pick = rand.nextInt(tiles.length);
						while (!isChoosing) {
							Tile a = ComputerPlayer.countryChooser(northAmerica, southAmerica, europe, africa, asia,
									australia, players[0], players[1]);
							a.setCurrentOwner(players[1]);
							a.setColor(players[1].getTeamColor());
							iterator++;
							isChoosing = true;
						}
						map.repaint();
					}
				}
				break;
			case "Done":
				switch (doneState) {
				case "Reinforcement":
					consoleText.append("\nChoose a number of troops");
					boolean botWon = true;

					b6.setVisible(false);
					movingIndex = -1;
					int botCardsDrawn = 0;
					roundNum++;
					if (roundNum > 1) {
						boolean willAttack = false;
						Tile temp[] = ComputerPlayer.countryAttackChooser(players[1], tiles);

						consoleText.append("\n" + ComputerPlayer.computerCardTurnIn(players[1], deck, tiles));
						for (int i = 0; i < tiles.length; i++) {
							try {
								if (temp[0].getName().equals(tiles[i].getName())) {
									int tempReinforce = players[1].calculateReinforcements(tiles);
									tiles[i].setCurrentSoldierCount(tiles[i].getCurrentSoldierCount() + tempReinforce);
									consoleText.append("\n" + players[1].getPlayerName() + " has reinforced "
											+ tiles[i].getName().replaceAll("_", " ") + " with " + tempReinforce
											+ " troops.");
									willAttack = true;
								}
							} catch (NullPointerException ex) {
							}
						}
						if (temp[0] != null || temp[1] != null) {
							try {
								while (temp[0].getCurrentSoldierCount() > temp[1].getCurrentSoldierCount() + 1
										&& temp[0].getCurrentOwner() != temp[1].getCurrentOwner()
										&& temp[0].getCurrentSoldierCount() != 1) {
									if (willAttack == true) {
										if (temp[0].getCurrentSoldierCount() > 1) {
											players[1].attack(temp[0], temp[1], d1, d2, d3, d4, d5, 3);
											map.repaint();
											if (temp[1].getCurrentOwner() == players[0] && botCardsDrawn < 1) {
												Deck.drawCard(players[1]);
												consoleText.append(
														"\nRobbie drew a card for conquering his first country for his turn");
												botCardsDrawn++;
											}
										}
									}
								}
							} catch (NullPointerException ez1) {

							}
						}
						temp = ComputerPlayer.countryAttackChooser(players[1], tiles);

						if (temp[0] != null || temp[1] != null) {
							try {
								while (temp[0].getCurrentSoldierCount() > temp[1].getCurrentSoldierCount() + 1
										&& temp[0].getCurrentOwner() != temp[1].getCurrentOwner()
										&& temp[0].getCurrentSoldierCount() != 1) {
									if (willAttack == true) {
										if (temp[0].getCurrentSoldierCount() > 1) {
											players[1].attack(temp[0], temp[1], d1, d2, d3, d4, d5, 3);
											map.repaint();
											if (temp[1].getCurrentOwner() == players[0] && botCardsDrawn < 1) {
												Deck.drawCard(players[1]);
												consoleText.append(
														"\nRobbie drew a card for conquering his first country for his turn");
												botCardsDrawn++;
											}
										}
									}
								}
							} catch (NullPointerException ez2) {

							}
						}

						temp = ComputerPlayer.countryAttackChooser(players[1], tiles);

						if (temp[0] != null || temp[1] != null) {
							try {
								while (temp[0].getCurrentSoldierCount() > temp[1].getCurrentSoldierCount() + 1
										&& temp[0].getCurrentOwner() != temp[1].getCurrentOwner()
										&& temp[0].getCurrentSoldierCount() != 1) {
									if (willAttack == true) {
										if (temp[0].getCurrentSoldierCount() > 1) {
											players[1].attack(temp[0], temp[1], d1, d2, d3, d4, d5, 3);
											map.repaint();
											if (temp[1].getCurrentOwner() == players[0] && botCardsDrawn < 1) {
												Deck.drawCard(players[1]);
												consoleText.append(
														"\nRobbie drew a card for conquering his first country for his turn");
												botCardsDrawn++;
											}
										}
									}
								}
							} catch (NullPointerException ez3) {

							}
						}

						for (int i = 0; i < tiles.length; i++) {
							if (tiles[i].getCurrentOwner() != players[1]) {
								botWon = false;
							}
						}

						if (botWon == true) {
							JFrame winFrame = new JFrame();
							JLabel winLabel = new JLabel("You lost");
							winFrame.setVisible(true);
							winFrame.setSize(applyX, applyY);
							winFrame.add(winLabel);
							dispose();
							winFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
						}
						consoleText.append("\n" + ComputerPlayer.computerFortify(players[1], tiles));

						players[0].setReinforcementCount(players[0].calculateReinforcements(tiles));
						troops = players[0].getReinforcementCount();
					} else {
						ComputerPlayer.initialTroopPlacement(players[1], tiles);
						map.repaint();
						players[0].setReinforcementCount(players[0].calculateReinforcements(tiles) + 19);
						troops = players[0].getReinforcementCount();
					}
					comboBox.removeAllItems();
					for (int i = 0; i < troops + 1; i++) {
						if (i == 0) {
							comboBox.addItem("Choose a number of troops");
						} else {
							comboBox.addItem(Integer.toString(i));
						}
					}

					name = players[0].getPlayerName();
					l1.setText(name + " it is your turn");
					l2.setText("Phase Reinforcement");
					l3.setText("You have " + players[0].getReinforcementCount() + " troops left");
					l4.setText("Choose a country to reinforce");
					b5.setText("Place Troops");
					b5.setVisible(true);
					b5.setEnabled(true);
					comboBox.setVisible(true);
					troops = players[0].getReinforcementCount();
					doneState = "Attack";
					break;
				case "Attack":

					cardsDrawn = 0;
					index = -1;
					cardsThisTurn = 0;
					comboBox.removeAllItems();
					for (int i = 0; i < 4; i++) {
						if (i == 0) {
							comboBox.addItem("Choose a number of dice");
						} else {
							comboBox.addItem(Integer.toString(i));
						}
					}
					name = players[0].getPlayerName();
					l1.setText(name + " it is your turn");
					l2.setText("Phase Attack");
					l3.setText("Choose an army");
					l4.setText("Choose a country to attack");
					b5.setText("Attack");
					b5.setVisible(true);
					b5.setEnabled(true);
					comboBox.setVisible(true);
					doneState = "Fortify";
					break;
				case "Fortify":
					consoleText.append("\nChoose a number of troops");
					movingIndex = -1;
					receivingIndex = -1;
					comboBox.removeAllItems();
					if (movingIndex == -1) {
						for (int i = 0; i < 1; i++) {
							if (i == 0) {
								comboBox.addItem("Choose a number of troops");
							} else {
								comboBox.addItem(Integer.toString(i));
							}
						}
					}
					name = players[0].getPlayerName();
					l1.setText(name + " it is your turn");
					l2.setText("Phase Fortify");
					l3.setText("Choose an army");
					l4.setText("Choose a country to fortify");
					b5.setVisible(true);
					b5.setText("Fortify");
					comboBox.setVisible(true);
					doneState = "Reinforcement";
					break;
				}
				break;
			case "Return to Main Menu":

				consoleText.setText("");

				na = 0;
				sa = 0;
				eur = 0;
				afr = 0;
				asi = 0;
				aus = 0;

				panel2.remove(consoleText);
				panel2.remove(scroll);

				mapSidePanel.setVisible(false);
				map.setVisible(false);
				panel.setBackground(Color.black);
				riskLogo.setVisible(true);
				gameState = "Main Menu";
				b4.setVisible(false);
				b1.setText("Single Player");
				b2.setVisible(false);
				b3.setVisible(true);
				b3.setText("Settings");
				break;
			case "Settings":
				applied = false;
				if (windowed) {
					resolutionList.setVisible(true);
					b1.setText("Full Screen");
				} else {
					resolutionList.setVisible(false);
					b1.setText("Windowed");
				}
				b2.setText("Back");
				b2.setVisible(true);
				b3.setVisible(false);
				b4.setVisible(false);
				break;
			case "Full Screen":
				b1.setText("Windowed");
				resolutionList.setVisible(false);
				windowed = false;
				this.setExtendedState(JFrame.MAXIMIZED_BOTH);
				try {
					this.setUndecorated(true);
					applyX = getX();
					applyY = getY();
					repaint();
				} catch (IllegalComponentStateException e) {

				}
				break;
			case "Windowed":
				b1.setText("Full Screen");
				resolutionList.setVisible(true);
				windowed = true;
				setSize(applyX, applyY);
				break;
			case "Back":
				switch (gameState) {
				case "Main Menu":
					leftPanel.setVisible(false);
					riskLogo.setVisible(true);
					b4.setVisible(false);
					b3.setVisible(true);
					b1.setText("Single Player");
					b2.setVisible(false);
					b3.setText("Settings");
					resolutionList.setVisible(false);
					break;
				case "Game":
					leftPanel.setVisible(false);
					b4.setVisible(true);
					b3.setVisible(true);
					b1.setText("Settings");
					b2.setText("Return to Main Menu");
					b3.setVisible(false);
					resolutionList.setVisible(false);
					break;
				}
				break;

			}
		};

		ActionListener otherListener = (ActionEvent e1) -> {
			String cmd = e1.getActionCommand();
			switch (cmd) {
			case "Confirm Single Player Game":
				try {
					BufferedReader br = new BufferedReader(
							new FileReader(System.getProperty("user.dir") + "\\default.txt"));
					String[] tileInfo;
					String continent;
					String line = null;
					line = br.readLine();
					tiles = new Tile[Integer.parseInt(line)];
					int tileCount = 0;
					while ((line = br.readLine()) != null) {
						tileInfo = line.split(" ");
						int lines = Integer.parseInt(tileInfo[1]);
						int[][] coords = new int[lines][2];
						line = br.readLine();
						continent = line;
						line = br.readLine();
						line = line.replaceAll(",", " ");
						String[] adj = line.split(" ");
						for (int i = 0; i < lines; i++) {
							line = br.readLine();
							line = line.replaceAll(",", " ");
							String[] temp = line.split(" ");
							int[] tempArray = { Integer.parseInt(temp[0]), Integer.parseInt(temp[1]) };
							coords[i] = tempArray;
						}
						tiles[tileCount] = new Tile(tileInfo[0], coords, adj);
						tiles[tileCount].setContinent(continent);
						switch (tiles[tileCount].getContinent()) {
						case ("North_America"):
							northAmerica[na] = tiles[tileCount];
							na++;
							break;
						case ("South_America"):
							southAmerica[sa] = tiles[tileCount];
							sa++;
							break;
						case ("Europe"):
							europe[eur] = tiles[tileCount];
							eur++;
							break;
						case ("Africa"):
							africa[afr] = tiles[tileCount];
							afr++;
							break;
						case ("Asia"):
							asia[asi] = tiles[tileCount];
							asi++;
							break;
						case ("Australia"):
							australia[aus] = tiles[tileCount];
							aus++;
							break;
						}
						tileCount++;
					}
					br.close();
				} catch (IOException exception) {
					consoleText.append("\nFile not found");
				}
				map = new MapGeneration(tiles);
				riskLogo.setVisible(false);
				panel2.add(map, BorderLayout.CENTER);
				map.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						Rectangle2D.Double[] squares = map.getSquares();
						for (int i = 0; i < squares.length; i++) {
							if (squares[i].contains(e.getX(), e.getY())) {
								for (int j = 0; j < tiles.length; j++) {
									for (int k = 0; k < tiles[j].getCoords().length; k++) {
										int[] temp = tiles[j].getCoords()[k];
										double calc = (int) (temp[0] * map.getSNumber()) + temp[1];
										if (calc == i) {
											map.setTile(tiles[j]);
											map.repaint();
											String tt = tiles[j].getName().replaceAll("_", " ");
											map.setToolTipText(
													tt + "     Troops: " + tiles[j].getCurrentSoldierCount());
										}
									}
								}
							}
						}
					}
				});
				map.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						Rectangle2D.Double[] squares = map.getSquares();
						for (int i = 0; i < squares.length; i++) {
							if (squares[i].contains(e.getX(), e.getY())) {
								for (int j = 0; j < tiles.length; j++) {
									for (int k = 0; k < tiles[j].getCoords().length; k++) {
										int[] temp = tiles[j].getCoords()[k];
										double calc = (int) (temp[0] * map.getSNumber()) + temp[1];
										if (calc == i) {
											String text = tiles[j].getName().replaceAll("_", " ");
											switch (doneState) {
											case "Reinforcement":
												index++;
												if (SwingUtilities.isLeftMouseButton(e)) {
													movingArmy = tiles[j].getName();
													l3.setText("Moving army: " + movingArmy.replaceAll("_", " "));
													movingIndex = j;
													if (movingIndex > -1) {
														comboBox.removeAllItems();
														for (int z = 0; z < tiles[movingIndex]
																.getCurrentSoldierCount(); z++) {
															if (z == 0) {
																comboBox.addItem("Choose a number of troops");
															} else {
																comboBox.addItem(Integer.toString(z));
															}
														}
														repaint();
													}
												} else if (SwingUtilities.isRightMouseButton(e)) {
													receivingArmy = tiles[j].getName();
													receivingIndex = j;
													l4.setText("Receiving army: " + receivingArmy.replaceAll("_", " "));
												}
												break;
											case "Choosing":
												String tt = tiles[j].getName().replaceAll("_", " ");
												l3.setText(tt);
												l4.setText("");
												break;
											case "Attack":
												reinforceCountry = tiles[j].getName();
												l4.setText("Reinforcing: " + reinforceCountry.replaceAll("_", " "));
												break;
											case "Fortify":
												if (SwingUtilities.isLeftMouseButton(e)) {
													attackingArmy = tiles[j].getName();
													l3.setText("Your army: " + attackingArmy.replaceAll("_", " "));
												} else if (SwingUtilities.isRightMouseButton(e)) {
													countryToAttack = tiles[j].getName();
													l4.setText("Attacking: " + countryToAttack.replaceAll("_", " "));
												}
												break;
											}
										}
									}
								}
							}
						}
					}
				});
				break;
			case "Place Troops":

				int countryNum = 0;
				int Reinforce = 0;
				boolean place = false;
				if (reinforceString.equals("Choose a number of troops") || reinforceString.equals("")
						|| reinforceString.equals("0")) {
				} else {
					Reinforce = Integer.parseInt(reinforceString);
				}
				if (troops >= Reinforce) {
					players[0].setReinforcementCount(troops);
					for (int i = 0; i < tiles.length; i++) {
						if (tiles[i].getName().equals(reinforceCountry) && players[0] == tiles[i].getCurrentOwner()) {
							tiles[i].setCurrentSoldierCount(Reinforce + tiles[i].getCurrentSoldierCount());
							place = true;
							countryNum = i;
						}
					}
					if (place == true) {
						if (Reinforce > 0) {
							troops = troops - Reinforce;
							reinforceCountry = reinforceCountry.replaceAll("_", " ");
							l3.setText("You have " + troops + " troops left");
							consoleText.append("\nYou placed " + Reinforce + " troops on " + reinforceCountry);
							consoleText.append("\n" + reinforceCountry + " now has "
									+ tiles[countryNum].getCurrentSoldierCount() + " troops");
							consoleText.append("\nYou currently have " + troops + " troops left");
							players[0].setReinforcementCount(troops);
						} else {
							consoleText.append("\nPlease select a number of troops to reinforce");
						}
					} else if (reinforceString.equals("Choose a number of troops") || reinforceString.equals("")) {
						consoleText.append("\nYou need to select a country to reinforce");
					} else {
						consoleText.append("\nYou cannot place troops on a country you do not own");
						consoleText.append("\nYou currently have " + troops + " troops left");
					}
				} else {
					consoleText.append("\nYou do not have that many troops");
				}
				if (players[0].getReinforcementCount() == 0) {
					b5.setVisible(false);
				}
				if (troops == 0) {
					b6.setVisible(true);
				}
				comboBox.removeAllItems();
				for (int i = 0; i < troops + 1; i++) {
					if (i == 0) {
						comboBox.addItem("Choose a number of troops");
					} else {
						comboBox.addItem(Integer.toString(i));
					}
				}
				l4.setText("Choose a country to Reinforce");
				break;
			case "View Cards":
				cardFrame.setVisible(true);
				cardFrame.setSize(900, 400);
				cardFrame.setLayout(new BorderLayout());
				cardFrame.add(mainCardPanel, BorderLayout.CENTER);
				cardFrame.add(sideCardPanel, BorderLayout.EAST);
				mainCardPanel.setLayout(new BorderLayout());
				sideCardPanel.setLayout(new GridLayout(8, 1));
				sideCardPanel.add(selectCards);
				sideCardPanel.add(message);
				card1.removeAllItems();
				card2.removeAllItems();
				card3.removeAllItems();
				card1.addItem("Choose the card numbers to turn in");
				card2.addItem("Choose the card numbers to turn in");
				card3.addItem("Choose the card numbers to turn in");
				if (players[0].getPlayerHand()[0] != null) {
					card1.addItem("Card" + 1);
					card2.addItem("Card" + 1);
					card3.addItem("Card" + 1);
				}
				if (players[0].getPlayerHand()[1] != null) {
					card1.addItem("Card" + 2);
					card2.addItem("Card" + 2);
					card3.addItem("Card" + 2);
				}
				if (players[0].getPlayerHand()[2] != null) {
					card1.addItem("Card" + 3);
					card2.addItem("Card" + 3);
					card3.addItem("Card" + 3);
				}
				if (players[0].getPlayerHand()[3] != null) {
					card1.addItem("Card" + 4);
					card2.addItem("Card" + 4);
					card3.addItem("Card" + 4);
				}
				if (players[0].getPlayerHand()[4] != null) {
					card1.addItem("Card" + 5);
					card2.addItem("Card" + 5);
					card3.addItem("Card" + 5);
				}
				sideCardPanel.add(card1);
				sideCardPanel.add(card2);
				sideCardPanel.add(card3);
				if (Player.checkMatch() == true) {
					cardsToTurnIn.setText("You have cards to turn in");
				} else {
					cardsToTurnIn.setText("You do not have cards to turn in");
				}
				sideCardPanel.add(turnInCards);
				sideCardPanel.add(info);
				sideCardPanel.add(close);
				mainCardPanel.add(topCardPanel, BorderLayout.NORTH);
				mainCardPanel.add(bottomCardPanel, BorderLayout.CENTER);
				topCardPanel.add(cardsToTurnIn);
				bottomCardPanel.setLayout(new GridLayout(3, 6));
				bottomCardPanel.add(blank);
				bottomCardPanel.add(card1Header);
				bottomCardPanel.add(card2Header);
				bottomCardPanel.add(card3Header);
				bottomCardPanel.add(card4Header);
				bottomCardPanel.add(card5Header);
				if (players[0].getPlayerHand()[0] == null) {
					card1Type.setText("empty");
					card1Country.setText("empty");
				} else {
					card1Type.setText(players[0].getPlayerHand()[0].getUnitType());
					card1Country.setText(players[0].getPlayerHand()[0].getCountry());
				}
				if (players[0].getPlayerHand()[1] == null) {
					card2Type.setText("empty");
					card2Country.setText("empty");
				} else {
					card2Type.setText(players[0].getPlayerHand()[1].getUnitType());
					card2Country.setText(players[0].getPlayerHand()[1].getCountry());
				}
				if (players[0].getPlayerHand()[2] == null) {
					card3Type.setText("empty");
					card3Country.setText("empty");
				} else {
					card3Type.setText(players[0].getPlayerHand()[2].getUnitType());
					card3Country.setText(players[0].getPlayerHand()[2].getCountry());
				}
				if (players[0].getPlayerHand()[3] == null) {
					card4Type.setText("empty");
					card4Country.setText("empty");
				} else {
					card4Type.setText(players[0].getPlayerHand()[3].getUnitType());
					card4Country.setText(players[0].getPlayerHand()[3].getCountry());
				}
				if (players[0].getPlayerHand()[4] == null) {
					card5Type.setText("empty");
					card5Country.setText("empty");
				} else {
					card5Type.setText(players[0].getPlayerHand()[4].getUnitType());
					card5Country.setText(players[0].getPlayerHand()[4].getCountry());
				}
				bottomCardPanel.add(cardType);
				bottomCardPanel.add(card1Type);
				bottomCardPanel.add(card2Type);
				bottomCardPanel.add(card3Type);
				bottomCardPanel.add(card4Type);
				bottomCardPanel.add(card5Type);
				bottomCardPanel.add(cardCountry);
				bottomCardPanel.add(card1Country);
				bottomCardPanel.add(card2Country);
				bottomCardPanel.add(card3Country);
				bottomCardPanel.add(card4Country);
				bottomCardPanel.add(card5Country);

				break;
			case "Close":
				map.setVisible(true);
				panel.setVisible(true);
				mapSidePanel.setVisible(true);

				cardFrame.dispose();
				break;

			case "Help":
				// Choosing
				if (doneState.equals("Choosing")) {
					BufferedImage img = null;
					try {
						img = ImageIO.read(new File(System.getProperty("user.dir") + "\\Choosing.png"));
					} catch (IOException e) {
						consoleText.append("\nFile not Found");
					}
					JComponent picture = new GraphComponent1(img);
					JFrame temp = new JFrame();
					temp.setVisible(true);
					temp.setSize(applyX, applyY);
					temp.add(picture);
					temp.setResizable(false);

				}
				// Fortify
				if (doneState.equals("Reinforcement")) {
					BufferedImage img = null;
					try {
						img = ImageIO.read(new File(System.getProperty("user.dir") + "\\Fortify.png"));
					} catch (IOException e) {
						consoleText.append("\nFile not Found");
					}
					JComponent picture = new GraphComponent1(img);
					JFrame temp = new JFrame();
					temp.setVisible(true);
					temp.setSize(applyX, applyY);
					temp.add(picture);
					temp.setResizable(false);
				}
				// Reinforce
				else if (doneState.equals("Attack")) {
					BufferedImage img = null;
					try {
						img = ImageIO.read(new File(System.getProperty("user.dir") + "\\Reinforcement.png"));
					} catch (IOException e) {
						consoleText.append("\nFile not Found");
					}
					JComponent picture = new GraphComponent1(img);
					JFrame temp = new JFrame();
					temp.setVisible(true);
					temp.setSize(applyX, applyY);
					temp.add(picture);
					temp.setResizable(false);
				}
				// Attack
				else if (doneState.equals("Fortify")) {
					BufferedImage img = null;
					try {
						img = ImageIO.read(new File(System.getProperty("user.dir") + "\\Attack.png"));
					} catch (IOException e) {
						consoleText.append("\nFile not Found");
					}
					JComponent picture = new GraphComponent1(img);
					JFrame temp = new JFrame();
					temp.setVisible(true);
					temp.setSize(applyX, applyY);
					temp.add(picture);
					temp.setResizable(false);
				}

				break;

			case "Info":
				BufferedImage img = null;
				try {
					img = ImageIO.read(new File(System.getProperty("user.dir") + "\\Info.png"));
				} catch (IOException e) {
					consoleText.append("\nFile not Found");
				}
				JComponent picture = new GraphComponent1(img);
				JFrame temp = new JFrame();
				temp.setVisible(true);
				temp.setSize(applyX, applyY);
				temp.add(picture);
				temp.setResizable(false);

				break;
			case "Attack":
				if (doneState.equals("Fortify")) {
					attackingArmyIndex = -1;
					countryToAttackIndex = -1;
					for (int i = 0; i < tiles.length; i++) {
						if (tiles[i].getName().equals(attackingArmy)) {
							attackingArmyIndex = i;
						}
						if (tiles[i].getName() == countryToAttack) {
							countryToAttackIndex = i;
						}
					}
					String adjacentCountries[];
					String tempEmpty[] = { "" };
					boolean attackable = false;
					if (attackingArmyIndex > -1) {
						adjacentCountries = tiles[attackingArmyIndex].getAdjacent();
					} else {
						adjacentCountries = tempEmpty;
					}
					for (int i = 0; i < adjacentCountries.length; i++) {
						try {
							if (adjacentCountries[i].equals(tiles[countryToAttackIndex].getName())) {
								attackable = true;
							}
						} catch (ArrayIndexOutOfBoundsException q) {

						}
					}
					if (attackingArmyIndex == -1 || countryToAttackIndex == -1) {
						consoleText.append("\nYou need to select a country to attack and/or an army to attack with");
					} else if (attackingArmyIndex == countryToAttackIndex) {
						consoleText.append("\nYou need to select two different countries");
					} else if (tiles[attackingArmyIndex].getCurrentOwner() != players[0]) {
						consoleText.append("\nPlease select an army that you own");
					} else if (tiles[countryToAttackIndex].getCurrentOwner() == players[0]) {
						consoleText.append("\nYou cannot attack your own country");
					} else if (tiles[attackingArmyIndex].getCurrentSoldierCount() <= 1) {
						consoleText.append("\nYou do not have enough troops on that country to attack");
					} else if (attackable == false) {
						consoleText.append("\nThe countries you chose are not adjacent to one another");
					} else if (Integer.parseInt(reinforceString) < 1) {
						consoleText.append("\nYou must select a number of dice");
					} else if (Integer.parseInt(reinforceString) > tiles[attackingArmyIndex].getCurrentSoldierCount()) {
						consoleText.append("\nYou do not have enough troops to use that many dice");
					} else {
						Player orginalOwner = tiles[countryToAttackIndex].getCurrentOwner();
						players[0].attack(tiles[attackingArmyIndex], tiles[countryToAttackIndex], d1, d2, d3, d4, d5,
								Integer.parseInt(reinforceString));
						boolean playerWon = true;
						if (orginalOwner != tiles[countryToAttackIndex].getCurrentOwner()) {
							for (int i = 0; i < tiles.length; i++) {
								if (tiles[i].getCurrentOwner() != players[0]) {
									playerWon = false;
								}
							}
							if (playerWon == true) {
								JFrame winFrame = new JFrame();
								JLabel winLabel = new JLabel("You won");
								winFrame.setVisible(true);
								winFrame.setSize(applyX, applyY);
								winFrame.add(winLabel);
								dispose();
								winFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
							}
						}
						if (orginalOwner != tiles[countryToAttackIndex].getCurrentOwner() && playerWon == false) {
							attackPopup = true;
							consoleText.append(
									"\nYou conquered " + tiles[countryToAttackIndex].getName().replaceAll("_", " "));

							// update combo box
							moveAttackingArmyTroops.removeAllItems();
							for (int i = 0; i < tiles[attackingArmyIndex].getCurrentSoldierCount(); i++) {
								if (i == 0) {
									moveAttackingArmyTroops.addItem("Choose a number of troops to move");
								} else {
									moveAttackingArmyTroops.addItem(Integer.toString(i));
								}
							}
							attackPopupFrame.setVisible(true);
							attackPopupFrame.setSize(900, 400);
							attackPopupFrame.add(mainAttackPanel);
							mainAttackPanel.setLayout(new BorderLayout());
							mainAttackPanel.add(topAttackPanel, BorderLayout.NORTH);
							mainAttackPanel.add(middleAttackPanel, BorderLayout.CENTER);
							mainAttackPanel.add(bottomAttackPanel, BorderLayout.SOUTH);
							topAttackPanel.setLayout(new GridLayout(4, 1));
							bottomAttackPanel.setLayout(new GridLayout(2, 1));
							conquerMessage.setText("You conquered " + countryToAttack.replaceAll("_", " ") + " with "
									+ attackingArmy.replaceAll("_", " "));
							troopsMessage.setText(attackingArmy.replaceAll("_", " ") + " now has "
									+ tiles[attackingArmyIndex].getCurrentSoldierCount() + " troops. "
									+ countryToAttack.replaceAll("_", " ") + " has "
									+ tiles[countryToAttackIndex].getCurrentSoldierCount() + " troops");
							topAttackPanel.add(winMessage);
							topAttackPanel.add(conquerMessage);
							topAttackPanel.add(troopsMessage);
							bottomAttackPanel.add(updateCountryAttackedWithArmy);
							bottomAttackPanel.add(updateCountryAttackedArmy);
							updateCountryAttackedArmy.setVisible(false);
							updateCountryAttackedWithArmy.setVisible(false);

							if (tiles[attackingArmyIndex].getCurrentSoldierCount() > 1) {
								moveTroops.setText("Move Troops");
								topAttackPanel.add(questionMessage);
								middleAttackPanel.add(moveAttackingArmyTroops);
								middleAttackPanel.add(moveTroops);
							} else {
								middleAttackPanel.remove(moveAttackingArmyTroops);
								moveTroops.setText("Close Move Troops Screen");
								middleAttackPanel.add(moveTroops);
								topAttackPanel.remove(questionMessage);
							}
							if (cardsDrawn < 1) {
								Deck.drawCard(players[0]);
								consoleText.append("\nYou drew one card for conquerering a country this turn");
								cardsDrawn++;
							}
						}
					}
				}
				break;
			case "Close Move Troops Screen":
				attackPopupFrame.dispose();
				map.repaint();
				break;

			case "Move Troops":
				attackPopupFrame.dispose();
				if (troopsToMove != "0") {
					tiles[attackingArmyIndex].setCurrentSoldierCount(
							tiles[attackingArmyIndex].getCurrentSoldierCount() - troopsToMoveInt);
					tiles[countryToAttackIndex].setCurrentSoldierCount(
							tiles[countryToAttackIndex].getCurrentSoldierCount() + troopsToMoveInt);

					consoleText.append("\nYou moved " + troopsToMoveInt + " from "
							+ tiles[attackingArmyIndex].getName().replaceAll("_", " ") + " to "
							+ tiles[countryToAttackIndex].getName().replaceAll("_", " "));
				}
				map.repaint();
				break;
			case "Fortify":

				if (movingIndex == -1) {
					consoleText.append("\nYou need to select a country to move troops from");
				} else if (receivingIndex == -1) {
					consoleText.append("\nYou need to select a country to move troops to");
				} else if (tiles[movingIndex].getCurrentOwner() != players[0]) {
					consoleText.append("\nPlease choose a country to move troops from that you own.");
				} else if (tiles[receivingIndex].getCurrentOwner() != players[0]) {
					consoleText.append("\nPlease choose a country to move troops to that you own.");
				} else if (reinforceString.equals("0")) {
					consoleText.append("\nPlease choose a number of troops to move");
				} else {
					tiles[movingIndex].setCurrentSoldierCount(
							tiles[movingIndex].getCurrentSoldierCount() - Integer.parseInt(reinforceString));
					tiles[receivingIndex].setCurrentSoldierCount(
							tiles[receivingIndex].getCurrentSoldierCount() + Integer.parseInt(reinforceString));
					b5.setVisible(false);
					consoleText.append("\nYou moved " + Integer.parseInt(reinforceString) + " troops from "
							+ tiles[movingIndex].getName().replaceAll("_", " ") + " to "
							+ tiles[receivingIndex].getName().replaceAll("_", " "));
				}

				break;
			case "Turn in Cards":
				if (doneState.equals("Attack")) {
					int bonus = 0;
					int card1Int = Integer.parseInt(card1ComboBoxSelection);
					int card2Int = Integer.parseInt(card2ComboBoxSelection);
					int card3Int = Integer.parseInt(card3ComboBoxSelection);
					if (card1Int == 0 || card2Int == 0 || card3Int == 0) {
						consoleText.append("\nPlease select cards to turn in");
					} else if (card1ComboBoxSelection == card2ComboBoxSelection
							|| card2ComboBoxSelection == card3ComboBoxSelection
							|| card1ComboBoxSelection == card3ComboBoxSelection) {
						consoleText.append("\nPlease select 3 different cards to turn in");
					} else if ((players[0].getPlayerHand()[card1Int - 1].getUnitType()
							.equals(players[0].getPlayerHand()[card2Int - 1].getUnitType()))
							&& (players[0].getPlayerHand()[card1Int - 1].getUnitType()
									.equals(players[0].getPlayerHand()[card3Int - 1].getUnitType()))
							&& (players[0].getPlayerHand()[card2Int - 1].getUnitType()
									.equals(players[0].getPlayerHand()[card3Int - 1].getUnitType()))) {
						for (int i = 0; i < tiles.length; i++) {
							if (tiles[i].getCurrentOwner() == players[0]) {
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card1Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card2Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card3Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
							}
						}
						players[0].setTurnIn(deck, players[0].getPlayerHand()[card1Int - 1],
								players[0].getPlayerHand()[card2Int - 1], players[0].getPlayerHand()[card3Int - 1],
								tiles);

						bonus = bonus + 8;
						troops = players[0].getReinforcementCount();
						l3.setText("You have " + players[0].getReinforcementCount() + " troops left");
						comboBox.removeAllItems();
						for (int i = 0; i < players[0].getReinforcementCount() + 1; i++) {
							if (i == 0) {
								comboBox.addItem("Choose a number of troops");
							} else {
								comboBox.addItem(Integer.toString(i));
							}
						}
						consoleText.append("\nYou turned in 3 cards of the same type");
						consoleText.append("\nYou received " + 8 + " troops");
						repaint();
						cardFrame.dispose();

					} // if you turn in at least one wild card it is a valid set
					else if ((players[0].getPlayerHand()[card1Int - 1].getUnitType().equals("Wild"))
							|| (players[0].getPlayerHand()[card2Int - 1].getUnitType().equals("Wild"))
							|| (players[0].getPlayerHand()[card3Int - 1].getUnitType().equals("Wild"))) {
						for (int i = 0; i < tiles.length; i++) {
							if (players[0].getPlayerHand()[card1Int - 1].getUnitType() != "Wild") {
								if (tiles[i].getCurrentOwner() == players[0]) {
									if (tiles[i].getName().replaceAll("_", " ")
											.equals(players[0].getPlayerHand()[card1Int - 1].getCountry()
													.replaceAll("_", " "))) {
										bonus = bonus + 2;
									}
									if (players[0].getPlayerHand()[card2Int - 1].getUnitType() != "Wild") {
										if (tiles[i].getCurrentOwner() == players[0]) {
											if (tiles[i].getName().replaceAll("_", " ")
													.equals(players[0].getPlayerHand()[card2Int - 1].getCountry()
															.replaceAll("_", " "))) {
												bonus = bonus + 2;
											}
										}
									}
									if (players[0].getPlayerHand()[card3Int - 1].getUnitType() != "Wild") {
										if (tiles[i].getCurrentOwner() == players[0]) {
											if (tiles[i].getName().replaceAll("_", " ")
													.equals(players[0].getPlayerHand()[card3Int - 1].getCountry()
															.replaceAll("_", " "))) {
												bonus = bonus + 2;
											}
										}
									}
								}
							}
						}

						players[0].setTurnIn(deck, players[0].getPlayerHand()[card1Int - 1],
								players[0].getPlayerHand()[card2Int - 1], players[0].getPlayerHand()[card3Int - 1],
								tiles);

						bonus = bonus + 8;
						troops = players[0].getReinforcementCount();
						l3.setText("You have " + players[0].getReinforcementCount() + " troops left");
						comboBox.removeAllItems();
						for (int i = 0; i < players[0].getReinforcementCount() + 1; i++) {
							if (i == 0) {
								comboBox.addItem("Choose a number of troops");
							} else {
								comboBox.addItem(Integer.toString(i));
							}
						}
						consoleText.append("\nYou turned in a set containing at least one wild card");
						consoleText.append("\nYou received " + 8 + " troops");
						repaint();
						cardFrame.dispose();

					} else if ((players[0].getPlayerHand()[card1Int - 1]
							.getUnitType() != players[0].getPlayerHand()[card2Int - 1].getUnitType())
							&& (players[0].getPlayerHand()[card1Int - 1]
									.getUnitType() != (players[0].getPlayerHand()[card3Int - 1].getUnitType()))
							&& (players[0].getPlayerHand()[card2Int - 1]
									.getUnitType() != (players[0].getPlayerHand()[card3Int - 1].getUnitType()))) {
						for (int i = 0; i < tiles.length; i++) {
							if (tiles[i].getCurrentOwner() == players[0]) {
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card1Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card2Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card3Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
							}
						}

						players[0].setTurnIn(deck, players[0].getPlayerHand()[card1Int - 1],
								players[0].getPlayerHand()[card2Int - 1], players[0].getPlayerHand()[card3Int - 1],
								tiles);

						bonus = bonus + 8;
						troops = players[0].getReinforcementCount();
						l3.setText("You have " + players[0].getReinforcementCount() + " troops left");
						comboBox.removeAllItems();
						for (int i = 0; i < players[0].getReinforcementCount() + 1; i++) {
							if (i == 0) {
								comboBox.addItem("Choose a number of troops");
							} else {
								comboBox.addItem(Integer.toString(i));
							}
						}
						consoleText.append("\nYou turned in 3 cards of different types");
						consoleText.append("\nYou received " + 8 + " troops");
						repaint();
						cardFrame.dispose();

					} else {
						consoleText.append("\nPlease turn in a valid set of cards");
					}
				} else {
					consoleText.append("\nYou cannot turn in cards if you are not on the Reinforcement phase");
				}
				break;

			}
		};

		ItemListener itemListener = (ItemEvent e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Object item = e.getItem();
				if (item == "720p") {
					setSize(1280, 720);
					applyX = 1280;
					applyY = 720;
				}
				if (item == "1080p") {
					setSize(1920, 1045);
					applyX = 1920;
					applyY = 1045;
				}
			}
		};
		ItemListener itemListenerCard1 = (ItemEvent e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Object item = e.getItem();
				if (item.equals("Card1")) {
					card1ComboBoxSelection = "1";
				} else if (item.equals("Card2")) {
					card1ComboBoxSelection = "2";
				} else if (item.equals("Card3")) {
					card1ComboBoxSelection = "3";
				} else if (item.equals("Card4")) {
					card1ComboBoxSelection = "4";
				} else if (item.equals("Card5")) {
					card1ComboBoxSelection = "5";
				} else {
					card1ComboBoxSelection = "0";
				}
				if (doneState.equals("Attack")) {
					int bonus = 0;
					int card1Int = Integer.parseInt(card1ComboBoxSelection);
					int card2Int = Integer.parseInt(card2ComboBoxSelection);
					int card3Int = Integer.parseInt(card3ComboBoxSelection);
					if (card1Int == 0 || card2Int == 0 || card3Int == 0) {
					} else if (card1ComboBoxSelection == card2ComboBoxSelection
							|| card2ComboBoxSelection == card3ComboBoxSelection
							|| card1ComboBoxSelection == card3ComboBoxSelection) {
					} else if ((players[0].getPlayerHand()[card1Int - 1].getUnitType()
							.equals(players[0].getPlayerHand()[card2Int - 1].getUnitType()))
							&& (players[0].getPlayerHand()[card1Int - 1].getUnitType()
									.equals(players[0].getPlayerHand()[card3Int - 1].getUnitType()))
							&& (players[0].getPlayerHand()[card2Int - 1].getUnitType()
									.equals(players[0].getPlayerHand()[card3Int - 1].getUnitType()))) {
						for (int i = 0; i < tiles.length; i++) {
							if (tiles[i].getCurrentOwner() == players[0]) {
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card1Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card2Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card3Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
							}
						}

						bonus = bonus + 8;

					} // if you turn in at least one wild card it is a valid set
					else if ((players[0].getPlayerHand()[card1Int - 1].getUnitType().equals("Wild"))
							|| (players[0].getPlayerHand()[card2Int - 1].getUnitType().equals("Wild"))
							|| (players[0].getPlayerHand()[card3Int - 1].getUnitType().equals("Wild"))) {
						for (int i = 0; i < tiles.length; i++) {
							if (players[0].getPlayerHand()[card1Int - 1].getUnitType() != "Wild") {
								if (tiles[i].getCurrentOwner() == players[0]) {
									if (tiles[i].getName().replaceAll("_", " ")
											.equals(players[0].getPlayerHand()[card1Int - 1].getCountry()
													.replaceAll("_", " "))) {
										bonus = bonus + 2;
									}
									if (players[0].getPlayerHand()[card2Int - 1].getUnitType() != "Wild") {
										if (tiles[i].getCurrentOwner() == players[0]) {
											if (tiles[i].getName().replaceAll("_", " ")
													.equals(players[0].getPlayerHand()[card2Int - 1].getCountry()
															.replaceAll("_", " "))) {
												bonus = bonus + 2;
											}
										}
									}
									if (players[0].getPlayerHand()[card3Int - 1].getUnitType() != "Wild") {
										if (tiles[i].getCurrentOwner() == players[0]) {
											if (tiles[i].getName().replaceAll("_", " ")
													.equals(players[0].getPlayerHand()[card3Int - 1].getCountry()
															.replaceAll("_", " "))) {
												bonus = bonus + 2;
											}
										}
									}
								}
							}
						}

						bonus = bonus + 8;

					} else if ((players[0].getPlayerHand()[card1Int - 1]
							.getUnitType() != players[0].getPlayerHand()[card2Int - 1].getUnitType())
							&& (players[0].getPlayerHand()[card1Int - 1]
									.getUnitType() != (players[0].getPlayerHand()[card3Int - 1].getUnitType()))
							&& (players[0].getPlayerHand()[card2Int - 1]
									.getUnitType() != (players[0].getPlayerHand()[card3Int - 1].getUnitType()))) {
						for (int i = 0; i < tiles.length; i++) {
							if (tiles[i].getCurrentOwner() == players[0]) {
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card1Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card2Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card3Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
							}
						}
						bonus = bonus + 8;
					} else {
					}
					if (bonus > 0) {
						message.setText("Bonus: " + 8);
					} else {
						message.setText("This is not a valid set");
					}

				} else {
				}
				cardFrame.repaint();
			}
		};
		ItemListener itemListenerCard2 = (ItemEvent e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Object item = e.getItem();
				if (item.equals("Card1")) {
					card2ComboBoxSelection = "1";
				} else if (item.equals("Card2")) {
					card2ComboBoxSelection = "2";
				} else if (item.equals("Card3")) {
					card2ComboBoxSelection = "3";
				} else if (item.equals("Card4")) {
					card2ComboBoxSelection = "4";
				} else if (item.equals("Card5")) {
					card2ComboBoxSelection = "5";
				} else {
					card2ComboBoxSelection = "0";
				}
				if (doneState.equals("Attack")) {
					int bonus = 0;
					int card1Int = Integer.parseInt(card1ComboBoxSelection);
					int card2Int = Integer.parseInt(card2ComboBoxSelection);
					int card3Int = Integer.parseInt(card3ComboBoxSelection);
					if (card1Int == 0 || card2Int == 0 || card3Int == 0) {
					} else if (card1ComboBoxSelection == card2ComboBoxSelection
							|| card2ComboBoxSelection == card3ComboBoxSelection
							|| card1ComboBoxSelection == card3ComboBoxSelection) {
					} else if ((players[0].getPlayerHand()[card1Int - 1].getUnitType()
							.equals(players[0].getPlayerHand()[card2Int - 1].getUnitType()))
							&& (players[0].getPlayerHand()[card1Int - 1].getUnitType()
									.equals(players[0].getPlayerHand()[card3Int - 1].getUnitType()))
							&& (players[0].getPlayerHand()[card2Int - 1].getUnitType()
									.equals(players[0].getPlayerHand()[card3Int - 1].getUnitType()))) {
						for (int i = 0; i < tiles.length; i++) {
							if (tiles[i].getCurrentOwner() == players[0]) {
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card1Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card2Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card3Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
							}
						}

						bonus = bonus + 8;

					} // if you turn in at least one wild card it is a valid set
					else if ((players[0].getPlayerHand()[card1Int - 1].getUnitType().equals("Wild"))
							|| (players[0].getPlayerHand()[card2Int - 1].getUnitType().equals("Wild"))
							|| (players[0].getPlayerHand()[card3Int - 1].getUnitType().equals("Wild"))) {
						for (int i = 0; i < tiles.length; i++) {
							if (players[0].getPlayerHand()[card1Int - 1].getUnitType() != "Wild") {
								if (tiles[i].getCurrentOwner() == players[0]) {
									if (tiles[i].getName().replaceAll("_", " ")
											.equals(players[0].getPlayerHand()[card1Int - 1].getCountry()
													.replaceAll("_", " "))) {
										bonus = bonus + 2;
									}
									if (players[0].getPlayerHand()[card2Int - 1].getUnitType() != "Wild") {
										if (tiles[i].getCurrentOwner() == players[0]) {
											if (tiles[i].getName().replaceAll("_", " ")
													.equals(players[0].getPlayerHand()[card2Int - 1].getCountry()
															.replaceAll("_", " "))) {
												bonus = bonus + 2;
											}
										}
									}
									if (players[0].getPlayerHand()[card3Int - 1].getUnitType() != "Wild") {
										if (tiles[i].getCurrentOwner() == players[0]) {
											if (tiles[i].getName().replaceAll("_", " ")
													.equals(players[0].getPlayerHand()[card3Int - 1].getCountry()
															.replaceAll("_", " "))) {
												bonus = bonus + 2;
											}
										}
									}
								}
							}
						}

						bonus = bonus + 8;

					} else if ((players[0].getPlayerHand()[card1Int - 1]
							.getUnitType() != players[0].getPlayerHand()[card2Int - 1].getUnitType())
							&& (players[0].getPlayerHand()[card1Int - 1]
									.getUnitType() != (players[0].getPlayerHand()[card3Int - 1].getUnitType()))
							&& (players[0].getPlayerHand()[card2Int - 1]
									.getUnitType() != (players[0].getPlayerHand()[card3Int - 1].getUnitType()))) {
						for (int i = 0; i < tiles.length; i++) {
							if (tiles[i].getCurrentOwner() == players[0]) {
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card1Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card2Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card3Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
							}
						}
						bonus = bonus + 8;
					} else {
					}
					if (bonus > 0) {
						message.setText("Bonus: " + 8);
					} else {
						message.setText("This is not a valid set");
					}

				} else {
				}
				cardFrame.repaint();
			}
		};
		ItemListener itemListenerCard3 = (ItemEvent e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Object item = e.getItem();
				if (item.equals("Card1")) {
					card3ComboBoxSelection = "1";
				} else if (item.equals("Card2")) {
					card3ComboBoxSelection = "2";
				} else if (item.equals("Card3")) {
					card3ComboBoxSelection = "3";
				} else if (item.equals("Card4")) {
					card3ComboBoxSelection = "4";
				} else if (item.equals("Card5")) {
					card3ComboBoxSelection = "5";
				} else {
					card3ComboBoxSelection = "0";
				}
				int bonus = 0;
				int card1Int = Integer.parseInt(card1ComboBoxSelection);
				int card2Int = Integer.parseInt(card2ComboBoxSelection);
				int card3Int = Integer.parseInt(card3ComboBoxSelection);
				if (card1Int == 0 || card2Int == 0 || card3Int == 0) {
				} else if (card1ComboBoxSelection == card2ComboBoxSelection
						|| card2ComboBoxSelection == card3ComboBoxSelection
						|| card1ComboBoxSelection == card3ComboBoxSelection) {
				} else if ((players[0].getPlayerHand()[card1Int - 1].getUnitType()
						.equals(players[0].getPlayerHand()[card2Int - 1].getUnitType()))
						&& (players[0].getPlayerHand()[card1Int - 1].getUnitType()
								.equals(players[0].getPlayerHand()[card3Int - 1].getUnitType()))
						&& (players[0].getPlayerHand()[card2Int - 1].getUnitType()
								.equals(players[0].getPlayerHand()[card3Int - 1].getUnitType()))) {
					for (int i = 0; i < tiles.length; i++) {
						if (tiles[i].getCurrentOwner() == players[0]) {
							if (tiles[i].getName().replaceAll("_", " ").equals(
									players[0].getPlayerHand()[card1Int - 1].getCountry().replaceAll("_", " "))) {
								bonus = bonus + 2;
							}
							if (tiles[i].getName().replaceAll("_", " ").equals(
									players[0].getPlayerHand()[card2Int - 1].getCountry().replaceAll("_", " "))) {
								bonus = bonus + 2;
							}
							if (tiles[i].getName().replaceAll("_", " ").equals(
									players[0].getPlayerHand()[card3Int - 1].getCountry().replaceAll("_", " "))) {
								bonus = bonus + 2;
							}
						}
					}

					bonus = bonus + 8;

				} // if you turn in at least one wild card it is a valid set
				else if ((players[0].getPlayerHand()[card1Int - 1].getUnitType().equals("Wild"))
						|| (players[0].getPlayerHand()[card2Int - 1].getUnitType().equals("Wild"))
						|| (players[0].getPlayerHand()[card3Int - 1].getUnitType().equals("Wild"))) {
					for (int i = 0; i < tiles.length; i++) {
						if (players[0].getPlayerHand()[card1Int - 1].getUnitType() != "Wild") {
							if (tiles[i].getCurrentOwner() == players[0]) {
								if (tiles[i].getName().replaceAll("_", " ").equals(
										players[0].getPlayerHand()[card1Int - 1].getCountry().replaceAll("_", " "))) {
									bonus = bonus + 2;
								}
								if (players[0].getPlayerHand()[card2Int - 1].getUnitType() != "Wild") {
									if (tiles[i].getCurrentOwner() == players[0]) {
										if (tiles[i].getName().replaceAll("_", " ")
												.equals(players[0].getPlayerHand()[card2Int - 1].getCountry()
														.replaceAll("_", " "))) {
											bonus = bonus + 2;
										}
									}
								}
								if (players[0].getPlayerHand()[card3Int - 1].getUnitType() != "Wild") {
									if (tiles[i].getCurrentOwner() == players[0]) {
										if (tiles[i].getName().replaceAll("_", " ")
												.equals(players[0].getPlayerHand()[card3Int - 1].getCountry()
														.replaceAll("_", " "))) {
											bonus = bonus + 2;
										}
									}
								}
							}
						}
					}

					bonus = bonus + 8;

				} else if ((players[0].getPlayerHand()[card1Int - 1]
						.getUnitType() != players[0].getPlayerHand()[card2Int - 1].getUnitType())
						&& (players[0].getPlayerHand()[card1Int - 1]
								.getUnitType() != (players[0].getPlayerHand()[card3Int - 1].getUnitType()))
						&& (players[0].getPlayerHand()[card2Int - 1]
								.getUnitType() != (players[0].getPlayerHand()[card3Int - 1].getUnitType()))) {
					for (int i = 0; i < tiles.length; i++) {
						if (tiles[i].getCurrentOwner() == players[0]) {
							if (tiles[i].getName().replaceAll("_", " ").equals(
									players[0].getPlayerHand()[card1Int - 1].getCountry().replaceAll("_", " "))) {
								bonus = bonus + 2;
							}
							if (tiles[i].getName().replaceAll("_", " ").equals(
									players[0].getPlayerHand()[card2Int - 1].getCountry().replaceAll("_", " "))) {
								bonus = bonus + 2;
							}
							if (tiles[i].getName().replaceAll("_", " ").equals(
									players[0].getPlayerHand()[card3Int - 1].getCountry().replaceAll("_", " "))) {
								bonus = bonus + 2;
							}
						}
					}
					bonus = bonus + 8;
				} else {
				}
				if (bonus > 0) {
					message.setText("Bonus: " + 8);
				} else {
					message.setText("This is not a valid set");
				}

				cardFrame.repaint();
			}
		};
		ItemListener itemListenerReinforce = (ItemEvent e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Object item = e.getItem();
				if (item == "Choose a number of troops") {
					if (roundNum >= 1) {
						reinforceString = item.toString();
					}
				} else if (item == "Choose a number of dice" || item == "Choose a number of troops") {
					reinforceString = "0";
				} else {
					reinforceString = item.toString();
				}
			}
		};
		ItemListener itemListenerAttack = (ItemEvent e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Object item = e.getItem();
				if (item == "Choose a number of troops to move") {
					troopsToMove = "0";
					attackPopupFrame.repaint();
				} else {
					troopsToMove = item.toString();
					troopsToMoveInt = Integer.parseInt(troopsToMove);
					updateCountryAttackedWithArmy.setText(attackingArmy.replaceAll("_", " ") + " will have "
							+ (tiles[attackingArmyIndex].getCurrentSoldierCount() - troopsToMoveInt) + " troops.");
					updateCountryAttackedArmy.setText(countryToAttack.replaceAll("_", " ") + " will have "
							+ (tiles[countryToAttackIndex].getCurrentSoldierCount() + troopsToMoveInt) + " troops.");
					updateCountryAttackedArmy.setVisible(true);
					updateCountryAttackedWithArmy.setVisible(true);
					attackPopupFrame.repaint();
				}
			}
		};
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(System.getProperty("user.dir") + "\\Risk.png"));
		} catch (IOException e) {
			consoleText.append("\nFile not Found");
		}
		riskLogo = new GraphComponent(img);
		setResizable(false);
		setVisible(true);
		setIconImage(img);
		setSize(applyX, applyY);
		setLocation(0, 0);
		setTitle("RISK");
		add(panel2);
		panel2.setBackground(Color.black);
		panel2.setLayout(new BorderLayout());
		panel2.add(panel, BorderLayout.SOUTH);
		panel2.add(leftPanel, BorderLayout.WEST);
		panel2.add(riskLogo, BorderLayout.CENTER);
		panel.add(resolutionList, BorderLayout.SOUTH);
		resolutionList.setVisible(false);
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		b4.setVisible(false);
		b2.setVisible(false);
		getContentPane().setBackground(Color.black);
		panel.setBackground(Color.black);
		b1.addActionListener(buttonListener);
		b1.addActionListener(otherListener);
		b2.addActionListener(buttonListener);
		b3.addActionListener(buttonListener);
		b3.addActionListener(otherListener);
		b4.addActionListener(buttonListener);
		b4.addActionListener(otherListener);
		b5.addActionListener(buttonListener);
		b5.addActionListener(otherListener);
		b6.addActionListener(buttonListener);
		b7.addActionListener(buttonListener);
		b7.addActionListener(otherListener);
		resolutionList.addActionListener(buttonListener);
		resolutionList.addItemListener(itemListener);
		comboBox.addItemListener(itemListenerReinforce);
		moveAttackingArmyTroops.addItemListener(itemListenerAttack);
		card1.addItemListener(itemListenerCard1);
		card2.addItemListener(itemListenerCard2);
		card3.addItemListener(itemListenerCard3);
		info.addActionListener(otherListener);

		close.addActionListener(otherListener);
		moveTroops.addActionListener(otherListener);
		turnInCards.addActionListener(otherListener);

		mapSidePanel.setVisible(false);
		leftPanel.setVisible(false);
		mapSidePanel.setLayout(new GridLayout(8, 1));
		l1.setText("Player 1 it is your turn");
		l2.setText("Phase Reinforcement");
		l4.setText("Choose a country to Reinforce");
		mapSidePanel.setBackground(b);
		leftPanel.setBackground(b);
		mapSidePanel.add(l1);
		l1.setForeground(Color.WHITE);
		mapSidePanel.add(l2);
		l2.setForeground(Color.WHITE);
		mapSidePanel.add(l3);
		l3.setForeground(Color.WHITE);
		mapSidePanel.add(l4);
		l4.setVerticalAlignment(SwingConstants.CENTER);
		l4.setForeground(Color.WHITE);
		comboBox.removeAllItems();
		for (int i = 0; i < troops + 1; i++) {
			if (i == 0) {
				comboBox.addItem("Choose a number of troops");
			} else {
				comboBox.addItem(Integer.toString(i));
			}
		}
		mapSidePanel.add(comboBox);
		mapSidePanel.add(b5);
		mapSidePanel.add(b6);
		mapSidePanel.add(b7);
		leftPanel.add(tf);
		colorLabel.setForeground(Color.WHITE);
		leftPanel.add(colorLabel);
		leftPanel.add(color);
		leftPanel.add(startPos);
		panel2.add(mapSidePanel, BorderLayout.EAST);
		mapSidePanel.repaint();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		RiskMenu risk = new RiskMenu();
	}

	public class GraphComponent extends JComponent {

		private BufferedImage y;

		private GraphComponent(BufferedImage x) {
			y = x;
		}

		public void paintComponent(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, applyX, applyX);

			if (applyX == 640) {
				g.drawImage(y, 100, 100, 400, 200, Color.BLACK, null);
			}
			if (applyX == screenWidth) {
				g.drawImage(y, 100, 100, 800, 400, Color.BLACK, null);
			}
			if (applyX == 1280) {
				g.drawImage(y, 100, 100, 800, 400, Color.BLACK, null);
			}
			if (applyX == 1920) {
				g.drawImage(y, 100, 100, 1000, 500, Color.BLACK, null);
			}
			if (applyX == 2560) {
				g.drawImage(y, 100, 100, 1200, 600, Color.BLACK, null);
			}
		}

	}

	public class GraphComponent1 extends JComponent {

		private BufferedImage y;

		private GraphComponent1(BufferedImage x) {
			y = x;
		}

		public void paintComponent(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, applyX, applyX);
			g.drawImage(y, -5, -30, applyX, applyY, Color.BLACK, null);

		}

	}
}
