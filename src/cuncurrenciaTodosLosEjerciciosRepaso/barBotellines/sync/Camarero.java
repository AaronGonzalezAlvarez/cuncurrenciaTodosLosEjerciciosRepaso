package cuncurrenciaTodosLosEjerciciosRepaso.barBotellines.sync;


public class Camarero extends Thread {
	Bar bar;

	public Camarero(Bar bar) {
		this.bar = bar;
	}

	public void run() {
		try {
			bar.reponer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
