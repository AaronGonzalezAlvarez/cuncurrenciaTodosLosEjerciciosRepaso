package cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteDos.sync;

public class Vuelta {
	
	int num;
	int id;
	
	public Vuelta(int id){
		this.num = 0;
		this.id =id;
	}
	
	public void addVuelta() {
		num++;
	}
	
	public int mostrarVueltas() {
		return num;
	}
	
	public int mostrarid() {
		return id;
	}

}
