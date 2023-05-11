package algoritmoAEstrella;

import java.util.ArrayList;

public class ListaCeldas {
	
	
	private ArrayList<Celda> listaCeldas;

	
	public void createEmpty () { //Inicializa el listaCeldas
		listaCeldas = new ArrayList<Celda>();
	}
	
	public void push (Celda c) {
		listaCeldas.add(c);
	}
	
		
		public Celda devolverCelda (int n) { //Devuelve el nodo que yo quiero de la lista
			Celda c = null;
			
			if (!listaCeldas.isEmpty()) {
				c = listaCeldas.get(n);
			}
		
		return c;
	}
		
	public void borrarCelda (int n) {
		listaCeldas.remove(n);
	}
	public void borrartodo () {
		listaCeldas.removeAll(listaCeldas);
	}
	
	public boolean buscarCelda(Celda c) {
		boolean encontrada=false;
		for(int i=0;i<listaCeldas.size();i++) {
			if(listaCeldas.get(i)==c) {
				encontrada = true;
			}
		}
		
		return encontrada;
	}
		
	public int tamanio() {
		return listaCeldas.size();
	}
	
}