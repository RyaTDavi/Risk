import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

//NOAH ADDED!
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class TestProgram {

	int allX = 640;
	int allY = 480;
	int applyX = 640;
	int applyY = 480;
	static boolean windowed = true;
	boolean holderWindowed = true;
	boolean applied = false;
	boolean quickSettings = false;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public JFrame menuFrame = new JFrame();
	public JFrame singlePlayerFrame = new JFrame();
	public JFrame singlePlayerGameFrame = new JFrame();
	public JFrame multiplayerFrame = new JFrame();
	public JFrame multiplayerGameFrame = new JFrame();
	public JFrame settingsFrame = new JFrame();

	int screenHeight = screenSize.height - 30;
	int screenWidth = screenSize.width + 20;

	public JButton singlePlayer = new JButton("Single Player");
	public JButton multiplayer = new JButton("Multiplayer");
	public JButton settings = new JButton("Settings");
	public JButton back = new JButton("Back");
	public JButton menu = new JButton("Return to Main Menu");
	public JButton inGameSettings = new JButton("Quick Settings");
	public JButton save = new JButton("Save");
	public JButton confirmMultiplayer = new JButton("Confirm Multiplayer Game");
	public JButton confirmSinglePlayer = new JButton("Confirm Single Player Game");
	public JButton applySettings = new JButton("Apply");
	public JButton fullScreen = new JButton("Full Screen: OFF");
	public JButton close = new JButton("Close");

	public JLabel resolution = new JLabel("Change Resolution");

	public JPanel menuPanel = new JPanel();
	public JPanel singlePlayerPanel = new JPanel();
	public JPanel settingsPanel = new JPanel(); 
	public JPanel multiplayerPanel = new JPanel();
	public JPanel singlePlayerGamePanel = new JPanel();
	public JPanel multiplayerGamePanel = new JPanel();
	public JPanel test = new JPanel();
	
	private Tile[] tiles;
	
	//NOAH ADDED!
	public JFileChooser fc = new JFileChooser();

	String[] resolutions = new String[] { "480p", "720p", "1080p", "1440p" };
	JComboBox<String> resolutionList = new JComboBox<>(resolutions);

	JCheckBox muteAll = new JCheckBox("Mute All Audio");
	JCheckBox muteMusic = new JCheckBox("Mute All Music");
	JCheckBox muteSFX = new JCheckBox("Mute SFX");

	MyItemListener actionListener = new MyItemListener();
	BorderLayout bLayout = new BorderLayout();

	public TestProgram() {
		
		menuFrame.setResizable(false);
		singlePlayerFrame.setResizable(false);
		singlePlayerGameFrame.setResizable(false);
		multiplayerFrame.setResizable(false);
		multiplayerGameFrame.setResizable(false);
		settingsFrame.setResizable(false);
		
		System.out.println(screenHeight);
		System.out.println(screenWidth);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:\\Users\\nrudolph\\Desktop\\Risk.png"));
		} catch (IOException e) {
			System.out.println("File not Found");
		}
		JComponent riskLogo = new GraphComponent(img);

		menuFrame.setVisible(true);
		menuFrame.setSize(applyX, applyY);
		// menuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		menuFrame.setLocation(-8, 0);
		menuFrame.setTitle("RISK");

		//BorderLayout bLayout = new BorderLayout();
		menuFrame.setLayout(bLayout);
		menuFrame.add(riskLogo, bLayout.CENTER);
		menuPanel.setBackground(Color.BLACK);
		menuFrame.add(menuPanel, bLayout.SOUTH);
		menuPanel.add(singlePlayer, bLayout.SOUTH);
		menuPanel.add(multiplayer, bLayout.SOUTH);
		menuPanel.add(settings, bLayout.SOUTH);

		menuFrame.setBackground(Color.black);
		singlePlayer.addActionListener(new ButtonListener());
		settings.addActionListener(new ButtonListener());
		multiplayer.addActionListener(new ButtonListener());
		menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/*public static void main(String[] args) {
		TestProgram w = new TestProgram();
	}*/

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();

			if (s == "Single Player") {
				menuFrame.dispose();
				singlePlayerFrame.setVisible(true);
				singlePlayerPanel.setBackground(Color.BLACK);
				singlePlayerFrame.setSize(applyX, applyY);
				singlePlayerFrame.setLocation(-8, 0);
				singlePlayerFrame.setTitle("Single Player");
				singlePlayerFrame.add(singlePlayerPanel);
				singlePlayerPanel.add(confirmSinglePlayer);
				singlePlayerPanel.add(back);
				back.addActionListener(new ButtonListener());
				confirmSinglePlayer.addActionListener(new ButtonListener());

				singlePlayerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
			if (s == "Confirm Single Player Game") {
				//NOAH ADDED!
				int result = fc.showOpenDialog(singlePlayerFrame);
				try{
					BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()));
					String[] tileInfo;
					String line = null;
					line = br.readLine();
					tiles = new Tile[Integer.parseInt(line)];
					System.out.println(line);
					int tileCount = 0;
					while((line = br.readLine()) != null){
						tileInfo = line.split(" ");
						int lines = Integer.parseInt(tileInfo[1]);
						int[][] coords = new int[lines][2];
						line = br.readLine();
						line = line.replaceAll(","," ");
						String[] adj = line.split(" ");
						for(int i=0;i<lines;i++){
							line = br.readLine();
							line = line.replaceAll(",", " ");
							String[] temp = line.split(" ");
							int[] tempArray = {Integer.parseInt(temp[0]),Integer.parseInt(temp[1])};
							System.out.println(tempArray[0] + " " + tempArray[1]);
							coords[i] = tempArray;
						}
						tiles[tileCount] =  new Tile(tileInfo[0],coords,adj); 
						System.out.println(tileInfo[0]);
						System.out.println(tileCount);
						tileCount++;
					}
				}catch(IOException exception){
					System.out.println("File not found");
				}
				
				singlePlayerFrame.dispose();
				singlePlayerGameFrame.setVisible(true);
				Color b = new Color(0,100,210);
				singlePlayerGamePanel.setBackground(b);
				test.setBackground(b);
				BorderLayout bLayout = new BorderLayout();
				test.setLayout(bLayout);
				singlePlayerGameFrame.add(test);
				//singlePlayerGameFrame.setLayout(bLayout);
				///singlePlayerGameFrame.add(singlePlayerGamePanel, bLayout.SOUTH);
				test.add(singlePlayerGamePanel, bLayout.SOUTH);
				singlePlayerGameFrame.setSize(applyX, applyY);
				singlePlayerGameFrame.setLocation(-8, 0);
				singlePlayerGameFrame.setTitle("RISK");
				
				for(int i=0;i<tiles.length;i++){
					System.out.println(tiles[i].getName());
				}

				MapGeneration map = new MapGeneration(tiles);
				System.out.println(tiles[0].getAdjacent()[0]);
				//singlePlayerGameFrame.add(map, bLayout.CENTER);
				test.add(map,bLayout.CENTER);
				singlePlayerGamePanel.add(inGameSettings);
				singlePlayerGamePanel.add(menu);
				singlePlayerGamePanel.add(save);
				menu.addActionListener(new ButtonListener());
				inGameSettings.addActionListener(new ButtonListener());

				singlePlayerGameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
			if (s == "Confirm Multiplayer Game") {
				multiplayerFrame.dispose();
				multiplayerGameFrame.setVisible(true);
				Color b = new Color(0,100,210);
				multiplayerGamePanel.setBackground(b);
				test.setBackground(b);
				BorderLayout bLayout = new BorderLayout();
				test.setLayout(bLayout);
				multiplayerGameFrame.add(test);
				test.add(multiplayerGamePanel, bLayout.SOUTH);
				multiplayerGameFrame.setSize(applyX, applyY);
				multiplayerGameFrame.setLocation(-8, 0);
				multiplayerGameFrame.setTitle("RISK");

				MapGeneration map = new MapGeneration(tiles);
				
				test.add(map,bLayout.CENTER);
				multiplayerGamePanel.add(inGameSettings);
				multiplayerGamePanel.add(menu);
				multiplayerGamePanel.add(save);
				menu.addActionListener(new ButtonListener());
				inGameSettings.addActionListener(new ButtonListener());

				multiplayerGameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
			if (s == "Settings" || s == "Quick Settings") {
				if(windowed == true) {
					fullScreen.setText("Full Screen: OFF");
				}
				if(windowed == false) {
					fullScreen.setText("Full Screen: ON");
				}
				if(s == "Settings") {
					quickSettings = false;
				}
				if(s == "Quick Settings") {
					quickSettings = true;
				}
				applied = false;
				menuFrame.setVisible(false);
				settingsFrame.setVisible(true);
				settingsPanel.setBackground(Color.BLACK);
				settingsFrame.setSize(applyX, applyY);
				settingsFrame.setLocation(-8, 0);
				settingsFrame.setTitle("Settings");
				settingsFrame.add(settingsPanel);
				settingsPanel.remove(applySettings);
				if (windowed == true) {
					settingsPanel.add(resolution);
					settingsPanel.add(resolutionList);
				}
				settingsPanel.add(fullScreen);
				settingsPanel.add(muteAll);
				settingsPanel.add(muteMusic);
				settingsPanel.add(muteSFX);
				if(quickSettings == false) {
					//settingsPanel.remove(close);
					settingsPanel.add(back);
					back.addActionListener(new ButtonListener());
				}
				if(quickSettings == true) {
					//settingsPanel.remove(back);
					settingsPanel.add(close);
					close.addActionListener(new ButtonListener());
				}
				resolutionList.addItemListener(actionListener);
				fullScreen.addActionListener(new ButtonListener());

			}
			if(s == "Close") {
				settingsPanel.remove(close);
				settingsFrame.dispose();
				if(applied == false && holderWindowed != windowed) {
					if(holderWindowed == true) {
						windowed = true;
					}
					if(holderWindowed == false) {
						windowed = false;
					}
				}
			}
			if (s == "Back" || s == "Return to Main Menu") {
				if (applied == false) {
					fullScreen.setText("Full Screen: OFF");
				}
				multiplayerFrame.dispose();
				settingsFrame.dispose();
				singlePlayerFrame.dispose();
				multiplayerFrame.dispose();
				menuFrame.setVisible(true);
				singlePlayerGameFrame.dispose();
				multiplayerGameFrame.dispose();
				//menuFrame.remove(settings);
				//menuFrame.add(settings,bLayout.SOUTH);
			}
			if (s == "Multiplayer") {
				menuFrame.dispose();
				multiplayerFrame.setVisible(true);
				multiplayerPanel.setBackground(Color.BLACK);
				multiplayerFrame.setSize(applyX, applyY);
				multiplayerFrame.setLocation(-8, 0);
				multiplayerFrame.setTitle("Multiplayer");
				multiplayerFrame.add(multiplayerPanel);
				multiplayerPanel.add(confirmMultiplayer);
				multiplayerPanel.add(back);
				back.addActionListener(new ButtonListener());
				confirmMultiplayer.addActionListener(new ButtonListener());
			}
			if (s == "Apply") {
				applied = true;
				if (windowed == false) {
					holderWindowed = false;
					settingsPanel.remove(applySettings);
					settingsPanel.repaint();
					//singlePlayerGamePanel.repaint();
					setAllX(screenWidth);
					setAllY(screenHeight);
					// applyX = screenWidth;
					// applyY = screenHeight;
					apply();
				} else {
					holderWindowed = true;
					settingsPanel.remove(applySettings);
					settingsPanel.repaint();
					//singlePlayerGamePanel.repaint();
					apply();
				}
				singlePlayerGamePanel.repaint();
				test.repaint();
				singlePlayerGameFrame.repaint();
				singlePlayerGameFrame.setSize(applyX,applyY);
			}
			if (s == "Full Screen: OFF") {
				windowed = false;
				// settingsFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				settingsPanel.remove(resolutionList);
				settingsPanel.remove(resolution);
				settingsFrame.setSize(screenWidth, screenHeight);
				settings(screenWidth, screenHeight);
				// setAllX(settingsFrame.getWidth());
				// setAllY(settingsFrame.getHeight());
				fullScreen.setText("Full Screen: ON");

			}
			if (s == "Full Screen: ON") {
				windowed = true;
				settingsPanel.removeAll();
				settingsPanel.remove(resolution);
				settingsPanel.remove(applySettings);
				settingsPanel.add(resolution);
				settingsPanel.add(resolutionList);
				settingsPanel.add(fullScreen);
				settingsPanel.add(muteAll);
				settingsPanel.add(muteMusic);
				settingsPanel.add(muteSFX);
				if(quickSettings == true) {
					settingsPanel.remove(back);
					settingsPanel.add(close);
				}
				if(quickSettings == false) {
					settingsPanel.remove(close);
					settingsPanel.add(back);
				}
				// settingsFrame.setSize(applyX, applyY);
				settings(640, 480);
				setAllX(640);
				setAllY(480);
				resolutionList.setSelectedIndex(0);
				fullScreen.setText("Full Screen: OFF");

			}
		}

	}

	class MyItemListener implements ItemListener {
		// This method is called only if a new item has been selected.
		public void itemStateChanged(ItemEvent evt) {
			JComboBox resolutionList = (JComboBox) evt.getSource();
			int s = resolutionList.getSelectedIndex();
			if (s == 0) {
				settings(640, 480);
				setAllX(640);
				setAllY(480);
			}
			if (s == 1) {
				settings(1280, 720);
				setAllX(1280);
				setAllY(720);
			}
			if (s == 2) {
				settings(1920, 1080);
				setAllX(1920);
				setAllY(1080);
			}
			if (s == 3) {
				settings(2560, 1440);
				setAllX(2560);
				setAllY(1440);
			}
		}
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
			/*
			 * if (applyX == 900) { g.drawImage(y, 100, 100, 600, 300, Color.BLACK, null); }
			 */
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

	public void settings(int x, int y) {

		settingsFrame.setSize(x, y);
		settingsPanel.add(applySettings);
		applySettings.addActionListener(new ButtonListener());
	}

	public void setAllX(int x) {
		allX = x;
	}

	public void setAllY(int y) {
		allY = y;
	}

	public int getAllX() {
		return allX;
	}

	public int getAllY() {
		return allY;
	}

	public void setApplyX() {
		applyX = getAllX();
	}

	public void setApplyY() {
		applyY = getAllY();
	}

	public void apply() {
		setApplyX();
		setApplyY();
		menuFrame.setSize(applyX, applyY);
	}
	static public boolean getWindowed() {
		return windowed;
	}
}