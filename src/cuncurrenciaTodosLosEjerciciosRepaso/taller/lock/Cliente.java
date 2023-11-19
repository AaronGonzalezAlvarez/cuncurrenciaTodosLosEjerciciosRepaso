package cuncurrenciaTodosLosEjerciciosRepaso.taller.lock;

public class Cliente extends Thread {
	Taller taller;
	int id;

	public Cliente(Taller taller,int id) {
		this.taller = taller;
		this.id = id;
	}

	public void run() {
		try {
			taller.vehículo(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
