package Skeleton;

import Core.energy;
import Soul.soul;
import XML.xml;
import XML.xml_energy;
import XML.xml_recents;
import Cover.tsettings;
import Skeleton.myImage;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.MouseAdapter;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * 
 * <b>ML.Player - myCover - Super Class for every cover frames</b>
 * <p> - It resizes and positions the various GUI components as relative size convention of its.
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Added formal Documentation.
 * <p> - {@link #show()} method moved in Super class.
 * <p> - {@link #finalize()} method tweaks.
 * 
 * @author msahil432
 *
 */
public abstract class myCover extends myFrame
{
    public static boolean mCrunning, program_closed=false;
	protected JTextField soul_text;
    protected JButton playBtn, nextBtn, previousBtn, shuffleBtn, repeatBtn, resizeBtn, closeBtn, forwardBtn, reverseBtn;
    protected Image playImg, pauseImg, nextImg, previousImg, shuffleImg1, shuffleImg2, repeatImg1, repeatImg2, repeatImg0, resizeImg1, resizeImg2, resizeImg3, closeImg, forwardImg, reverseImg;
    protected static boolean use_addsome;
    protected boolean shuffle=false;
    protected boolean playing = false;
    protected myImage albumart;
    protected int repeat=0;
    protected xml xobj;
    protected xml_recents xrecents;
    protected xml_energy xe;
    protected int size_index =0;
    protected JMenuBar menubar;
    protected JMenu file, prefs, about, recent;
    protected JMenuItem open, exit, settings, rs, abt;
    @SuppressWarnings("rawtypes")
	protected DefaultListModel jlist;
    @SuppressWarnings("rawtypes")
	protected JList jl;
    protected JScrollPane playlist;
    protected int playlist_size=0;
    protected Thread tList;
    
    /**
     * Reads various parameters from file and loads images from disk
     * @param width the width of the display screen
     * @param height the height of the display screen
     */
    @SuppressWarnings("deprecation")
	public myCover(int width, int height)
    {
        super(width,height);
        this.refreshInstances();
        this.playing = energy.running;
        this.size_index = Integer.parseInt(xobj.get("size"));
        this.repeat = Integer.parseInt(xe.get("repeat"));
        this.shuffle = Boolean.parseBoolean(xe.get("shuffle"));
        
        // Loading Images
        try
        {
        	playImg = ImageIO.read(new myFile("data/img/buttons/play.jpg"));
        	pauseImg = ImageIO.read(new myFile("data/img/buttons/stop.jpg"));
        	nextImg = ImageIO.read(new myFile("data/img/buttons/next.jpg"));
        	previousImg = ImageIO.read(new myFile("data/img/buttons/previous.jpg"));
        	reverseImg = ImageIO.read(new myFile("data/img/buttons/reverse.jpg"));
        	forwardImg = ImageIO.read(new myFile("data/img/buttons/forward.jpg"));
        	resizeImg1 = ImageIO.read(new myFile("data/img/buttons/resize1.jpg"));
        	resizeImg2 = ImageIO.read(new myFile("data/img/buttons/resize2.jpg"));
        	resizeImg3 = ImageIO.read(new myFile("data/img/buttons/resize3.jpg"));
        	closeImg = ImageIO.read(new myFile("data/img/buttons/close.jpg"));
        	shuffleImg1 = ImageIO.read(new myFile("data/img/buttons/shuffle1.jpg"));
        	shuffleImg2 = ImageIO.read(new myFile("data/img/buttons/shuffle2.jpg"));
        	repeatImg1 = ImageIO.read(new myFile("data/img/buttons/repeat1.jpg"));
        	repeatImg2 = ImageIO.read(new myFile("data/img/buttons/repeat2.jpg"));
        	repeatImg0 = ImageIO.read(new myFile("data/img/buttons/repeat0.jpg"));
        	
        	albumart = new myImage("data/img/logo.jpg", true);
        }
        catch(Exception e)
        {
        	JOptionPane.showMessageDialog(null, e.getMessage()+" Cover", "Error - ML.Player",JOptionPane.ERROR_MESSAGE); 
        }
        mCrunning = true;
    }
    
    /**
     * This gets the instances of various XML classes required
     */
    private void refreshInstances()
    {
    	xobj = xml.getInstance();
    	xe = xml_energy.getInstance();
    	xrecents = xml_recents.getInstance();
    }
    
    /**
     * it initializes adds all the GUI components to JPanel 'panel'
     * @return panel which contained all the components
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    protected JPanel design()
    {
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(36, 175, 255));
        
        //Buttons
        
        playBtn = new JButton(new ImageIcon(playImg));
        playBtn.getAccessibleContext().setAccessibleDescription("Play");
        playBtn.setBackground(new Color(5, 187, 253));
        playBtn.addActionListener(this);
        panel.add(playBtn);
        
        forwardBtn = new JButton(new ImageIcon(forwardImg));
        forwardBtn.setBackground(new Color(5, 187, 253));
        forwardBtn.getAccessibleContext().setAccessibleDescription("Forward");
        forwardBtn.addActionListener(this);
        panel.add(forwardBtn);
        
        reverseBtn = new JButton(new ImageIcon(reverseImg));
        reverseBtn.setBackground(new Color(5, 187, 253));
        reverseBtn.getAccessibleContext().setAccessibleDescription("Reverse");
        reverseBtn.addActionListener(this);
        panel.add(reverseBtn);
        
        nextBtn = new JButton(new ImageIcon(nextImg));
        nextBtn.setBackground(new Color(5, 187, 253));
        nextBtn.getAccessibleContext().setAccessibleDescription("Next");
        nextBtn.addActionListener(this);
        panel.add(nextBtn);
        
        previousBtn = new JButton(new ImageIcon(previousImg));
        previousBtn.setBackground(new Color(5, 187, 253));
        previousBtn.getAccessibleContext().setAccessibleDescription("Previous");
        previousBtn.addActionListener(this);
        panel.add(previousBtn);
        
        resizeBtn = new JButton(new ImageIcon(resizeImg1));
        resizeBtn.setBackground(new Color(5, 187, 253));
        resizeBtn.getAccessibleContext().setAccessibleDescription("Resize");
        resizeBtn.addActionListener(this);
        if(size_index == 1)
        	resizeBtn.setIcon(new ImageIcon(resizeImg2));
        else if(size_index == 2)
        	resizeBtn.setIcon(new ImageIcon(resizeImg3));
        panel.add(resizeBtn);
        
        closeBtn = new JButton(new ImageIcon(closeImg));
        closeBtn.setBackground(new Color(5, 187, 253));
        closeBtn.getAccessibleContext().setAccessibleDescription("Close");
        closeBtn.addActionListener(this);
        panel.add(closeBtn);
        
        repeatBtn = new JButton(new ImageIcon(repeatImg0));
        repeatBtn.setBackground(new Color(5, 187, 253));
        repeatBtn.getAccessibleContext().setAccessibleDescription("Repeat");
        repeatBtn.addActionListener(this);
        if(repeat == 1)
        	repeatBtn.setIcon(new ImageIcon(repeatImg1));
        else if(repeat == 2)
        	repeatBtn.setIcon(new ImageIcon(repeatImg2));
        panel.add(repeatBtn);
        
        shuffleBtn = new JButton();
        shuffleBtn.setBackground(new Color(5, 187, 253));
        shuffleBtn.addActionListener(this);
        if(shuffle)
        {
        	shuffleBtn.getAccessibleContext().setAccessibleDescription("Shuffle ON");
        	shuffleBtn.setIcon(new ImageIcon(shuffleImg1));
        }
        else
        {
        	shuffleBtn.getAccessibleContext().setAccessibleDescription("Shuffle OFF");
        	shuffleBtn.setIcon(new ImageIcon(shuffleImg2));
        }
        panel.add(shuffleBtn);
        
        
        //Album art
        panel.add(albumart);
        
        
        //Menu bar
        
        menubar = new JMenuBar();
        
        file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        file.getAccessibleContext().setAccessibleDescription("Click for Play, Add, Exit....");
        menubar.add(file);

        prefs = new JMenu("Preferences");
        prefs.setMnemonic(KeyEvent.VK_P);
        prefs.getAccessibleContext().setAccessibleDescription("Clcik for settings, ....");
        menubar.add(prefs);
        
        about = new JMenu("About");
        about.setMnemonic(KeyEvent.VK_A);
        about.getAccessibleContext().setAccessibleDescription("Click for knowing about ML.Player");
        menubar.add(about);
        
        //Items of File Menu
        open = new JMenuItem("Add File", KeyEvent.VK_F);
        open.addActionListener(this);
        file.add(open);
        
        recent = new JMenu("Recent");
        recent.setMnemonic(KeyEvent.VK_R);
        this.refreshInstances();
        String files[] = xrecents.getFiles();
        if(files!=null)
        {	
        	JMenuItem rc1, rc2, rc3, rc4, rc5, rc6, rc7, rc8, rc9, rc10;
        	rc1 = new JMenuItem(files[0]);
        	rc1.addActionListener(new ActionListener()
    		{
    			@Override
    			public void actionPerformed(ActionEvent ae)
    			{
    				toggles(100+1);
    			}
    		});
        	recent.add(rc1);
        	
        	rc2 = new JMenuItem(files[1]);
        	rc2.addActionListener(new ActionListener()
    		{
    			@Override
    			public void actionPerformed(ActionEvent ae)
    			{
    				toggles(100+2);
    			}
    		});
        	recent.add(rc2);
        	
        	rc3 = new JMenuItem(files[2]);
        	rc3.addActionListener(new ActionListener()
    		{
    			@Override
    			public void actionPerformed(ActionEvent ae)
    			{
    				toggles(100+3);
    			}
    		});
        	recent.add(rc3);
        	
        	rc4 = new JMenuItem(files[3]);
        	rc4.addActionListener(new ActionListener()
    		{
    			@Override
    			public void actionPerformed(ActionEvent ae)
    			{
    				toggles(100+4);
    			}
    		});
        	recent.add(rc4);
        	
        	rc5 = new JMenuItem(files[4]);
        	rc5.addActionListener(new ActionListener()
    		{
    			@Override
    			public void actionPerformed(ActionEvent ae)
    			{
    				toggles(100+5);
    			}
    		});
        	recent.add(rc5);
        	
        	rc6 = new JMenuItem(files[5]);
        	rc6.addActionListener(new ActionListener()
    		{
    			@Override
    			public void actionPerformed(ActionEvent ae)
    			{
    				toggles(100+6);
    			}
    		});
        	recent.add(rc6);
        	
        	rc7 = new JMenuItem(files[6]);
        	rc7.addActionListener(new ActionListener()
    		{
    			@Override
    			public void actionPerformed(ActionEvent ae)
    			{
    				toggles(100+7);
    			}
    		});
        	recent.add(rc7);
        	
        	rc8 = new JMenuItem(files[7]);
        	rc8.addActionListener(new ActionListener()
    		{
    			@Override
    			public void actionPerformed(ActionEvent ae)
    			{
    				toggles(100+8);
    			}
    		});
        	recent.add(rc8);
        	
        	rc9 = new JMenuItem(files[8]);
        	rc9.addActionListener(new ActionListener()
    		{
    			@Override
    			public void actionPerformed(ActionEvent ae)
    			{
    				toggles(100+9);
    			}
    		});
        	recent.add(rc9);
        	
        	rc10 = new JMenuItem(files[9]);
        	rc10.addActionListener(new ActionListener()
    		{
    			@Override
    			public void actionPerformed(ActionEvent ae)
    			{
    				toggles(100+10);
    			}
    		});
        	recent.add(rc10);
        }
        file.add(recent);
        
        
        exit = new JMenuItem("Exit program", KeyEvent.VK_E);
        exit.addActionListener(this);
        file.add(exit);
        
        //Items of prefs menu
        settings = new JMenuItem("Settings", KeyEvent.VK_S);
        settings.addActionListener(this);
        prefs.add(settings);
        
        rs = new JMenuItem("Resize Player", KeyEvent.VK_R);
        rs.addActionListener(this);
        prefs.add(rs);
        
        //Items of About menu
        abt = new JMenuItem("About Player", KeyEvent.VK_U);
        abt.addActionListener(this);
        about.add(abt);
        
        
        //The Playlist
        xe = xml_energy.getInstance();
        getList();
        jl = new JList(jlist);
        jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jl.addMouseListener(new MouseAdapter()
        {
        	public void mouseClicked(MouseEvent evt)
        	{
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() > 2)
                {
                	toggles(list.locationToIndex(evt.getPoint()));
                }
            }
        });
        playlist = new JScrollPane(jl);
        
        soul_text = new JTextField();
        soul_text.setLocation(400, 300);
        soul_text.setSize(40, 50);
        soul_text.addKeyListener(this);
        soul_text.requestFocusInWindow();
        soul_text.setEditable(true);
        panel.add(soul_text);
        
        return panel;
    }
    
    /**
     * it provides frame all its properties, etc
     * <p> while inheriting some from super.create(){@see Skeleton.myFrame#create()} 
     */
    protected void create()
    {
    	super.create();
    	this.trackButtonChange();
    	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter()
        {
        	@Override
        	public void windowClosing(WindowEvent we)
        	{
        		frame.dispose();
        		toggles("exit");
        		System.exit(0);
        	}
        });
    }
    
    /**
     * gets the list which will be displayed as playlist.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void getList()
    {
    	this.refreshInstances();
    	if(jlist==null)
    	{
    		jlist = xe.getList();
    	}
    	else
    	{
    		DefaultListModel tempjlist = xe.getList();
    		int existing = jlist.getSize();
    		int newer = tempjlist.getSize();
    		
    		if(newer>existing)
    		{
    			do
    			{
    				Object o = tempjlist.get(existing);
    				jlist.addElement(o);
    				existing++;
    			}while(existing<newer);
    		}
    		
    		jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jl.addMouseListener(new MouseAdapter()
            {
            	public void mouseClicked(MouseEvent evt)
            	{
                    JList list = (JList)evt.getSource();
                    if (evt.getClickCount() > 2)
                    {
                    	toggles(list.locationToIndex(evt.getPoint()));
                    }
                }
            });
            playlist = new JScrollPane(jl);
            panel.add(playlist);
    		System.out.println("here");
            playlist.repaint();
    		panel.repaint();
    		frame.repaint();
    		xobj.set("resize", "true");
    	}
    }
    
    /**
     * sends the response of various events to energy object
     * <p> also responds to those after energy have done its work and had thrown appropriate exceptions
     * <p> these exceptions are treated differently based upon type and changes due to these are also reflected in UI
     * @param response these are responses received
     */
    public void toggles(String response)
    {
    	try
    	{
    		energy.toggles(response);
    	}
    	catch(IOException ioe)
    	{
    		System.out.println(ioe.getMessage() +" gdsafjgdsf  "+ ioe.getCause().getMessage());
    		if(ioe.getMessage().equalsIgnoreCase("file added"))
    		{
    			getList();
    		}
    		Exception e = (Exception) ioe.getCause();
    		if((e.getMessage() !=null)&&(e.getMessage()=="playing started"))
    		{
    			if(e.getMessage().equalsIgnoreCase("playing started"))
        		{
        			playing = true;
        			this.trackButtonChange();
        		}
        		else if(e.getMessage().equalsIgnoreCase("playing stopped"))
        		{
        			playing = false;
        			this.trackButtonChange();
        		}
    		}
    	}
    	catch(Exception e)
    	{
    		System.out.println("Message in exception for myCove from energy is ="+e.getMessage());
    		if(e.getMessage().equalsIgnoreCase("playing started"))
    		{
    			playing = true;
    			this.trackButtonChange();
    		}
    		else if(e.getMessage().equalsIgnoreCase("playing stopped"))
    		{
    			playing = false;
    			this.trackButtonChange();
    		}
    	}
    }
    
    /**
     * this manages the click responses from the play and recents list
     * @param i it is the index on which click event is made
     */
    public void toggles(int i)
    {
    	try
    	{
    		energy.toggles(i);
    	}
    	catch(Exception e)
    	{
    		if(e.getMessage()=="playing started")
    		{
    			playing = true;
    			trackButtonChange();
    		}
    		else if(e.getMessage()=="playing stopped")
    		{
    			playing = false;
    			trackButtonChange();
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(frame, e.getMessage()+" Cover", "Error - ML.Player",JOptionPane.ERROR_MESSAGE);
    			e.printStackTrace();
    		}
    	}
    }
    
    /**
     * resizes the Player
     */
    private void resizing()
    {
    	size_index++;
    	if(size_index>3)
    		size_index=1;
    	xobj.set("size", Integer.toString(size_index));
    	xobj.set("resize", "true");
    	frame.dispose();
    	frame.setVisible(false);
    	frame = null;
    	
    	System.gc();
    	mCrunning = false;
    	
    }
    
    /**
     * it tracks events happened in player and changes buttons, etc based upon them
     */
    private void trackButtonChange()
    {
    	
    	//For Play Button
    		if(playing)
    		{
    			if(size_index<2)
    				playBtn.setText("Stop");
    			playBtn.setIcon(new ImageIcon(pauseImg));
    			playBtn.getAccessibleContext().setAccessibleDescription("Stop");
    		}
    		else
    		{
    			if(size_index<2)
    				playBtn.setText("Play");
    			playBtn.setIcon(new ImageIcon(playImg));
    			playBtn.getAccessibleContext().setAccessibleDescription("Play");
    		}
    	
    	
    	//For Shuffle Button
    	
    		if(shuffle)
    		{
    			shuffleBtn.getAccessibleContext().setAccessibleDescription("Shuffle ON");
    			shuffleBtn.setIcon(new ImageIcon(shuffleImg1));
    		}
    		else
    		{
    			shuffleBtn.getAccessibleContext().setAccessibleDescription("Shuffle OFF");
    			shuffleBtn.setIcon(new ImageIcon(shuffleImg2));
    		}
    	
    	//For Repeat Button
    	
    		if(repeat == 1)
    			repeatBtn.setIcon(new ImageIcon(repeatImg1));
    		else if(repeat == 2)
    			repeatBtn.setIcon(new ImageIcon(repeatImg2));
    		else
    			repeatBtn.setIcon(new ImageIcon(repeatImg0));
    	
    }
    
    /**
     * it is responsible for handling of button and menu item click events
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        this.refreshInstances();
        try
        {
    	//Buttons
        if(ae.getSource() == playBtn)
        {
        	this.toggles("play");
        }
        else if(ae.getSource()==previousBtn)
        {
            this.toggles("previous");
        }
        else if(ae.getSource()==nextBtn)
        {
            this.toggles("next");
        }
        else if(ae.getSource()==forwardBtn)
        {
        	this.toggles("forward");
        }
        else if(ae.getSource()==reverseBtn)
        {
        	this.toggles("reverse");
        }
        else if(ae.getSource()==shuffleBtn)
        {
        	if(shuffle)
        	{
        		shuffle = false;
        	}
        	else
        	{
        		shuffle = true;
        	}
            this.toggles("shuffle");
        }
        else if(ae.getSource()==repeatBtn)
        {
        	repeat++;
        	if(repeat>2)
        		repeat=0;
            this.toggles("repeat");
        }
        else if(ae.getSource()==resizeBtn)
        {
        	this.resizing();
        }
        else if(ae.getSource()==closeBtn)
        {
        	this.finalize();
        	System.exit(0);
        }
        
        //Event of Menu bar
    	if(ae.getSource()==open)
    	{
    		this.toggles("add");
    	}
    	else if(ae.getSource()==recent)
    	{
    		//To be added
    	}
    	else if(ae.getSource()==exit)
    	{
    		frame.dispose();
    		this.toggles("exit");
    		System.exit(0);
    	}
    	else if(ae.getSource()==settings)
    	{
    		tsettings.main();
    	}
    	else if(ae.getSource()==rs)
    	{
    		this.resizing();
    	}
    	else if(ae.getSource()==abt)
    	{
    		JOptionPane optionPane = new JOptionPane("Made by Sahil Malik & Kaushalendra Rajput", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
    		JDialog dialog = new JDialog();
    		dialog.setTitle("About ML.Player");
    		dialog.setModal(true);
    		dialog.setContentPane(optionPane);
    		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    		dialog.pack();
    		dialog.setVisible(true);
    	}
    	}
        finally
        {
        	trackButtonChange();
        	soul_text.requestFocusInWindow();
        }
    }
    
    /**
     * It is responsible for key pressed events happened
     */
    @Override
    public void keyPressed(KeyEvent ke)

    {
    	soul_text.requestFocusInWindow();
    	String response = soul.go(ke);
    	if(!response.equalsIgnoreCase("NA"))
    		this.toggles(response);
    }
    
    /**
     * @deprecated
     * Not used
     */
    @Override
    public void keyTyped(KeyEvent ke){}
    
    /**
     * @deprecated
     * Not used
     */
    @Override
    public void keyReleased(KeyEvent ke){}

    /**
     * it sets the running flag as false, if the object is garbage collected
     */
    @Override
    public void finalize()
    {
    	mCrunning = false;
    	super.finalize();
    }
    
}