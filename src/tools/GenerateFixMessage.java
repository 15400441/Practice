package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateFixMessage {
	private String template;
	private String sender;
	private String target;
	private String tag57;
	private String tag115;
	private String startClientOrdID;
	private int seqNum;
	
	
	
	public static void main(String[] args) {
		String template="8=FIX.4.2 | 9=154 | 35=D | 34= | 49=DYLAN | 52=20170822-09:39:12.144 | 56=MFTST | 11=test | 15=HKD | 21=3 | 38=1000 | 40=2 | 44=13.3 | 54=1 | 55=51 | 60=20170822-09:39:12.144 | 207=HK | 10=009 |";
		GenerateFixMessage generateFixMessage=new GenerateFixMessage(template, "", "", "", "", "001");
		Map<Integer, Integer> numDelayMap=new HashMap();
		//numDelayMap.put(99, 1000);
		numDelayMap.put(1350, 1000);
		
		
		
		try {
			generateFixMessage.gen(numDelayMap);
			System.out.println("finished------------seqNum:"+generateFixMessage.seqNum);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	public GenerateFixMessage(String template,String sender,String target,String tag57, String tag115,String startClientOrdID) {
		this.template=template;
		this.sender=sender;
		this.target=target;
		this.tag57=tag57;
		this.tag115=tag115;
		this.startClientOrdID=startClientOrdID;
		this.seqNum=1;
	}
	
	
	
	public   void gen(Map<Integer, Integer> numDelayMap) throws UnsupportedEncodingException, IOException{
		File file=new File("message.fix");
		BufferedWriter bWriter=new BufferedWriter(new FileWriter(file));
		
		
			
			
		numDelayMap.forEach((num,delay)->{
			
			try{
			for(int i=0;i<=num-1;i++){
				
			    String str=template.replaceFirst("11=", "11="+startClientOrdID+i).replaceFirst("34=", "34="+(seqNum++));
				bWriter.write(str);
				bWriter.newLine();
			   
			}
			
			bWriter.newLine();
			bWriter.write(delay+"");
			bWriter.newLine();
			}catch (Exception e) {
				
			}
			
		});
		
		
		bWriter.flush();
		bWriter.close();
		
		
	}
	
	
	
	public void transfer(){
		
	}
	
	
	

}


