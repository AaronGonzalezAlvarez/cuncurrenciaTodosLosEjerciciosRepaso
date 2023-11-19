package cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteDos.lock;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteDos.semaforo.Vuelta;

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
	HashMap<Integer, Vuelta> vueltaSave = new HashMap<Integer, Vuelta>();
	Prueba prueba= new Prueba();
	Random r = new Random();

	public void pelarse(int id) throws InterruptedException {

		while (prueba.mostrar() == 5) {
			vueltaSave.get(id).addVuelta();
			System.out.println("Cliente " + id + " dando una vuelta");
			Thread.sleep(r.nextInt(2500, 4000));
			if (vueltaSave.get(id).mostrarVueltas() == 3) {
				System.out.println("CLIENTE " + id + " SE VA DE LA BABERIA: ");
				return;
			}
		}
		prueba.add();
		lock.lock();
		/*while (uaxSillas == 5) {
			System.out.println("cliente " + id + " a espera a sentarse en la silla");
			esperarAsentarseSilla.await();
		}*/
		//uaxSillas++;
		//prueba.add();
		while (auxSillon == 1) {
			System.out.println("cliente " + id + " a espera sentado en silla");
			esperarAsillon.await();
			esperarAsentarseSilla.signal();
		}
		//uaxSillas--;
		prueba.delete();
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
	
	public synchronized void rellenar(int id) {
		vueltaSave.put(id,new Vuelta(id));
	}

}
