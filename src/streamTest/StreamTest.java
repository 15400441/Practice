package streamTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;




public class StreamTest {
	
	
	
	public static void main(String[] args) {
		List<Fruit> fruits=Arrays.asList(new Fruit("apple", "red"),new Fruit("orange", "yellow"),new Fruit("lemon", "red"));
		
		List<Fruit> redFruit=fruits.stream().filter(fruit->fruit.getColor()=="red").limit(2).collect(Collectors.toList());
		
		Boolean allRed=fruits.stream().allMatch(fruit->fruit.getColor()=="red");
		
		List<String> allColors=fruits.stream().map(fruit->fruit.getColor()).collect(Collectors.toList());
		
		String combinFruit=fruits.stream().map(fruit->fruit.getName()).reduce("", (a,b)->a+b+"_");
		
		
		
		System.out.println(redFruit);
		System.out.println(allRed);
		System.out.println(allColors);
		System.out.println(combinFruit);
		
	}

}



class Fruit{
	private String name;
	private String color;
	
	Fruit(String name,String color){
		this.name=name;
		this.color=color;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}


	@Override
	public String toString() {
		return "Fruit [name=" + name + ", color=" + color + "]";
	}
	

	
}
