package Skeleton;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * <b>ML.Player - myxml, the super class for all xml parser files</b>
 * <p> - It was mainly created to minimize the code repetition
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - Formal Documentation added
 * <p> - {@link #finalize()} added
 * <p> - {@link #savetofile()} return type changed to boolean from void
 * 
 * @author msahil432
 *
 */
public abstract class myxml
{
    protected NodeList coverlist;
    protected Node temp;
    protected int i=0;
    protected Document doc;
    protected String filepath, te;
    protected boolean check;
    
    /**
     * This constructor also contains the method which makes the program (XML parts) run on 
     * <p>both WIndows and Unix/Linux based systems without any alternations.
     * @param filePath the path to the file to be parsed
     */
    protected myxml(String filePath)
    {
        try
        {
        	if((System.getProperty("os.name").contains("Linux"))&&(filePath.contains("\\")))
        		filepath = filePath.replace('\\', '/');
        	else if ((System.getProperty("os.name").contains("Windows"))&&(filePath.contains("/")))
        		filepath = filePath.replace('/', '\\');
        	else
        		filepath = filePath;
        	System.out.println("XML File opened  "+filepath);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(filepath);
            coverlist = doc.getElementsByTagName("*");
         }
        catch(Exception e)
        {
        	JOptionPane.showMessageDialog(null, e.getMessage()+", XML File Read Error", "Error - ML.Player",JOptionPane.ERROR_MESSAGE);
        	e.printStackTrace();
        }
    }
    
    /**
     * A custom designed method to check whether a content is already present in XML or not. 
     * @param content the string which is checked to be existed or not
     * @return true/false, based upon whether content was found or not.
     */
    public boolean match(String content)
    {
        check=false;
        i=0;
        while((temp = coverlist.item(i))!=null)
        {
            String tem = temp.getTextContent();
            
            if(tem.compareToIgnoreCase(content)==0)
            {
                check = true;
                break;
            }
            i++;
        }
        return check;
    }

    /**
     * A custom designed method to check whether an element or field is present in XML or not.
     * @param elementname the string which is checked to be existed or not
     * @return true/false, based upon whether content was found or not.
     */
    public boolean find(String elementname)
    {
    	check = false;
    	i=0;
    	while((temp = coverlist.item(i))!=null)
        {
            String tem = temp.getNodeName();
            
            if(tem.compareToIgnoreCase(elementname)==0)
            {
                check = true;
                break;
            }
            i++;
        }
     	return check;
    }
    
    /**
     * Read content from XML
     * @param elementname the name of field of whose data is needed
     * @return data of the elementname field, returns NA when no such field is present
     */
    public String get(String elementname)
    {
        te="NA";
        try
        {
            check = this.find(elementname);
            if(check)
                te = temp.getTextContent();
            else
                te = "NA";
        }
        catch(Exception e)
        {
        	JOptionPane.showMessageDialog(null, e.getMessage()+", XML Object Read Error", "Error - ML.Player",JOptionPane.ERROR_MESSAGE);
        	e.printStackTrace();
        }
        return te;
    }
    
    /**
     * Save data to XML
     * @param elementname the field in which data is to be saved
     * @param data the data which would be saved in the field
     * @return true/false, whether the saving of data was successful or not.
     */
    public boolean set(String elementname, String data)
    {
    	check=false;
        if((elementname == null)||(data == null))
        	JOptionPane.showMessageDialog(null, "An error occured while saving data in xml, Null data", "Dialog",JOptionPane.ERROR_MESSAGE);
        try
        {
            check=this.find(elementname);
            if(check)
            {
                temp.setTextContent(data);
                check = this.savetofile();
            }
        }
        catch(Exception e)
        {
        	JOptionPane.showMessageDialog(null, e.getMessage()+", XML Write Object Error", "Error - ML.Player",JOptionPane.ERROR_MESSAGE);
        	check = false;
        }
        return check;
    }
    
    /**
     * Save the XML object data to XML file on disk
     */
    public boolean savetofile()
    {
    	boolean b = false;
        try
        {
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer trans = transFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(filepath));
            trans.transform(domSource, streamResult);
            b = true;
        }
        catch(Exception e)
        {
        	JOptionPane.showMessageDialog(null, e.getMessage()+", XML Disk Write Error", "Error - ML.Player",JOptionPane.ERROR_MESSAGE);
        	b = false;
        }
        return b;
    }
    
    /**
     * Tracks from where the reset was triggered
     * @param s provides the info about source
     */
    public void reset(String s)
    {
    	System.out.println("Reset Command - "+s);
    }

    /**
     * To make sure that all object data is saved to disk before being garbage collected.
     */
    @Override
    public void finalize()
    {
    	this.savetofile();
    	try
    	{super.finalize();}
    	catch(Throwable e)
    	{e.printStackTrace();}
    }
    
}
