package lambda;


import java.util.Arrays;
import java.util.List;

/*
 * use to replace anonymous class
 * 
 * */


public class SimpleTest {
	
	public static void main(String[] args) {
		System.out.println("main fun");
		
		new Thread(new RunnableTest()).start();
		
		new ThreadTest().start();
		
		new Thread(()->{System.out.println("new version=>thread run");}).start();
		
		new ReplaceForeach().run();
	}

}





class RunnableTest implements Runnable{
	public void run() {
	   System.out.println("old version=>implements runnable=>thread run");
		
	}
	
}


class ThreadTest extends Thread{
	
	public void run(){
		System.out.println("old version=>extends thread=>thread run");
	}
	
}



class ReplaceForeach{
	public void run(){
		System.out.println("replace for each");
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		languages.forEach((item)->{
			System.out.println(item);
		});
		
	}
	
	
}