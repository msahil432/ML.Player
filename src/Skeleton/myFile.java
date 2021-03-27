package Skeleton;

import java.io.File;

/**
 * 
 * <b>ML.Player - myFile</b>
 * <p> - Every file in program is opened using this class, not in-built java.io.File class.
 * <p> - It makes the path to be same used for Linux and Windows systems.
 * <p> - The user or dev don't have to edit the file paths before running it on various systems. 
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1s -> 0.2c</b>
 * <p> - added formal documentation
 * @author msahil432
 *
 */
public class myFile extends File
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7799638059255874576L;

	/**
	 * this is the only tweaked area of File class
	 * <p> it just calls the super() constructor after editing the path
	 * @param pathname the path of file to be opened
	 */
	public myFile(String pathname)
	{
		super((System.getProperty("os.name").contains("Windows"))?pathname.replace('/', '\\'):pathname.replace('\\', '/'));
	}

}
