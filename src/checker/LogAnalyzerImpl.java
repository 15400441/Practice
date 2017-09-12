package checker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

public class LogAnalyzerImpl implements LogAnalyzer {

	
	//analyze log according different strategy
	
	/**
	 * 
	 * @Author: Dylan Duan 
	 * @Description: TODO 
	 * @param @param path
	 * @param @param regex
	 * @param @throws IOException    
	 * @return void    
	 * @date 12 Sep 2017 18:02:25
	 */
	public static void match(Path path,String regex) throws IOException{
		  
		  AtomicInteger lineNum=new AtomicInteger(0);
		  
		  System.out.println("file:"+path.getFileName());
		  
		  Files.lines(path).forEach(line->{
			 lineNum.getAndIncrement();
			  if(line.matches(regex))
				  System.out.println(lineNum.get()+" "+ line);
		  });
		  
		  System.out.println("finished-------------------");
	  }
	
	
	
	
	//find reject (clientOrderID)
}
