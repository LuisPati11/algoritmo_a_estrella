package algoritmoAEstrella;

import java.util.ArrayList;
import java.util.Random;

import java.io.*;

public class LaberintoObjetos {
	final static int filas = 60;
	final static int columnas = 80;
	static boolean resuelto = false;
	
	public static void main (String[] args) throws IOException{
		

		Celda [][] matriz = new Celda[filas][columnas];
		ListaCeldas openSet = new ListaCeldas();
		ListaCeldas closedSet=new ListaCeldas();
		ListaCeldas solucion=new ListaCeldas();
		
		
		openSet.createEmpty(); //Creamos el arraylist openset
		closedSet.createEmpty();
		solucion.createEmpty();
		
		rellenar(matriz);
		obstaculos(matriz);
		Celda inicio=aniadir_caracter(matriz, 'I');
		Celda fin= aniadir_caracter(matriz, 'O');
		openSet.push(inicio);//iniciamos el arraylist openset con inicio
		rellenarVecinos(matriz);
		calcularHeuristica(matriz,fin);	
		algoritmo(openSet, closedSet, solucion, fin);
		pintaCamino(matriz, solucion);
		String salida=imprimir(matriz);	
		
		   
	    PrintWriter outputfile = new PrintWriter("salida.txt");
	    if(resuelto== true) {
	    	outputfile.println("Camino encontrado: \n");
		    outputfile.print(salida);
	    } else{
		    outputfile.println("No hay un camino posible: \n");
		    outputfile.print(salida);
	    }

	    outputfile.close(); 
	}
		
	
	public static void rellenar (Celda [][]matriz) {
		for(int i=0;i<filas;i++) {
			for(int j=0;j<columnas;j++) {
				matriz[i][j]=new Celda(i,j,' ',null,null,null,null,0,0,0,null);;
			}
		}
	}
	
	public static void obstaculos (Celda [][] matriz) {
		
		double porcentaje = 0.3*(filas*columnas);
		int j=0;
		while (j < porcentaje) {
			
			int fila=numeroAletaorio(0,filas);
			int columna=numeroAletaorio(0,columnas);
			if (matriz[fila][columna].getvalor() !='*') {
				matriz[fila][columna].setvalor('*');;
				j++;
			}
		}
	}
	public static Celda aniadir_caracter (Celda [][] matriz, char caracter) {
		boolean continuar = true;
		Celda c = null;
		while (continuar) {
			int i = numeroAletaorio(0,filas-1);
			int j = numeroAletaorio(0,columnas-1);
			
			if (matriz[i][j].getvalor()==(' ')) {
				matriz[i][j].setvalor(caracter);
				c=matriz[i][j];
				continuar = false;
			}
		}
		return c;
	}

	static String imprimir(Celda[][] matriz) {
		String salida = "";
		for(int i=0;i<filas;i++) {
			System.out.print('|');
			salida+="|";
			for(int j=0;j<columnas;j++) {
				 System.out.print (matriz[i][j].getvalor());
				 salida+=matriz[i][j].getvalor();
				 if (j!=columnas-1) {
					 System.out.print(' ');
				 }
			  }
			  System.out.println('|');
			  salida+="|\n";
			}
		return salida;
		}  
    
	public static int numeroAletaorio(int min, int max){

		Random random = new Random();
		int value = random.nextInt(max + min) + min;		
		return value;
		
	}
	public static void aniadirVecinos(Celda c,Celda[][] matriz) {		
		if(c.getx()>0) {
			c.setvup(matriz[c.getx()-1][c.gety()]);
		}
		 if(c.getx() < filas-1) {
			 c.setvdown(matriz[c.getx()+1][c.gety()]);
		 }
		 if(c.gety() > 0) {
		    c.setvizq(matriz[c.getx()][c.gety()-1]);
		    }
		 if(c.gety() < columnas-1) {
			 c.setvder(matriz[c.getx()][c.gety()+1]);
		 }
	}
	
	private static void rellenarVecinos(Celda[][] matriz) {
		for(int i=0;i<filas;i++) {
			for(int j=0;j<columnas;j++) {
				aniadirVecinos(matriz[i][j],matriz);
			}
		}
	}
	
