package cuncurrenciaTodosLosEjerciciosRepaso.pajaro.conAdiestrador.lock;

public class Principal {
	
	public static void main(String[] args) {
		System.out.println("Pajaro adiestrador con lock");
		Acciones acciones = new Acciones();
		Adiestrador adiestrador = new Adiestrador(acciones);
		adiestrador.start();
		for(int x=0;x<5;x++) {
			Pajaro c = new Pajaro(acciones,x,"dragon negro");
			c.start();
		}
		
		for(int x=5;x<10;x++) {
			Pajaro c = new Pajaro(acciones,x,"dragon verde");
			c.start();
		}
		
		for(int x=11;x<16;x++) {
			Pajaro c = new Pajaro(acciones,x,"dragon rojo");
			c.start();
		}
	}
}
