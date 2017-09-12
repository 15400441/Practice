package nioTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class FileManager2 {
	
	
  public static void main(String args[])
  {
	  Path path=Paths.get("log/stdout.txt");
	  try {
		match(path,".*39=8.*");
	} catch (IOException e) {
		
		e.printStackTrace();
	}
  }
	
 	
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

}
