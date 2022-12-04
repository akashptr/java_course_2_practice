package concurrencypackage;

public class ThreadNamePriorityCurrentThread implements Runnable{

	public void run() {
		System.out.println(Thread.currentThread());
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new ThreadNamePriorityCurrentThread());
		Thread t2 = new Thread(new ThreadNamePriorityCurrentThread());
		t1.setName("t1");
		t2.setName("t2");
		t2.setPriority(Thread.MAX_PRIORITY);
		t1.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start();
	}

}