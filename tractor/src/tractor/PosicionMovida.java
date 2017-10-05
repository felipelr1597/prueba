package tractor;

public class PosicionMovida {
	
	private int CantidadMover;
	private int PosicionXTierra;
	private int PosicionYTierra;
	
	public PosicionMovida(int cantidadMover, int posicionXTierra, int posicionYTierra) {
		CantidadMover = cantidadMover;
		PosicionXTierra = posicionXTierra;
		PosicionYTierra = posicionYTierra;
	}
	public int getCantidadMover() {
		return CantidadMover;
	}
	public void setCantidadMover(int cantidadMover) {
		CantidadMover = cantidadMover;
	}
	public int getPosicionXTierra() {
		return PosicionXTierra;
	}
	public void setPosicionXTierra(int posicionXTierra) {
		PosicionXTierra = posicionXTierra;
	}
	public int getPosicionYTierra() {
		return PosicionYTierra;
	}
	public void setPosicionYTierra(int posicionYTierra) {
		PosicionYTierra = posicionYTierra;
	}
	@Override
	public String toString() {
		return " [" + CantidadMover + ", (" + PosicionXTierra
				+ ", " + PosicionYTierra + ")]";
	}
	

}
