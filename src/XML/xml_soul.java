package XML;

import java.awt.event.KeyEvent;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import Skeleton.myxml;

/**
 * 
 * <b>ML.Player - XML Parser for Soul</b>
 * <p> - It is a singleton class used to manage soul.xml file
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> -Added formal documentation
 * <p> - Better support for opening custom location files
 *  
 * @author msahil432
 *
 */
public class xml_soul extends myxml
{
    private static xml_soul sxml;
    private int key_index;
    private String[] keys_saved = new String[20];
    private String[] keys_data = new String[20];
	
    /**
     * Constructor to open xml file in default location.
     * <p> It calls {@link #xml_soul(String)} while providing default filepath as String argument
     */
    protected xml_soul()
    {
        this("data//db//soul.xml");
    }
    
    /**
     * Constructor to use while opening custom location files
     *  
     * @param filePath the path to the file to be parsed
     */
    protected xml_soul(String filePath)
    {
        super(filePath);
        key_index=0;
        this.keys_index();
        while(i<20)
        {
        	this.keys_data[i] = new String();
        	this.keys_saved[i] = new String();
        	i++;
        }
    }
    
    /**
     * Used when performing operations on default location file
     * @return sxml,the only created object of this class
     */
    public static xml_soul getInstance()
    {
    	if(sxml==null)
    		sxml = new xml_soul();
    	return sxml;
    }
    
    /**
     * Used when performing operations on custom location file
     * @param filepath filePath the path to the file to be parsed 
     * @return sxml,the only created object of this class
     */
    public static xml_soul getInstance(String filepath)
    {
    	if(sxml==null)
    		sxml= new xml_soul(filepath);
    	return sxml;
    }
    
    /**
     * @deprecated
     * used only for getting preferences of ML stored in soul.xml
     * 
     * @param elementname the field of which saved setting is needed
     */
    @Override @Deprecated
    public String get(String elementname)
    {
    	return super.get(elementname);
    }
    
    /**
     * @deprecated
     * used only for setting preferences of ML stored in soul.xml
     * 
     * @param elementname the field of which saved setting is to be changed
     * @param data the string value to be stored over existing one
     * 
     * @return boolean value, based upon the operation completes or not? 
     */
    @Override @Deprecated
    public boolean set(String elementname, String data)
    {
        return super.set(elementname, data);
    }
    
    /**
     * provides total number of saved ML learned keys in soul.xml
     * 
     * @return integer, number of saved keys
     */
    public int keys_index()
    {
    	i=1;
    	int t=0;
    	while(coverlist.item(i)!=null)
    	{
    		te = coverlist.item(i).getNodeName();
    		if((te.contains("k"))&&(te!=null))
    		{
    			keys_data[t] = coverlist.item(i).getTextContent();
    			keys_saved[t] = te.replace('k', '0');
    			t++;
    		}
    		i++;
    	}
    	key_index= t;
    	return key_index;
    }
    
    /**
     * provides all saved ML key codes of all saved keys
     * @return array of string, containing all keys' codes
     */
    public String[] key_saved()
    {
    	key_index = this.keys_index();
    	return keys_saved;
    }
    
    /**
     * provides all saved ML key functions of all saved keys
     * @return array of string, containing all keys' functions
     */
    public String[] key_data()
    {
    	key_index = this.keys_index();
    	return keys_data;
    }
    
    /**
     * used to save a key code and its associated function in soul.xml
     * @param ke key event of whose code would be saved
     * @param task function of that key
     */
    public void add_key(KeyEvent ke, String task)
    {
    	this.keys_index();
    	Element element = doc.getDocumentElement();				//Getting type of XML Doc
    	for(int i=2; i <= this.keys_index();i++)								//Checking if key already exists
    	{
    		temp = coverlist.item(i);
    		if((keys_data==null)||(keys_saved==null))
    		{
    			break;
    		}
    		else if(task.equalsIgnoreCase(temp.getTextContent())||(Integer.parseInt(temp.getNodeName().replace('k', '0'))==ke.getKeyCode()))						//Comparing keys
    		{
    			element.removeChild(temp);
    			break;
    		}
    	}
    	Node n = doc.createElement("k"+ke.getKeyCode());
    	n.setTextContent(task);
    	System.out.println("Key saved is "+ke.getKeyCode()+ " and data is "+task);
    	element.appendChild(n);
    	super.savetofile();
    	keys_index();
    }
    
   /**
    * Resets the soul.xml file to default values
    * 
    * @param s provides info about from where the reset was triggered, O/P only to cmd/terminal
    */
   @Override
    public void reset(String s)
    {
    	super.reset("xml energy "+s);
    	Element e = doc.getDocumentElement();
    	temp = coverlist.item(1);
    	temp.setTextContent("2");											//Magic No. Reseted to default value,2
    	int i =2;
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