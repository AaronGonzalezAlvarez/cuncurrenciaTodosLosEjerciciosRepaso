package cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteUno.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barberia {
	
	ReentrantLock lock = new ReentrantLock();
	ReentrantLock sillas = new ReentrantLock();
	ReentrantLock cola = new ReentrantLock();
	ReentrantLock sillon = new ReentrantLock();
	Condition esperar = sillas.newCondition();
	Condition esperarcola = cola.newCondition();
	Condition barbero = lock.newCondition();
	
	//pruebas
	ReentrantLock lockPeluquero = new ReentrantLock();
	Condition esperarAsentarseSilla = lock.newCondition();
	Condition esperarAsillon = lock.newCondition();
	Condition esperarApeluqiero = lock.newCondition();
	Condition llamarBarbero = lockPeluquero.newCondition();
	//fin pruebas
	boolean auxsillon=false;
	int uaxSillas=0;
	int auxSillon=0;

	public void pelarse(int id) throws InterruptedException {
		lock.lock();
		while (uaxSillas == 5) {
			System.out.println("cliente " + id + " a espera a sentarse en la silla");
			esperarAsentarseSilla.await();
		}
		uaxSillas++;
		while (auxSillon == 1) {
			System.out.println("cliente " + id + " a espera sentado en silla");
			esperarAsillon.await();
			esperarAsentarseSilla.signal();
		}
		uaxSillas--;
		auxSillon++;
		System.out.println("Cliente " + id + "esperando al peluqiero");
		auxsillon=true;
		lockPeluquero.lock();
		llamarBarbero.signal();
		lockPeluquero.unlock();
		esperarApeluqiero.await();
		//Thread.sleep(3500);
		auxsillon=false;
		auxSillon--;
		esperarAsillon.signal();
		System.out.println("Cliente "+id+" se va satisfecho");
		lock.unlock();
	}
	
	public void trabajar() throws InterruptedException {
		while(true) {
			lockPeluquero.lock();
			if(auxsillon) {
				System.out.println("VOY A PELAR");
				Thread.sleep(5000);
				System.out.println("TERMINADO");
				Thread.sleep(1500);
				lock.lock();
				esperarApeluqiero.signal();
				lock.unlock();
			}
			llamarBarbero.await();
			lockPeluquero.unlock();
		}

	}

}
