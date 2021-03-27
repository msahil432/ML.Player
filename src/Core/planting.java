package Core;

import javax.swing.JOptionPane;

import Planting.planting1;
import Planting.planting2;
import Planting.planting3;
import XML.xml;
import XML.xml_soul;

/**
 * <b>ML.Player - Planting Controller</b>
 * <p> - Planting controller is shown till the user havn't completed it fully.
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c </b>
 * <p> - Formal Documentation added
 * <p> - {@link #finalize()} tweaking = true;
 * 
 * @author msahil432
 *
 */

public class planting extends Object
{
	private int screen_width, screen_height;
	/** To check whether cancel button is pressed on any plating frames */
	public boolean cancelled = false;
	
	/**
	 * 
	 * @param width width of screen on which program is to be displayed
     * @param height height of screen on which program is to be displayed
	 */
	
	public planting(int width, int height)
	{
		this.screen_width = width;
		this.screen_height = height;
	}
	
	/**
	 *  Creates object of three planting frames, one by one.
	 *  <p> Uses {@link #cancelled} and boolean plantingx.done to determine whether continue the flow or exit.
	 *   
	 */
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public void run()
	{
		try
		{
			planting1 p1 = new planting1(screen_width,screen_height);
			p1.run();
			while((!p1.done)&&(!this.cancelled))									//Waiting till the work is not completed
			{
				this.cancelled = p1.cancelled;
				Thread.currentThread().sleep(200);
			}
			p1.finalise();
			if(!this.cancelled)														//If cancelled, not going further
			{
				planting2 p2 = new planting2(screen_width,screen_height);
				p2.run();
				while(!p2.done)														//Waiting till the work is not completed
				{
					this.cancelled = p2.cancelled;
					Thread.currentThread().sleep(200);
				}
				p2.finalise();
			}
			
			if(!this.cancelled)														//If cancelled, not going further
			{
				planting3 p3 = new planting3(screen_width,screen_height);
				p3.run();
				while((!p3.done)&&(!this.cancelled))								//Waiting till the work is not completed
				{
					this.cancelled = p3.cancelled;
					Thread.currentThread().sleep(200);
				}
				int a [] = p3.getData();											//Getting all user inputed data
				xml x1 = xml.getInstance();
				xml_soul xs = xml_soul.getInstance();
				x1.set("size", String.valueOf(a[0]));
				xs.set("magicno", String.valueOf(a[1]));
				p3.finalise();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage()+" Planting", "Error - ML.Player",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	/**
	 * this makes sure that no planting frame should exist after garbahe collection of object
	 */
	@Override
	public void finalize()
	{
		cancelled = true;
		try
		{super.finalize();}
		catch(Throwable e)
		{e.printStackTrace();}
	}
}
