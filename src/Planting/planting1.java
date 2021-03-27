package Planting;

import Skeleton.myPlanting;
import Skeleton.myImage;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;
/**
 * <b>ML.Player - First Planting Frame</b>
 * <p>   It displays of logo of ML.Player and welcomes user to setup of program
 * <p>   It is one of total 3 planting frames.
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c </b>
 * <p> - Formal Documentation Added
 * 
 * @author msahil432
 */
public class planting1 extends myPlanting
{
	/** Custom myImage class to display the Logo*/
	myImage logo;
	
	/**
	 * @param width sent to super's constructor 
	 * @param height sent to super's constructor
	 */
    public planting1(int width, int height)
    {
        super(width, height);
        done = false;
        logo = new myImage("data/img/logo-p.jpg", false);
    }
    
    /**
     * gets buttons and some other components from {@link Skeleton.myPlanting#design()}
     * <p>adds logo by itself
     * 
     * @return panel a JPanel which have all the GUI components added to it.
     */
    @Override
    protected JPanel design()
    {
    	super.design();
    	
    	logo.setBounds(0, 43, 480, 480);
    	panel.add(logo);
        
        return panel;
    }
    
    /**
     * manages buttons events for going forward and backward in planting frames
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==next)
        {
        	done = true;
        }
        else if(ae.getSource()==cancel)
        {
        	cancelled = true;
        }
        super.actionPerformed(ae);
        frame.dispose();
   }
}