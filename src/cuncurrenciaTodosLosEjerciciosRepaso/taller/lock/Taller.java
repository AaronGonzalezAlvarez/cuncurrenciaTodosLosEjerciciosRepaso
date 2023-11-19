package cuncurrenciaTodosLosEjerciciosRepaso.taller.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Taller {
	
	final int PUESTOS_CA = 3;
	final int PUESTOS_RG = 5;
	final int MAX_BIDONES = 5;
	
	private ReentrantLock lock = new ReentrantLock();
	
	private ReentrantLock cambioAceite = new ReentrantLock();
	private Condition esperarCambioAceite = cambioAceite.newCondition();
	
	private ReentrantLock revisionGeneral = new ReentrantLock();
	private Condition esperarrevisionGeneral = revisionGeneral.newCondition();
	
	private Condition robot = lock.newCondition();
	private Condition esperar = lock.newCondition();
	int cochesAceite = 0;
	int cochesRevisionGeneral = 0;
	private int consumoAceite;
	
	
	public Taller() {
		this.consumoAceite=4;
	}

	public void vehículo(int id) throws InterruptedException {
		cambioAceite.lock();
		while(cochesAceite ==3) {
			System.out.println("Coche " + id + " esperando al cambio de aceite");
			esperarCambioAceite.await();
		}
		cochesAceite++;

		//coger aceite para el cambio
		while(consumoAceite ==MAX_BIDONES) {
			System.out.println("Coche " + id + " llama al robot");
			lock.lock();
			robot.signal();
			esperar.await();
			lock.unlock();
		}
		consumoAceite++;
		
		cambioAceite.unlock();
		Thread.sleep(2500);
		cambioAceite.lock();
		cochesAceite--;
		System.out.println("aceite cambiado por el coche "+id);
		esperarCambioAceite.signal();
		revisionGeneral.lock();
		cambioAceite.unlock();
		while(cochesRevisionGeneral ==5) {
			System.out.println("Coche " + id + " esperando la revision general");
			esperarrevisionGeneral.await();
		}
		cochesRevisionGeneral++;
		revisionGeneral.unlock();
		Thread.sleep(4000);
		revisionGeneral.lock();
		System.out.println("Clicnete "+ id + "se va");
		cochesRevisionGeneral--;
		esperarrevisionGeneral.signal();
		revisionGeneral.unlock();

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
			lock.lock();
			reponerBidones();
			lock.unlock();
		}
	}

	private void reponerBidones() throws InterruptedException {
		while(consumoAceite == MAX_BIDONES) {
			Thread.sleep(2000);
			consumoAceite=0;
			System.out.println("ROBOT HA CAMBIADO ACEITE");
			esperar.signal();
		}
		robot.await();
		
	}
}