package mk1;

import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Dimension;
import mk1.TileUtils;
//import java.awt.Frame;

@SuppressWarnings("serial")
public class DrawTerrain extends Canvas
{
	static int boardSize = 1000;
	//public static GenerateTiles terrain = new GenerateTiles(20,20);
	public static GenerateSimplexTiles terrain = new GenerateSimplexTiles(boardSize,boardSize);
	//public static GenerateOutline terrain = new GenerateOutline(1000,1000);
	Tile[][] tiles = terrain.tiles;
	JFrame frameData;
	public static void main(String[] args)
	
	{
		JFrame frame = new JFrame("Terrain Board"); // Create window
		Canvas canvas = new DrawTerrain(); // For drawing graphics
		
		// Size of frame is based on canvas because of pack(), and canvas will be based on terrain grid 
		canvas.setSize(1000, 1000);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true); // Make it exist
	}
	
	public void paint(Graphics g)
	{
		// For each tile in the array, draw a rectangle onto the panel, using TileUtils to get the color of each tile from its type.
		// This is probably VERY slow because it recalculates every tile on every resize, but idk how to fix it
		for (int i = 0; i < terrain.x; i++)
		{
			for ( int j = 0; j < terrain.y; j++)
			{
				// Get color tile should be drawn as from TileUtils
				g.setColor(TileUtils.getColor(tiles[i][j]));
				
				// Get size of panel for drawing tiles proportionally
				//System.out.println("Drew " + TileUtils.getColor(tiles[i][j]) + " tile!");
				Dimension dim = getSize();
				g.fillRect(j * (dim.width / terrain.x), (i * dim.height / terrain.y) , (int) dim.width / terrain.x, (int) dim.height / terrain.y + 1);
			}
		}
		//System.out.println("Finished drawing!");

		
	}
	
	
}
