package model;

import java.util.LinkedList;

public class SnakeList {
	private static LinkedList<Snake> snakes = new LinkedList<Snake>();
	
	
	public static void addSnake(Snake snake) {
		snakes.addLast(snake);
	}
	
	public static void removeSnake(Snake snake) {
		snakes.remove(snake);
	}
	
	public static LinkedList<Snake> getSnakeList(){
		return  SnakeList.snakes;
	}
	
	public static void moveSnakes() {
		for(Snake snake:snakes) {
			if (snake.isDead()) {
				snakes.remove(snake);
				continue;
			}
			snake.move();
		}
	}
	
	public static void calculateSnakes() {
		for(Snake snake:snakes) {
			snake.calculateMoveStep();
		}
	}
}
