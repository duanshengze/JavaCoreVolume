package multthread.unsynch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
		
		private final double[]accounts;
		private Lock lock=new ReentrantLock();
		
		private Condition sufficientFunds;
		public Bank(int n,double initialBalance){
			accounts=new double[n];
			sufficientFunds=lock.newCondition();
			for(int i=0;i<accounts.length;i++){
				accounts[i]=initialBalance;
			}
		}
		
	public void  transfer(int from ,int to,double amount) throws InterruptedException{
			lock.lock();
			try{
				while (accounts[from]<amount) sufficientFunds.await();;
				System.out.println(Thread.currentThread());
				accounts[from]-=amount;
				System.out.printf("%10.2f from %d to %d",amount,from,to);
				accounts[to]+=amount;
				System.out.printf("Total Balance :%10.2f%n",getTotalBalance());
				sufficientFunds.signalAll();
			}finally{
				lock.unlock();
				
			}

			
		}

		public double getTotalBalance() {
			try{
				lock.lock();
				double sum=0;
				for(double a:accounts){
					sum+=a;
				}
				return sum;	
			}finally{
				lock.unlock();
			}
			
		}
		public int size(){
			
			return accounts.length;
		}
		
}
