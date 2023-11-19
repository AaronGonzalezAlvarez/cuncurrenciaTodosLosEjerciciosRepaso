package cuncurrenciaTodosLosEjerciciosRepaso.taller.semaforo;


public class Principal {

	public static void main(String[] args) {
		System.out.println("taller con semaforo");
		Taller taller = new Taller();
		Robot r = new Robot(taller);
		r.start();
		for (int x = 0; x < 10; x++) {
			Cliente c = new Cliente(taller, x);
			c.start();
		}
	}
}
