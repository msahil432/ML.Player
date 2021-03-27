package Planting;

import Skeleton.myPlanting;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
/**
 * 
 * <b>ML.Player - Third Planting Frame</b>
 * <p> - It takes some preferences from the user  
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Formal Documentation added
 * 
 * @author msahil432
 *
 */
public class planting3 extends myPlanting
{
	private int size=1;
	private String magicno = "2";
	/**
	 * @param width sent to super's constructor 
	 * @param height sent to super's constructor
	 */
    public planting3(int width, int height)
    {
        super(width, height);
        done = false;
    }
    
    /**
     * gets buttons and some other components from {@link Skeleton.myPlanting#design()}
     * <p>adds Radio buttons and comboboxes to get preferences
     * 
     * @return panel a JPanel which have all the GUI components added to it.
     */
    @SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	@Override
    protected JPanel design()
    {
    	super.design();
    	
        next.setLabel("Finish");
       
        JLabel lblByClickingOn = new JLabel("Click on finish to open ML.Player");
		lblByClickingOn.setBounds(144, 203, 209, 14);
		panel.add(lblByClickingOn);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Maximized");
		rdbtnNewRadioButton.setBounds(263, 59, 109, 23);
		rdbtnNewRadioButton.addItemListener(new ItemListener()
		{
	         public void itemStateChanged(ItemEvent e)
	         {         
	            size = 1;
	         }           
	      });

		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnMid = new JRadioButton("Normal");
		rdbtnMid.setBounds(263, 88, 109, 23);
		rdbtnMid.addItemListener(new ItemListener()
		{
	         public void itemStateChanged(ItemEvent e)
	         {         
	            size = 2;
	         }           
	      });

		panel.add(rdbtnMid);
		
		JRadioButton rdbtnMinimal = new JRadioButton("Minimal");
		rdbtnMinimal.setBounds(263, 114, 109, 23);
		rdbtnMinimal.addItemListener(new ItemListener()
		{
	         public void itemStateChanged(ItemEvent e)
	         {         
	            size = 3;
	         }           
	      });

		panel.add(rdbtnMinimal);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnMinimal);
		bg.add(rdbtnMid);
		bg.add(rdbtnNewRadioButton);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2", "5", "10", "15", "20", "25", "30", "60"}));
		comboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				magicno = String.valueOf(comboBox.getSelectedItem());
			}
		});
		comboBox.setBounds(263, 158, 89, 20);
		panel.add(comboBox);
		
		JLabel lblSelectThePreferred = new JLabel("Select the preferred size for ML.Player:");
		lblSelectThePreferred.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSelectThePreferred.setBounds(35, 61, 226, 74);
		panel.add(lblSelectThePreferred);
		
		JLabel lblSelectTheNumber = new JLabel("Select the time after which Player");
		lblSelectTheNumber.setBounds(24, 158, 226, 14);
		panel.add(lblSelectTheNumber);
		
		JLabel lblAskToSave = new JLabel(" would ask to save a key");
		lblAskToSave.setBounds(24, 171, 140, 14);
		panel.add(lblAskToSave);
    	
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
    
    /**
     *  send the user fed data as in an array
     *  <p> should be called before finalizing the object
     * @return a an array which contains all the data  
     */
    public int[] getData()
    {
    	int[] a = {size, Integer.parseInt(magicno)};
    	return a;
    }
}