package stringTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringTest {
	
	
	public static void main(String[] args) {
		String s1="ab";
		String s2="a";
		
		String exchange="HK";
		String currency=null;
		String result=String.format("More than one record for same isin code is found. but since no exchange or currency information provided in FIX Msg, order will be rejected. exchange=<%s> currency=<%s> ",exchange,currency);
		System.out.println(result);
		
		
		String string="agb	ggg";
		System.out.println(Arrays.asList(string.split("[b]")));
		
		
		
		
		
		s1.toUpperCase();
		
		
	    
		
		System.out.println(s1);
		
		
		String s3=new String("ab");
		String s4=new String("ab");
		
		//s3=new String("c");
		
		
		System.out.println(s1==s2);
		
		System.out.println(s1.equals(s2));
		
		System.out.println("s3==s4:"+ (s3==s4));
		
		System.out.println(s3.equals(s1));
		
		
		System.out.println(s1==s3);
		System.out.println(s1.equals(s3));
		
		new StringTest().test("a");
		
		
		testTrim();
		
	}
	
	
	
	void test(String a){
		System.out.println("a".equals(a));
		System.out.println("a"==a);
		
		
		String c="c";
		String c1="c";
		System.out.println(c==c1);
	}
	
	
	
	static void testTrim(){
		String a="    abc     ";
		System.out.println(a.length());
		System.out.println(a.trim().length());
		
		
	}
	
	

}
