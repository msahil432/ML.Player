package Cover;

import Skeleton.myCover;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * 
 * <b>ML.Player - Cover Medium Frame</b>
 * <p> - It resizes and positions the various GUI components as relative size convention of its.
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Added formal Documentation
 * 
 * @author msahil432
 *
 */
public class mid extends myCover
{
	/**
     *  nothing happens here, just calling {@link Skeleton.myCover#myCover(int, int)} Super Constructor 
     * @param width width of display screen
     * @param height height of display screen
     */
    public mid(int w, int h)
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
        
        previousBtn.setBounds((int)(0.1*x),(int)(6.8*y),(int)(1.5*x),(int)(1.5*y));
        
        playBtn.setBounds((int)(1.7*x),(int)(6.8*y),(int)(1.5*x),(int)(1.5*y));
        
        forwardBtn.setBounds((int)(3.3*x),(int)(6.8*y),(int)(1.5*x),(int)(1.5*y));
        
        nextBtn.setBounds((int)(4.9*x),(int)(6.8*y),(int)(1.5*x),(int)(1.5*y));
        
        reverseBtn.setBounds((int)(6.5*x),(int)(6.8*y),(int)(1.5*x),(int)(1.5*y));
        
        shuffleBtn.setBounds((int)(8.1*x),(int)(6.8*y),(int)(1.5*x),(int)(1.5*y));
        
        repeatBtn.setBounds((int)(9.7*x),(int)(6.8*y),(int)(1.5*x),(int)(1.5*y));
        
        resizeBtn.setBounds((int)(11.3*x),(int)(6.8*y),(int)(0.8*x),(int)(1.5*y));

        albumart.setBounds((int)(0.1*x), (int)(0.2*y), (int)(6.4*x), (int)(6.4*y));
        
        soul_text.setBounds(3000, 2000, 400, 500);
       
        playlist.setBounds((int)(7*x),(int)(0.2*y),(int)(4.8*x),(int)(6.4*y));
        panel.add(playlist);
       
        return panel;
    }
    
    /**
     * It sets the sizes and adds menubar to JFrame 'frame'.
     * Most of features of this 'frame' were already created in Super.create {@see Skeleton.myCover#create()}. 
     */
    @Override
    protected void create()
    {
        JFrame.setDefaultLookAndFeelDecorated(false);
        frame = new JFrame("ML.Player");
        super.create();
        frame.setSize((x*12+xr),(y*10+yr));
        frame.setJMenuBar(menubar);
    }
    
    /**
     * does nothing, Calls Super method
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
    	super.actionPerformed(ae);
    }
    
    
    /**
     * @deprecated 
     * Not used
     */
    @Override
    public void keyTyped(KeyEvent ke)
    {}
    
    /**
     * @deprecated 
     * Not used
     */
    @Override
    public void keyReleased(KeyEvent ke)
    {}
}
