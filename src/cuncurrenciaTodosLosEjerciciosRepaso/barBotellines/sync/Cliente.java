package cuncurrenciaTodosLosEjerciciosRepaso.barBotellines.sync;

import java.util.Random;

public class Cliente extends Thread {
	Bar bar;
	int id;
	private Random r = new Random();

	public Cliente(Bar bar,int id) {
		this.bar = bar;
		this.id = id;
	}

	public void run() {
		try {
			//Thread.sleep(r.nextInt(1000,2000));
			bar.tomarBotellin(id,2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}