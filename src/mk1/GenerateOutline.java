package mk1;

public class GenerateOutline 
{
	int x;
	int y;
	Tile[][] tiles;
	OpenSimplexNoise noise;
	
	public GenerateOutline(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		this.tiles = new Tile[x][y];
		this.noise = new OpenSimplexNoise();
		
		generate();
	}
	
	void generate()
	{
		// Set all tiles to debug for testing; this also initializes them
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				tiles[i][j] = new Tile();
				tiles[i][j].type = "DEBUG";
			}
		}
		
		
		double value = 0.0;
		double scale = 0.006;
		
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				value = octaveSimplex(i * scale, j * scale, .2, 8, .5);
				
				tiles[i][j].type = getType(value);
			}
		}
	}
	
	public String getType(double value)
	{
		String type = "";
		
		if (value < -0.01)
			type = "WATER";
		else if (value >= -0.05 && value < 0.05)
			type = "BORDER";
		else 
			type = "SAND";
		
		System.out.println("Tile is type : "+ type);
		return type;
	}
	
	public double octaveSimplex(double x, double y, double z, int octaves, double persistance)
	{
		double total = 0;
		double frequency = 1;
		double amplitude = 1;
		double maxValue = 0;
		
		for (int i = 0; i < octaves; i++)
		{
			total += noise.eval(x * frequency, y * frequency, .5) * amplitude;
			
			maxValue += amplitude;
			
			amplitude *= persistance;
			frequency *= 2;
		}
		
		return total/maxValue;	
		
	}
}
