package ui;

import java.awt.Color;
import java.awt.Graphics;

public class GameWidget implements GameWidgetAction{
	private GamePanel root;
	private int[] position;
	private boolean disabled = false;
	
	
	GameWidget(GamePanel root, int x, int y){
		this.root = root;
		position = new int[]{x,y};
	}
	
	public void active() {
		this.disabled = false;
	}
	
	public void disable() {
		this.disabled = true;
	}
	
	protected int getX() {
		return position[0];
	}
	
	protected int getY() {
		return position[1];
	}
	
	protected void setX(int x) {
		 this.position[0] = x;
	}
	
	protected void setY(int y) {
		this.position[1] = y;
	}
	
	public boolean isDisable() {
		return this.disabled;
	}
	
	protected GamePanel getRoot() {
		return this.root;
	}

	@Override
	public void setColor(Color colorDefault, Color colorOnHover, Color colorOnClick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isClick(int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColor(Color colorTitle, Color colorText) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getNumber(int keycode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEntered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getEnteredNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
}
