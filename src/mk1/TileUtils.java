package mk1;
import java.awt.Color;

public class TileUtils 
{
	static float partition = 0.15f;
	public static Color getColor(Tile tile)
	{
		Color c;
		//System.out.println(tile.type + " tile generated!");
		switch(tile.type)
		{
			case "GRASS" : 
				c = getTemperatureAdjustedColor(new Color(95,224,56), tile.temperature);
				break;
			case "FOREST" :
				c = getTemperatureAdjustedColor(new Color(77,161,51), tile.temperature);
				break;
			case "WATER" :
				c = new Color(0,98,227);
				break;
			case "DEEP_WATER" :
				c = new Color(0,75,173);
				break;
			case "SAND" :
				c = new Color(245,232,137);
				break;
			case "ROCK" :
				c = new Color(115,115,115);
				break;
			case "DARK_ROCK" :
				c = new Color(102,102,102);
				if(tile.temperature < -1 * partition)
					c = new Color(200,200,200);
				break;
			case "SNOW" :
				c = new Color(255,255,255);
				// If snow is in a "warm zone", it becomes rock instead
				if(tile.temperature > partition)
					c = new Color(102,102,102);
				break;
			case "DEBUG" :
				c = new Color(255, 0, 225);
				break;
			case "BORDER" :
				c = new Color(166,160,91);
				break;
			default :
				c = new Color(0,0,0);
				break;
				
		}
		
		//c = getTemperatureAdjustedColor(c, tile.temperature);
		return c; 
	}
	
	public static Color getTemperatureAdjustedColor(Color c, double temperature)
	{
		
		if (temperature <= -1 * partition)
		{
			// Very cold lol
			c = blendColors(c, new Color (150,150,227));
		}
		else if ( temperature > -1 * partition && temperature <= partition)
		{
			// Temperate
		}
		else 
		{
			// HOT
			c = blendColors(c, new Color (235, 170, 52));
		}
		
		return c;
	}
	
	// Evenly blend two colors
	public static Color blendColors(Color colorA, Color colorB)
	{
		double totalAlpha = colorA.getAlpha() + colorB.getAlpha();
	    double weightA = colorA.getAlpha() / totalAlpha;
	    double weightB = colorB.getAlpha() / totalAlpha;

	    double r = weightA * colorA.getRed() + weightB * colorB.getRed();
	    double g = weightA * colorA.getGreen() + weightB * colorB.getGreen();
	    double b = weightA * colorA.getBlue() + weightB * colorB.getBlue();
	    double a = Math.max(colorA.getAlpha(), colorB.getAlpha());

	    return new Color((int) r, (int) g, (int) b, (int) a);	
	}
}
