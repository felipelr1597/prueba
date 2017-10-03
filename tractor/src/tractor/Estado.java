package tractor;

import java.util.ArrayList;

public class Estado {

	private int destinoX;
	private int destinoY;
	private int tierraAMover;

	public Estado(int destinoX, int destinoY, int tierraAMover) {
		this.destinoX = destinoX;
		this.destinoY = destinoY;
		this.tierraAMover = tierraAMover;
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
	
	public int getTierraAMover() {
		return tierraAMover;
	}

	public void setTierraAMover(int tierraAMover) {
		this.tierraAMover = tierraAMover;
	}

	@Override
	public String toString() {
		return "Estado [destinoX=" + destinoX + ", destinoY=" + destinoY + "]";
	}

}
