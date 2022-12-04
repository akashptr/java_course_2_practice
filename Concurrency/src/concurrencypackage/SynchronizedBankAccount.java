package concurrencypackage;

public class SynchronizedBankAccount implements Runnable {

	private int balance;

	public void setBalance(int amount) {
		this.balance = amount;
	}

	public int getBalance() {
		return balance;
	}

	public void withdraw(int amount) {
		if (amount <= balance) {
			balance -= amount;
			System.out.println(Thread.currentThread().getName() + " has withdrawn Rs. " + amount);
		} else
			System.out.println(Thread.currentThread().getName() + " can not withdrawn Rs. " + amount);
	}

	public void run() {
		synchronized(this) {
			if(Thread.currentThread().getName().equals("John"))
				withdraw(75);
			else
				System.out.println("The available balance is Rs. " + getBalance());
		}
	}

	public static void main(String[] args) {
		SynchronizedBankAccount task = new SynchronizedBankAccount();
		task.setBalance(100);
		
		Thread john = new Thread(task);
		john.setName("John");
		
		Thread anita = new Thread(task);
		anita.setName("Anita");
		
		john.start();
		anita.start();
	}

}
