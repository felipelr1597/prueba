package tractor;

public class Tractor {
	
	private int posicionX;
	private int posicionY;
	private int tierraMinima;
	private int maximo;
	private int tierraActual;
	private int tierraAlmacenada;
	
	public Tractor(int posicionX, int posicionY, int tierraMinima, int maximo,int tierraActual) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.tierraMinima=tierraMinima;
		this.maximo=maximo;
		this.tierraActual=tierraActual;
		this.tierraAlmacenada=0;
		
	}
	public int getPosicionX() {
		return posicionX;
	}
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}
	public int getPosicionY() {
		return posicionY;
	}
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}
	public int getTieraActual() {
		return tierraActual;
	}
	public void setTierraActual(int tierraActual) {
		this.tierraActual=tierraActual;
	}
	public int getMaximo() {
		return maximo;
	}
	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}
	public int getTierraAlmacenada() {
		return tierraAlmacenada;
	}
	public void setTierraAlmacenada() {
		int valor=tierraAlmacenada=tierraActual-tierraMinima;
		if(valor<=0) {
			tierraAlmacenada=0;
		}else {
			tierraAlmacenada=valor;
		}
	}
	public int getTierraActual() {
		return tierraActual;
	}
	public int getTierraMinima() {
		return tierraMinima;
	}
	public void setTierraMinima(int tierraMinima) {
		this.tierraMinima = tierraMinima;
	}

}
