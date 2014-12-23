import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class Board extends JPanel {
	private Ball ball;
	private Paddle paddle1, paddle2;
	private int scale;
	private int boardHeight, boardWidth;
	
	public Board() {
		scale = 25;
		this.setBackground(Color.BLACK);
	}
	
	public Paddle getPaddle1() {
		return paddle1;
	}
	
	public Paddle getPaddle2() {
		return paddle2;
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public void createObjects() {
		boardHeight = this.getHeight();
		boardWidth = this.getWidth();
		
		ball = new Ball(boardWidth/scale, boardWidth/2, boardHeight/2, 1.0f);
		paddle1 = new Paddle(PaddlePosition.LEFT, 5*scale, scale/2, boardWidth, boardHeight, Color.YELLOW);
		paddle2 = new Paddle(PaddlePosition.RIGHT, 5*scale, scale/2, boardWidth, boardHeight, Color.GREEN);
	}
	
	public void drawBackground(Graphics2D g2D) {
		g2D.setStroke(new BasicStroke(8));	//Increases width of line.
		g2D.setColor(Color.WHITE);
		g2D.drawLine(boardWidth/2, 0, boardWidth/2, boardHeight);	//Center line.
		g2D.drawOval(boardWidth/2 - 5*scale-1, boardHeight/2 - 5*scale, 10*scale, 10*scale);	//10*scale is radius. radius/2 is offset. offset-1 cuz I thought it made it more accurately positioned.
		g2D.setColor(Color.CYAN);
		g2D.setFont(new Font("SansSerif", Font.BOLD, 40));			//Title
		g2D.drawString("PONG", boardWidth/2 - (scale+35), boardHeight/10);
		
		g2D.setFont(new Font("Serif", Font.BOLD, 100));				//Scores.
		g2D.drawString(new Integer(paddle1.getScore()).toString(), boardWidth/4, boardHeight/4);
		g2D.drawString(new Integer(paddle2.getScore()).toString(), boardWidth * 3/4, boardHeight/4);
	}
	
	public void drawBall(Graphics2D g2D) {
		if(ball != null) {
			if(ball.missed()) {	//Add changes to scores here.
				ball.reset();
			}
			g2D.setColor(ball.getColor());
			g2D.fillOval(ball.getX() - ball.getDiameter()/2, ball.getY() - ball.getDiameter()/2, ball.getDiameter(), ball.getDiameter());
			ball.move(boardWidth, boardHeight, paddle1, paddle2);
		}
	}
	
	public void drawPaddle(Graphics2D g2D, Paddle paddle, Color color) {
		if(paddle != null) {
			g2D.setColor(color);
			g2D.fillRect(paddle.getX(), paddle.getY(), paddle.getBreadth(), paddle.getLength());
			paddle.move(boardHeight);
		}
	}
	
	public void paintComponent(Graphics g) {
		//This methods is called to update all the stuff on the panel.
		super.paintComponent(g);	//Helps set the background black.
		
		Graphics2D g2D = (Graphics2D) g;
		drawBackground(g2D);
		drawBall(g2D);
		drawPaddle(g2D, paddle1, paddle1.getColor());
		drawPaddle(g2D, paddle2, paddle2.getColor());
	}
}
