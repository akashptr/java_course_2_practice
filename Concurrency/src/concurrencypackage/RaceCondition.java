package concurrencypackage;

public class RaceCondition {

	public static void main(String[] args) {
		BankAccount task = new BankAccount();
		task.setBalance(100);

		Thread john = new Thread(task);
		Thread anita = new Thread(task);

		john.setName("John");
		anita.setName("Anita");

		john.start();
		anita.start();
	}

}

class BankAccount implements Runnable {
	private int balance;

	public void setBalance(int amount) {
		this.balance = amount;
	}

	public void run() {
		withdraw(75);
		if (balance < 0)
			System.out.println("Money overdrawn!");
	}

	public void withdraw(int amount) {
		if (balance >= amount) {
			System.out.println(Thread.currentThread().getName() + " is abount to withdraw...");
			balance -= amount;
			System.out.println(Thread.currentThread().getName() + " has withdrawn Rs. " + amount);
		} else
			System.out.println("Sorry not enough balance for " + Thread.currentThread().getName());
	}
}
