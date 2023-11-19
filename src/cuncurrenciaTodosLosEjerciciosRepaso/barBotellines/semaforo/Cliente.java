package cuncurrenciaTodosLosEjerciciosRepaso.barBotellines.semaforo;


public class Cliente extends Thread {
	Bar bar;
	int id;

	public Cliente(Bar bar,int id) {
		this.bar = bar;
		this.id = id;
	}

	public void run() {
		try {
			bar.tomarBotellin(id,2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}