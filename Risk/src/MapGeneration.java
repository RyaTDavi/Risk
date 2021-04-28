//Jacob Paluso, Noah Rudolph, Harrison Grogan, Ryan Davis
//Risk

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class MapGeneration extends JPanel {
	private int sNumber = 160;
	private double sWidth;
	private double sHeight;
	private double offsetX = 0;
	private double offsetY;
	private Rectangle2D.Double[] rTangles;
	private int sNumberHeight;
	private Tile[] tiles;
	private Tile hgTile;

	public MapGeneration(Tile[] tiles) {
		this.tiles = tiles;
	}

	public void setSelected() {

	}

	public Rectangle2D.Double[] getSquares() {
		return rTangles;
	}

	public int sCalc(int row, int column) {
		return (row * sNumber) + column;
	}

	public int getSNumber() {
		return sNumber;
	}

	public void setTile(Tile x) {
		hgTile = x;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		double width = getSize().getWidth();
		double height = getSize().getHeight();
		sNumberHeight = 134;
		this.sWidth = (width / sNumber);
		this.sHeight = height / sNumberHeight;
		this.offsetX = (width % (sNumber * sWidth)) / 2.0;
		this.offsetY = (height % (sNumberHeight * sHeight)) / 2.0;
		rTangles = new Rectangle2D.Double[sNumber * sNumberHeight];
		Dimension sDim = new Dimension();
		sDim.setSize(sWidth, sHeight);
		double offX = offsetX;
		double offY = offsetY;
		int count = 0;
		for (int j = 0; j < sNumberHeight; j++) {
			for (int i = 0; i < sNumber; i++) {
				rTangles[count] = new Rectangle2D.Double(offX, offY, sWidth, sHeight);
				offX += sWidth;
				count++;
			}
			offX = offsetX;
			offY += sHeight;
		}
		g.setColor(new Color(0, 100, 210));
		for (int i = 0; i < rTangles.length; i++) {
			g2d.fill(rTangles[i]);
		}

		g.setColor(new Color(205, 183, 142));
		for (int i = 0; i < tiles.length; i++) {
			g.setColor(tiles[i].getColor());
			int[][] coords = tiles[i].getCoords();
			for (int j = 0; j < coords.length; j++) {
				coords = tiles[i].getCoords();
				int pixel = sCalc(coords[j][0], coords[j][1]);
				g2d.fill(rTangles[pixel]);
			}
		}
		if (hgTile != null)
			g.setColor(hgTile.getColor().brighter());
		if (hgTile != null) {
			int[][] coords = hgTile.getCoords();
			for (int i = 0; i < coords.length; i++) {
				int pixel = sCalc(coords[i][0], coords[i][1]);
				g2d.fill(rTangles[pixel]);
			}
		}

	}

}