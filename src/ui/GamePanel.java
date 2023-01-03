package ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import model.SnakeList;
import util.*;

public class GamePanel extends JPanel implements ActionListener {

	private GameStatus gameState;
	private Timer timer;
	private GameWidget titleGame, titleBotPlayer, titleSinglePlayer, titleMultiPLayer;
	private GameWidget entryNumberBot;
	private GameWidget snakeBoard;
	private ArrayList<GameWidget> widgets;
	private static final long serialVersionUID = 1L;
	

	GamePanel(){
		this.setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		this.addMouseListener(new MyMouseAdapter());
		this.titleGame =  new GameString(this,"Snake", new Font("Ink Free", Font.BOLD, GameConfig.TITLE_SIZE),GameString.Side.N);
		this.titleBotPlayer = new GameString(this,"Play With Bot", new Font("Ink Free", Font.BOLD, 30),350,300);
		this.titleSinglePlayer =  new GameString(this,"Single Player",new Font("Ink Free", Font.BOLD, 30),350,350);
		this.titleMultiPLayer =  new GameString(this,"Multi Player",new Font("Ink Free", Font.BOLD, 30),350,400);
		this.entryNumberBot = new GameEntry(this, "Enter number of bots: ",  new Font("Ink Free", Font.BOLD, 20), 100,100);
		
		this.snakeBoard = new GameBoard(this);
		
		this.widgets = new ArrayList<>();
		this.widgets.add(titleGame);
		this.widgets.add(titleBotPlayer);
		this.widgets.add(entryNumberBot);
		this.widgets.add(titleSinglePlayer);
		this.widgets.add(titleMultiPLayer);
		startGame();

	}
	
	public void startGame() {
		this.switchState(GameStatus.START);
		this.titleGame.setColor(Color.WHITE, Color.RED, Color.CYAN);
		this.titleBotPlayer.setColor(Color.WHITE, Color.YELLOW, Color.CYAN);
		this.titleSinglePlayer.setColor(Color.WHITE, Color.YELLOW, Color.CYAN);
		this.titleMultiPLayer.setColor(Color.WHITE, Color.YELLOW, Color.CYAN);
		
		this.entryNumberBot.setColor(Color.CYAN, Color.WHITE);
		
		this.timer = new Timer(50,this);
		this.timer.start();
	}
	
	public void switchState(GameStatus state) {
		this.gameState = state;
		this.clearAllWidgets();
	}
	
	public void clearAllWidgets() {
		for (GameWidget widget : widgets) {
			widget.disable();
		}
	}
	
	public void updateAllWidgets() {
		for (GameWidget widget : widgets) {
			widget.update();
		}
	}
		
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		draw(g);
	}
	
	public void drawGameStart(Graphics g) {
		this.titleGame.active();
		this.titleBotPlayer.active();
		this.titleSinglePlayer.active();
		this.titleMultiPLayer.active();
		
		this.titleGame.draw(g);
		this.titleBotPlayer.draw(g);
		this.titleSinglePlayer.draw(g);
		this.titleMultiPLayer.draw(g);
	}
	
	public void drawGameBotPlayerOption(Graphics g) {
		
		this.entryNumberBot.active();
		this.entryNumberBot.draw(g);
	}
	
	public void drawGameSinglePlayer(Graphics g) {
		this.snakeBoard.draw(g);
	}
	
	public void draw(Graphics g) {
		switch (gameState) {
		case START:
			drawGameStart(g);
			break;
		case BOT_PLAYER_OPTION:
			drawGameBotPlayerOption(g);
			break;
			
		case SINGLE_PLAYER:
			drawGameSinglePlayer(g);
			break;
			
		default:
			break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println(this.getLocationOnScreen());
//		System.out.println(MouseInfo.getPointerInfo().getLocation());
		switch(gameState) {
		case START: 
			titleGame.update();
			titleBotPlayer.update();
			titleSinglePlayer.update();
			titleMultiPLayer.update();
			break;
		
		case BOT_PLAYER_OPTION:
			break;
		
		case SINGLE_PLAYER:
			snakeBoard.update();
			break;
		default:
			break;
		
		}
		
		
	    repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e){
			switch(gameState) {
			case BOT_PLAYER_OPTION:
				
				entryNumberBot.getNumber(e.getKeyCode());
				if (entryNumberBot.isEntered()) {
					titleGame.setText(Integer.toString(entryNumberBot.getEnteredNumber()));
					switchState(GameStatus.START);
					clearAllWidgets();
				}
				break;
			
			case SINGLE_PLAYER: 
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT:
					SnakeList.getSnakeList().get(0).changeDirection(SnakeDirection.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					SnakeList.getSnakeList().get(0).changeDirection(SnakeDirection.RIGHT);
					break;
				case KeyEvent.VK_UP:
					SnakeList.getSnakeList().get(0).changeDirection(SnakeDirection.UP);
					break;
				case KeyEvent.VK_DOWN:
					SnakeList.getSnakeList().get(0).changeDirection(SnakeDirection.DOWN);
					break;
				}
				break;
			
			default:
				break;
			
			}
		}
	}
	
	public class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e){
//			System.out.println(e.toString());
			if (titleGame.isClick(e.getButton())) {
			};
			
			if (titleBotPlayer.isClick(e.getButton())) {
				switchState(GameStatus.BOT_PLAYER_OPTION);
				clearAllWidgets();
			};
			
			if (titleSinglePlayer.isClick(e.getButton())) {
				switchState(GameStatus.SINGLE_PLAYER);
				clearAllWidgets();
			};
		}
	}


}
