package throttle;

import java.awt.im.spi.InputMethod;
import java.sql.ResultSetMetaData;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Throttle {
	
	
	public static void main(String[] args) throws InterruptedException {
		DelayQueue<Delayed> throttleItems=new DelayQueue<>();
		throttleItems.put(new ThrottleItem("m1", (long)3*1000));
		System.out.println(throttleItems.size());
		throttleItems.put(new ThrottleItem("m2", (long)3*1000));
		System.out.println(throttleItems.size());
		throttleItems.put(new ThrottleItem("m3", (long)3*1000));
		System.out.println(throttleItems.size());
		
		ThrottleItem item=(ThrottleItem) throttleItems.poll();
		if(item ==null){
			System.out.println("null");
		}
	   
	    
	    //will block in here until take an expire item from the queue
	    System.out.println("here");
		
	}
	
	
	private boolean block=false;
	private double currentThrottleRate=-1;
	private double blockRate=Integer.MAX_VALUE;
	private double secondLevel=1;
	
	private final DelayQueue<Delayed> throttleItems=new DelayQueue<>();
	
	public Throttle(double blockRate,double secondLevel) {
		this.blockRate=blockRate;
		this.secondLevel=secondLevel;
	}
	
	
	public void putItem(String data){
	    
		throttleItems.put(new ThrottleItem(data, (long)secondLevel*1000));
		
	}
	
	
    
   public void resume(){
	   throttleItems.clear();
	   currentThrottleRate=-1;
	   block=false;
   }


	public boolean isBlock() {
		if(currentThrottleRate>blockRate){
			block=true;
		}

		return block;
	}


	

	public double getCurrentThrottleRate() {
		clearExpiredItems();
		currentThrottleRate=throttleItems.size()/secondLevel;
		return currentThrottleRate;
	}
	

	public double getBlockRate() {
		return blockRate;
	}


	public void setBlockRate(double blockRate) {
		this.blockRate = blockRate;
	}
	
	
	public void setSecondLevel(double secondLevel){
		this.secondLevel=secondLevel;
		throttleItems.clear();
		currentThrottleRate=-1;
	}
	
	
	private void clearExpiredItems(){
		int size=throttleItems.size();
		for(int i=1;i<=size;i++){
			//System.out.println("size:"+throttleItems.size());
			throttleItems.poll();
		}
	}
	
}





class ThrottleItem implements Delayed{

	private long entryTime;
	private long aliveUntilTime;
	private String data;
	
	
    public ThrottleItem(String data,long delay) {
    	
		this.entryTime=System.currentTimeMillis();
		this.aliveUntilTime=this.entryTime+delay;
		this.data=data;
	}
	
	@Override
	public int compareTo(Delayed o) {
		
		if(this.aliveUntilTime<((ThrottleItem)o).getAliveUntilTime())
		  return -1;
		if(this.aliveUntilTime>((ThrottleItem)o).getAliveUntilTime())
			return 1;
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long diff=aliveUntilTime-System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	
	public long getAliveUntilTime(){
		return aliveUntilTime;
	}
	
	
	
	public String getData(){
		return data;
	}
	
}


