package ui;

import java.awt.Color;
import java.awt.Graphics;

import model.Apple;
import model.Snake;
import model.SnakeList;
import model.Tile;
import model.TileGrid;

public class GameBoard extends GameWidget{
	private static TileGrid grid;
	private static SnakeList snakes;
	GameBoard(GamePanel root) {
		super(root, 0, 0);
		GameBoard.grid = new TileGrid( GameConfig.SCREEN_WIDTH/GameConfig.UNIT_SIZE,GameConfig.SCREEN_HEIGHT/GameConfig.UNIT_SIZE,GameConfig.UNIT_SIZE );
		GameBoard.snakes = new SnakeList();
		GameBoard.snakes.addSnake(new Snake(0,0,Color.GREEN,Color.GREEN ));

	}
	
	
	
	public void update() {
		if(this.isDisable()) {
			return;
		}
		Apple.generate(10);
		SnakeList.calculateSnakes();
		SnakeList.moveSnakes();
	}
	
	public void draw(Graphics g) {
		if(this.isDisable()) {
			return;
		}
		
		for (int x = 0; x < TileGrid.getColumns();x++)
			for (int y = 0; y < TileGrid.getRows(); y++)
			{
				Tile tile = TileGrid.getTile(x, y);
				g.setColor(tile.getColor());
				g.fillRect(tile.getStepX(), tile.getStepY(), tile.getSize()[0]-1, tile.getSize()[1]-1);
			}
	}
	
	public static TileGrid getGrid() {
		return GameBoard.grid;
	}

}
