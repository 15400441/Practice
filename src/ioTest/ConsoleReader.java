package ioTest;

import java.io.Console;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleReader {
	
	
	public static void main(String[] args)
	{
		new ConsoleReader().changePwd();
	}
	
	
	
	public void changePwd(){
		/*
		Console c=System.console();
		
		if(c==null){
			System.out.println("no console, will exit");
			return;
		}
		
		*/
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter login");
		String login= sc.nextLine();
		
		System.out.println("Enter pwd");
		String pwd=sc.nextLine();
		
		
		if(verify(login,pwd)){
			boolean match;
			
			do{
			System.out.println("Enter your new pwd:");
			String newPwd=sc.nextLine();
			
			System.out.println("Enter your new pwd again:");
			
			String newPwdAgain=sc.nextLine();
			
			match=newPwd.equals(newPwdAgain);
			
			if(!match){
				System.out.println(String.format("pwd do not match,Try again. %n"));
				
			}else{
				System.out.println(String.format("pwd for %s has changed.%n",login));
			
			}
			
			}while(!match);
			
		}
		
		
	}
	
	
	private boolean verify(String login,String pwd){
		return true;
		
	}

}
