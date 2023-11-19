package cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteUno.semaforo;

public class Principal {
	
	public static void main(String[] args) {
		System.out.println("Barberia parte uno semaforo");
		Barberia barberia = new Barberia();
		Barbero barbero = new Barbero(barberia);
		barbero.start();
		
		for(int x=0;x<10;x++) {
			Cliente c = new Cliente(barberia,x);
			c.start();
		}
	}
}
