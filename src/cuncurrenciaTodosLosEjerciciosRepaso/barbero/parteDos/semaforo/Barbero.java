package cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteDos.semaforo;

public class Barbero extends Thread {
	Barberia barberia;

	public Barbero(Barberia barberia) {
		this.barberia = barberia;
	}

	public void run() {
		try {
			barberia.trabajar();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
