package enumtest;

public enum Direction {
	
	SOURCE,
	NORTH,
	EAST,
	WEST;
	
}


enum Direction2{
	
	SOURCE("SOURCE=>");
	
	private String value;
	Direction2(String value){
		this.value=value;
	}
	
	public String value(){
		return this.value;
	}
	
	
}
