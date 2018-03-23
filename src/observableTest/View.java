package observableTest;

import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
	private String config;
	//private Observable observable;
	public View(String config) {
		//this.observable=o;
		this.config=config;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		updateConfig((String)arg);
	}
	
	
	public void updateConfig(String config){
		System.out.println("config changed:"+this.config+"=>"+config);
		this.config=config;
	}
	
	
	
	
	public static void main(String[] args) {
		
		View view=new View("init config");
		
		
		Model model=new Model();
		model.addObserver(view);
		
		model.change();
		
		
	}

}
