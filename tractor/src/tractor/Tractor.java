package tractor;

public class Tractor {

	private int posicionX;
	private int posicionY;
	private int tierraMinima;
	private int maximo;
	private int tierraActual;
	private int tierraAlmacenada;

	public Tractor(int posicionX, int posicionY, int tierraMinima, int maximo, int tierraActual) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.tierraMinima = tierraMinima;
		this.maximo = maximo;
		this.tierraActual = tierraActual;
		this.tierraAlmacenada = 0;

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

	public int getTierraActual() {
		return tierraActual;
	}

	public void setTierraActual(int tierraActual) {
		this.tierraActual = tierraActual;
	}

	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	public int getTierraAlmacenada() {
		if ((tierraActual- tierraMinima )  +tierraAlmacenada <= 0) {
			return tierraAlmacenada = 0;
		} else if (tierraActual == tierraMinima) {
			return tierraAlmacenada = 0;
		} else {
			return (tierraActual- tierraMinima )  +tierraAlmacenada ;
		}

	}
public void setTierraAlmacenada(int tierra) {
	this.tierraAlmacenada=tierra;
}

	public int getTierraMinima() {
		return tierraMinima;
	}

	public void setTierraMinima(int tierraMinima) {
		this.tierraMinima = tierraMinima;
	}

}
