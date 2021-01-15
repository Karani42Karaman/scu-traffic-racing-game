/**
 * @author Karani Karaman 
 * Cumhuriyet Üniversitesi Mühendislik Fakültesi Bilgisayar Mühendisliði Bölümü
 * 3.Sýnýf Ýkinci Öðretim 2018141025
 * Yapýlýþ Tarihi : 14.01.2021
 */

/*
 * Bilgisayar Grafik Prog. Giriþ Dersi Ödevi 
 * Verilen ödev java 2d ile bir araba yarýþý  oyunu 
 */
import javax.swing.JFrame;

public class Game extends JFrame {

	public static void main(String[] args) {

		JFrame jf = new JFrame("Racing Game");
		GameBoard ng = new GameBoard();
		jf.add(ng);
		jf.setSize(500, 500);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int count = 1, c = 1;
		while (true) {
			ng.moveRoad(count);
			while (c <= 1) {
				ng.repaint();
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					System.out.println(e);
				}
				c++;
			}
			c = 1;
			count++;
			if (ng.nOpponent < 8 && count % 200 == 0) {
				ng.imageOpp[ng.nOpponent] = "src/images/car_left_" + ((int) ((Math.random() * 100) % 8) + 1) + ".png";
				ng.ly[ng.nOpponent] = -70;
				int p = (int) (Math.random() * 100) % 4;
				if (p == 0) {
					p = 250;
				} else if (p == 1) {
					p = 300;
				} else if (p == 2) {
					p = 185;
				} else {
					p = 130;
				}
				ng.lx[ng.nOpponent] = p;
				ng.speedOpp[ng.nOpponent] = (int) (Math.random() * 100) % 2 + 2;
				ng.nOpponent++;
			}
		}
	}
}
