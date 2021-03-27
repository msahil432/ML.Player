package XML;

import javax.swing.DefaultListModel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import Skeleton.myxml;

/**
 * 
 * <b>ML.Player - XML Parser for Energy</b>
 * <p> - It is a singleton class which is used to manage energy.xml
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Added Formal Documentation
 * <p> - Better support for opening custom location files
 * <p> - Tweaks in {@link #getInstance()} method
 * <p> - Tweaks in {@link #getPrevious()} and {@link #getnext()} methods to reduce code repetition
 * @author msahil432
 *
 */
public class xml_energy extends myxml
{
	/** Points at position of current played song*/
    private static int current=4;
    /**the only object of the class*/
    private static xml_energy x;
    
    /**
     *  Constructor to open xml file in default location.
     * <p> It calls {@link #xml_energy(String)} while providing default filepath as String argument
     */
    protected xml_energy()
    {
    	this("data//db//energy.xml");
    }
    
    /**
     * Constructor to use while opening custom location files
     *  
     * @param filepath the path to the file to be parsed
     */
    protected xml_energy(String filepath)
    {
        super(filepath);
        
        if(super.get("savePlaylist").equalsIgnoreCase("false"))		//To clear Playlist from last run, if required.
        {
        	clear("xml_Energy.java");
        }
    }

    /**
     * to get number of songs in the energy.xml file
     * @return integer, number of songs
     */
    public static int count()
    {
    	if(x!=null)
    		x = xml_energy.getInstance(); 
    	int count = x.coverlist.getLength()-4;							//Number of songs present
    	return count;
    }
    
    /**
     * Used when performing operations on default location file
     * @return x,the only created object of this class
     */
    public static xml_energy getInstance()
    {
    	if(x==null)
    		x = new xml_energy();
        return x;																						//Returning instance of this class. 
    }
    
    /**
     * Used when performing operations on custom location file
     * @param filepath filePath the path to the file to be parsed 
     * @return x,the only created object of this class
     */
    public static xml_energy getInstance(String filepath)
    {
    	if(x==null)
    		x= new xml_energy(filepath);
    	return x;
    }
   
    /**
     * checks if song already exists in list
     * 
     * @return boolean, true if exits otherwise false.
     */
    @Override
    public boolean match(String content)
    {
    	check = false;
    	
    	if(count()>0)
    		check = super.match(content);
    	
    	return check;
    }
   
    /**
     * for getting current song
     * <p> doesn't move song pointer
     * 
     * @return String, file path of the current song
     */
    public String get()
    {
        
        temp = coverlist.item(current);
        te = temp.getTextContent();
        return te;
    }
    
    /**
     * for getting previous song
     * <p> moves song pointer to previous location
     * 
     * @return String, file path of the previous song
     */
    public String getPrevious()
    {
    	String song = null;
    	if((current-4)<2)
    		song = "null";
    	else
    	{
    		current -= 1;
    		song = get();
    	}
    		
    	return song;
    }
    
    /**
     * for getting next song
     * <p> moves song pointer to next song
     * @return String, file path of next song
     */
    public String getnext()															//For Getting next Song
    {
        if((current-4)<count())
        {
        	current+=1;
        	te = get();
        }
        else
        	te = "null";
        return te;
    }
    
    /**
     * @deprecated to be used only for getting preferences
     * 
     * @param elementname preference which is needed
     * 
     * @return String Value, saved in preferences
     */
    @Deprecated @Override
    public String get(String elementname)
    { 
    	if(elementname.contains("song"))
    	{
    		super.find(elementname);
    		current = i;
    		System.out.println(current+" "+super.get(elementname));
    	}
        return super.get(elementname);
    }
    
    /**
     * @deprecated to be used only for setting preferences
     * 
     * @param elementname preference which is to be changed
     * @param data new preference setting which is to be saved
     * 
     * @return boolean, whether the operation was successful or not
     */
    @Deprecated @Override
    public boolean set(String elementname, String data)		//Cautioned Use of this function
    {
    	return super.set(elementname, data);
    }
    
    /**
     * to add a song in energy.xml
     * @param data file path of song
     * @return integer, total number of songs
     */
    public int set(String data)
    {
    	Element element = doc.getDocumentElement();
        Node n = doc.createElement("song"+count());
		element.appendChild(n);
        n.setTextContent(data);
        XML.xml_recents.getInstance().set(data);
        
        this.savetofile();
        return count();
    }

    /**
     * resets the playing preferences to default
     * 
     * @param source some data to track from where the reset was triggered 
     */
    @Override
    public void reset(String source)
    {
    	super.reset(" energy xml - "+source);
    	
    	clear(" while resetting ");
    	
    	temp = coverlist.item(1);
    	temp.setTextContent("0");
    	
    	int i =2;
    	while(i<4)
    	{
    		temp = coverlist.item(i);
    		temp.setTextContent("false");								//Reseting saved functions
    		i++;
    	}
    	while(i<coverlist.getLength())
    	{
    		temp = coverlist.item(i);									//clearing playlist, if any
    		temp.setTextContent("");
    		i++;
    	}
    }
    
    /**
     * provides the last added song
     * 
     * @return String, file path of last song
     */
    public String getLast()
    {
    	current = count();
    	return get();
    }
    
    /**
     * provides song list for playlist
     * @return DefaultListModel, containing all the songs
     */
    @SuppressWarnings("rawtypes")
	DefaultListModel dlist;
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultListModel getList()										//Returning list for Playlist 
    {
    	if(count()==0)
    	if(super.get("savePlaylist").equalsIgnoreCase("false"))
    		clear("While JList creation");
    	
    	if(dlist!=null)
    	{
    		dlist = null;
    	}
    	dlist = new DefaultListModel();

    	int i =4, length = coverlist.getLength();
    	String song, stemp[];
    	while(i<=length)
    	{
    		temp = coverlist.item(i);
    		i++;
    		try
    		{
    			stemp = org.apache.commons.lang3.StringUtils.split(temp.getTextContent(),"\\");
    			song = stemp[(stemp.length-1)];
    			if(song!=" ")
    			{
    				dlist.addElement(song);
    			}
    		}
    		catch(Exception e)
    		{
    			i++;
    			break;
    		}
    	}
    	
    	return dlist;
    }
    
    /**
     * clears the songs in energy.xml
     * 
     * @param source to track the source of reset command
     */
    public void clear(String source)
    {
    	Element e = doc.getDocumentElement();
    	int i =4;
    	Node n;
    	while(i<coverlist.getLength())
    	{
    		n = coverlist.item(i);
    		e.removeChild(n);
    		i++;
    	}
    	this.savetofile();
    }
    
}
