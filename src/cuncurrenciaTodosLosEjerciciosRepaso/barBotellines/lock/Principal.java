package cuncurrenciaTodosLosEjerciciosRepaso.barBotellines.lock;

public class Principal  {
	
	public static void main(String[] args) {
		System.out.println("barBotellin con lock");
		Bar bar = new Bar(5);
		
		Thread camarero = new Camarero(bar);
		camarero.start();
		
		for(int x=0;x<10;x++) {
			Cliente c= new Cliente(bar,x);
			c.start();
		}
	}
}
