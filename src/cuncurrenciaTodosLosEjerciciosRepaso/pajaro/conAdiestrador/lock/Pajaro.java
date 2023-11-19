package cuncurrenciaTodosLosEjerciciosRepaso.pajaro.conAdiestrador.lock;

public class Pajaro extends Thread {
	Acciones acciones;
	int id;
	String raza ;

	public Pajaro(Acciones acciones,int id, String raza) {
		this.acciones = acciones;
		this.id = id;
		this.raza = raza;
	}

	public void run() {
		try {
			acciones.cantar(id,raza);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
