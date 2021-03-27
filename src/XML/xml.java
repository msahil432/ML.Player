package XML;

import Skeleton.myxml;
/**
 * 
 * <b>ML.Player - XML Parser for Settings</b>
 * <p> - It is a singleton class which is used to manage root.xml file
 * 
 * @version 0.2c 
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Formal Documentation added
 * <p> - Better support for opening custom location files 
 * <p> - {@link #finalize()} moved to {@link Skeleton.myxml} (Super) class.
 * 
 * @author msahil432
 *
 */
public class xml extends myxml
{
	/** the only object of the class*/
    public static xml x1;
    
    /**
     * Constructor to open xml file in default location.
     * <p> It calls {@link #xml(String)} while providing default filepath as String argument
     */
    protected xml()
    {
        this("data\\db\\root.xml");
    }
    
    /**
     * Constructor to use while opening custom location files
     *  
     * @param filePath the path to the file to be parsed
     */
    protected xml(String filePath)
    {
        super(filePath);
        this.set("resize", "false");
    }
    
    /**
     * Used when performing operations on default location file
     * @return x1,the only created object of this class
     */
    public static xml getInstance()
    {
    	if(x1==null)
    		x1= new xml();
        return x1;
    }
    
    /**
     * Used when performing operations on custom location file
     * @param filepath filePath the path to the file to be parsed 
     * @return x1,the only created object of this class
     */
    public static xml getInstance(String filepath)
    {
    	if(x1==null)
    		x1= new xml(filepath);
    	return x1;
    }
    /**
     * Resets the xml file to default values
     * 
     * @param s provides info about from where the reset was triggered, O/P only to cmd/terminal
     */
    @Override
    public void reset(String s)
    {
    	super.set("size", "2");
    	super.set("configured", "0");
    	super.set("resize","f");
    	super.reset(s);
    }
}