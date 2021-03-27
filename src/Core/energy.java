package Core;

import XML.xml_energy;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;                                                 
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 
 * <b>ML.Player - Energy Controller</b>
 * <p> - This class manages the audio output
 * <p> - This class works as controller of ePlayer class
 * <p> - Singleton class and its object is never passed outside
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Added formal documentation
 * <p> - Tweaked {@link #finalise()} method
 * @author msahil432
 *
 */
public class energy
{
    private String filename;
    int position;
    boolean shuffle = false;
    int repeat;
    int count;
    private static boolean thread_active = false;
    private xml_energy exml;
    private ePlayer ep;
    private static energy en;
    public static boolean playlist = false;
    public static boolean running = false;
    
    /**
     * private constructor as of singleton class
     */
    @SuppressWarnings("deprecation")
	private energy()
    {
    	exml = xml_energy.getInstance();
        count=xml_energy.count();
        if(count>0)
        	playlist=true;
        
        if(exml.get("shuffle").equalsIgnoreCase("true"))
        {
        	System.out.println("Shuffle is true");
        	shuffle = true;
        }
        if(exml.get("repeat").equalsIgnoreCase("one"))
        {
        	System.out.println("Repeat one song only");
        	repeat = 1;
        }
        else if(exml.get("repeat").equalsIgnoreCase("all"))
        {
        	System.out.println("Repeat whole list");
        	repeat = 2;
        }
        else
        	repeat = 0;
    }
    
    /**
     * this is the method through which the external components interact with this class
     * <p> this used in every case except for lists
     * 
     * @param s service they need from the class
     * @throws IOException when a file is loaded in the play list
     * @throws Exception when the playback is started, paused or resumed 
     */
    @SuppressWarnings("deprecation")
	public static void toggles(String s) throws IOException, Exception
    {
    	try
    	{
    	if(!thread_active)                                                      //Checking if thread already runing.
        {
            en = new energy();                                                  //Singleton class, so only one object is possible.
            thread_active=true;
        }
        if(s.equalsIgnoreCase("exit"))
    	{
    		System.exit(0);
    	}
    	else if(s.equalsIgnoreCase("shuffle"))
    	{
        	en.shuffle = !en.shuffle;
        	en.exml = xml_energy.getInstance();
        	en.exml.set("shuffle", String.valueOf(en.shuffle));
        }
    	else if(s.equalsIgnoreCase("repeat"))
    	{
            en.repeat++;
            if(en.repeat>2)
            	en.repeat=0;
        	en.exml = xml_energy.getInstance();
        	en.exml.set("repeat", String.valueOf(en.repeat));
    	}
    	else if(playlist)
    	{
        switch(s)
        {
            case "play":
            {
                if(!running)
                    en.play();
                
                else if(running)
                	en.pause(); 
                break;
            }
            case "previous":
            {
               en.previous();
                break;
            }
            case "next":
            {
                en.next();
                break;
            }
            case "reverse":
            {
            	en.reverse();
            	break;
            }
            case "forward":
            {
            	en.forward();
            	break;
            }
            case "add":
            {
            	en.add_file_in_playlist();
            	break;
            }
            default:
            {
                System.out.println("Wrong argument in toogles in energy.java");
                break;
            }
        }
    	}
    	else
    	{
    		en.add_file_in_playlist();
    	}
    	}
    	catch(Exception e)
    	{
    		if(e.getMessage().equalsIgnoreCase("file added"))
    		{
    			if(!running)
    			{
    				playlist = true;
    				running = true;
    				try
    				{
    					en.play();
    				}
    				catch(Exception ex)
    				{
    					throw new IOException("file added", new Exception("playing started"));
    				}
    			}
    			else
    				throw e;
    		}
    		if(e.getMessage()!=null)
    			throw new Exception(e.getMessage());
    	}
    }
    
    /**
     * this is for components (only list related) to invoke the class
     * @param i the index value of the item in list
     * @throws IOException when a file is added to playlist
     * @throws Exception  when there is change in play back state 
     */
    @SuppressWarnings({"deprecation"})
	public static void toggles(int i) throws IOException, Exception
    {
    	if(!thread_active)
    		en =new energy();
    	if(i<100)
    	{
    		if(energy.running)
    			en.pause();
    		en.exml.get("song"+i);
    		en.play();
    	}
    	else if(i>100)
    	{
    		if(energy.running)
    			en.pause();
    		String ts = XML.xml_recents.getInstance().getFiles()[i-101];
    		System.out.println(ts);
    		en.add_file_to_xml(ts);
    		en.exml.getLast();
    		en.play();
    	}
    }
    
    /**
     * adds song to playlist / energy.xml
     * @param song path of file
     */
    private void add_file_to_xml(String song)
    {
      	if(!exml.find(song))
       		count = exml.set(song);
    }
    
    /**
     * this is invoked on play commands
     * @throws Exception when playback state is changed
     */
    private void play() throws Exception
    {
    	ep = null;
        
        if(playlist)
        {
        	String file = exml.get();
        	ep = new ePlayer(file, position);
        	ep.start();
        }
        running = true;
        throw new Exception("playing started");
    }
    
    /**
     * this is invoked on next commands
     * @throws Exception when playback state is changed
     */
    private void next() throws Exception
    {
    	try
    	{
    		pause();
    	}
    	catch(Exception e)
    	{}
        	String file = exml.getnext();
        	if(file.equalsIgnoreCase("null"))
        	{
        		this.add_file_in_playlist();
        	}
        	else
        	{
        		ep = new ePlayer(file, position);
        		ep.start();
        	}
    		running = true;
    		throw new Exception("playing started");
    }
    
    /**
     * this is invoked on pause commands
     * @throws Exception when playback state is changed
     */
    @SuppressWarnings("deprecation")
	private void pause() throws Exception
    {
        if(ep!=null)
        {
            position = ep.pos();
        	ep.stop();
        	System.out.println("Player stopped at position "+position);
        	running = false;
        }   
        ep = null;
        throw new Exception("playing stopped");
    }
    
    /**
     * this is invoked on previous commands
     * @throws Exception when playback state is changed
     */
    private void previous() throws Exception
    {
    	try
    	{
    		pause();
    	}
    	catch(Exception e)
    	{}
    	String file = exml.getPrevious();
    	if((file.equalsIgnoreCase("null"))|(file==null))
    		play();
    	else
    	{
    		ep = new ePlayer(file, position);
    		ep.start();
    	}
    		running = true;
    		throw new Exception("playing started");
    }
    
    /**
     * this is invoked on forward commands
     * @throws Exception when playback state is changed
     */
    private void forward() throws Exception
    {
    	try
    	{
    		pause();
    	}
    	catch(Exception e)
    	{}
    	ep = null;
        
        if(playlist)
        {
        	String file = exml.get();
        	ep = new ePlayer(file, position+2500);
        	ep.start();
        }
        running = true;
        throw new Exception("playing started");
    }
    
    /**
     * this is invoked on reverse commands
     * @throws Exception when playback state is changed
     */
    private void reverse() throws Exception
    {
    	try
    	{
    		pause();
    	}
    	catch(Exception e)
    	{}
    	ep = null;
        if(playlist)
        {
        	String file = exml.get();
        	position -= 2500;
        	if(position<0)
        		position = 0;
        	ep = new ePlayer(file, position);
        	ep.start();
        }
        running = true;
        throw new Exception("playing started");
    }
    
    /**
     * this is invoked on add songs commands
     * <p> it uses GUI to pickup song files
     * @throws Exception when a song is added to playlist
     */
    private void add_file_in_playlist() throws Exception
    {
        
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Add files to play");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            filename = StringEscapeUtils.escapeJava(""+chooser.getSelectedFile());
            if (filename == null)
                JOptionPane.showMessageDialog(null, "Null selected");
            else
            {
                this.add_file_to_xml(filename);
                throw new Exception("file added");
            }
        }
        else
        {
        	JOptionPane.showMessageDialog(null, "Nothing Selected", "Dialog",JOptionPane.INFORMATION_MESSAGE); 
        }
    }
    
    /**
     * this makes sure that Playback is stopped upon garbage collection of the object
     */
    public void finalise()
    {
    	ep.interrupt();
    	try
    	{super.finalize();}
    	catch(Throwable e)
    	{e.printStackTrace();}
    }
}

