package stringTest;

public class ReverseLongString {
	
	public static void main(String[] args) {
		long beginTime,elapsedTime;
		
		//Build a long string
		int size=15000;
		ReverseLongString obj=new ReverseLongString();
		beginTime=System.nanoTime();
		String longStr=obj.buildString(size);
		elapsedTime=System.nanoTime()-beginTime;
		System.out.println("build str cost:"+elapsedTime);
		
		//reverse string by building another string 
		String reverseStr="";
		beginTime=System.nanoTime();
		for(int pos=longStr.length()-1;pos>=0;pos--){
			reverseStr=reverseStr+longStr.charAt(pos);
		}
		elapsedTime=System.nanoTime()-beginTime;
		System.out.println("reverse by building another string cost:"+elapsedTime);
		
		
		//reverse string by StringBuilder
		beginTime=System.nanoTime();
		StringBuilder reverseStringBuilder=new StringBuilder("");
		for(int pos=longStr.length()-1;pos>=0;pos--){
			reverseStringBuilder.append(longStr.charAt(pos));
		}
		elapsedTime=System.nanoTime()-beginTime;
		System.out.println("reverse by StringBuilder cost:"+elapsedTime);
		
		//reverse string by the reverse() method of StringBuilder
		beginTime=System.nanoTime();
		StringBuilder stringBuilder=new StringBuilder(longStr);
		stringBuilder.reverse();
		elapsedTime=System.nanoTime()-beginTime;
		System.out.println("reverse by reverse() method cost:"+elapsedTime);
		
	}
	
	
	
	
	
	String buildString(long size){
		String str="";
		char ch='a';
		for(int i=0;i<=size;i++){
			str += ch;
			++ch;
			if(ch>'z'){
				ch='a';
			}
		}
		
		return str;
	}

}
