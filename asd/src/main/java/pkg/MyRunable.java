package pkg;

public class MyRunable implements Runnable {

	public void run() {
		System.out.println("jedu");
	}
	
	public static void main(String[] args) {
		/*
		 * tady asi jediny rozdil je, ze v Jave se muze
		 * dedit jen z jedne tridy kdezto interfacu muze
		 * mit trida vic. takze je tu moznost vlakna
		 * delat skrz dedeni Thread nebo implementaci
		 * Runnable. oba zpusoby jsou ekvivalentni
		 * */
		MyRunable r = new MyRunable();
		Thread t = new Thread(r);
		t.start();
	}
	
}
