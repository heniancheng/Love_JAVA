package javacore.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 采用ReentrantLock锁对互斥变量取锁/解锁
 * 另外还有一个读写锁ReentrantReadWriteLock
 * 通过该锁的方法readlock() writelock()可以获得不同的锁对象
 */

public class LockSynchBankTest {
	
	public static final double INITIAL_BALANCE = 5000;
	public static final double MOVE_BALANCE = 500;
	public static final int NACCOUNTS = 10;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankI b = new BankI(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for(i = 0; i < NACCOUNTS; i++){
			TransferRunnableI r = new TransferRunnableI(b, i, MOVE_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}

}

class BankI{
	
	private final double[] accounts;
	private Lock banLock = new ReentrantLock();
	
	public BankI(int n, double initialBalance){
		accounts = new double[n];
		for(int i = 0; i < n; i++){
			accounts[i] = initialBalance;
		}
	}
	
	public void transfer(int from, int to, double amount){
		banLock.lock();
		try{
			if(accounts[from] < amount) return ;
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf(" Total balance: %10.2f%n", getTotalBalance());
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

class TransferRunnableI implements Runnable{

	private BankI bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
	
	public TransferRunnableI(BankI bank, int fromAccount, double maxAmount) {
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
