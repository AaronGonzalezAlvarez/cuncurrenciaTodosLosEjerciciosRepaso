package cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteDos.lock;

public class Cliente extends Thread {
	Barberia barberia;
	int id;

	public Cliente(Barberia barberia,int id) {
		this.barberia = barberia;
		this.id = id;
	}

	public void run() {
		try {
			Thread.sleep(500);
			barberia.rellenar(id);
			barberia.pelarse(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}