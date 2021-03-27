package Soul;

/**
 * 
 * <b>ML.Player - Key Details</b>
 * <p> - Used for storing details of single key
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - added formal documentation
 * 
 * @author msahil432
 *
 */
class key_details
{
	private String keycode;
	private String keydata;
	private static int length=0;
	
	/**
	 * calling {@link #key_details(String, String)} while giving (null, null) as parameters
	 */
	public key_details()
	{
		this(null, null);
	}
	
	/**
	 * 
	 * @param keycode key code
	 * @param keydata data associated with the key, either function or time
	 */
	public key_details(String keycode, String keydata)
	{
		if((keycode!=null) && (keydata!=null))
		{
			this.keycode = keycode;
			this.keydata = keydata;
			length++;
		}
	}

	/**
	 * 
	 * @return provides key code
	 */
	public String getKeycode()
	{
		return keycode;
	}

	/**
	 * sets the key code
	 * @param keycode the code of key
	 */
	public void setKeycode(String keycode)
	{
		this.keycode = keycode;
	}

	/**
	 * @return data associated with key
	 */
	public String getKeydata()
	{
		return keydata;
	}

	/**
	 * saves the data of a key
	 * @param keydata String data to be saved
	 */
	public void setKeydata(String keydata)
	{
		this.keydata = keydata;
	}

	/**
	 * the length of the array of this object
	 * @return length in integer
	 */
	public int getLength()
	{
		return length;
	}
}
