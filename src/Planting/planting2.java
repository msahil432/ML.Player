package Planting;

import Skeleton.myPlanting;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * <b>ML.Player - Second Planting Frame</b>
 * <p>   It displays of T&C of ML.Player and agrees user to them
 * <p>   It is one of total 3 planting frames.
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c </b>
 * <p> - Formal Documentation Added
 * 
 * @author msahil432
 */
public class planting2 extends myPlanting
{
	/**
	 * @param width sent to super's constructor 
	 * @param height sent to super's constructor
	 */
    public planting2(int width, int height)
    {
        super(width, height);
        done = false;
     }
    
    /**
     * gets buttons and some other components from {@link Skeleton.myPlanting#design()}
     * <p>adds Terms and Condition panel
     * 
     * @return panel a JPanel which have all the GUI components added to it.
     */
    @Override
    protected JPanel design()
    {
    	super.design();
    	JTextField txttermsAndConditions = new JTextField();
		txttermsAndConditions.setText("//Terms and Conditions will be added when published");
		txttermsAndConditions.setEditable(false);
		txttermsAndConditions.setBounds(10, 46, 414, 150);
		panel.add(txttermsAndConditions);
		txttermsAndConditions.setColumns(10);
		
		JLabel lblByClickingOn = new JLabel("By Clicking on Next, you agree to Above Terms and Conditions.");
		lblByClickingOn.setBounds(28, 207, 362, 14);
		panel.add(lblByClickingOn);
    	
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