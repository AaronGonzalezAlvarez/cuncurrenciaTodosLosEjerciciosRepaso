package cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteUno.sync;

public class Barbero extends Thread {
	Barberia barberia;

	public Barbero(Barberia barberia) {
		this.barberia = barberia;
	}

	public void run() {
		try {
			Thread.sleep(1000);
			barberia.trabajar();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
