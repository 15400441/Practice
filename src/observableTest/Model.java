package observableTest;

import java.util.Observable;

public class Model extends Observable {
     public void change(){
    	 setChanged();
    	 notifyObservers("new config message");
     }
}
