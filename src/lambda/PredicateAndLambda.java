package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateAndLambda {
	
	
	
	public static void main(String[] args) {
		List<Box> inventory = Arrays.asList(new Box(80, "green"), new Box(
		        155, "green"), new Box(120, "red"));
		
		List<Box> greenApples=filter(inventory, PredicateAndLambda::isGreenApple);
		
		List<Box> greenApples2=filter(inventory, (Box a)->"green".equals(a.getColor()));
		
		System.out.println(greenApples);
		System.out.println(greenApples2);
		
	}
	
	
	public static boolean isGreenApple(Box apple) {
	    return "green".equals(apple.getColor());
	  }

	  public static boolean isHeavyApple(Box apple) {
	    return apple.getWeight() > 150;
	  }

	  public static List<Box> filter(List<Box> inventory,
	      Predicate<Box> p) {
	    List<Box> result = new ArrayList<>();
	    
	    inventory.forEach((apple)->{
	    	if(p.test(apple))
	    		result.add(apple);
	    	
	    });
	    
	    return result;
	  }
   

}

class Box {
	  private int weight = 0;
	  private String color = "";

	  public Box(int weight, String color) {
	    this.weight = weight;
	    this.color = color;
	  }

	  public Integer getWeight() {
	    return weight;
	  }

	  public void setWeight(Integer weight) {
	    this.weight = weight;
	  }

	  public String getColor() {
	    return color;
	  }

	  public void setColor(String color) {
	    this.color = color;
	  }

	  public String toString() {
	    return "Apple{" + "color='" + color + '\'' + ", weight=" + weight + '}';
	  }
	}
