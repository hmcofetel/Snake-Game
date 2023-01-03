package ui;

import java.awt.*;

public class GameString extends GameWidget   {
	private String text;
	private Color colorDefault, colorOnHover, colorOnClick, colorCurrent;
	private Font font;	
	private GameString.Side side;
	private int[] size;
	private boolean clicked = false;

	GameString(GamePanel root,String text, Font font, int x, int y){
		super(root, x, y);
		this.text = text;
		this.font = font;
		this.colorCurrent = Color.WHITE;
		this.colorOnHover = Color.WHITE;
		this.colorOnClick = Color.WHITE;
		this.colorDefault = Color.WHITE;
		this.side = GameString.Side.DEFAULT;
		size =  new int[] {0,0};
		

		
	}
	
	GameString(GamePanel root,String text, Font font, GameString.Side side ){
		super(root, 0,0);
		this.text = text;
		this.font = font;
		this.colorCurrent = Color.WHITE;
		this.colorOnHover = Color.WHITE;
		this.colorOnClick = Color.WHITE;
		this.colorDefault = Color.WHITE;
		size =  new int[] {0,0};
		this.side = side;
		
	}
	
	public void setColor(Color colorDefault,Color colorOnHover, Color colorOnClick ) {
		this.colorDefault = colorDefault;
		this.colorOnHover = colorOnHover;
		this.colorOnClick = colorOnClick;
		this.colorCurrent = colorDefault;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	
	public void update() {
		onHover();
		onClick();
	}
	
	private boolean isHover() {
		if (MouseInfo.getPointerInfo().getLocation().x-this.getRoot().getLocationOnScreen().x < this.getX()) {
			return false;
		}

		if (MouseInfo.getPointerInfo().getLocation().x-this.getRoot().getLocationOnScreen().x > this.getX()+size[0]) {
			return false;
		}
		
		if (MouseInfo.getPointerInfo().getLocation().y-this.getRoot().getLocationOnScreen().y < this.getY()-size[1]) {
			return false;
		}
		
		if (MouseInfo.getPointerInfo().getLocation().y-this.getRoot().getLocationOnScreen().y > this.getY()) {
			return false;
		}
		
		return true;
	}

	public void onHover() {
		if (this.isDisable())
			return;
		
		if (isHover()) {
			this.colorCurrent = this.colorOnHover;
		}
		else {
			this.colorCurrent = this.colorDefault;
		}
	}
	
	public boolean isClick(int button) {
		if (this.isDisable())
			return false;
		
		if (isHover() && button == 1) {
			this.clicked = true;
			return true;

		}
		this.clicked = false;
		return false;
		
		

	}
	
	public void onClick() {
		if (this.isDisable())
			return;
		if (this.clicked) {
			this.colorCurrent = this.colorOnClick;
		}
		else if(!isHover()){
			this.colorCurrent = this.colorDefault;
		}
		this.clicked = false;
	}
	
	public void draw(Graphics g) {
		if (this.isDisable())
			return;
		g.setColor(this.colorCurrent);
		g.setFont(this.font);
		size[0] = g.getFontMetrics(this.font).stringWidth(this.text);
		size[1] = (int) (g.getFontMetrics(g.getFont()).getHeight()*0.65);
		switch(this.side) {
		case N:
			this.setX((this.getRoot().getWidth() - size[0])/2); 
			this.setY(size[1]);
			break;
		
		case CENTER:
			this.setX((getRoot().getWidth() - size[0])/2) ;
			this.setY( (getRoot().getHeight())/2) ;
			break;
		
		default:
			break;
		
		}
		
		g.drawString(this.text,this.getX(),this.getY());
		
	}
	
	
	public enum Side{
		CENTER, DEFAULT, N;
	}
	
}
