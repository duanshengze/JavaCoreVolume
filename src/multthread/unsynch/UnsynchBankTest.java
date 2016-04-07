package multthread.unsynch;

public class UnsynchBankTest {
	public static final int NACCOUNT=100;
	public static final double INTIAL_BALANCE=1000;
	public static void main(String[] args) {
		 Bank b=new Bank(NACCOUNT, INTIAL_BALANCE);
		 int i;
		 for(i=0;i<NACCOUNT;i++){
			 TransferRunnable r=new TransferRunnable(b, i
			 , INTIAL_BALANCE);
			 new Thread(r).start();
			 
		 }
	}
}
