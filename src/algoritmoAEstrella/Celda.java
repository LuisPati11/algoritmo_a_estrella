package algoritmoAEstrella;

public class Celda {
	private int x,y;
	private char valor;
	private Celda vizq;
	private Celda vder;
	private Celda vup;
	private Celda vdown;
	private int heuristica;
	private int g;
	private int f;
	private Celda padre;
	
	public Celda(int x, int y, char valor,Celda vizq,Celda vder,Celda vup,Celda vdown,int heuristica,int g,int f,Celda padre) {
		this.x=x;
		this.y=y;
		this.valor=valor;
		this.vizq=vizq;
		this.vder=vder;
		this.vup=vup;
		this.vdown=vdown;
		this.heuristica=heuristica;
		this.g=g;
		this.f=f;
		this.padre=padre;
		
	}
	
	public int getx () {
		return x;
	}
	
	public void setx (int x) {
		this.x = x;
	}
	public int gety () {
		return y;
	}
	
	public void sety (int y) {
		this.y = y;
	}
	public char getvalor () {
		return valor;
	}
	
	public void setvalor (char valor) {
		this.valor = valor;
	}
	
	public Celda getvizq() {
		return vizq;
	}
	public void setvizq(Celda vizq) {
		this.vizq=vizq;
	}
	public Celda getvder() {
		return vder;
	}
	public void setvder(Celda vder) {
		this.vder=vder;
	}
	public Celda getvup() {
		return vup;
	}
	public void setvup(Celda vup) {
		this.vup=vup;
	}
	public Celda getvdown() {
		return vdown;
	}
	public void setvdown(Celda vdown) {
		this.vdown=vdown;
	}
	public int getheuristica() {
		return heuristica;
	}
	public void setheuristica(int heuristica) {
		this.heuristica=heuristica;
	}
	public int getg () {
		return g;
	}
	
	public void setg (int g) {
		this.g = g;
	}
	public int getf () {
		return f;
	}
	
	public void setf (int f) {
		this.f = f;
	}
	public Celda getpadre() {
		return padre;
	}
	public void setpadre(Celda padre) {
		this.padre=padre;
	}
}



