package functionalTest;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TradeAccounting {
	
	
	public static void main(String[] args) {
		List<Trade> trades=new LinkedList<>();
		
	    for(int i=0;i<=9;i++){
	    	if(i%2==0){
	    		trades.add(new Trade("new",i,"order"));
	    	}else{
	    		trades.add(new Trade("filled",i,"order"));
	    	}
	    		
	    	
	    }
	    
	    ITTrade findNewTrade=(t)->{ return t.getStatus().equals("new"); };
	    
	    List<Trade> newTrades=new TradeAccounting().filterTrades(findNewTrade, trades);
	    System.out.println(newTrades);
	}
	
	
	
	
	public List<Trade> filterTrades(ITTrade filter,List<Trade> trades)
	{
		List<Trade> result=new LinkedList<>();
		result=trades.stream().filter((t)->{return filter.check(t);}).collect(Collectors.toList());
		return result;
	}
	
	

}


class Trade{
	private String status;
	private int id;
	private String type;
	
	public Trade() {
		
	}

	public Trade(String status, int id, String type) {
		super();
		this.status = status;
		this.id = id;
		this.type = type;
	}
	
	
	@Override
	public String toString() {
		
		return "Trade:"+id+" => status="+this.status+" type="+type;
	}
	
	
	public String getStatus(){
		return status;
	}
	
	
	
}