	private static void calcularHeuristica(Celda[][] matriz,Celda fin) {
		for(int i=0;i<filas;i++) {
			for(int j=0;j<columnas;j++) {
				matriz[i][j].setheuristica(heuristica(matriz[i][j],fin));
			}
		}
	}
	
	private static int heuristica(Celda celda, Celda fin) {
		int distanciaFilas=Math.abs(fin.getx()-celda.getx());
		int distanciaColumnas=Math.abs(fin.gety()-celda.gety());
		int distancia=distanciaFilas+distanciaColumnas;
		
		return distancia;
	}
	
	
	private static void pintaCamino(Celda [][]matriz,ListaCeldas solucion) {
		for(int k=1;k<solucion.tamanio()-1;k++) {
			for(int i=0;i<filas;i++) {
				for(int j=0;j<columnas;j++) {
					if(solucion.devolverCelda(k).getx()==matriz[i][j].getx()&&
					   solucion.devolverCelda(k).gety()==matriz[i][j].gety()) {
						matriz[i][j].setvalor('@');
					}
				}
			}
		}
	}
	
	private static void algoritmo(ListaCeldas openSet,ListaCeldas closedSet,ListaCeldas solucion,Celda fin) {
		
		
		int ganador = 0;
		boolean camino=false;
			 
			  while(openSet.tamanio()>0) {
		      ganador =0;  
		    
		      
		      for(int i=0; i<openSet.tamanio(); i++){
		        if(openSet.devolverCelda(i).getf() < openSet.devolverCelda(ganador).getf()){
		          ganador = i;
		        }
		      }
		      
		      Celda actual= openSet.devolverCelda(ganador);
		      
		      if (actual==fin) {
		    	  Celda temporal=actual;
		    	  solucion.push(temporal);
		    	 
		    	  while(temporal.getpadre()!=null) {
		    		  temporal=temporal.getpadre();
		    		  solucion.push(temporal);
		    	  }
		    	  openSet.borrartodo();
		    	  camino=true;
		      }
		      else {
		    	  if(openSet.tamanio()>0) {
		    		  openSet.borrarCelda(ganador);
			    	  closedSet.push(actual);
		    	  }
			    	  
			    	  ArrayList<Celda> vecinos=new ArrayList<Celda>();
			    	  
			    	  if (actual.getvizq()!=null) {
			    		  if ( actual.getvizq().getvalor() != '*' )
			    			  vecinos.add(actual.getvizq());
			    	  }
			    	  if (actual.getvder()!=null) {
			    		  if ( actual.getvder().getvalor() != '*' )
			    			  vecinos.add(actual.getvder());
			    	  }if (actual.getvup()!=null) {
			    		  if ( actual.getvup().getvalor() != '*' )
			    			  vecinos.add(actual.getvup());
			    	  }if (actual.getvdown()!=null) {
			    		  if ( actual.getvdown().getvalor() != '*' )
			    			  vecinos.add(actual.getvdown());
			    	  }
			    	  
			    	  for(int i=0;i<vecinos.size();i++) {
			    		  Celda vecino=vecinos.get(i);
			    	  
			    		  if(!closedSet.buscarCelda(vecino)) {
			    			  int tempG = actual.getg() + 1;

			    	            if(openSet.buscarCelda(vecino)){
			    	              if(tempG < vecino.getg()){
			    	                vecino.setg(tempG);     
			    	              }
			    	            }
			    	            else{
			    	              vecino.setg(tempG);
			    	              openSet.push(vecino);
			    	            }

			    	            
			    	            vecino.setheuristica(heuristica(vecino,fin)) ;
			    	            vecino.setf(vecino.getg() + vecino.getheuristica()) ;
			    	            
			    	            vecino.setpadre(actual);
			    	  	}
			    	  }
			      }
		      }
			 
			  if(camino) {
				  resuelto = true;
				  System.out.println("Camino encontrado");
				  
			  }
			  
			  else{
				  System.out.println("No hay un camino posible");
			  }		  
	}
	
}
