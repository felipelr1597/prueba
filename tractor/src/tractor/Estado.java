package tractor;

public class Estado {

	private int destinoX;
	private int destinoY;
	
	public Estado(int destinoX, int destinoY) {
		this.destinoX = destinoX;
		this.destinoY = destinoY;
	}

	public int getDestinoX() {
		return destinoX;
	}

	public void setDestinoX(int destinoX) {
		this.destinoX = destinoX;
	}

	public int getDestinoY() {
		return destinoY;
	}

	public void setDestinoY(int destinoY) {
		this.destinoY = destinoY;
	}

	@Override
	public String toString() {
		return "Estado [destinoX=" + destinoX + ", destinoY=" + destinoY + "]";
	}
	
	
}
