package tractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Terrenoss {

	int k;
	int v;
	int maximo;
	String ruta = null;
	ArrayList<Estado> listaMovimientos = new ArrayList<Estado>();
	ArrayList<PosicionMovida> combinaciones = new ArrayList<PosicionMovida>();

	public void TerrenoAleatorio() {
		int filas = (int) (Math.random() * 10) + 5;
		int columnas = (int) (Math.random() * 10) + 5;
		int Terreno[][] = new int[filas][columnas];
		String TerrenoString[][] = new String[filas][columnas];
		k = (int) (Math.random() * 10) + 1;
		v = filas * columnas * k;
		Tractor t = new Tractor(0, 0, k, maximo, 0);
		RellenarTerreno(Terreno);
		CopiarMatriz(Terreno, TerrenoString);
		MostrarTerreno(TerrenoString, t);
		EscrituraFichero(TerrenoString, t, filas, columnas);
	}

	public void TerrenoFichero(String Archivo) throws FileNotFoundException {		
		ruta = Archivo;
		Scanner datos = new Scanner(new FileReader(Archivo + "TerrenoInicial.txt"));
		int filas = 0;
		int columnas = 0;
		while (datos.hasNext()) {
			filas = datos.nextInt();
			columnas = datos.nextInt();
			k = datos.nextInt();
			maximo = datos.nextInt();
		}
		v = filas * columnas * k;
		int Terreno[][] = new int[filas][columnas];
		String TerrenoString[][] = new String[filas][columnas];
		Tractor t = new Tractor(0, 0, k, maximo, 0);
		RellenarTerreno(Terreno);
		t.setTierraActual(Terreno[t.getPosicionX()][t.getPosicionY()]);
		CopiarMatriz(Terreno, TerrenoString);
		MostrarTerreno(TerrenoString, t);
		EscrituraFichero(TerrenoString, t, filas, columnas);
		MoverTractorAbajo(t, Terreno, listaMovimientos);
		for (int x = 0; x < listaMovimientos.size(); x++) {
			System.out.println(listaMovimientos.get(x).toString());
		}
	}

	public void EscrituraFichero(String Terreno[][], Tractor t, int filas, int columnas) {
		System.out.println(ruta);
		try {
			File file = new File(ruta + "TerrenosFinales.txt");
			FileWriter fos = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fos);
			bw.write("" + t.getPosicionX());
			bw.write("" + t.getPosicionY());
			bw.write("" + k);
			bw.write("" + maximo);
			bw.write("" + filas);
			bw.write("" + columnas);
			bw.newLine();
			for (int i = 0; i < Terreno.length; i++) {
				for (int j = 0; j < Terreno[i].length; j++) {
					bw.write(Terreno[i][j]);
				}
				bw.newLine();
			}
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void CopiarMatriz(int Terreno[][], String TerrenoString[][]) {
		for (int i = 0; i < Terreno.length; i++) {
			for (int j = 0; j < Terreno[i].length; j++) {
				TerrenoString[i][j] = "" + Terreno[i][j];
			}
		}
	}

	public void MostrarTerreno(String TerrenoString[][], Tractor t) {
		for (int i = 0; i < TerrenoString.length; i++) {
			System.out.print("|");
			for (int j = 0; j < TerrenoString[i].length; j++) {
				if (i == t.getPosicionX() && j == t.getPosicionY()) {
					if (TerrenoString[i][j].length() == 1) {
						System.out.print(" |" + TerrenoString[i][j] + "| ");
					} else {
						System.out.print("|" + TerrenoString[i][j] + " |");
					}
				} else {
					if (TerrenoString[i][j].length() == 1) {
						System.out.print("  " + TerrenoString[i][j] + "  ");
					} else {
						System.out.print(" " + TerrenoString[i][j] + "  ");
					}
				}
			}
			System.out.println("|");
		}
	}

	public void RellenarTerreno(int[][] Terreno) {
		int aux = this.v;
		int cantidad_metida;
		for (int i = 0; i < Terreno.length; i++) {
			for (int j = 0; j < Terreno[i].length; j++) {
				cantidad_metida = (int) (Math.random() * this.maximo + 1);
				if (aux - cantidad_metida >= 0 && aux - cantidad_metida <= aux) {
					Terreno[i][j] = cantidad_metida;
					aux -= cantidad_metida;
				}
			}
		}
		if (aux != 0)
			RellenarTerreno(Terreno);
	}

	public void MoverTractorAbajo(Tractor t, int Terreno[][], ArrayList listaMovimientos) {
		int posicion = t.getPosicionX();
		if (posicion >= Terreno.length - 1) {
			MoverTactorIzquierda(t, Terreno, listaMovimientos);
		} else {
			Estado estado = new Estado(t.getPosicionX() + 1, t.getPosicionY(), t.getTierraAlmacenada());
			listaMovimientos.add(listaMovimientos.size(), estado);
			MoverTactorIzquierda(t, Terreno, listaMovimientos);
		}
	}

	public void MoverTactorIzquierda(Tractor t, int Terreno[][], ArrayList listaMovimientos) {
		int posicion = t.getPosicionY();
		if (posicion <= 0) {
			MoverTractorArriba(t, Terreno, listaMovimientos);
		} else {
			Estado estado = new Estado(t.getPosicionX() , t.getPosicionY()-1, t.getTierraAlmacenada());
			listaMovimientos.add(listaMovimientos.size(), estado);
			MoverTractorArriba(t, Terreno, listaMovimientos);
		}
	}

	public void MoverTractorArriba(Tractor t, int Terreno[][], ArrayList listaMovimientos) {
		int posicion = t.getPosicionX();
		if (posicion <= 0) {
			MoverTractorDerecha(t, Terreno, listaMovimientos);
		} else {
			Estado estado = new Estado(t.getPosicionX() -1, t.getPosicionY(), t.getTierraAlmacenada());
			listaMovimientos.add(listaMovimientos.size(), estado);
			MoverTractorDerecha(t, Terreno, listaMovimientos);
		}
	}

	public void MoverTractorDerecha(Tractor t, int Terreno[][], ArrayList listaMovimientos) {
		int lineas = 0;
		for (int i = 0; i < Terreno.length; i++) {
			for (int j = 0; j < Terreno[i].length; j++) {
				lineas = lineas + 1;
			}
		}
		int tierra = 0;
		System.out.println(t.getTierraAlmacenada()+"-"+t.getTierraMinima()+"-"+t.getTierraActual());
		int[] Elementos = new int[t.getTierraAlmacenada()+1];
		for (int i = 0; i < Elementos.length; i++) {
			Elementos[i] = i;
			
		}
		System.out.println(Elementos.length);
		int posicion = t.getPosicionY();
		if (posicion >= lineas) {
			GenerarListaPosibles(Elementos, "", listaMovimientos.size(), Elementos.length);
		} else {
			Estado estado = new Estado(t.getPosicionX() , t.getPosicionY()+1, t.getTierraAlmacenada());
			listaMovimientos.add(listaMovimientos.size(), estado);
			System.out.println(Elementos.length);
			GenerarListaPosibles(Elementos, "", listaMovimientos.size(), t.getTierraAlmacenada());
		}
	}

	public void GenerarListaPosibles(int[] elemento, String act, int n, int r) {
		if (n == 0) {
			System.out.println(act);
		} else {
			for (int i = 0; i < r; i++) {
				GenerarListaPosibles(elemento, act + elemento[i]+"," , n - 1, r);
			}
		}
	}

}
