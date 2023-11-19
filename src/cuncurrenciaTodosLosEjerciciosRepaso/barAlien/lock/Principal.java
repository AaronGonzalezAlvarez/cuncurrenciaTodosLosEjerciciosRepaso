package cuncurrenciaTodosLosEjerciciosRepaso.barAlien.lock;

public class Principal {
	
	public static void main(String[] args) {
		System.out.println("barAlien con lock");
		Bar bar = new Bar(2);
		
		for(int x=0;x<5;x++) {
			Cliente c = new Cliente(bar,x,"negro");
			c.start();
		}
		for(int x=6;x<11;x++) {
			Cliente c = new Cliente(bar,x,"blanco");
			c.start();
		}
	}
}
