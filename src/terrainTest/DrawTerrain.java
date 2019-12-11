package terrainTest;

import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Frame;

public class DrawTerrain extends Canvas
{
	public static GenerateTiles terrain = new GenerateTiles(200,200);
	public static final int tileSize = 50;
	Tile[][] tiles = terrain.tiles;
	JFrame frameData;
	public static void main(String[] args)
	
	{
		// Create terrain object, passing size and will be future settings
		
		//System.out.println(terrain.x);
		
		JFrame frame = new JFrame("Terrain Board"); // Create window
		Canvas canvas = new DrawTerrain(); // For drawing graphics
		
		// Size of frame is based on canvas because of pack(), and canvas will be based on terrain grid 
		//canvas.setSize(terrain.x * tileSize, terrain.y * tileSize);
		canvas.setSize(1000, 1000);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true); // Make it exist
		//System.out.println(frame.getWidth());
	}
	
	public void paint(Graphics g)
	{
		// Draw tiles by size
		/*
		for (int i = 0; i < terrain.y; i++)
		{
			for ( int j = 0; j < terrain.x; j++)
			{
				//System.out.println("yeet");
				switch (tiles[i][j].type)
				{
					case 0 : g.setColor(new Color(0,255,0));
						break;
					case 1 : g.setColor(new Color(0,0,255));
						break;
					case 2 : g.setColor(new Color(255,255,0));
						break;
					case 3 : g.setColor(new Color(150,150,150));
						break;
				}
				g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
			}
		}
		*/
		// Draw tiles by proportion
		// There are.. problems with this.
		// It basically only works if its a square and I don't know why
		// Also certain dimensions cause out of bounds errors
		// When drawing even square boards it makes lines everywhere
		// But it works decently and im happy for now
		
		for (int i = 0; i < terrain.x; i++)
		{
			for ( int j = 0; j < terrain.y; j++)
			{
				//System.out.println("yeet");
				switch (tiles[i][j].type)
				{
					case 0 : g.setColor(new Color(0,255,0));
						break;
					case 1 : g.setColor(new Color(0,0,255));
						break;
					case 2 : g.setColor(new Color(255,255,0));
						break;
					case 3 : g.setColor(new Color(150,150,150));
						break;
				}
				Dimension dim = getSize();
				//System.out.println(dim.height);
				g.fillRect(j * (dim.width / terrain.x), (i * dim.height / terrain.y) , (int) dim.width / terrain.x, (int) dim.height / terrain.y);
				//g.fillRect(x, y, width, height);
			}
		}
		// Draw horizontal tile grid
		/*
		g.setColor(new Color(0,0,0));
		//g.drawLine(0, 50, 500, 0);
		for (int i = 0; i < terrain.y; i ++)
		{
			g.drawLine(0, i * tileSize, terrain.x * tileSize, i * tileSize);
		}
		//Draw Vertical Tile Grid
		
		for (int i = 0; i < terrain.x; i ++)
		{
			g.drawLine(i * tileSize, 0, i * tileSize, terrain.y * tileSize);
		}
		
		
		*/
		
	}
	
	
}
