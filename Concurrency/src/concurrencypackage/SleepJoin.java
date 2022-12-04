package concurrencypackage;

public class SleepJoin implements Runnable{

	public void run() {
		String tName = Thread.currentThread().getName();
		try {
			System.out.println(tName + " is going to sleep");
			Thread.sleep(1000);
			System.out.println(tName + " has woke up");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new SleepJoin());
		Thread t2 = new Thread(new SleepJoin());
		t1.setName("thread 1");
		t2.setName("thread 2");
		t1.start();
		t2.start();
		try {
			//t2.join();
			t2.join(100);
			System.out.println("Inside main");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
