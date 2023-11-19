package cuncurrenciaTodosLosEjerciciosRepaso.barAlien.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bar {
	
	private int aforo;
	private int clientesDentro=0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition colaBlanco = lock.newCondition();
	private Condition colaNegro = lock.newCondition();
	int blanco= 0;
	int negro=0;
	
	public Bar(int aforo) {
		this.aforo = aforo;
	}

	public void entrar(int id, String raza) throws InterruptedException {
		lock.lock();
		while (clientesDentro == aforo) {
			if (raza.equals("negro")) {
				negro++;
				colaNegro.await();
				negro--;
			} else if (raza.equals("blanco")) {
				blanco++;
				colaBlanco.await();
				blanco--;
			}
		}
		clientesDentro++;
		// abrirPuertaE();
		System.out.println("cliente " + id + " entra\n\t cola blanco: " + blanco + " cola negro: " + negro);
		lock.unlock();

	}

	public void salir(int id) {
		lock.lock();
		clientesDentro--;
		System.out.println("cliente " + id + " sale");
		if (blanco > 0) {
			colaBlanco.signal();
		} else {
			colaNegro.signal();
		}
		lock.unlock();
	}
}
