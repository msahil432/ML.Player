package Soul;

import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import XML.xml_soul;

/**
 * 
 * <b>ML.Player - Soul - The ML Component</b>
 * <p> - This manages all the ML related (only keys, till now) works in the program
 * <p> - Singleton class, and its object is also not passed outside
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - added formal documentation
 * <p> - removed <code> private void getInstance() </code> method, as of never practically used 
 * 
 * @author msahil432
 *
 */
public class soul
{
	private static soul ML = new soul();										//Singleton object of Soul class
	private static xml_soul sxml;												//Instance of xml_soul object
	/**
	 * Array of objects of key_details for saved keys
	 */
	private key_details keys[] = new key_details[20];
	/**
	 * Array of objects of key_details for new keys
	 */
	private key_details nkeys[] = new key_details[20];
	private String response = new String("NA");
	private int key_saved=-1, nkey=-1, i=0;										//Key_saved is for no of keys saved and nkey is no of new keys to be added
	private KeyEvent ke;
	
	/**
	 * private constructor as of singleton class
	 */
    private soul()
    {
    	refreshInstances();
    	nkey=-1;
    	key_saved = sxml.keys_index();
    }
    
    /**
     * Refreshes and gets the instance of XML_soul class
     */
    public static void refreshInstances()
    {
    	sxml = xml_soul.getInstance();
    }
    
    /**
     * its object is never passed, so it is the only way to interact with this class
     * @param ke key_event which caused to interact with ML 
     * @return String value based upon whether the key is already saved, then functions or is to be saved, then "NA" 
     */
    public static String go(KeyEvent ke)
    {
    	ML.response= "NA";
    	
    	int kc = ke.getKeyCode();
    	ML.getKeys();
    	ML.ke = ke;
    	
    	for(ML.i=0; ML.i<ML.key_saved; ML.i++)
    	{
    		int temp = Integer.parseInt(ML.keys[ML.i].getKeycode());
    		if(kc==temp)
    		{
    			System.out.println(kc +"  "+temp);
    			ML.response = ML.keys[ML.i].getKeydata();
    			break;
    		}
    	}
    	if(ML.response.equalsIgnoreCase("NA"))
    	{
    		ML.toadd();
    	}
    	System.out.println("Response sent for key "+ke.getKeyCode()+" is "+ML.response);
    	return ML.response;
    }
    
    /**
     * gets the saved keys from the XML
     */
    private void getKeys()
    {
    	refreshInstances();
    	key_saved = sxml.keys_index();
    	for(i=0; i<key_saved; i++)
    	{
    		keys[i] = new key_details(sxml.key_saved()[i], sxml.key_data()[i]);
    	}
    }
    
    /**
     * used tracking of keys, the number of times pressed
     */
    @SuppressWarnings("deprecation")
	private void toadd()
    {
    	boolean not_matched = true;
    	int keycode = ke.getKeyCode();
    	for(i=0; i<nkey; i++)
    	{
    		int savedcode = Integer.parseInt(nkeys[i].getKeycode());
    		if(keycode==savedcode)
    		{
    			long systemtime = System.currentTimeMillis()/1000;
    			long timegot = Long.parseLong(nkeys[i].getKeydata()); 
    			if(systemtime<(timegot+(Long.parseLong(sxml.get("magicno")))))
    			{
    				this.save_the_key();
    				this.getKeys();
    			}
    			else
    			{
    				nkeys[i].setKeydata(String.valueOf(System.currentTimeMillis()/1000));
    			}
    			not_matched=false;
    			break;
    		}
    	}
    	if(not_matched)
    	{
    		nkey++;
    		nkeys[nkey] = new key_details();
    		nkeys[nkey].setKeycode(String.valueOf(ke.getKeyCode()));
    		nkeys[nkey].setKeydata(String.valueOf(System.currentTimeMillis()/1000));
    	}
    }
    
    /**
     * saving the key to ML with a function
     * <p> the function is determined using a GUI dialog box
     */
    private void save_the_key()																//It displays a GUI to help assign function to key and save to xml
    {
    	Object possibilites[] = {"Play", "Pause", "Play/Pause", "Previous", "Next", "Rewind", "Forward", "Stop", "Shuffle", "Repeat", "Close"};
    	response = (String) JOptionPane.showInputDialog(null, "For key "+ ke.getKeyChar(), "Choose action", JOptionPane.QUESTION_MESSAGE, null, possibilites, possibilites[1]);
    	if(response !=null)
    	{
    		if((response=="Play/Pause")||(response=="Pause"))
    			response="play";
    		soul.refreshInstances();
    		sxml.add_key(ke, response.toLowerCase());
    		System.out.println("You chose "+ response.toLowerCase());
    	}
    }
}