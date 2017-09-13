package nioTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class FileManager2 {
	
	
  public static void main(String args[])
  {
	  /*
	  Path path=Paths.get("log/stdout.txt");
	  try {
		match(path,".*39=8.*");
	} catch (IOException e) {
		
		e.printStackTrace();
	}*/
	  
	  //testCopy1();
	  testCopy2();
	  
	  
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
  
  
  
  
  public static void testCopy1(){
	  Path from=Paths.get("log/stdout.txt");
	  Path to=Paths.get("log/stdoutCopy1.txt");
	  copy1(from,to);
  }
  
  public static void testCopy2(){
	  Path from=Paths.get("log/stdout.txt");
	  Path to=Paths.get("log/stdoutCopy2.txt");
	  copy2(from,to);
  }
  
  
  public static void copy1(Path from, Path to){
	  long startTime=System.nanoTime();
	  try(BufferedReader reader=Files.newBufferedReader(from);BufferedWriter writer=Files.newBufferedWriter(to);){
		 String content=null; 
		 while((content=reader.readLine())!=null){
			 writer.write(content,0,content.length());
			 writer.newLine();
		 }
		  
	  }catch(IOException e){
		  e.printStackTrace();
	  }
	  
	  System.out.println("test copy1 finished, consuming time:"+ ((System.nanoTime()-startTime))/1000000);
	  
  }
  
  
  
  public static void copy2(Path from, Path to){
	 long startTime=System.nanoTime();
	 final ByteBuffer buffer =ByteBuffer.allocate(2*1024);
	 try( ReadableByteChannel rc=Files.newByteChannel(from,StandardOpenOption.READ);WritableByteChannel wc=Files.newByteChannel(to,StandardOpenOption.CREATE,StandardOpenOption.WRITE)){
		 
		 while(rc.read(buffer)!=-1){
			 buffer.flip();
			 wc.write(buffer);
			 buffer.compact();
		 }
		 
		 buffer.flip();
		 while(buffer.hasRemaining()){
			 wc.write(buffer);
		 }
		 
		 
	 }catch(IOException e){
		 e.printStackTrace();
	 }
	  
	  
	  System.out.println("test copy2 finished, consuming time:"+ ((System.nanoTime()-startTime))/1000000);
	  
  }
  

}
