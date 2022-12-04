package concurrencypackage;

import java.util.concurrent.TimeUnit;

public class VolatileExample {

	private static volatile boolean stop;

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			public void run() {
				int x = 0;
				while(!stop) {
					System.out.println(x++);
				}
			}
		}).start();
		TimeUnit.MILLISECONDS.sleep(10);
		stop = true;
	}

}
