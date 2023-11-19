package cuncurrenciaTodosLosEjerciciosRepaso.bar.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bar {
	
	private int aforo;
	private int clientesDentro=0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition esperar = lock.newCondition();
	public Bar(int aforo) {
		this.aforo = aforo;
	}

	public void entrar(int id) throws InterruptedException {
		lock.lock();
		if(clientesDentro == aforo) {
			System.out.println("cliente a la espera de entrar "+id);
			esperar.await();
		}
		clientesDentro++;
		//abrirPuertaE();
		System.out.println("cliente "+ id +" entra");
		lock.unlock();
		
	}

	private void abrirPuertaE() {
		// TODO Auto-generated method stub
		
	}

	public void salir(int id) {
		lock.lock();
		clientesDentro--;
		System.out.println("cliente "+id +" sale");
		esperar.signal();
		lock.unlock();
		//abrirPuertaS();
	}

	private void abrirPuertaS() {
		// TODO Auto-generated method stub
		
	}
}
