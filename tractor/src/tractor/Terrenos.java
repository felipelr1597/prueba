package tractor;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Terrenos {

	private int tierraMinima = 0;

	public void TerrenoManual() {
		Scanner TECLADO = new Scanner(System.in);
		try {
			
			int horizontal = (int) (Math.random() * 10) + 5;
			int vertical = (int) (Math.random() * 10) + 5;
			int Terreno[][] = new int[horizontal][vertical];
			String TerrenoString[][] = new String[horizontal][vertical];
			tierraMinima = (int) (Math.random() * 10) + 1;
			Tractor t = new Tractor(0, 0,tierraMinima);
			System.out.println("Tierra minima en el campo:" + tierraMinima + "\n");
			CrearTerreno(Terreno);
			CopiarMatriz(Terreno, TerrenoString);
			MostrarTerreno(TerrenoString, t);
			EscrituraFichero(Terreno);
			MoverTractor(TerrenoString, Terreno, t);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void TerrenoFichero(String Archivo) throws FileNotFoundException {
		Scanner datos = new Scanner(new FileReader(Archivo + "TerrenoInicial.txt"));
		int horizontal = 0;
		int vertical = 0;
		while (datos.hasNext()) {
			horizontal = datos.nextInt();
			vertical = datos.nextInt();
			tierraMinima = datos.nextInt();
		}
		int Terreno[][] = new int[horizontal][vertical];
		String TerrenoString[][] = new String[horizontal][vertical];
		CrearTerreno(Terreno);
	}

	public void EscrituraFichero(int Terreno[][]) {
		String Archivo = "D:/3�/inteligentes/inteligentesJava/tractor/";
		System.out.println(Archivo);
		try {
			FileOutputStream fos = new FileOutputStream(Archivo + "TerrenosFinales.txt");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (int i = 0; i < Terreno.length; i++) {
				for (int j = 0; j < Terreno[i].length; j++) {
					bw.write(Terreno[i][j]);
					bw.newLine();
				}
			}
			bw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void CrearTerreno(int Terreno[][]) {
		for (int i = 0; i < Terreno.length; i++) {
			for (int j = 0; j < Terreno[i].length; j++) {
				int valor = (int) (Math.random() * 10) + tierraMinima;
				Terreno[i][j] = valor;
			}
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

	public void MoverTractor(String TerrenoString[][], int Terreno[][], Tractor t) {
		Scanner TECLADO = new Scanner(System.in);
		int posicion = 0;
		int lineas = 0;
		for (int i = 0; i < Terreno.length; i++) {
			for (int j = 0; j < Terreno[i].length; j++) {
				lineas = lineas + 1;
			}
		}
		try {
			System.out.println(
					"Seleccione como quiere mover el tractor \n 1- Abajo \n 2- Arriba \n 3- Derecha \n 4- Izquierda \n 5- Posicion actual \n");
			int seleccion = TECLADO.nextInt();
			switch (seleccion) {
			case 1:
				posicion = t.getPosicionX();
				if (posicion >= Terreno.length - 1) {
					System.out.println("El tractor esta en el borde del terreno\n");
					MoverTractor(TerrenoString, Terreno, t);
				} else {
					t.setPosicionX(t.getPosicionX() + 1);
					t.setTierraActual(Terreno[t.getPosicionX()][t.getPosicionY()]);
					MostrarTerreno(TerrenoString, t);
					System.out.println(t.toString());
					Operaciones(TerrenoString, Terreno, t);
				}
				break;
			case 2:
				posicion = t.getPosicionX();
				if (posicion <= 0) {
					System.out.println("El tractor esta en el borde del terreno\n");
					MoverTractor(TerrenoString, Terreno, t);
				} else {
					t.setPosicionX(t.getPosicionX() - 1);
					t.setTierraActual(Terreno[t.getPosicionX()][t.getPosicionY()]);
					MostrarTerreno(TerrenoString, t);
					System.out.println(t.toString());
					Operaciones(TerrenoString, Terreno, t);
				}
				break;
			case 3:
				posicion = t.getPosicionY();
				if (posicion >= lineas) {
					System.out.println("El tractor esta en el borde del terreno\n");
					MoverTractor(TerrenoString, Terreno, t);
				} else {
					t.setPosicionY(t.getPosicionY() + 1);
					t.setTierraActual(Terreno[t.getPosicionX()][t.getPosicionY()]);
					MostrarTerreno(TerrenoString, t);
					System.out.println(t.toString());
					Operaciones(TerrenoString, Terreno, t);
				}
				break;
			case 4:
				posicion = t.getPosicionY();
				if (posicion <= 0) {
					System.out.println("El tractor esta en el borde del terreno\n");
					MoverTractor(TerrenoString, Terreno, t);
				} else {
					t.setPosicionY(t.getPosicionY() - 1);
					t.setTierraActual(Terreno[t.getPosicionX()][t.getPosicionY()]);
					MostrarTerreno(TerrenoString, t);
					System.out.println(t.toString());
					Operaciones(TerrenoString, Terreno, t);
				}
				break;
			case 5:
				t.setTierraActual(Terreno[t.getPosicionX()][t.getPosicionY()]);
				MostrarTerreno(TerrenoString, t);
				System.out.println(t.toString());
				Operaciones(TerrenoString, Terreno, t);
				break;
			default:
				System.out.println("No hay seleecion posible \n");
				break;
			}
			MoverTractor(TerrenoString, Terreno, t);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void Operaciones(String TerrenoString[][], int Terreno[][], Tractor t) {
		int result;
		Scanner TECLADO = new Scanner(System.in);
		System.out.println("Como desea mover la tierra \n 1- Quitar 5 \n 2- Poner 5 \n 3- No hacer nada \n");
		int valor = TECLADO.nextInt();
		switch (valor) {
		case 1:
			result = t.restarCinco();
			if (result == -1) {
				System.out.println("No hay tierra suficente \n");
				MoverTractor(TerrenoString, Terreno, t);
			} else {
				Terreno[t.getPosicionX()][t.getPosicionY()] = t.getTieraActual();
				CopiarMatriz(Terreno,TerrenoString);
				MostrarTerreno(TerrenoString, t);
				System.out.println(t.toString());
				MoverTractor(TerrenoString, Terreno, t);
			}
			break;
		case 2:
			result = t.sumarCinco();
			if (result == -1) {
				System.out.println("No hay tierra suficente \n");
				MoverTractor(TerrenoString, Terreno, t);
			} else {
				Terreno[t.getPosicionX()][t.getPosicionY()] = t.getTieraActual();
				CopiarMatriz(Terreno,TerrenoString);
				MostrarTerreno(TerrenoString, t);
				System.out.println(t.toString());
				MoverTractor(TerrenoString, Terreno, t);
			}
			break;
		case 3:
			MoverTractor(TerrenoString, Terreno, t);
		default:
			System.out.println("No hay seleecion posible \n");
			MoverTractor(TerrenoString, Terreno, t);
			break;
		}
	}
}