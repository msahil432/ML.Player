package Skeleton;

import javax.swing.*;

import java.awt.event.*;

import javax.imageio.ImageIO;
/**
 * 
 * <b>ML.Player - myFrame - Super Class of every Frame</b>
 * <p> - It resizes and positions the various GUI components as relative size convention of its.
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Added formal Documentation
 * 
 * @author msahil432
 *
 */
public abstract class myFrame extends Object implements ActionListener, KeyListener
{
    protected JFrame frame;
    protected JPanel panel;
    protected int x, xr, y, yr;
    
    /**
     * It gets screen dimensions and calculates x and y, two variables 
     * <p>which would be used to position GUI components in all frames.
     * 
     * @param width width of the display screen
     * @param height height of the display screen
     */
    public myFrame(int width, int height)
    {
        x = width / 28;
        xr = width % 28;
        y = height / 26;
        yr = height % 26;
     }
    
    /**
     * abstract function which should be implemented be every child class
     * <p> this would be used to add components, resize and position them respectively in the container JPanel '{@link #panel}'
     *  
     * @return panel, which contained all these will be returned
     */
    protected abstract JPanel design();
    
    /**
     * It provides the frame various of its features and other things
     */
    protected void create()
    {
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setContentPane(this.design());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter()
        {
        	@Override
        	public void windowClosing(WindowEvent we)
        	{
        		frame.dispose();
        	}
        });
        try
        {
            frame.setIconImage(ImageIO.read(new myFile("data/img/logo.png")));
        }
        catch (Exception ex)
        {
        	JOptionPane.showMessageDialog(null, ex.getMessage()+" Frame", "Error - ML.Player",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * this calls the swing utilities to display the frame 
     */
    public void show()
    {
    	 SwingUtilities.invokeLater(new Runnable()
         {
             @Override
             public void run()
             {
                 create();
             }
         });
    }
    
    /**
     * abstract function which should be implemented be every child class
     * <p> this would be used to handle all types of click events
     */
    @Override
    public abstract void actionPerformed(ActionEvent ae);
    
    /**
     * abstract function which should be implemented be every child class
     * <p> this would be used to handle all key pressed events
     */
    @Override
    public abstract void keyPressed(KeyEvent ke);
    
    /**
     * abstract function which should be implemented be every child class
     * <p> this would be used to handle all key types events
     */
    @Override
    public abstract void keyTyped(KeyEvent ke);
    
    /**
     * abstract function which should be implemented be every child class
     * <p> this would be used to handle all key released events
     */
    @Override
    public abstract void keyReleased(KeyEvent ke);
    
    /**
     * This is the method, to be called from outside, to display the frame
     */
    public void run()
    {
    	this.show();
    }
    
    /**
     * makes sure that frame is also disposed when object is garbage collected
     */
    public void finalize()
    {
    	frame.dispose();
    	try
    	{super.finalize();}
    	catch(Throwable e)
    	{e.printStackTrace();}
    }
}
