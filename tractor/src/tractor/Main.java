package tractor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
// hola borras
public class Main {

	public static void main(String[] args) {
		Menu();
	}

	public static int OpcionEjecucion() {
		int peticion = 0;
		Scanner TECLADO = new Scanner(System.in);
		try {
			System.out.println(
					"¿Desea meter los valores por archivo de texto? En caso negativo los datos se usaran valores aleatorios \n 1-Si \n 2-No ");
			peticion = TECLADO.nextInt();
			if (peticion < 1 || peticion > 2) {
				System.out.println("Opcion incorrecta, introduzca de nuevo");
				peticion = TECLADO.nextInt();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			OpcionEjecucion();
		}
		return peticion;
	}

	public static void Menu() {
		BuscarArchivo();
		String ruta="C:/Users/Martinez/OneDrive - Universidad de Castilla-La Mancha/1_Entorno_Desarrollo/Tractor/";
		System.out.println("Test, la ruta actual es"+ruta);
		Terrenoss t = new Terrenoss();
		int peticion = OpcionEjecucion();
		Scanner TECLADO = new Scanner(System.in);
		switch (peticion) {
		case 1:
			try {
				System.out.println("Introduzca la ruta deseada \n");
				// String ruta = TECLADO.next();
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
			FileWriter fos = new FileWriter(Archivo + "TerrenosFinales.txt");
			BufferedWriter bw = new BufferedWriter(fos);
			bw.write("");
			bw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void BuscarArchivo() {
		File f = null;
		String[] strs = { "TerrenoInicial.txt", "TerrenosFinales.txt" };
		String ruta=null;
		String directorio=null;
		boolean existir;
		try {
			// for each string in string array
			for (String s : strs) {
				// create new file
				f = new File(s);
				
				// Prueba la existencia
				existir=f.exists();
				System.out.println("El archivo existe: "+existir);
				
				// true if the file is executable
				boolean bool = f.canExecute();

				// find the absolute path
				ruta = f.getAbsolutePath();
	
				// prints absolute path
				System.out.print(ruta);

				// prints
				System.out.println(" is executable: " + bool);
				
				directorio=f.getParent();
			}			
		}
		catch (Exception e) {
			// if any I/O error occurs
			e.printStackTrace();
		}
	}
}
