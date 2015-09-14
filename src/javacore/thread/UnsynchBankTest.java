package javacore.thread;

/*
 * 没同步时，对于往同一个账户转账时执行
 * accounts[from] -= amount 或者 accounts[to] += amount;
 * 由于非原子操作，一个线程的更新可能覆盖另一个线程的更新。
 */

public class UnsynchBankTest {
	
	public static final double INITIAL_BALANCE = 5000;
	public static final double MOVE_BALANCE = 500;
	public static final int NACCOUNTS = 10;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for(i = 0; i < NACCOUNTS; i++){
			TransferRunnable r = new TransferRunnable(b, i, MOVE_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}

}

class Bank{
	
	private final double[] accounts;
	
	public Bank(int n, double initialBalance){
		accounts = new double[n];
		for(int i = 0; i < n; i++){
			accounts[i] = initialBalance;
		}
	}
	
	public void transfer(int from, int to, double amount){
		if(accounts[from] < amount) return ;
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf(" Total balance: %10.2f%n", getTotalBalance());
		
		
	}

	private double getTotalBalance() {
		// TODO Auto-generated method stub
		double sum = 0;
		
		for(double a : accounts){
			sum += a;
		}
		
		return sum;
	}
	
	public int size(){
		return accounts.length;
	}
	
}

class TransferRunnable implements Runnable{

	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
	
	public TransferRunnable(Bank bank, int fromAccount, double maxAmount) {
		this.bank = bank;
		this.fromAccount = fromAccount;
		this.maxAmount = maxAmount;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				int toAccount = (int) (bank.size() * Math.random()); //随机的往其他用户转账
				double amount = this.maxAmount;
				bank.transfer(this.fromAccount, toAccount, amount);
				Thread.sleep((int) (this.DELAY * Math.random())); 
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}
