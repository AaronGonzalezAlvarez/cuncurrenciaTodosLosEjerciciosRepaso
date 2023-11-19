package cuncurrenciaTodosLosEjerciciosRepaso.taller.sync;

public class Robot extends Thread {
	Taller taller;

	public Robot(Taller taller) {
		this.taller = taller;
	}

	public void run() {
		try {
			taller.robotMontacargas();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}