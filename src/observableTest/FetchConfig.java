package observableTest;

import java.util.Observable;

public class FetchConfig extends Observable {
     public void change(){
    	 setChanged();
    	 notifyObservers("new config message");
     }
}