//-------------------------------------------------------------------------------------------------------------------------------

/**
 * 
 * <b>ML.Player - ePlayer - Media(Audio) playing component</b>
 * <p> - this class is responsible for playing of audio files
 * <p> - extends thread, so to makes the playback in background
 * <p> - this class is controlled by energy.java class
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - added formal documentation 
 * 
 * @author msahil432
 *
 */
class ePlayer extends Thread
{
    String song_add;                                                            //To store file name of played file
    int posi;                                                                   //To save position of current song
    private Player p;                                                           //JavaZoom Player Object Declaration.
    boolean playing;                                                            //song is playing.
    
    /**
     * when no start position is provided
     * @param song path to song file
     */
    ePlayer(String song)
    {
        this(song, 0);
    }
    
    /**
     * when start position is provided
     * @param song path to song file
     * @param position from which position to play the song
     */
    ePlayer(String song, int position)
    {
        this.playing = false;
        song_add = song;
        this.posi = position;
        setDaemon(true);
   }
    
    /**
     * As of a thread, the run method
     */
    @Override
    public void run()												//For starting playing of song 
    {
    	playing=true;
        try
        {
            p = null;
            
        	p = new Player(new BufferedInputStream(new FileInputStream(song_add)));  //Creating object of player
            if(posi==0)
                p.play();
            if(posi>0)
                p.play(posi);
        }
        catch(FileNotFoundException fe)
        {
        	JOptionPane.showMessageDialog(null, fe.getMessage()+" ePlayer", "Error - ML.Player",JOptionPane.ERROR_MESSAGE); 
            playing=false;
        }
        catch(Exception e)
        {
        	playing=false;
        	JOptionPane.showMessageDialog(null, e.getMessage()+" ePlayer", "Error - ML.Player",JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    /**
     * provides the current position of song playing 
     * @return integer value, of the song position
     */
    public int pos()
    {
        if(playing)
        {
            this.posi = p.getPosition();
      }
        return posi;
    }
}