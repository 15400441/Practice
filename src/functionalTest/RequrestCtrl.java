package functionalTest;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public class RequrestCtrl {
	
	private String b="B ";
			
	public static void main(String[] args) throws InterruptedException {
		new RequrestCtrl().handleRequest("www.com");
	}
	
	public void handleRequest(String url) throws InterruptedException{
		
		String base="Base ";
		new RequestHandler().handle(url, (t)->{
			String result= b+t;
			System.out.println(result);
			
		});
	    
		
	}

}


class RequestHandler{
	public void handle(String url,Consumer<String> done) throws InterruptedException{
		System.out.println("handle:"+url);
		System.out.println("time consuming job");
		TimeUnit.SECONDS.sleep(1);
		
		done.accept("handle first time");
		
		done.accept("handle second time");
		
		
	}
}

