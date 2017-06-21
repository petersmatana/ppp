package pkg;

public class Begin extends Thread {

	private String name;
	
	public Begin(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			System.out.println(this.name + " - " + String.valueOf(i));
		}
	}
	
	public static void main(String[] args) {
		/*
		 * timto pustim 2 vlakna, ve vypisu je jednou t1 pak zas
		 * t2, vlakna jedou vedle sebe a provadi run...
		 * */
		Begin b1 = new Begin("t1");
		Begin b2 = new Begin("t2");
		
		b1.start();
		b2.start();
	}
	
}
