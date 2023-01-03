package ui;

import java.awt.*;

public class GameEntry extends GameWidget{
	private String title;
	private String text;
	private Font font;
	private Color colorTitle,colorText;
	private int[] size;
	
	private boolean entered = false;
	
	GameEntry(GamePanel root,String title, Font font, int x, int y){
		super(root, x,y);
		this.title = title;
		this.text = "";
		this.font = font;
		this.colorTitle = Color.WHITE;
		this.colorText = Color.WHITE;
		size =  new int[] {0,0};
	}
	
	public void getChar(char c) {
		if (this.isDisable())
			return;
		this.text += c;
	}
	
	public boolean isEntered() {
		return this.entered;
	}
	
	public int getEnteredNumber() {
		this.entered = false;
		int result = Integer.parseInt(this.text);
		this.text = "";
		return result;
	}
	
	public void getNumber(int keycode) {
		if (this.isDisable())
			return;

		if (keycode == 10 && this.text.length() > 0) {
			this.entered = true;
			return;
		}
		
		if (keycode == 8 && this.text.length() > 0 ) {
			this.text = this.text.substring(0, this.text.length() - 1);
		}
		if (keycode <= 57 && keycode >= 48 )
			this.text += (char)keycode;
	}
	
	public void draw(Graphics g) {
		if (this.isDisable())
			return;
		g.setColor(this.colorTitle);
		g.setFont(this.font);
//		size[0] = g.getFontMetrics(this.font).stringWidth( this.title );
//		size[1] = (int) (g.getFontMetrics(g.getFont()).getHeight()*0.65);
		g.drawString(this.title,this.getX(),this.getY());
		
		g.setColor(this.colorText);
		g.setFont(this.font);
//		size[0] = g.getFontMetrics(this.font).stringWidth(this.title );
//		size[1] = (int) (g.getFontMetrics(g.getFont()).getHeight()*0.65);
		g.drawString( this.text ,this.getX()+g.getFontMetrics(this.font).stringWidth(this.title ),this.getY());
		
	}
	
	public void setColor(Color colorTitle, Color colorText) {
		this.colorTitle = colorTitle;
		this.colorText = colorText;
	}
	
	
	public enum Side{
		CENTER, DEFAULT, N;
	}
}
