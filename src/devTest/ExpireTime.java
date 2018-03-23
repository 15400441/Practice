package devTest;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


// Use in UTC date time representation

/**
 * @author alex.yu
 * @since
 * @version $Name: 1.4 $ - $Revision: 1.7 $ by $Author: jeff.tseung $ on $Date: 2015/09/30 03:18:08 $
 */
public class ExpireTime extends DateField{
	
	public static void main(String agrs [])
	{
		new ExpireTime("20170212102332");
	}
	
	
	protected  SimpleDateFormat EXPIRE_TIME_FORMAT;

	public ExpireTime() {
		super();
	}

	public ExpireTime(String value) {
		super(value);
	}
	

	public ExpireTime(Date value) {
		super(value);
	}
	

    


	@Override
	protected DateFormat getFormat() {
		if (EXPIRE_TIME_FORMAT == null)
			EXPIRE_TIME_FORMAT= new SimpleDateFormat("yyyyMMddHHmmss");
        return EXPIRE_TIME_FORMAT;
    }
}
