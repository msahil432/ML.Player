package Skeleton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * 
 * <b>ML.Player - myPlanting - Super class of all Planting frames</b>
 * <p> - Remember planting frames and planting controller.
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Added fromal documentation.
 * <p> - {@link #show()} method moved in Super class.
 * @author msahil432
 *
 */
public abstract class myPlanting extends myFrame
{
    public boolean done, cancelled;
    protected JButton next, cancel;
    protected JPanel panel;
    
    public myPlanting(int w, int h)
    {
        super(w, h);
    }
    
    @Override
    protected JPanel design()
    {
    	panel = new JPanel();
    	panel.setLayout(null);
    	
    	next = new JButton("Next");
		next.addActionListener(this);
		next.setBounds(321, 228, 89, 23);
		panel.add(next);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setBounds(159, 228, 89, 23);
		panel.add(cancel);
		
		JLabel label = new JLabel("-----------------------------------------------------------------------------------------------------------------------------");
		label.setBounds(-20, 218, 479, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("---------------------------------------------------------------------------------------------------------------------------");
		label_1.setBounds(0, 23, 459, 37);
		panel.add(label_1);
		
		JLabel lblWelcomeToSetup = new JLabel("ML.Player");
		lblWelcomeToSetup.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 14));
		lblWelcomeToSetup.setBounds(305, -2, 129, 37);
		panel.add(lblWelcomeToSetup);
		
		JLabel lblWelcomeToSetup_1 = new JLabel("Welcome to setup of ");
		lblWelcomeToSetup_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblWelcomeToSetup_1.setBounds(144, 1, 140, 26);
		panel.add(lblWelcomeToSetup_1);
    	
    	return panel;
    }
    
    @Override
    protected void create()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("ML.Player - Setup");
        super.create();
        frame.setBounds(14*x, 12*y, (int)9.38*x, (int)10.35*y);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
    	if(ae.getSource()==cancel)
    	{
    		cancelled = true;
    		System.exit(0);
    	}
    }
    
    @Override
    public void keyPressed(KeyEvent ke)
    {}
    
    @Override
    public void keyTyped(KeyEvent ke)
    {}
    @Override
    public void keyReleased(KeyEvent ke)
    {}
    
    public void finalise()
    {
    	frame.dispose();
    	frame = null;
    	panel = null;
    	done = false;
    	next = null;
    	cancel = null;
    }
 }
