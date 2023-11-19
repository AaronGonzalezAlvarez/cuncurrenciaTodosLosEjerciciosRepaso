package cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteDos.semaforo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Barberia {
	
	Semaphore sillas = new Semaphore(5);
	Semaphore sillon = new Semaphore(1);
	Semaphore peluquero = new Semaphore(0);
	Semaphore esperar = new Semaphore(0);
	boolean auxsillon=false;
	Random r = new Random();
	HashMap<Integer, Vuelta> vueltaSave = new HashMap<Integer, Vuelta>();

	public void pelarse(int id) throws InterruptedException {
		//aqui se espera la cola
		while(!sillas.tryAcquire()) {
			vueltaSave.get(id).addVuelta();
			System.out.println("Cliente "+id +" dando una vuelta");
			Thread.sleep(r.nextInt(2500,4000));
			if(vueltaSave.get(id).mostrarVueltas() == 3) {
				System.out.println("CLIENTE "+id+" SE VA DE LA BABERIA: ");
				return;
			}
		};
		System.out.println("cliente "+id +" sentado en la silla");
		sillon.acquire();
		sillas.release();
		System.out.println("cliente "+id +" sentado en el sillon a la espera del peluqiero");
		auxsillon=true;
		peluquero.release();
		esperar.acquire();
		auxsillon=false;
		System.out.println("Cliente "+id +" se va satisfecho");
		sillon.release();
	}
	
	public void trabajar() throws InterruptedException {
		while(true) {
			if(auxsillon) {
				System.out.println("VOY A PELAR");
				Thread.sleep(20000);
				System.out.println("TERMINADO");
				Thread.sleep(1500);
				esperar.release();
				
			}
			peluquero.acquire();
		}

	}

	public synchronized void rellenar(int id) {
		vueltaSave.put(id,new Vuelta(id));
	}

}
