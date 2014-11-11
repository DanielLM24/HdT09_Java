/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Seccion 20
 * Peter Bennett 13243
 * Santiago Gonzalez 13263
 * Daniel Lara Moir 13424 
 * 
 * Programa para simular logistica de partido politico. Se basa en la implementacion de grafos
 * propuesta por Duane Bailey.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

import javax.swing.JOptionPane;


public class GraphDriver {
	private static File txt;	
	private static GraphMatrixDirected grafo = new GraphMatrixDirected(5);
	private static ArrayList<String> instrucciones = new ArrayList<String>();
	private static int[][] rutas_cortas=new int[5][5];
	private static int[][] path = new int[5][5];


	public static void main(String[] args) {
		menu();
	}

	public static void menu(){
		boolean exit = false;
		read();
		while(!exit){
			System.out.println("\n\n***     Alianza Nacional: Logistica     ***\n");
			System.out.println("1) Ruta mas corta entre dos ciudades.\n");
			System.out.println("2) Base de operaciones recomendada. (Centro del grafo)\n");
			System.out.println("3) Modificacion de grafo.\n");
			System.out.println("4) Finalizar el programa.\n");
			System.out.println("Clave:SOL = 0, REU = 1, ESC = 2, QUE = 3, GUA = 4\n");
			int entrada = userInput();
			switch(entrada){
			case(1):
				cortas();
			System.out.println("Ingrese la ciudad de origen:\n");
			String ciudad1=stringInput();
			int origen = 0;
			switch(ciudad1){
			case("SOL"):
				origen = 0;
			break;
			case("REU"):
				origen = 1;
			break;
			case("ESC"):
				origen = 2;
			break;
			case("QUE"):
				origen = 3;
			break;
			case("GUA"):
				origen = 4;
			break;
			default:
				break;
			}
			System.out.println("Ingrese la ciudad fin:\n");
			String ciudad2=stringInput();
			int fin = 0;
			switch(ciudad2){
			case("SOL"):
				fin = 0;
			break;
			case("REU"):
				fin = 1;
			break;
			case("ESC"):
				fin = 2;
			break;
			case("QUE"):
				fin = 3;
			break;
			case("GUA"):
				fin = 4;
			break;
			default:
				break;
			}
			rutaCorta(origen,fin);
			grafo.reset();
			break;
			case(2):
				cortas();
			centro();
			break;
			case(3):
				System.out.println("Escoja una opcion:\n");
			System.out.println("1) Interrupcion de trafico entre dos ciudades.\n");
			System.out.println("2) Establecer conexion nueva.\n");
			int pick = userInput();
			if(pick==1){
				System.out.println("Ingrese el nombre de la primera ciudad.\n");
				String partida = stringInput();
				System.out.println("Ingrese el nombre de la segunda ciudad.\n");
				String destino = stringInput();
				grafo.removeEdge(partida, destino);
			}
			if(pick==2){
				System.out.println("Ingrese el nombre de la primera ciudad.\n");
				String partida = stringInput();
				System.out.println("Ingrese el nombre de la segunda ciudad.\n");
				String destino = stringInput();
				System.out.println("Ingrese la distancia entre las ciudades:\n");
				int distancia = userInput();
				grafo.addEdge(partida, destino,distancia);
			}
			cortas();
			break;
			case(4):
				System.out.println("Gracias por utilizar el programa de logistica.");
			exit=true;
			default:
				break;
			}

		}
	}

