import Core.GUI;
/**
 *
 *<b>	ML.Player - Main Class </b>
 *<p> - Contains main method, and so it is the entry point of program
 *<p> - No object of class created
 *
 * @version 0.0.2 , 0.2c of Class
 * <p><b> Change Log: 0.0.1 -> 0.0.2</b>
 * <p> - Added Formal Documentation for whole project
 * <p> - <code>finalize()</code> method tweaks in most of Classes
 * <p> - Some tweaks in various classes too but no major changes, see class-wise change log.
 * 
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c </b>
 * <p> - Documentation added for the class
 * <p> - Overridden Finalize method wasn't needed, as of no object creation.  
 * 
 * @author msahil432
 */
public class ML_Player
{
	private static GUI obj;
	
	/**
	 * 
	 * Creates object 'obj' of {@link Core.GUI} created and {@link Core.GUI#start()} method called and used for resizing purposes
	 * <p> An Exception caught (in case of resize) to recurse itself.
	 * 
	 * @param args nothing required
	 * 
	 */
    public static void main(String ... args)
    {
        try
        {
        	obj = new GUI();
        	obj.start();
        }
        catch(Exception e)
        {
        	main();
        }
        System.exit(0);
    }
}