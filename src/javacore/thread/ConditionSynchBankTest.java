package javacore.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 采用条件对象对不满足条件的情况进行阻塞（首先是要有个锁）
 * 重新进入运行状态时，它将先再次获得锁并从阻塞的地方继续进行，
 * 一般放于循环中再次判断条件是否满足
 */

public class ConditionSynchBankTest {
	
	public static final double INITIAL_BALANCE = 0;
	public static final double MOVE_BALANCE = 500;
	public static final int NACCOUNTS = 10;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankII b = new BankII(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for(i = 0; i < NACCOUNTS; i++){
			TransferRunnableII r = new TransferRunnableII(b, i, MOVE_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}

}

class BankII{
	
	private final double[] accounts;
	private Lock banLock = new ReentrantLock();
	private Condition banCondition = banLock.newCondition();
	
	public BankII(int n, double initialBalance){
		accounts = new double[n];
		for(int i = 0; i < n; i++){
			accounts[i] = initialBalance;
			if(i == 9)accounts[i] = 1000;
		}
	}
	
	public void transfer(int from, int to, double amount) throws InterruptedException{
		banLock.lock();
		try{
			while(accounts[from] < amount){
				banCondition.await();
			}
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf(" Total balance: %10.2f%n", getTotalBalance());
			banCondition.signalAll();
		}finally{
			banLock.unlock();
		}
	
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

class TransferRunnableII implements Runnable{

	private BankII bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
	
	public TransferRunnableII(BankII bank, int fromAccount, double maxAmount) {
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
