package concurrencypackage;

public class CreateThread {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Task());
		Thread t2 = new Thread(new Task());
		t1.start();
		t2.start();
		System.out.println("Inside main thread");
	}

}

class Task implements Runnable {

	@Override
	public void run() {
		go();
		System.out.println("Inside run() method");
	}

	public void go() {
		move();
		System.out.println("Inside go() method");
	}

	public void move() {
		System.out.println("Inside move() method");
	}
}
