package XML;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import Skeleton.myxml;
/**
 * 
 * <b>ML.Player - XML Parser for Recents</b>
 * <p> - It is a singleton class used to manage recents.xml
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Formal documentation added
 * <p> - Better support for opening custom location files
 * <p> - Now only the name(File name) of songs are shown, not whole file address
 * 
 * @author msahil432
 *
 */
public class xml_recents extends myxml
{
	/** the only object of the class*/
	private static xml_recents xr;
	
	protected String files[];
	private int count;
	
	/**
     *  Constructor to open xml file in default location.
     * <p> It calls {@link #xml_energy(String)} while providing default filepath as String argument
     */
    protected xml_recents()
	{
		this("data\\db\\recents.xml");
	}
    
    /**
     * Constructor to use while opening custom location files
     *  
     * @param filepath the path to the file to be parsed
     */
    protected xml_recents(String filePath)
	{
		super(filePath); 
		
		count = coverlist.getLength()-1;
	}
    
    /**
     * Used when performing operations on default location file
     * @return xr,the only created object of this class
     */
    public static xml_recents getInstance()
	{
		if(xr==null)
			xr = new xml_recents();
		
		return xr;
	}
	
	/**
     * Used when performing operations on custom location file
     * @param filepath filePath the path to the file to be parsed 
     * @return xr,the only created object of this class
     */
    public static xml_recents getInstance(String filepath)
    {
    	if(xr==null)
    		xr= new xml_recents(filepath);
    	return xr;
    }
	
    /**
     * @deprecated not used
     */
	@Override @Deprecated
	public String get(String elementname)
	{return super.get(elementname);}
	
	/**
	 * @deprecated not used
	 */
	@Override @Deprecated
	public boolean find(String elementname)
	{return super.find(elementname);}
	
	/**
	 * @deprecated not used
	 */
	@Override @Deprecated
	public boolean set(String elementname, String data)
	{return super.set(elementname, data);}
	
	/**
	 * adding songs to recents.xml
	 * @param data file path of song added
	 */
	public void set(String data)
	{
		Element element = doc.getDocumentElement();							//Getting type of XML Doc
		
		//Handling already existing songs
		int a=1;
		if(count>0)
		{
			while(a<coverlist.getLength())
			{
				if(data.equalsIgnoreCase(coverlist.item(a).getTextContent()))
				{
					element.removeChild(coverlist.item(a));
					break;
				}
				a++;
			}
		}
		
		//Adding files
		if(count<10)
		{
			Node n = doc.createElement("song"+count);
			n.setTextContent(data);
			element.insertBefore(n, coverlist.item(1));
			count++;
	    }
		else if(count==10)
		{
			 Node n = coverlist.item(10);
			 element.removeChild(n);
			 
			 n = doc.createElement("song"+count);
		     n.setTextContent(data);
		     element.insertBefore(n, coverlist.item(1));
		}
		this.savetofile();
	}
	
	/**
	 * provides list of recent songs
	 * @return Array of String, which contains the songs
	 */
	public String[] getFiles()
	{
		int i =1;
		if(coverlist.getLength()>i)
		{
			files = new String[10];
			String [] te;
			while(i<coverlist.getLength())
			{
				temp = coverlist.item(i);
				String tf = temp.getTextContent();
				if((tf!=" ")&&(tf!=null))
				{
					te = (tf.contains("\\"))?tf.split("\\"):tf.split("/");
					tf = te[te.length-1];
					System.out.println(tf);
					files[i-1] = tf;
				}
				i++;
			}
		}
		else
			files = null;
		return files;
	}
	
	/**
	 * resets the recents.xml to default values
	 * 
	 * @param source info which can be used to track source of reset command
	 */
	public void reset(String source)
	{
		Element e = doc.getDocumentElement();
    	int i =1;
    	Node n;
    	while(i<coverlist.getLength())
    	{
    		n = coverlist.item(i);
    		System.out.println(n.getNodeName());
    		e.removeChild(n);
    		i++;
    	}
    	super.reset(source);
    	this.savetofile();
	}
}
