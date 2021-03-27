package Cover;

import Skeleton.myCover;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
/**
 * 
 * <b>ML.Player - Cover Maximum Frame</b>
 * <p> - It resizes and positions the various GUI components as relative size convention of its.
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Added formal Documentation
 * 
 * @author msahil432
 *
 */
public class max extends myCover
{
    /**
     *  nothing happens here, just calling {@link Skeleton.myCover#myCover(int, int)} Super Constructor 
     * @param width width of display screen
     * @param height height of display screen
     */
    public max(int width, int height)
    {
        super(width, height);
    }
    
    /**
     * It positions and resizes the GUI components as per the Size in the JPanel 'panel'.
     * <p> Theses components were already added in Super.design() {@see Skeleton.myCover#design}. 
     */
    @Override
    protected JPanel design()
    {
        panel = super.design();
      
        repeatBtn.setBounds((int)(0.2*x),(int)(21.5*y),(int)(3*x),(int)(2*y));
        repeatBtn.setText("Repeat");
        
        previousBtn.setBounds((int)(3.7*x),(int)(21.5*y),(int)(3*x),(int)(2*y));
        previousBtn.setText("Previous");
        
        reverseBtn.setBounds((int)(7.2*x),(int)(21.5*y),(int)(3*x),(int)(2*y));
        reverseBtn.setText("Reverse");
        
        playBtn.setBounds((int)(10.7*x),(int)(21.5*y),(int)(3*x),(int)(2*y));
        playBtn.setText("Play");
        
        forwardBtn.setBounds((int)(14.2*x),(int)(21.5*y),(int)(3*x),(int)(2*y));
        forwardBtn.setText("Forward");
        
        nextBtn.setBounds((int)(17.7*x),(int)(21.5*y),(int)(3*x),(int)(2*y));
        nextBtn.setText("Next");
        
        shuffleBtn.setBounds((int)(21.2*x),(int)(21.5*y),(int)(3*x),(int)(2*y));
        shuffleBtn.setText("Shuffle");
        
        resizeBtn.setBounds((int)(24.7*x),(int)(21.5*y),(int)(3*x),(int)(2*y));
        resizeBtn.setText("Resize");
        
        albumart.setBounds((int)(0.3*x), (int)(0.2*y), (int)(15*x), (int)(20.8*y));
        
        soul_text.setBounds(3000, 2000, 400, 500);
        
        playlist.setBounds((int)(16*x),(int)(0.2*y),(int)(11*x),(int)(21*y));
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
        frame.setSize((x*28+xr),(y*25+yr));
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