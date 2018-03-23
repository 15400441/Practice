package stringTest;

public class WildCardTest {
	
	public static void main(String[] args) {
		String str="abc.com";
		
		String str1="aaa";
		
		System.out.println(str.matches("^a*"));
		System.out.println(str1.matches("^a*"));
	}

}
