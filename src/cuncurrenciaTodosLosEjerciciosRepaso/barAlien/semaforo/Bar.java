package cuncurrenciaTodosLosEjerciciosRepaso.barAlien.semaforo;

import java.util.concurrent.Semaphore;

public class Bar {
	
	private int aforo;
	private int clientesDentro=0;
	private Semaphore colanegro = new Semaphore(0);
	private Semaphore colablanco = new Semaphore(0);
	private Semaphore foro;
	int blanco= 0;
	int negro=0;
	
	public Bar(int aforo) {
		this.aforo = aforo;
		this.foro = new Semaphore(aforo);
	}

	public void entrar(int id, String raza) throws InterruptedException {
		while (!foro.tryAcquire()) {
			if (raza.equals("negro")) {
				negro++;
				colanegro.acquire();
				negro--;
			} else if (raza.equals("blanco")) {
				blanco++;
				colablanco.acquire();
				blanco--;
			}
		}
		System.out.println("cliente " + id + " entra\n\t cola blanco: " + blanco + " cola negro: " + negro);
	}

	public void salir(int id) throws InterruptedException {
		System.out.println("cliente " + id + " sale");
		if (blanco > 0) {
			colablanco.release();
		} else {
			colanegro.release();
		}
		foro.release();
	}
}
