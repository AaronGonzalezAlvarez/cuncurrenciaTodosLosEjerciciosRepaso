package cuncurrenciaTodosLosEjerciciosRepaso.bar.semaforo;

import java.util.Random;

public class Cliente extends Thread {
	Bar bar;
	int id;
	Random rand = new Random();

	public Cliente(Bar bar,int id) {
		this.bar = bar;
		this.id = id;
	}

	public void run() {
		try {
			bar.entrar(id);
			Thread.sleep(3000);
			bar.salir(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
