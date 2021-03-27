package Cover;

import Skeleton.myCover;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * 
 * <b>ML.Player - Cover Minimal Frame</b>
 * <p> - It resizes and positions the various GUI components as relative size convention of its.
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Added formal Documentation
 * 
 * @author msahil432
 *
 */
public class min extends myCover
{
	/**
     *  nothing happens here, just calling {@link Skeleton.myCover#myCover(int, int)} Super Constructor 
     * @param width width of display screen
     * @param height height of display screen
     */
    public min(int w, int h)
    {
        super(w, h);
    }
    
    /**
     * It positions and resizes the GUI components as per the Size in the JPanel 'panel'.
     * <p> Theses components were already added in Super.design() {@see Skeleton.myCover#design}. 
     */
    @Override
    protected JPanel design()
    {
        panel = super.design();
        
        playBtn.setBounds((int)(3*x),(int)(4.8*y),(1*x),(1*y));
        
        shuffleBtn.setBounds((int)(0.2*x),(int)(0.8*y),(1*x),(1*y));
        
        repeatBtn.setBounds((int)(0.2*x),(int)(3*y),(1*x),(1*y));
        
        previousBtn.setBounds((int)(0.2*x),(int)(4.8*y),(1*x),(1*y));
        
        reverseBtn.setBounds((int)(1.5*x),(int)(4.8*y),(1*x),(1*y));
        
        forwardBtn.setBounds((int)(4.5*x),(int)(4.8*y),(1*x),(1*y));
        
        nextBtn.setBounds((int)(5.8*x),(int)(4.8*y),(1*x),(1*y));
        
        resizeBtn.setBounds((int)(5.8*x),(int)(3*y),(1*x),(1*y));
        
        closeBtn.setBounds((int)(5.8*x),(int)(0.8*y),(1*x),(1*y));
        
        albumart.setBounds((int)(1.5*x), (int)(0.3*y), (4*x), (4*y));
        
        soul_text.setBounds(3000, 2000, 400, 500);
        
        return panel;
    }
    
    /**
     * It sets the sizes and doesn't add menubar to JFrame 'frame'.
     * Most of features of this 'frame' were already created in Super.create {@see Skeleton.myCover#create()}. 
     */
    @Override
    protected void create()
    {
        JFrame.setDefaultLookAndFeelDecorated(false);
        frame = new JFrame("ML.Player");
        super.create();
        frame.setSize((x*7),(y*7));
    }
   
    /**
     * does nothing, Calls Super method
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
    	super.actionPerformed(ae);
    }
}