package threadTest;

public class CalcPI2 {
	public static void main(String[] args) throws InterruptedException {
		
		MyThread myThread=new MyThread();
		myThread.start();
		
		while(myThread.isAlive()){
		  Thread.sleep(1);
		}
		
		
		System.out.println(myThread.getPI());
		
	}
	
	

}


class MyThread extends Thread
{
   boolean negative = true;
   double pi; // Initializes to 0.0, by default
   public void run ()
   {
      for (int i = 3; i < 100000; i += 2)
      {
           if (negative)
               pi -= (1.0 / i);
           else
               pi += (1.0 / i);
           negative = !negative;
      }
      pi += 1.0;
      pi *= 4.0;
      System.out.println ("Finished calculating PI");
   }
   
   double getPI(){
	   return this.pi;
   }
}