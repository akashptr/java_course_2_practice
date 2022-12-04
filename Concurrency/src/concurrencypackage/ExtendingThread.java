package concurrencypackage;

public class ExtendingThread extends Thread {

	public void run() {
		System.out.println("running: " + Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		Thread t1 = new ExtendingThread();
		Thread t2 = new ExtendingThread();
		t1.start();
		t2.start();
		System.out.println("Inside main");
	}

}
