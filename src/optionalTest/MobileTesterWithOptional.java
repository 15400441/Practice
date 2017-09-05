package optionalTest;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.Optional;

public class MobileTesterWithOptional {
	 public static void main(String[] args) {

			ScreenResolution2 resolution = new ScreenResolution2(750,1334);
			DisplayFeatures2 dfeatures = new DisplayFeatures2("4.7", Optional.of(resolution));
			Mobile2 mobile = new Mobile2(2015001, "Apple", "iPhone 6s", Optional.of(dfeatures));
			Mobile2Service mService = new Mobile2Service();
			int mobileWidth = mService.getMobile2ScreenWidth(Optional.of(mobile));
			System.out.println("Apple iPhone 6s Screen Width = " + mobileWidth);

			ScreenResolution2 resolution2 = new ScreenResolution2(10,0);
			DisplayFeatures2 dfeatures2 =null;// new DisplayFeatures2("0", Optional.of(resolution2));
			Mobile2 mobile2 = new Mobile2(2015001, "Apple", "iPhone 6s", Optional.ofNullable(dfeatures2));
			int mobileWidth2 = mService.getMobile2ScreenWidth(Optional.ofNullable(null));
			System.out.println("Apple iPhone 16s Screen Width = " + mobileWidth2);

		}
	

}



class Mobile2Service {
	 public Integer getMobile2ScreenWidth(Optional<Mobile2> mobile){

		return mobile.flatMap(Mobile2::getDisplayFeatures2).flatMap(DisplayFeatures2::getResolution).map(ScreenResolution2::getWidth).orElse(0);
			
}
}


class ScreenResolution2{
	private int width;
	private int height;

	public ScreenResolution2(int width, int height){
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	
}



class DisplayFeatures2 {
	private String size; // In inches
	private Optional<ScreenResolution2> resolution;

	public DisplayFeatures2(String size, Optional<ScreenResolution2> resolution){
		this.size = size;
		this.resolution = resolution;
	}

	public String getSize() {
		return size;
	}
	public Optional<ScreenResolution2> getResolution() {
		return resolution;
	}
	
}


class Mobile2 {
	private long id;
	private String brand;
	private String name;
	private Optional<DisplayFeatures2> displayFeatures;
	// Likewise we can see Memory Features, Camera Features etc.

	public Mobile2(long id, String brand, String name,
			Optional<DisplayFeatures2> displayFeatures){
		this.id = id;
		this.brand = brand;
		this.name = name;
		this.displayFeatures = displayFeatures;
	}

	public long getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public String getName() {
		return name;
	}

	public Optional<DisplayFeatures2> getDisplayFeatures2() {
		return displayFeatures;
	}

	
}

