package tractor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Menu();
	}

	public static int OpcionEjecucion() {
		int peticion = 0;
		Scanner TECLADO = new Scanner(System.in);
		System.out.println(
				"�Desea meter los valores por archivo de texto? En caso negativo los datos se usaran valores aleatorios \n 1-Si \n 2-No ");
		peticion = TECLADO.nextInt();
		if (peticion < 1 || peticion > 2) {
			System.out.println("Opcion incorrecta, introduzca de nuevo");
			peticion = TECLADO.nextInt();
		}
		return peticion;
	}

	public static void Menu() {
		String ruta="D:/3�/inteligentes/inteligentesJava/tractor/";
		Terrenoss t = new Terrenoss();
		int peticion = OpcionEjecucion();
		Scanner TECLADO = new Scanner(System.in);
		switch (peticion) {
		case 1:
			try {
				System.out.println("Introduzca la ruta deseada \n");
				//String ruta = TECLADO.next();				
				System.out.println(ruta);
				BorrarContenidoFichero(ruta);
				t.TerrenoFichero(ruta);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
			}
			break;
		case 2:
			BorrarContenidoFichero(ruta);
			t.TerrenoAleatorio();
			break;
		}
	}
	public static void BorrarContenidoFichero(String Archivo) {
		try {
			FileWriter  fos = new FileWriter (Archivo + "TerrenosFinales.txt");
			BufferedWriter bw = new BufferedWriter(fos);
			bw.write("");
			bw.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
