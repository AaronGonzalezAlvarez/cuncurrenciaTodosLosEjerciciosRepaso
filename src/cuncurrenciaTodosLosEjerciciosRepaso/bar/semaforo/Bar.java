package cuncurrenciaTodosLosEjerciciosRepaso.bar.semaforo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bar {

	private int aforo;
	private int clientesDentro = 0;
	private Semaphore semaforo = new Semaphore(5);
	private Semaphore esperar = new Semaphore(0);

	public Bar(int aforo) {
		this.aforo = aforo;
	}

	public void entrar(int id) throws InterruptedException {
		semaforo.acquire();
		// abrirPuertaE();
		System.out.println("cliente " + id + " entra");
	}

	private void abrirPuertaE() {
		// TODO Auto-generated method stub

	}

	public void salir(int id) throws InterruptedException {
		System.out.println("cliente " + id + " sale");
		semaforo.release();
		// abrirPuertaS();
	}

	private void abrirPuertaS() {
		// TODO Auto-generated method stub

	}
}
