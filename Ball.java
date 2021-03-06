import java.awt.Color;

public class Ball {
	private int diameter;
	private int x, y, initialX, initialY;
	private float speed, initialSpeed;
	private int incrX, incrY;
	private boolean miss, speedFlag;
	Color color;
	SoundPlayer paddleSound, ballSound;
	
	public Ball(int d, int x, int y, float s) {
		diameter = d;
		initialX = x;
		initialY = y;
		initialSpeed = s;
		incrX = incrY = 3;
		color = Color.RED;
		paddleSound = new PaddleSoundPlayer();
		ballSound = new BallSoundPlayer(this);
		
		reset();
	}
	
	public Color getColor() {
		return color;
	}
	
	public void move(int bWidth, int bHeight, Paddle p1, Paddle p2) {
		if(touchesPaddle(p1, p2)) {
			incrX = -incrX;
//			speedFlag = true;		//Used for the alternative way to increase ball speed.
		}
		else if((x + incrX - diameter/2 ) < 0) {	//Ball misses paddle1.
			miss = true;
			ballSound.playSound();
			//incrX = -incrX;
			p2.increaseScore();
		}
		else if((x + incrX + diameter/2) > bWidth) {		//Ball misses paddle2.
			miss = true;
			ballSound.playSound();
			//incrX = -incrX;
			p1.increaseScore();
		}
		
		if((y + incrY + diameter/2) > bHeight || (y + incrY - diameter/2) < 0) {
			ballSound.playSound();
			incrY = - incrY;
		}
		
/*		if(speedFlag && (x + diameter/2 > bWidth/2 && x - diameter/2 < bWidth/2)) {		//Could use this for an alternative way to increase the speed of the ball.
			speedFlag = false;		//Increases ball speed only when it crosses the center of the board!
			increaseSpeed();
		}
*/		
		x += incrX * speed;
		y += incrY * speed;
	}
	
	public boolean touchesPaddle(Paddle p1, Paddle p2) {
		if(((y + diameter/4 > p1.getY()) && (y - diameter/4 < (p1.getY() + p1.getLength()))) && (x - diameter/2 < (p1.getX() + p1.getBreadth()))) {
			color = Color.WHITE;
			p1.setColor(Color.WHITE);
			paddleSound.playSound();
			increaseSpeed();		//Primary way of increasing ball speed. Speed of ball increases only after hitting one of the paddles.
			x = p1.getBreadth() + diameter/2 + 1;		//To fix the bug of the ball sticking to the paddle. (Happens when x remains less than the paddle's breadth even after increasing x!!
			return true;
		}
		
		if(((y + diameter/4 > p2.getY()) && (y - diameter/4 < (p2.getY() + p2.getLength()))) && ((x + diameter/2) > p2.getX())) {
			color = Color.WHITE;
			p2.setColor(Color.WHITE);
			paddleSound.playSound();
			increaseSpeed();
			x = p2.getX() - diameter/2 - 1;				//Removes the sticky bug...
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
		speedFlag = false;
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
