package functionalTest;

import java.util.function.Supplier;

public class SimpleTest {
	
	
	public static void main(String [] args){
		
		
		Supplier<String> test=()->{
			return "apple";
		};
		
		
		test.get();
		
	}

}
