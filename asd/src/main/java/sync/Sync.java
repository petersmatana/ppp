package sync;

public class Sync extends Thread {

	private String name;
	private ShareData sd;
	
	private Sync b1, b2;

	public Sync(String name) {
		this.name = name;
		this.sd = new ShareData(0);
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			/*
			synchronized (sd) {
				if (this.name == "t1") {
					this.sd.odecist();
				}
			}

			synchronized (sd) {
				if (this.name == "t2") {
					this.sd.pricist();
				}
			}
			*/

			/*
			synchronized (this.sd) {
				if (this.name == "t1") {
					this.sd.odecistSync();
				}
			}

			synchronized (this.sd) {
				if (this.name == "t2") {
					this.sd.pricistSync();
				}
			}
			*/
			
			/*
			if (this.name == "t1") {
				this.sd.odecistSync();
			}
			

			if (this.name == "t2") {
				this.sd.pricistSync();
			}
			*/
			
			if (this.name == "t1") {
				this.sd.plusSync();
			}
			

			if (this.name == "t2") {
				this.sd.minusSync();
			}
			
			System.out.println("vlakno = " + this.name + " = " + this.sd.getCislo());
		}
	}

	public void go() {
		try {
			b1 = new Sync("t1");
			b2 = new Sync("t2");
			
			b1.start();
			b2.start();
		} catch (Exception ex) {
			System.out.println("chyba = " + ex.toString());
		}
	}

}
