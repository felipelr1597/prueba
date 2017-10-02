package tractor;

public class Tractor {
	
	private int posicionX;
	private int posicionY;
	private int tierraActual;
	private int tierraAlmacenada;
	private int tierraMinima;
	
	public Tractor(int posicionX, int posicionY, int tierraMinima) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.tierraMinima=tierraMinima;
		tierraActual=0;
		tierraAlmacenada=0;
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
	public int getTierraAlmacenada() {
		return tierraAlmacenada;
	}
	public void setTierraAlmacenada(int tierraAlamcenada) {
		this.tierraAlmacenada=tierraAlmacenada;
	}
	public int sumarCinco() {
		if(tierraAlmacenada-5<0) {
			return -1;
		}else {
			this.tierraAlmacenada=tierraAlmacenada-5;
			this.tierraActual=tierraActual+5;		
			return 0;
		}
	}
	public int restarCinco() {
		if(tierraActual-5<0 || tierraActual-5<tierraMinima) {
			return -1;
		}else {
			this.tierraAlmacenada=tierraAlmacenada+5;
			this.tierraActual=tierraActual-5;	
			return 0;
		}
	}
	@Override
	public String toString() {
		return "Tractor [posicionX=" + posicionX + ", posicionY=" + posicionY + ", tierraActual=" + tierraActual
				+ ", tierraAlmacenada=" + tierraAlmacenada + "]";
	}
	
}
