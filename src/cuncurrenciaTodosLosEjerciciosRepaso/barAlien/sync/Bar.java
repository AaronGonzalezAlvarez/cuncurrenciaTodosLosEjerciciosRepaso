package cuncurrenciaTodosLosEjerciciosRepaso.barAlien.sync;

public class Bar {
	
	private int aforo;
	private int clientesDentro=0;
	private Object o1 = new Object();
	private Object o2 = new Object();
	private Object o3 = new Object();
	int blanco= 0;
	int negro=0;
	
	public Bar(int aforo) {
		this.aforo = aforo;
	}

	public void entrar(int id, String raza) throws InterruptedException {
		while (clientesDentro == aforo) {
			if (raza.equals("negro")) {
				synchronized (o2) {
					negro++;
					o2.wait();
					negro--;
				}
			} else if (raza.equals("blanco")) {
				synchronized (o3) {
					blanco++;
					o3.wait();
					blanco--;
				}
			}
		}
		clientesDentro++;
		// abrirPuertaE();
		System.out.println("cliente " + id + " entra\n\t cola blanco: " + blanco + " cola negro: " + negro);

	}

	public void salir(int id) {
		clientesDentro--;
		System.out.println("cliente " + id + " sale");
		if (blanco > 0) {
			synchronized (o3) {
				o3.notify();
			}
		} else {
			synchronized (o2) {
				o2.notify();
			}
		}
	}
}
