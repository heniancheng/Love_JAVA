package javacore.thread;


/*
 * 采用synchronized修改方法，从而同步方法中的代码块
 * 采用wait/notifyAll 方法达到条件阻塞的目的
 */

public class SynchronizedBankTest {
	
	public static final double INITIAL_BALANCE = 0;
	public static final double MOVE_BALANCE = 500;
	public static final int NACCOUNTS = 10;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankII b = new BankII(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for(i = 0; i < NACCOUNTS; i++){
			TransferRunnableIII r = new TransferRunnableIII(b, i, MOVE_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}

}

class BankIII{
	
	private final double[] accounts;
	
	public BankIII(int n, double initialBalance){
		accounts = new double[n];
		for(int i = 0; i < n; i++){
			accounts[i] = initialBalance;
			if(i == 9)accounts[i] = 1000;
		}
	}
	
	public synchronized void transfer(int from, int to, double amount)
			throws InterruptedException {
		while (accounts[from] < amount) {
			wait();
		}
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf(" Total balance: %10.2f%n", getTotalBalance());
		notifyAll();

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

class TransferRunnableIII implements Runnable{

	private BankII bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
	
	public TransferRunnableIII(BankII bank, int fromAccount, double maxAmount) {
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
