package simpleTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PermutationTest {

	public static String[] getAllLists(String[] elements, int lengthOfList) {
		// initialize our returned list with the number of elements calculated
		// above
		String[] allLists = new String[(int) Math.pow(elements.length, lengthOfList)];

		// lists of length 1 are just the original elements
		if (lengthOfList == 1)
			return elements;
		else {
			// the recursion--get all lists of length 3, length 2, all the way
			// up to 1
			String[] allSublists = getAllLists(elements, lengthOfList - 1);

			// append the sublists to each element
			int arrayIndex = 0;

			for (int i = 0; i < elements.length; i++) {
				for (int j = 0; j < allSublists.length; j++) {
					// add the newly appended combination to the list
					allLists[arrayIndex] = elements[i] + allSublists[j];
					arrayIndex++;
				}
			}
			return allLists;
		}
	}

	public static void test2() {
		String[] database = { "a", "b", "c" };
		for (int i = 1; i <= database.length; i++) {
			String[] result = getAllLists(database, i);
			for (int j = 0; j < result.length; j++) {
				System.out.println(result[j]);
			}
		}
	}
	
	
	private static List<String> results= new ArrayList();
	private static void combineStringFromElements(String[] elements, String currentString, int maxLength) {
		
		if (currentString.length() == maxLength) {
			results.add(currentString);
			//System.out.println(currentString);
			return;
		}
		for (String element : elements) {
			combineStringFromElements(elements, currentString + element, maxLength);
		}
	}

	
	public static void test1() {

		/*
		String[] elements = { "a", "b", "c", "1", "2", "3" };
		int maxLength = 4;
		combineStringFromElements(elements, "", maxLength);
		*/
		
		String[] elements={"1","2","3"};
		int maxLength=7;
		
		
		for(int i=3;i<=maxLength;i++){
			combineStringFromElements(elements, "", i);
		}
		
		
		
		Set<String> sets=new TreeSet();
		results.stream().forEach((s->{
			int sum =0;
			for(int i=0;i<=s.length()-1;i++){
				sum=sum+Integer.parseInt(s.charAt(i)+"");
			}
			
			if(sum==7)
				sets.add(s);
			
		}));
		
		sets.forEach((n)->{
			System.out.println(n);
		});

	}

	
	public static void main(String[] args) {
		test1();

	}

}
