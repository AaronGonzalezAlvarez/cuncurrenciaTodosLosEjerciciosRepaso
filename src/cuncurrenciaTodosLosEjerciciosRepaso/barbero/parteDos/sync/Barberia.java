package cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteDos.sync;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteDos.lock.Prueba;
import cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteDos.semaforo.Vuelta;

public class Barberia {
	
	Object barbero = new Object();
	Object sillas = new Object(); 
	Object sillon = new Object();
	Object cola = new Object(); 
	Object o1 = new Object(); 
	boolean auxsillonBoolean=false;
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
		synchronized (sillas) {
			/*while (uaxSillas == 5) {
				System.out.println("Cliente " + id + " a la espera de coger silla");
				sillas.wait();
			}
			uaxSillas++;*/
			while (auxSillon == 1) {
				System.out.println("cliente " + id + " sentado en silla " + uaxSillas);
				sillas.wait();
			}
			//uaxSillas--;
			prueba.delete();
		}
		synchronized (sillon) {
			auxSillon++;
			System.out.println("Cliente " + id + " esperando al peluqiero");
			auxsillonBoolean = true;
			synchronized (barbero) {
				barbero.notify();
			}
			sillon.wait();
			auxsillonBoolean = false;
			auxSillon--;
			synchronized (sillas) {
				sillas.notify();
			}
			System.out.println("Cliente "+ id + " sale contento");
		}
	}
	
	public void trabajar() throws InterruptedException {
		while(true) {
			synchronized (barbero) {
				if(auxsillonBoolean) {
					System.out.println("VOY A PELAR");
					Thread.sleep(5000);
					System.out.println("TERMINADO");
					Thread.sleep(1500);
					//luego
					synchronized (sillon) {
						sillon.notify();
					}
				}
				barbero.wait();
			}			
		}

	}
	
	public synchronized void rellenar(int id) {
		vueltaSave.put(id,new Vuelta(id));
	}

}
