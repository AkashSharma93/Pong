import java.awt.Color;

enum PaddlePosition {
	LEFT,
	RIGHT
}

public class Paddle {
	private int length, breadth;
	private int x, y;
	private int incr, score;
	private Color color;
	
	public Paddle(PaddlePosition pos, int length, int breadth, int boardWidth, int boardHeight, Color c) {
		this.length = length;
		this.breadth = breadth;
		incr = 0;
		color = c;
		score = 0;
		
		if(pos == PaddlePosition.LEFT) {
			reset(0, boardHeight/2 - length/2);
		}
		else {
			reset(boardWidth - breadth, boardHeight/2 - length/2);
		}
	}
	
	public void reset(int x, int y) {
		this.x = x;
		this.y = y;
		score = 0;
	}
	
	public void setIncr(int incr) {
		this.incr = incr;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void move(int boardHeight) {
		synchronized(this) {
			if((y + incr < 0) || (y + incr > boardHeight - length)) {
				return;
			}
		
			y += incr;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getBreadth() {
		return breadth;
	}
	
	public int getScore() {
		return score;
	}
	
	public void increaseScore() {
		score += 1;
	}
}
