package observableTest;

import java.util.Observable;
import java.util.Observer;

public class ApplyConfig implements Observer {
	private String config;
	//private Observable observable;
	public ApplyConfig(String config) {
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
		
		ApplyConfig applyConfig=new ApplyConfig("init config");
		
		
		FetchConfig fetchConfig=new FetchConfig();
		fetchConfig.addObserver(applyConfig);
		
		fetchConfig.change();
		
		
	}

}
