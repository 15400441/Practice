package ioTest;

import java.io.*;

public class FileManager {
	
	FileManager(){
		
	}
	
	
	public static void main(String[] args) throws IOException{
		//copyTo("log/test.txt","log/copyTo.txt");
		
		match("log/test.txt","\\*.*");
	}
	
	
	
	/**
	 * 
	 * @Author: Dylan Duan 
	 * @Description: copy file from one path to another
	 * @param @param fromPath
	 * @param @param toPath    
	 * @return void    
	 * @throws IOException 
	 * @date 11 Sep 2017 14:19:07
	 */
	public static void copyTo(String fromPath,String toPath) throws IOException{
		
		InputStream is=null;
		BufferedInputStream bi=null;
		OutputStream os=null;
		BufferedOutputStream bo=null;
		
		try{
		
		File file=new File(fromPath);
		 is=new FileInputStream(file);
		 bi=new BufferedInputStream(is);
		
		
		 os=new FileOutputStream(new File(toPath));
		 bo=new BufferedOutputStream(os);
		
		int b;
		while( (b= bi.read()) !=-1){
			bo.write(b);
		}
		
		//flush before close
		bo.flush();
		
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			is.close();
			bi.close();
			os.close();
			bo.close();
			
		}
		
		System.out.println("finished:"+fromPath +"=>"+toPath);
		
		
	}
	
	
	
	public static void match(String fromPath,String regex) throws IOException{
		
		Reader reader=null;
		BufferedReader br=null;
		int lineNum=0;
		
		System.out.println("find:"+regex);
		try{
			reader=new FileReader(new File(fromPath));
			br=new BufferedReader(reader);
			
			String line;
			while((line=br.readLine())!=null){
				lineNum++;
				if(line.matches(regex))
					System.out.println(lineNum+"  "+line);
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			reader.close();
			br.close();
		}
		
	}

}
