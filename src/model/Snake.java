package model;

import java.awt.Color;
import java.util.LinkedList;

import ui.GameConfig;
import util.SnakeDirection;
import util.TileType;

public class Snake {
	private LinkedList <Tile> body;
	private Tile head, nextHead;
	private Color colorBody, colorHead;
	private int maxIndexX = GameConfig.SCREEN_WIDTH/GameConfig.UNIT_SIZE - 1;
	private int maxIndexY = GameConfig.SCREEN_HEIGHT/GameConfig.UNIT_SIZE - 1;
	private SnakeDirection direction;
	private boolean dead;
	
	public Snake(int x ,int y, Color colorHead, Color colorBody){
		this.body = new LinkedList <Tile>();
		this.head = TileGrid.getTile(x, y);
		this.nextHead = TileGrid.getTile(x, y+1);
		this.colorBody = colorBody;
		this.colorHead = colorHead;
		this.direction = SnakeDirection.DOWN;
		this.dead = false;
	}
	
	public void changeDirection(SnakeDirection direction){
		if (this.direction == SnakeDirection.DOWN && direction == SnakeDirection.UP){

		}

		else if (this.direction == SnakeDirection.UP && direction == SnakeDirection.DOWN){
			
		}

		else if (this.direction == SnakeDirection.LEFT && direction == SnakeDirection.RIGHT){
			
		}

		else if (this.direction == SnakeDirection.RIGHT && direction == SnakeDirection.LEFT){
			
		}
		else{
			this.direction = direction;
		}
		
	}
	
	public void calculateMoveStep() {
		if (this.isDead())
			return;
		int[]curHead = this.head.getIndex();
		
		
		switch(this.direction) {
		case UP:
			if (curHead[1] == 0) {
				this.nextHead = TileGrid.getTile(curHead[0], maxIndexY);
				break;
			}
			this.nextHead = TileGrid.getTile(curHead[0], curHead[1]-1);
			break;
		case DOWN:
			if (curHead[1] == maxIndexY) {
				this.nextHead = TileGrid.getTile(curHead[0], 0);
				break;
			}
			this.nextHead = TileGrid.getTile(curHead[0], curHead[1]+1);
			break;
		case LEFT:
			if (curHead[0] == 0) {
				this.nextHead = TileGrid.getTile(maxIndexX, curHead[1]);
				break;
			}
			this.nextHead = TileGrid.getTile(curHead[0] - 1, curHead[1]);
			break;
		case RIGHT: 
			if (curHead[0] == maxIndexX) {
				this.nextHead = TileGrid.getTile(0, curHead[1]);
				break;
			}
			this.nextHead = TileGrid.getTile(curHead[0] + 1, curHead[1]);
			break;
			
			
		default:
			break;
		}
	}
	
	public void move() {
		if (this.isDead())
			return;
		
		this.body.addLast(this.head);
		
		if (this.nextHead.getType() == TileType.FOOD ) {
			this.body.addFirst(this.nextHead);
			Apple.removeApple(this.nextHead);
		}
		
		if (this.nextHead.getType() ==  TileType.BODY) {
			this.dead();
			return;
		}
		
		
		
		this.body.getLast().setType(TileType.BODY, this.colorBody);
		this.body.getFirst().setType(TileType.BACKGROUND);
		this.body.removeFirst();
		this.head = this.nextHead;
		this.head.setType(TileType.HEAD, this.colorHead);
	}
	
	private void dead() {
		this.dead = true;
		for (Tile tile: body) {
			tile.setType(TileType.FOOD, Color.RED);
		}
	}
	
	public boolean isDead() {
		return dead;
	}
}
