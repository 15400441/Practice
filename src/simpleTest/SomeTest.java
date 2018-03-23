package simpleTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SomeTest {

	public static void mathTest() {

		System.out.println(Math.pow(2, 3));
		System.out.println(Math.pow(3, 3));

		System.out.println(Math.pow(27, 1.0 / 3));

		System.out.println(Math.log(8) / Math.log(2));

		System.out.println(Math.round(2.3));
		System.out.println(Math.round(2.6));
		System.out.println(Math.ceil(2.6));
		System.out.println(Math.ceil(2.3));

		System.out.println(Math.floor(2.3));
		System.out.println(Math.floor(2.9));

		System.out.println(Math.exp(2));

	}

	public static void randomTest() {

		Random rd = new Random();

		int i = 0;
		while (true) {
			if (i++ > 100)
				break;
			System.out.print(rd.nextInt(3));
		}

	}

	public static void automaticTest() {

		AtomicInteger i = new AtomicInteger(1);

		// get first and then add
		System.out.println(i.getAndIncrement());

		// add first and then get
		System.out.println(i.incrementAndGet());

		System.out.println(i.addAndGet(5));

		System.out.println(i.getAndAdd(5));

	}

	public static void arraysTest() {
		int[] ints = new int[20];

		Arrays.fill(ints, 0);

		System.out.println(Arrays.binarySearch(ints, 1));

		String[] names = { "james", "dylan", "jeff", "apple" };
		Arrays.sort(names);

		System.out.println(Arrays.asList(names));

		System.out.println(Arrays.binarySearch(names, "jeff"));
		System.out.println(Arrays.binarySearch(names, "apple"));
		System.out.println(Arrays.binarySearch(names, "james"));

	}

	public static void collectionsTest() {
		List<String> as = Arrays.asList("a,b,c,d".split(","));

		// error,can not change size
		// as.add("e");

		as = new ArrayList<String>(as);
		as.add("e");
		System.out.println(as);

		// use functional interface
		as.sort(Collections.reverseOrder());

		System.out.println(as);

		// TimeUnit.MILLISECONDS.sleep(1000);

		// use utils
		Collections.reverse(as);

		System.out.println(as);
	}

	public static void charsetTest() throws UnsupportedEncodingException {

		String s = "中文";
		
		
		

		String v = new String(s.getBytes(), "utf-8");

		String x = new String(v.getBytes(), "ISO-8859-1");
		String x1 = new String(v.getBytes(), "utf-8");

		System.out.println(s);
		System.out.println(v);
		System.out.println(x);
		System.out.println(x1);
	}
	
	
	public static void transferCharset() throws IOException{
		
		String s = "中文";
		String v = new String(s.getBytes(), "utf-8");
		
		//transfer utf-8 to iso-8859-1
		//since console use utf-8 to print out the content, it will cause unreadable code
		String value=new String(v.getBytes("utf-8"),"ISO_8859_1");
		
		String t=new String("汉字".getBytes(),"ISO-8859-1");
		
		
		Path path=Paths.get("test.txt");
		Path path1=Paths.get("test1.txt");
		Path path1_1=Paths.get("test1_1.txt");
		Path path2=Paths.get("test2.txt");
		
		Files.write(path,v.getBytes(),StandardOpenOption.CREATE);
		Files.write(path1,Collections.singletonList(value),StandardCharsets.ISO_8859_1,StandardOpenOption.CREATE);
		Files.write(path1_1,Collections.singletonList(s),StandardCharsets.UTF_8,StandardOpenOption.CREATE);
		Files.write(path2,Collections.singletonList(t),StandardCharsets.ISO_8859_1,StandardOpenOption.CREATE);
		
		
		
		System.out.println(v);
		System.out.println(value);
		System.out.println(t);
		
		
	}
	
	
	 public static void rotate(int[] nums, int k) {
		  
	     
	    }

	public static void main(String[] args) {

		try {
			
			
			
			//transferCharset();
			// mathTest();

			// randomTest();

			// automaticTest();

			// arraysTest();

			// collectionsTest();
			charsetTest();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

		}

	}

}
