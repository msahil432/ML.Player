package Skeleton;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * 
 * <b>ML.Player - myImage</b>
 * <p> - used to display the images inside frames
 * <p> - this behaves like an applet class
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Added formal documentation
 * <p> - Tweaked constructors 
 * @author msahil432
 *
 */
public class myImage extends JComponent
{
	private static final long serialVersionUID = 1L;
	boolean resizing;
	
	private BufferedImage img;
	
	/**
	 * used when resizing parameter, taken as false further, not provided
	 * <p> calls {@link #myImage(String, boolean)}
	 * @param filepath path to image file 
	 */
	public myImage(String filepath)
	{
		this(filepath, false);		
	}
	
	/**
	 * used when resizing parameter is also provided
	 * <p> 
	 * @param filepath path to image file
	 * @param resize image is to be resized or not?
	 */
	public myImage(String filepath, boolean resize)
	{
		try
		{
			img = ImageIO.read(new myFile(filepath));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		resizing = resize;
	}
	
	/**
	 * behaves like an applet running, so paint method
	 */
	public void paint(Graphics g)
	{
		if(resizing)
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		else
			g.drawImage(img, 0, 0, this);
	}
}