package cuncurrenciaTodosLosEjerciciosRepaso.bar.sync;

public class Principal {
	
	public static void main(String[] args) {
		System.out.println("bar con sync");
		Bar bar = new Bar(5);
		
		for(int x=0;x<10;x++) {
			Cliente c = new Cliente(bar,x);
			c.start();
		}
	}
}
