package Core;

import XML.xml;
import Cover.max;
import Cover.mid;
import Cover.min;
import Skeleton.myCover;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 * <b> ML.Player - GUI Class </b>
 * <p> - Controller of Graphical User Interface
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c </b>
 * <p> - Formal Documentation Added for class.
 * <p> - Constructors created for better testing on different resolutions.
 * <p> - {@link #finalize()} method tweaked.
 * 
 * @author msahil432
 */
public class GUI
{
    private String temp;
    private max max_frame;
    private mid mid_frame;
    private min min_frame;
    private planting planting_obj;
    public int screen_width, screen_height;
    private xml xml_obj;
    
    /**
     * Unparameterized Constructor of GUI
     * <p> Calls {@link #GUI(int, int)} and sends current screen's dimensions as parameters.
     */
    
    public GUI()
    {
    	this((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    }
    
    /**
     * Parameterized Constructor of GUI
     * <p>  Recommended for testing App for different resolutions on a single system.
     * 
     * @param width width of screen on which program is to be displayed
     * @param height height of screen on which program is to be displayed
     */
    
    public GUI(int width, int height)
    {
    	screen_width = width;
    	screen_height = height;
    	xml_obj = xml.getInstance();
    }
    
    /**
     * First checks if configuration done, if no then executes {@link Planting} Controller and goes ahead
     * <p>if yes, skips planting
     * <p>Then starts to act as {@link Cover} controller and executes the correct frame on basis of size 
     * 
     * @throws Exception when resize needed
     */
    
    @SuppressWarnings("static-access")
	public void start() throws Exception
    {
            try
            {
                if(xml_obj.find("configured"))
                {
                    temp = xml_obj.get("configured");
                    if(temp.compareToIgnoreCase("0")==0)
                    {
                        planting_obj = new planting(screen_width, screen_height);
                        planting_obj.run();
                        xml_obj.set("configured", "1");
                    }
                
                    if(true)
                    {
                        if(xml_obj.find("size"))
                        {
                            temp = xml_obj.get("size");
                            xml_obj.set("resize", "f");
                            switch(temp)
                            {
                                case "1":
                                {
                                    max_frame = new max(screen_width, screen_height);
                                    max_frame.run();
                                    break;
                                }
                                case "2":
                                {
                                    mid_frame = new mid(screen_width, screen_height);
                                    mid_frame.run();
                                    break;
                                }
                                case "3":
                                {
                                    min_frame = new min(screen_width, screen_height);
                                    min_frame.run();
                                    break;
                                }
                                default:
                                {
                                	xml_obj.reset("From GUI ");
                                	break;
                                }
                            }
                            while(myCover.mCrunning)
                            {
                            	Thread.currentThread().sleep(500);
                            }
                        }
                    }
                }
                else
                {
                	System.out.println("Configured not found in xml");
                }
            }
            catch(Exception e)
            {
            	JOptionPane.showMessageDialog(null, e.getMessage()+" GUI", "Error - ML.Player",JOptionPane.ERROR_MESSAGE);
            	e.printStackTrace();
            }
            
         if((!myCover.mCrunning) && (xml_obj.get("resize").equalsIgnoreCase("true")))
        	{
        	 xml_obj.set("resize", "false");
       			throw new Exception("Resize needed");																	//Means needs to resized
       		}
     }
    
    /**
     * makes sure that all the created objects are garbage collected <p>if this class's object is garbage collected. 
     */
    @Override
    public void finalize()
    {
    	if(planting_obj!=null)
    	{
    		planting_obj.finalize();
    	}
    	else if(min_frame!=null)
    	{
    		min_frame.finalize();
    	}
    	else if(mid_frame!=null)
    	{
    		mid_frame.finalize();
    	}
    	else if(max_frame!=null)
    	{
    		max_frame.finalize();
    	}
    	try
    	{super.finalize();}
    	catch(Throwable e)
    	{e.printStackTrace();}
    }
}