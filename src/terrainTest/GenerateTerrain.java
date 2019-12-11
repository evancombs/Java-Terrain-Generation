package terrainTest;
import java.util.Random;

public class GenerateTerrain 
{
	int x;
	int y;
	public int[][] arrayRep;
	Random rand = new Random();
	
	public GenerateTerrain(int x, int y)
	{
		// Take in size of board and set this objects size to that.
		this.x = x;
		this.y = y;
		int[][] arrayRep = new int[x][y];
		generate(arrayRep);
	}
	
	// Key: 
	// 0 : Grass
	// 1 : water
	// 2 : sand
	void generate(int[][] arrayRep)
	{
		// Set all to grass
		for (int i = 0; i < arrayRep.length; i++)
		{
			for (int j = 0; j < arrayRep[0].length; j++)
			{
				//arrayRep[i][j] = ((rand.nextInt(2) == 1) ? 1 : 0);
				arrayRep[i][j] = 0;
			}
		}
		arrayRep = generateRiver(arrayRep);
		this.arrayRep = arrayRep;
	}
	
	// Choose two points on the border, i.e. either the x or y is zero and fill every tile between them with water( set to 1)
	int[][] generateRiver(int[][] arrayRep)
	{
		// Pick a tile on the border;
		Tile tileA = new Tile();
		Tile tileB = new Tile();
		int x = rand.nextInt(arrayRep.length);
		int y = rand.nextInt(arrayRep.length);
		
		while (y != 0)
			y = rand.nextInt(arrayRep.length);
		
		tileA.x = x;
		tileA.y = y;
		
		// Pick a second Tile on border
		
		
		
		
		
		return arrayRep;
	}
}
