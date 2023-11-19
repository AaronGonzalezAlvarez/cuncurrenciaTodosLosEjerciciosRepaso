package cuncurrenciaTodosLosEjerciciosRepaso.bar.sync;

public class Bar {
	
	private int aforo;
	private int clientesDentro=0;
	
	public Bar(int aforo) {
		this.aforo = aforo;
	}

	public synchronized void entrar(int id) throws InterruptedException {
		
		if(clientesDentro == aforo) {
			System.out.println("cliente a la espera de entrar "+id);
			wait();
		}
		clientesDentro++;
		//abrirPuertaE();
		System.out.println("cliente "+ id +" entra");
		
	}

	private synchronized void abrirPuertaE() {
		// TODO Auto-generated method stub
		
	}

	public synchronized void salir(int id) {
		clientesDentro--;
		System.out.println("cliente "+id +" sale");
		notifyAll();
		//abrirPuertaS();
	}

	private void abrirPuertaS() {
		// TODO Auto-generated method stub
		
	}
}
