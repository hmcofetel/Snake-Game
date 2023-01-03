package model;

import java.awt.Color;
import java.util.HashSet;
import util.TileType;

public class Apple {
	private static HashSet<Tile> apples = new HashSet<Tile>();
	
	
	public static void generate(int num) {
		while(apples.size() < num) {
			int appleX = (int)(Math.random()*(TileGrid.getColumns() - 1));
			int appleY = (int)(Math.random()*(TileGrid.getRows() - 1));
			if (TileGrid.getTile(appleX,appleY).getType() == TileType.BACKGROUND){
				TileGrid.getTile(appleX,appleY).setType(TileType.FOOD, Color.RED);
				apples.add(TileGrid.getTile(appleX,appleY));
			
			}
		}
	}
	
	public static void removeApple(Tile apple) {
		apples.remove(apple);
	}
}