	public static int userInput(){
		/**
		 * Scanner for user input.
		 */
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		/**
		 * Variable to store user input.
		 */
		int entrada=0;
		try{
			entrada = in.nextInt();
		}
		catch(InputMismatchException e){
			JOptionPane.showMessageDialog(null, "Ingreso invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return entrada;
	}
	public static String stringInput(){
		/**
		 * Scanner for user input.
		 */
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		/**
		 * Variable to store user input.
		 */
		String entrada="";
		try{
			entrada = in.nextLine();
		}
		catch(InputMismatchException e){
			JOptionPane.showMessageDialog(null, "Ingreso invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return entrada;
	}

	public static void read(){
		String instruccion = "";
		try{
			txt = new File("grafo.txt");
		}
		catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "No existe el archivo.", "ERROR", 
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		try{
			Scanner s = new Scanner(txt);
			while (s.hasNextLine()) {
				instruccion = s.nextLine();
				instrucciones.add(instruccion);
			}
			s.close();
		} 
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Archivo no encontrado.", "ERROR", 
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		construirGrafo();
	}
	public static void construirGrafo(){
		for(String st:instrucciones){
			grafo.add(st.substring(0,3));
			grafo.add(st.substring(4,7));
			grafo.addEdge(st.substring(0,3),st.substring(4,7),st.substring(8,st.length()));
		}
	}

	public static void rutaCorta(int start,int end){
		if(rutas_cortas[start][end]>=99999){
			System.out.println("No hay ruta.\n");
			return;
		}
		String fin = "";
		fin = keyToCity(end);
		// The path will always end at end.
		String myPath = fin + "";
		System.out.println("Distancia total: " + rutas_cortas[start][end] + " Km.");


		// Loop through each previous vertex until you get back to start.
		while (path[start][end] != start) {
			myPath = keyToCity(path[start][end]) + " -> " + myPath;
			end = path[start][end];
		}

		String inicio = "";
		inicio = keyToCity(start);
		// Just add start to our string and print.
		myPath = inicio + " -> " + myPath;
		System.out.println("El itinerario es: "+myPath);
	}

	public static void centro(){
		int[] array = new int[5];
		int[] array1 = new int[5];
		int[] array2 = new int[5];
		int[] array3 = new int[5];
		int[] array4 = new int[5];
		int[] excentricidad = new int[5];

		for(int i = 0; i<5;i++){
			array[i]=rutas_cortas[i][0];
			array1[i]=rutas_cortas[i][1];
			array2[i]=rutas_cortas[i][2];
			array3[i]=rutas_cortas[i][3];
			array4[i]=rutas_cortas[i][4];
		}
		Arrays.sort(array);
		Arrays.sort(array1);
		Arrays.sort(array2);
		Arrays.sort(array3);
		Arrays.sort(array4);
		excentricidad[0] = array[4];	
		excentricidad[1] = array1[4];	
		excentricidad[2] = array2[4];	
		excentricidad[3] = array3[4];	
		excentricidad[4] = array4[4];

		int min = Integer.MAX_VALUE;
		int valor = 0;
		for(int i = 0; i<excentricidad.length;i++){
			if(excentricidad[i]<min){
				min=excentricidad[i];
				valor=i;
			}
		}
		String ciudad ="";
		switch(valor){
		case(0):
			ciudad = "SOL";
		break;
		case(1):
			ciudad = "REU";
		break;
		case(2):
			ciudad = "ESC";
		break;
		case(3):
			ciudad = "QUE";
		break;
		case(4):
			ciudad = "GUA";
		break;
		default:
			break;
		}
		System.out.println("El centro se encuentra en: " + ciudad);

	}

	public static void cortas(){
		for (int i=0;i<grafo.size;i++){
			for (int j=0;j<grafo.size;j++){
				if(grafo.data[i][j]!=null){
					rutas_cortas[i][j] = Integer.parseInt(grafo.data[i][j].label().toString());
				}
				else
					rutas_cortas[i][j] = 999999;
			}
		}

		// Initialize with the previous vertex for each edge. -1 indicates
		// no such vertex.
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if (rutas_cortas[i][j] == 9999999)
					path[i][j] = -1;
				else
					path[i][j] = i;
			}
		}
		for (int i=0; i<5; i++)
			path[i][i] = i;

		// Compute successively better paths through vertex k.
		for (int k=0; k<grafo.size;k++) {
			// Do so between each possible pair of points.
			for (int i=0; i<grafo.size; i++) {
				for (int j=0; j<grafo.size;j++) {        
					if (rutas_cortas[i][k]+rutas_cortas[k][j] < rutas_cortas[i][j]) {
						rutas_cortas[i][j] = rutas_cortas[i][k]+rutas_cortas[k][j];
						path[i][j] = path[k][j];
					}
				}
			}
		}
	}
	public static String keyToCity(int key){
		String result = "";
		switch(key){
		case(0):
			result = "SOL";
		break;
		case(1):
			result = "REU";
		break;
		case(2):
			result = "ESC";
		break;
		case(3):
			result = "QUE";
		break;
		case(4):
			result = "GUA";
		break;
		default:
			break;
		}
		return result;
	}
}
