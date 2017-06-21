package sync;

public class ShareData {

	private int cislo = 0;
	private Cislo c = null;

	public ShareData(int c) {
		this.cislo = c;
		this.c = new Cislo();
	}

	/*
	 * public int getCislo() { return cislo; }
	 * 
	 * public void setCislo(int cislo) { this.cislo = cislo; }
	 */

	public void pricist() {
		this.cislo += 1;
	}

	public void odecist() {
		this.cislo -= 1;
	}

	public synchronized void pricistSync() {
		this.cislo += 1;
	}

	public synchronized void odecistSync() {
		this.cislo -= 1;
	}
	
	public synchronized void plusSync() {
		this.c.pricist();
	}

	public synchronized void minusSync() {
		this.c.odecist();
	}

	public synchronized String toString() {
		// return "cislo = " + String.valueOf(this.cislo);
		return String.valueOf(this.cislo);
	}
	
	public Cislo getCislo() {
		return this.c;
	}

	private class Cislo {
		private int cislo = 0;

		public void pricist() {
			this.cislo += 1;
		}

		public void odecist() {
			this.cislo -= 1;
		}
		
		public synchronized String toString() {
			return String.valueOf(this.cislo);
		}
	}
}
