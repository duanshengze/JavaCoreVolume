package multthread.unsynch;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
		private final double[]accounts;
		private Lock lock=new ReentrantLock();
		public Bank(int n,double initialBalance){
			accounts=new double[n];
			for(int i=0;i<accounts.length;i++){
				accounts[i]=initialBalance;
			}
		}
		
	public void  transfer(int from ,int to,double amount){
			lock.lock();
			try{
				if(accounts[from]<amount) return;
				System.out.println(Thread.currentThread());
				
				accounts[from]-=amount;
				System.out.printf("%10.2f from %d to %d",amount,from,to);
				accounts[to]+=amount;
				System.out.printf("Total Balance :%10.2f%n",getTotalBalance());
			}finally{
				lock.unlock();
				
			}

			
		}

		public double getTotalBalance() {
			double sum=0;
			for(double a:accounts){
				sum+=a;
			}
			
			return sum;
		}
		public int size(){
			
			return accounts.length;
		}
		
}
