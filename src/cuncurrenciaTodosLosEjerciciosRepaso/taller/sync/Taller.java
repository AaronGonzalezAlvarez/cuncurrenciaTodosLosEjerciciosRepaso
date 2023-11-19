package cuncurrenciaTodosLosEjerciciosRepaso.taller.sync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Taller {
	
	final int PUESTOS_CA = 3;
	final int PUESTOS_RG = 5;
	final int MAX_BIDONES = 5;
	
	Object robot = new Object();
	Object cambioAceite = new Object();
	Object revisionGeneral = new Object();
	Object esperar = new Object();
	int cochesAceite = 0;
	int cochesRevisionGeneral = 0;
	private int consumoAceite;
	
	
	public Taller() {
		this.consumoAceite=4;
	}

	public void vehículo(int id) throws InterruptedException {
		synchronized (cambioAceite) {
			while (cochesAceite == 3) {
				System.out.println("Coche " + id + " esperando al cambio de aceite");
				cambioAceite.wait();
			}
			cochesAceite++;

			// coger aceite para el cambio
			while (consumoAceite == MAX_BIDONES) {
				System.out.println("Coche " + id + " llama al robot");
				synchronized (robot) {
					robot.notify();
				}
				synchronized (esperar) {
					esperar.wait();
				}
			}
			consumoAceite++;
		}
		Thread.sleep(2500);
		synchronized (cambioAceite) {
			cochesAceite--;
			System.out.println("aceite cambiado por el coche "+id);
			cambioAceite.notify();
		}
		
		//revision general
		synchronized (revisionGeneral) {
			while (cochesRevisionGeneral == MAX_BIDONES) {
				System.out.println("Coche " + id + " esperando a la revision general");
				revisionGeneral.wait();
			}
			cochesRevisionGeneral++;
		}
		Thread.sleep(8000);
		synchronized (revisionGeneral) {
			cochesRevisionGeneral--;
			System.out.println("Se va del taller coche  "+id);
			revisionGeneral.notify();
		}
		
	}

	private void cambiarAceite(int id) throws InterruptedException {
		System.out.println("Cliente "+ id + " cambiando aceite");
		Thread.sleep(3000);
	}

	private void revisionGeneral(int id) throws InterruptedException {
		System.out.println("Cliente "+ id + " haciendo revision general");
		Thread.sleep(5000);
		
	}

	public void robotMontacargas() throws InterruptedException {
		while (true) {
			synchronized (robot) {
				reponerBidones();
			}
		}
	}

	private void reponerBidones() throws InterruptedException {
		while(consumoAceite == MAX_BIDONES) {
			Thread.sleep(2000);
			consumoAceite=0;
			System.out.println("ROBOT HA CAMBIADO ACEITE");
			synchronized (esperar) {
				esperar.notify();
			}
		}
		robot.wait();
		
	}
}