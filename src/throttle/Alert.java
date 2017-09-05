package throttle;

public class Alert {
	
  private String message;
  public Alert(String message ){
	  this.message=message;
  }

  public String getMessage(){
	  return this.message;
  }

@Override
public String toString() {
	return "Alert [message=" + message + "]";
}
  
  
}
