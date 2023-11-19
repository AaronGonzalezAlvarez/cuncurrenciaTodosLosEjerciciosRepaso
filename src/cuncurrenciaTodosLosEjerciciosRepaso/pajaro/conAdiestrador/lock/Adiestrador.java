package cuncurrenciaTodosLosEjerciciosRepaso.pajaro.conAdiestrador.lock;

public class Adiestrador extends Thread {
	Acciones acciones;

	public Adiestrador(Acciones acciones) {
		this.acciones = acciones;
	}

	public void run() {
		try {
			Thread.sleep(1500);
			System.out.println("SOY EL ADIESTRADOR Y VA A CANTAR UN DRAGON");
			acciones.darAviso();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
