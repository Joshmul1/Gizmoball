package model;
import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

public class RandomiseColour {
static HashMap<Integer, Color> colours = new HashMap<Integer, Color>();


public static Color rndCol(){
	colours.put(1, Color.RED);
	colours.put(2, Color.BLUE);
	colours.put(3, Color.ORANGE);
	colours.put(4, Color.MAGENTA);
	colours.put(5, Color.PINK);
	colours.put(6, Color.GREEN); //ew
	colours.put(7, Color.BLACK);
	colours.put(8, Color.CYAN);
	colours.put(9, Color.YELLOW);

	Random rnd = new Random();
	
	int random = rnd.nextInt((9 - 1) + 1) + 1;
	Color clr = colours.get(random);
	return clr;
}
	
}
