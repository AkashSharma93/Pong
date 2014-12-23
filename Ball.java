import java.awt.Color;

public class Ball {
	private int diameter;
	private int x, y, initialX, initialY;
	private float speed, initialSpeed;
	private int incrX, incrY;
	private boolean miss;
	Color color;
	
	public Ball(int d, int x, int y, float s) {
		diameter = d;
		initialX = x;
		initialY = y;
		initialSpeed = s;
		incrX = incrY = 3;
		color = Color.RED;
		
		reset();
	}
	
	public Color getColor() {
		return color;
	}
	
	public void move(int bWidth, int bHeight, Paddle p1, Paddle p2) {
		if(touchesPaddle(p1, p2)) {
			incrX = -incrX;
		}
		else if((x + incrX - diameter/2 ) < 0) {	//Ball misses paddle1.
			miss = true;
			incrX = -incrX;
			p2.increaseScore();
		}
		else if((x + incrX + diameter/2) > bWidth) {		//Ball misses paddle2.
			miss = true;
			incrX = -incrX;
			p1.increaseScore();
		}
		
		if((y + incrY + diameter/2) > bHeight || (y + incrY - diameter/2) < 0) {
			incrY = - incrY;
		}
		
		x += incrX * speed;
		y += incrY * speed;
	}
	
	public boolean touchesPaddle(Paddle p1, Paddle p2) {
		if(((y + diameter/4 > p1.getY()) && (y - diameter/4 < (p1.getY() + p1.getLength()))) && (x - diameter/2 < (p1.getX() + p1.getBreadth()))) {
			color = Color.WHITE;
			p1.setColor(Color.WHITE);
			increaseSpeed();
			return true;
		}
		
		if(((y + diameter/4 > p2.getY()) && (y - diameter/4 < (p2.getY() + p2.getLength()))) && ((x + diameter/2) > p2.getX())) {
			color = Color.WHITE;
			p2.setColor(Color.WHITE);
			increaseSpeed();
			return true;
		}
		
		color = Color.RED;
		p1.setColor(Color.YELLOW);
		p2.setColor(Color.GREEN);
		return false;
	}
	
	public void increaseSpeed() {
		speed += speed * 0.1;
	}
	
	public void reset() {
		x = initialX;
		y = initialY;
		speed = initialSpeed;
		miss = false;
	}
	
	public int getDiameter() {
		return diameter;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public boolean missed() {
		return miss;
	}
}
