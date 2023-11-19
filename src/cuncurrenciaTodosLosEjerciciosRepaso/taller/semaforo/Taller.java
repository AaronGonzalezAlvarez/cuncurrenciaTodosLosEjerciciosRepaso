package cuncurrenciaTodosLosEjerciciosRepaso.taller.semaforo;

import java.util.concurrent.Semaphore;

public class Taller {
	
	final int PUESTOS_CA = 3;
	final int PUESTOS_RG = 5;
	final int MAX_BIDONES = 5;
	private int consumoAceite=0;
	
	private Semaphore cambioAceite = new Semaphore(3);
	private Semaphore cogerAceite = new Semaphore(1);
	private Semaphore revisionGeneral = new Semaphore(1);
	private Semaphore esperar = new Semaphore(0);
	private Semaphore robot = new Semaphore(0);
	
	public Taller() {
		
	}

	public void vehículo(int id) throws InterruptedException {
		cambioAceite.acquire();
		
		cogerAceite.acquire();
		while(consumoAceite == MAX_BIDONES) {
			System.out.println("llamo al robot no hay aceite");
			robot.release();
			esperar.acquire();
		}
		consumoAceite++;
		cogerAceite.release();
		
		cambiarAceite(id);
		revisionGeneral.acquire();
		cambioAceite.release();
		revisionGeneral(id);
		revisionGeneral.release();
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
			reponerBidones();
		}
	}

	private void reponerBidones() throws InterruptedException {
		while(consumoAceite == MAX_BIDONES) {
			Thread.sleep(2000);
			consumoAceite=0;
			System.out.println("ROBOT HA CAMBIADO ACEITE");
			esperar.release();
		}
		robot.acquire();
		
	}
}