package terrainTest;
import java.util.Random;
//import java.lang.Math;

// Generate a 2d array of tile objects and 
public class GenerateTiles 
{
	int x;
	int y;
	public Tile[][] tiles;
	Random rand;
	
	// Generate with seed
	public GenerateTiles(int x, int y, int seed)
	{
		// Take in size of board and set this objects size to that.
		this.x = x;
		this.y = y;
		this.rand = new Random(seed);
		// Tile[][] tiles = new Tile[x][y];
		System.out.println("Generating new tile array of size " + x + " x " + y);
		this.tiles = new Tile[x][y];
		//System.out.println("Tile at 5,5 is " + tiles[5][5]);
		//System.out.println("Tile at 5,5 is type" + tiles[5][5].type);
		generate();
	}
	
	// Generate without seed
	public GenerateTiles(int x, int y)
	{
		// Take in size of board and set this objects size to that.
		this.x = x;
		this.y = y;
		this.rand = new Random();
		// Tile[][] tiles = new Tile[x][y];
		System.out.println("Generating new tile array of size " + x + " x " + y);
		this.tiles = new Tile[x][y];
		//System.out.println("Tile at 5,5 is " + tiles[5][5]);
		//System.out.println("Tile at 5,5 is type" + tiles[5][5].type);
		generate();
	}
	
	// Key: 
	// 0 : Grass
	// 1 : water
	// 2 : sand
	// 3 : rock
	void generate()
	{
		// Set all tiles to grass
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				//arrayRep[i][j] = ((rand.nextInt(2) == 1) ? 1 : 0);
			//	System.out.println("Initialized tile at " + i + "," + j);
				tiles[i][j] = new Tile();
				tiles[i][j].type = 0;
			}
		}
		// tiles[5][5].type = 3;
		//generateRocksByNum(20);
		generateRocks(.5);
		generateRiver(10);
		generateSand();
	}
	
	// Pick n random locations and place n rocks (set tile type to 4)
	void generateRocks(int numRocks)
	{
		Tile newRock = new Tile();
		for(int i = 0; i < numRocks; i++)
		{
			newRock.x = rand.nextInt(x);
			newRock.y= rand.nextInt(y);
			//System.out.println("Tile at " + newRock.x + "," + newRock.y + " is changing to rock!");
			tiles[newRock.x][newRock.y].type = 3;
		}
	}
	
	// Place rocks based on percentage of tiles (set tile type to 4)
	void generateRocks(double percentRocks)
	{
		Tile newRock = new Tile();
		for(int i = 0; i < (int) (percentRocks * (x * y)); i++)
		{
			newRock.x = rand.nextInt(x);
			newRock.y= rand.nextInt(y);
			//System.out.println("Tile at " + newRock.x + "," + newRock.y + " is changing to rock!");
			tiles[newRock.x][newRock.y].type = 3;
		}
	}	
	
	// Pick two points on border, then "draw  a line" of water/blue tiles between
	void generateRiver(int rivers)
	{
		// "Wandering" approach mk 2
		Tile source = new Tile();
		int direction = 0;
		
		// Choose a random tile at top of screen. This approach will always go from from the top to the bottom somehow.
		for(int i = 0; i < rivers; i++)
		{
			source.y = rand.nextInt(y);
			source.x = 0;
			tiles[source.x][source.y].type = 1;
			System.out.println("got here");
			direction = rand.nextInt(2);
			System.out.println("Chose " + direction);
			System.out.println("x - 1 is : " + (x - 1));
			System.out.println("source.x is : " + source.x);
			
			while (source.x < x - 1)
			{
				direction = rand.nextInt(2);
				System.out.println("Chose " + direction);
				if(direction == 0)
				{
					// Down right
					// Protect from going off edge. If it hits edge, reselect until it doesn't.
					if(source.y == y - 1)
						continue;
					
					tiles[source.x + 1][source.y].type = 1; // down
					tiles[source.x + 1][source.y + 1].type = 1; // right
					source.x += 1;
					source.y += 1;
					System.out.println("New source tile at" + source.x + "," + source.y);
				}
				else
				{
					if(source.y == 0)
						continue;
					// Down left
					tiles[source.x + 1][source.y].type = 1; // down
					tiles[source.x + 1][source.y - 1].type = 1; // left
					source.x += 1;
					source.y -= 1;
					System.out.println("New source tile at" + source.x + "," + source.y);
				}
			}
		}
		// "Wandering" approach
		/*
		Tile source = new Tile();
		Tile next = new Tile();
		int direction = 0;
		source.x = rand.nextInt(x);
		source.y = rand.nextInt(y);
		//System.out.println("River source chosen at " + source.x + "," + source.y);
		
		while (source.x != 0 && source.y != 0)
		{
			//System.out.println("River source chosen at " + source.x + "," + source.y);
			//System.out.println("Rerolling river source");
			source.x = rand.nextInt(x);
			source.y = rand.nextInt(y);
		}
		System.out.println("Final river source chosen at " + source.x + "," + source.y);
		tiles[source.x][source.y].type = 1;

		// 0 - UP
		// 1 - RIGHT
		// 2 - DOWN
		// 3 - LEFT
		
		while(next.x != 0 && next.type != 0)
		{
			direction = rand.nextInt(4);
			switch (direction)
			{
				case 0 :
					next.x = source.x--;
					break;
				case 1 :
					
					break;
				case 2 :
					
					break;
				case 3 :
					
					break;
				
			}
		} 
		*/
		
		// "Line" approach
		/*
		Tile TileA = new Tile();
		Tile TileB = new Tile();
		do
		{
			//System.out.println("River source chosen at " + source.x + "," + source.y);
			//System.out.println("Rerolling river source");
			TileA.x = rand.nextInt(x);
			TileA.y = rand.nextInt(y);
		}
		while (TileA.x != 0 && TileA.y != 0);
		
		do
		{
			//System.out.println("River source chosen at " + source.x + "," + source.y);
			//System.out.println("Rerolling river source");
			TileB.x = rand.nextInt(x);
			TileB.y = rand.nextInt(y);
		}
		while (TileB.x != 0 && TileB.y != 0);
		
		System.out.println("TileA selected: " + TileA.x + "," + TileA.y);
		System.out.println("TileB selected: " + TileB.x + "," + TileB.y);
		
		double m = (TileB.y - TileA.y) / (TileB.x - TileA.x);
		int j;
		for (int i = TileA.x; i <= TileB.x; i++)
		{
			j = (int) Math.round((m * i));
			tiles[i][j].type = 1;
		}
		*/
	}
	// Create sand tiles on every tile adjacent to river, that is not river.
	void generateSand()
	{
		// This will be slow and awful
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				// If its a river tile, skip this.
				if(tiles[i][j].type == 1)
					continue;
				
				// Check adjacent tiles. If they are river, and this tile is not, this tile becomes sand.
				// Must also protect against checking out of bounds tiles.
				if(i - 1 > 0 && tiles[i - 1][j].type == 1) // Up
					tiles[i][j].type = 2;
				else if(j + 1 < y && tiles[i][j + 1].type == 1) // Right
					tiles[i][j].type = 2;
				else if(i + 1 < x && tiles[i + 1][j].type == 1) // Down
					tiles[i][j].type = 2;
				else if(j - 1 > 0 && tiles[i][j - 1].type == 1) // Left
					tiles[i][j].type = 2;
					
			}
		}
	}
}
