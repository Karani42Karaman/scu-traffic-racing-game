import java.awt.event.KeyEvent;
/*
 * Araba oynatma i�lemleri i�in kullan�lacak methodlar
 */
public interface ICar {
	
	public void moveRoad(int count);

	public void finish();

	public void moveCar(KeyEvent e);

	public void stopCar(KeyEvent e);
}
