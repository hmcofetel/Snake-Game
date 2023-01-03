package model;

import util.TileType;

public class TileGrid {
	private static Tile[][] tiles;
	private static int columns, rows;
	
	public TileGrid(int columns, int rows, int sizeTile){
		TileGrid.tiles = new Tile[columns][rows];
		TileGrid.columns = columns;
		TileGrid.rows = rows;
		for (int x = 0; x < columns;x++)
			for (int y = 0; y < rows; y++)
				TileGrid.tiles[x][y] = new Tile(new int[]{x,y},new int[]{sizeTile,sizeTile}) ;
	}
	
	public static Tile[][] getTiles() {
		return TileGrid.tiles;
	}
	
	public static Tile getTile(int x, int y) {
		return TileGrid.tiles[x][y];
	}
	
	public static void setTile(int x, int y, TileType type) {
		TileGrid.tiles[x][y].setType(type);
	}
	
	public static int getRows() {
		return TileGrid.rows;
	}
	
	public static int getColumns() {
		return TileGrid.columns;
	}
	
}
