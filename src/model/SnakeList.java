package model;

import java.util.LinkedList;

public class SnakeList {
	private static LinkedList<Snake> snakes;
	
	
	public SnakeList (){
		snakes = new LinkedList<Snake>();
	}
	
	public void addSnake(Snake snake) {
		snakes.addLast(snake);
	}
	
	public void removeSnake(Snake snake) {
		snakes.remove(snake);
	}
	
	public static void moveSnakes() {
		for(Snake snake:snakes) {
			snake.move();
		}
	}
	
	public static void calculateSnakes() {
		for(Snake snake:snakes) {
			snake.calculateMoveStep();
		}
	}
}
