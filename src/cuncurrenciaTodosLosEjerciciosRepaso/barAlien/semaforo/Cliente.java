package cuncurrenciaTodosLosEjerciciosRepaso.barAlien.semaforo;

import java.util.Random;

public class Cliente extends Thread {
	Bar bar;
	int id;
	String raza;
	private Random r = new Random();

	public Cliente(Bar bar,int id,String raza) {
		this.bar = bar;
		this.id = id;
		this.raza = raza;
	}

	public void run() {
		try {
			//Thread.sleep(r.nextInt(500,1000));
			bar.entrar(id,raza);
			Thread.sleep(3000);
			bar.salir(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
