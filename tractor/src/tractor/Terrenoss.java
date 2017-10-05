package tractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Terrenoss {

	int k;
	int v;
	int maximo;
	String ruta = null;
	ArrayList<PosicionMovida> movimientosNopermitidos = new ArrayList<PosicionMovida>();
	ArrayList<PosicionMovida> listaMovimientos = new ArrayList<PosicionMovida>();
	ArrayList<PosicionMovida> MovimientosFil = new ArrayList<PosicionMovida>();
	Stack<PosicionMovida> pila = new Stack<PosicionMovida>();;

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
		MoverTractorAbajo(t, Terreno);
		PosicionesNoAptas(Terreno);
		FiltarLista(t, Terreno);
		int cont = 0;
		for (int i = 0; i < MovimientosFil.size(); i++) {
			cont++;
			System.out.print(MovimientosFil.get(i).toString() + "---");
			if (cont == listaMovimientos.size()) {
				cont = 0;
				System.out.println();
			}

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

	public void MoverTractorAbajo(Tractor t, int Terreno[][]) {
		int posicion = t.getPosicionX();
		if (posicion >= Terreno.length - 1) {
			MoverTactorIzquierda(t, Terreno);
		} else {
			PosicionMovida estado = new PosicionMovida(t.getTierraAlmacenada(), t.getPosicionX() + 1, t.getPosicionY());
			listaMovimientos.add(listaMovimientos.size(), estado);
			MoverTactorIzquierda(t, Terreno);
		}
	}

	public void MoverTactorIzquierda(Tractor t, int Terreno[][]) {
		int posicion = t.getPosicionY();
		if (posicion <= 0) {
			MoverTractorArriba(t, Terreno);
		} else {
			PosicionMovida estado = new PosicionMovida(t.getTierraAlmacenada(), t.getPosicionX(), t.getPosicionY() - 1);
			listaMovimientos.add(listaMovimientos.size(), estado);
			MoverTractorArriba(t, Terreno);
		}
	}

	public void MoverTractorArriba(Tractor t, int Terreno[][]) {
		int posicion = t.getPosicionX();
		if (posicion <= 0) {
			MoverTractorDerecha(t, Terreno);
		} else {
			PosicionMovida estado = new PosicionMovida(t.getTierraAlmacenada(), t.getPosicionX() - 1, t.getPosicionY());
			listaMovimientos.add(listaMovimientos.size(), estado);
			MoverTractorDerecha(t, Terreno);
		}
	}

	public void MoverTractorDerecha(Tractor t, int Terreno[][]) {
		int lineas = 0;
		for (int i = 0; i < Terreno.length; i++) {
			for (int j = 0; j < Terreno[i].length; j++) {
				lineas = lineas + 1;
			}
		}
		System.out.println(t.getTierraAlmacenada() + "-" + t.getTierraMinima() + "-" + t.getTierraActual() + "-"
				+ listaMovimientos.size());

		int posicion = t.getPosicionY();
		if (posicion >= lineas) {

		} else {
			PosicionMovida estado = new PosicionMovida(t.getTierraAlmacenada(), t.getPosicionX(), t.getPosicionY() + 1);
			listaMovimientos.add(listaMovimientos.size(), estado);

		}
		ArrayList<PosicionMovida> Elementos = new ArrayList<PosicionMovida>();
		for (int j = 0; j < listaMovimientos.size(); j++) {
			for (int i = 0; i < t.getTierraAlmacenada() + 1; i++) {

				PosicionMovida m = new PosicionMovida(i, listaMovimientos.get(j).getPosicionXTierra(),
						listaMovimientos.get(j).getPosicionYTierra());
				// System.out.println(m.toString());
				Elementos.add(Elementos.size(), m);
			}
		}
		GenerarListaPosibles(Elementos, 0, listaMovimientos.size(), Elementos.size());
	}

	public void GenerarListaPosibles(ArrayList<PosicionMovida> elemento, int act, int n, int r) {
		if (n == 0) {
			pila.push(elemento.get(act));
		} else {
			for (int i = 0; i < r; i++) {
				pila.push(elemento.get(act));
				GenerarListaPosibles(elemento, i, n - 1, r);
			}
		}
	}

	public void FiltarLista(Tractor t, int Terreno[][]) {
		PosicionMovida[] matriz = new PosicionMovida[listaMovimientos.size()];
		while (!pila.isEmpty()) {
			int suma = 0;
			for (int i = 0; i < matriz.length; i++) {
				matriz[i] = pila.pop();
				suma = matriz[i].getCantidadMover() + suma;
			}
			int bloqueo = 0;
			for (int i = 0; i < matriz.length; i++) {
				int posx = matriz[i].getPosicionXTierra();
				int posy = matriz[i].getPosicionYTierra();
				for (int j = 0; j < matriz.length; j++) {
					int posx1 = matriz[j].getPosicionXTierra();
					int posy1 = matriz[j].getPosicionYTierra();
					if (posx == posx1 && posy == posy1 && i != j && bloqueo == 0) {
						bloqueo = 1;
					}
					
				}
			}
			int salida = 0;
			if (bloqueo == 0) {
				///////////flata el codigo 
				
				if (salida == 0) {
					for (int i = 0; i < matriz.length; i++) {
						if (suma == t.getTierraAlmacenada()) {
							if (!MovimientosFil.contains(matriz[i])) {
								MovimientosFil.add(MovimientosFil.size(), matriz[i]);
							}
						}
					}
				}

			}
		}
	}

	public void PosicionesNoAptas(int Terreno[][]) {
		for (int i = 0; i < listaMovimientos.size(); i++) {
			if (k <= Terreno[listaMovimientos.get(i).getPosicionXTierra()][listaMovimientos.get(i)
					.getPosicionYTierra()]) {
				movimientosNopermitidos.add(listaMovimientos.get(i));
			}
		}
	}

}
