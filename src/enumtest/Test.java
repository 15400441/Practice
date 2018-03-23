package enumtest;

public class Test {
	
	public static void main(String [] args){
		System.out.println(Direction.NORTH);
		
		System.out.println(Direction2.SOURCE.name());
		
		System.out.println(Direction2.SOURCE.name()+":"+Direction2.SOURCE.value());
	}

}
