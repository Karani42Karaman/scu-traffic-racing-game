/*
 * Oyunumun Gerçekleþtiði Ana Ekran 
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements ICar {
	// Arabanýn parametreleri
	int crx, cry;
	int car_x, car_y;
	int speedX, speedY;
	int nOpponent;
	String imageOpp[];
	int lx[], ly[];
	int score;
	int highScore;
	int speedOpp[];
	boolean isFinished;
	boolean isUp, isDown, isLeft, isRight;

	public GameBoard() {
		crx = cry = -999;
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				moveCar(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				stopCar(e);
			}
		});
		setFocusable(true);
		car_x = car_y = 300;
		isUp = isDown = isLeft = isRight = false;
		speedX = speedY = 0;
		lx = new int[20];
		ly = new int[20];
		imageOpp = new String[20];
		speedOpp = new int[20];
		isFinished = false;
		score = highScore = 0;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D obj = (Graphics2D) g;
		obj.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		try {
			obj.drawImage(getToolkit().getImage("src/images/st_road2.png"), 0, 0, this);
			if (cry >= -499 && crx >= -499)
				obj.drawImage(getToolkit().getImage("src/images/cross_road.png"), crx, cry, this);

			obj.drawImage(getToolkit().getImage("src/images/car_self.png"), car_x, car_y, this);

			if (isFinished) {
				obj.drawImage(getToolkit().getImage("src/images/boom.png"), car_x - 30, car_y - 30, this);
			}

			if (this.nOpponent > 0) {
				for (int i = 0; i < this.nOpponent; i++) {
					obj.drawImage(getToolkit().getImage(this.imageOpp[i]), this.lx[i], this.ly[i], this);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void moveRoad(int count) {
		if (crx == -999 && cry == -999) {
			if (count % 10 == 0) {
				crx = 0;
				cry = -499;
			}
		} else {
			cry++;
		}
		if (crx == 0 && cry == 499) {
			crx = cry = -999;
		}
		car_x += speedX;
		car_y += speedY;

		if (car_y < 0) {
			car_y = 0;
		}
		if (car_y + 93 >= 450) {
			car_y = 450 - 93;
		}
		if (car_x <= 120) {
			car_x = 120;
		}
		if (car_x >= 360 - 50) {
			car_x = 360 - 50;
		}

		for (int i = 0; i < this.nOpponent; i++) {
			this.ly[i] += speedOpp[i];
		}

		int index[] = new int[nOpponent];
		for (int i = 0; i < nOpponent; i++) {
			if (ly[i] <= 627) {
				index[i] = 1;
			}

		}
		int c = 0;

		for (int i = 0; i < nOpponent; i++) {
			if (index[i] == 1) {
				imageOpp[c] = imageOpp[i];
				lx[c] = lx[i];
				ly[c] = ly[i];
				speedOpp[c] = speedOpp[i];
				c++;
			}
		}

		score += nOpponent - c;

		if (score > highScore) {
			highScore = score;
		}
		nOpponent = c;

		
		for (int i = 0; i < nOpponent; i++) {
			if ((ly[i] >= car_y && ly[i] <= car_y + 87) || (ly[i] + 87 >= car_y && ly[i] + 87 <= car_y + 87)) {
				if (car_x + 46 >= lx[i] && !(car_x >= lx[i] + 46)) {
					System.out.println("My car : " + car_x + ", " + car_y);
					System.out.println("Colliding car : " + lx[i] + ", " + ly[i]);
					this.finish();
				}
			}
		}
	}

	@Override
	public void finish() {
		String str = "";
		isFinished = true;
		this.repaint();
		if (score == highScore && score != 0) {
			str = "\nCongratulations!!! Its a high score";
		}
		JOptionPane.showMessageDialog(this, "Game Over!!!\nYour Score : " + score + "\nHigh Score : " + highScore + str,
				"Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}

	@Override
	public void moveCar(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			isUp = true;
			speedY = -1;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			isDown = true;
			speedY = +1;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			isLeft = true;
			speedX = -1;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			isRight = true;
			speedX = 1;
		}
	}

	@Override
	public void stopCar(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			isUp = false;
			speedY = 0;
		}

		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			isDown = false;
			speedY = 0;
		}

		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			isLeft = false;
			speedX = 0;
		}

		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			isRight = false;
			speedX = 0;
		}
	}

}
