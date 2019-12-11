package mk1;
import java.util.Random;

//import OpenSimplexNoise;

// Generate array of tiles and store in in this object.
public class GenerateSimplexTiles 
{
	int x;
	int y;
	public Tile[][] tiles;
	OpenSimplexNoise noise;
	OpenSimplexNoise continentNoise;
	OpenSimplexNoise temperatureNoise;
	
	// Generate without seed
	// Generating without seed requires a random seed, as OpenSimplexNoise appears to seed itself
	public GenerateSimplexTiles(int x, int y)
	
	{
		// Set up instance variables
		this.x = x;
		this.y = y;
		this.tiles = new Tile[x][y];
		
		Random rand = new Random();
		this.noise = new OpenSimplexNoise(rand.nextInt());
		this.continentNoise = new OpenSimplexNoise(rand.nextInt());
		this.temperatureNoise = new OpenSimplexNoise(rand.nextInt());
		
		
		System.out.println("Generating new simplex tile array of size " + x + " x " + y);
		//generateIslands();
		generateContinents();
	
	}
	
	// Generate with seed
	public GenerateSimplexTiles(int x, int y, int seed)
	
	{
		// Set up instance variables
		this.x = x;
		this.y = y;
		this.tiles = new Tile[x][y];
		
		// Since this is the seeded method, we give OpenSimplexNoise the seed
		this.noise = new OpenSimplexNoise(seed);
		this.continentNoise = new OpenSimplexNoise(seed % 8);
		System.out.println("Generating new simplex tile array of size " + x + " x " + y);
		//generateIslands();
		generateContinents();
	}
	
	// Method that actually fills the array of tiles; this class will fill using simplex noise
	void generateIslands()
	{
		
		// Set all tiles to debug color, for testing
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				//arrayRep[i][j] = ((rand.nextInt(2) == 1) ? 1 : 0);
			//	System.out.println("Initialized tile at " + i + "," + j);
				tiles[i][j] = new Tile();
				tiles[i][j].type = "DEBUG";
			}
		}
		
		
		double value = 0.0;
		double scale = .03;
		// Generate simplex noise, and base tiles on the values. i represents x, and j represents y
		// The range of values is ~ -.866 to .866, so tiles must be evaluated accordingly 
		 for (int i = 0; i < x; i++)
		 {
			for ( int j = 0; j < y; j++) 
			{
				//value = noise.eval(i * scale, j * scale, .5);
				value = octaveSimplex(noise, i * scale, j * scale, 0.2, 8, .5);
				
				System.out.println("Simplex noise value is : " + value);
				
				// Initalize the tile
				tiles[i][j] = new Tile();
				
				// Set the tiles type
				tiles[i][j].type = getType(value);
								
			}
		 }
		//System.out.println(value);
		 
	}
	
	// Generate a second simplex noise filter and use positive values on it to raise the tiles on the first filter 
	void generateContinents()
	{
		// Set all tiles to debug color, for testing
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				tiles[i][j] = new Tile();
				tiles[i][j].type = "DEBUG";
			}
		}
				
				
		double value = 0.0;
		double scale = .02;
		
		double temperatureValue = 0.0;
		double temperatureScale = 0.002;
		
		double continentValue = 0.0;
		double continentScale = .005;
		// Generate simplex noise, and base tiles on the values. i represents x, and j represents y
		// The range of values is ~ -.866 to .866, so tiles must be evaluated accordingly 
		 for (int i = 0; i < x; i++)
		 {
			for ( int j = 0; j < y; j++) 
			{
				
				// The idea here is that you generate an initial terrain, then with a larger scale, raise or lower terrain to 
				// exaggerate the features, i.e. oceans get bigger, continents get bigger.
				//value = noise.eval(i * scale, j * scale, .5);
				value = octaveSimplex(noise, i * scale, j * scale, 0.2, 8, .5);
				continentValue = continentNoise.eval(i * continentScale, j * continentScale);
				
				continentValue *= 1.1;
				//System.out.println("Continent mask Simplex noise value is : " + continentValue);
				value += continentValue;
				//System.out.println("Simplex noise value is : " + value);
				
				// Here we generate yet another simplex noise distribution to determine the "temperature" of different regions.
				// we use steps to determine temperature bands.
				//temperatureValue = temperatureNoise.eval(i * temperatureScale, j * temperatureScale);
				temperatureValue = octaveSimplex(temperatureNoise, i * temperatureScale, j * temperatureScale, 0.2, 8, .5);
				
				// Set the tiles type
				//tiles[i][j].temperature = temperatureValue;
				tiles[i][j].type = getType(value);
										
			}
		 }
		//System.out.println(value);
				 
	}

	
	// Method to apply a type based on the value of the tile, basically a janky heightmap coloring
	public String getType(double value)
	{
		String type = "";
		
		if (value <= -.2)
		{
			type = "DEEP_WATER";
		}
		else if ( value > -.2 && value <= 0)
		{
			type = "WATER";
			//System.out.println("Generated WATER tile!");
		}
		else if (value > 0 && value <= .1)
		{
			type = "SAND";
			//System.out.println("Generated SAND tile!");
		}
		else if ( value > .1 && value <= .3)
		{
			type = "GRASS";
			//System.out.println("Generated GRASS tile!");
		}
		else if (value > .3 && value <= .6)
		{
			type = "FOREST";
		}
		else if (value > 0.6 && value <= .73)
		{
			type = "ROCK";
			//System.out.println("Generated ROCK tile!");
		}
		else if (value > .73 && value <= .97)
		{
			type = "DARK_ROCK";
		}
		else 
		{
			type = "SNOW";
		}
		
		return type;
	}
	
	// Method to add "octaves" (?) to the simplex noise.
	// This makes the noise look more natural, and less rounded.
	// Octaves are progressively lower impact noise maps applied over the main noise
	public double octaveSimplex(OpenSimplexNoise noiseSeed, double x, double y, double z, int octaves, double persistance)
	{
		double total = 0;
		double frequency = 1;
		double amplitude = .1;
		double maxValue = 0;
		
		for (int i = 0; i < octaves; i++)
		{
			total += noiseSeed.eval(x * frequency, y * frequency, .5) * amplitude;
			
			maxValue += amplitude;
			
			amplitude *= persistance;
			frequency *= 2;
		}
		
		return total/maxValue;	
		
	}
}
