package cuncurrenciaTodosLosEjerciciosRepaso.barAlien.sync;

public class Cliente extends Thread {
	Bar bar;
	int id;
	String raza;

	public Cliente(Bar bar,int id,String raza) {
		this.bar = bar;
		this.id = id;
		this.raza = raza;
	}

	public void run() {
		try {
			bar.entrar(id,raza);
			Thread.sleep(3000);
			bar.salir(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
