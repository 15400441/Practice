package devTest;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @author alex.yu
 * @since
 * @version $Name: 1.5 $ - $Revision: 1.10 $ by $Author: thomas.ngai $ on $Date: 2010/02/03 02:01:23 $
 */
public abstract class DateField  {
	
	
	
	
	
	
	

	private String value;
	
    public static final String ZERO_DATE = "00000000";
    public static final String ZERO_TIME = "000000";
    public static final String ZERO_TIMESTAMP = "00000000000000";

//    protected  final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
//    protected  final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HHmmss");
//    protected  final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    private static Set<String> validValues = new HashSet<String>();

    static {

        validValues.add(ZERO_DATE);
        validValues.add(ZERO_TIME);
        validValues.add(ZERO_TIMESTAMP);
    }

    /**
     * Constructs a <code>DateField</code>.
     */
    public DateField() {

        super();
    }

    /**
     * Constructs a <code>DateField</code>.
     * 
     * @param value The value.
     * @throws IllegalArgumentException If the value is invalid.
     */
    public DateField(String value) {

        this();
        setStringValue(value);
    }

    /**
     * Constructs a <code>DateField</code>.
     * 
     * @param value The value.
     * @throws IllegalArgumentException If the value is invalid.
     */
    public DateField(Date value) {

        this();
        setDateValue(value);
    }

    /**
     * Returns the value.
     * 
     * @return The value.
     */
    public final String getStringValue() {

        return ((String) value);
    }

    /**
     * Sets the value.
     * 
     * @param value The value.
     * @throws IllegalArgumentException If the value is invalid.
     */
    protected final void setStringValue(String value) {

        try {

            parseValue(value);
        } catch (ParseException e) {

            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the value.
     * 
     * @return The value.
     */
    public Date getDateValue() {

        try {

            return value==null?null:(this.getFormat().parse((String) value));
        } catch (Exception e) {
        	 e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    /**
     * Sets the value.
     * 
     * @param value The value.
     * @throws IllegalArgumentException If the value is invalid.
     */
    protected void setDateValue(Date value) {

        setValue(this.getFormat().format(value));
    }
    
    void setValue(String value){
    	this.value=value;
    }

    /**
     * Returns the date format for parsing and building string representation.
     * 
     * @return The format of date.
     */
    protected abstract DateFormat getFormat();

    
    protected String buildValue() {

        return (String) value;
    }

   
    protected void parseValue(String source) throws ParseException {

        try {

            validateString(source);
            parseDate(source);
            setValue(source);
        } catch (Exception e) {
        	
        	 e.printStackTrace();

            throw new ParseException("Unparseable date: \"" + source + "\"", 0);
           
        }
    }

   
    public void validate() throws IllegalArgumentException {

        try {

          
        } catch (Exception e) {
            // ignore it and return
            return;
        }
        if (!(value instanceof String)) {

            throw new IllegalArgumentException("Illegal data type in " + ".");
        }
        String v = (String) value;
        validateString(v);

        try {

            parseDate(v);
        } catch (ParseException e) {
        	
            
        }
    }

    /**
     * Validate the String value.
     * 
     * @param s The String value.
     * @throws IllegalArgumentException If the value is invalid.
     * @throws MissingValueException If the value is missing.
     */
    private void validateString(String s) throws IllegalArgumentException {

        try {

           
        } catch (Exception e) {
            // ignore it and return
            return;
        }
        if (validValues.contains(s)) {

            return;
        }
        if (s.length() != ((SimpleDateFormat) getFormat()).toPattern().length()) {

            throw new IllegalArgumentException();
        }
    }

    /**
     * Parse the string to a <code>Date</code>.
     * 
     * @param source The source string to be parsed.
     * @return The parsed Date.
     * @throws ParseException If the source string cannot be parsed.
     */
    private Date parseDate(String source) throws ParseException {
        return this.getFormat().parse(source);
    }
}