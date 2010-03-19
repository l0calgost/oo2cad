package oo2cad.gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;


/**
 * 
 * @author schugt
 * File Filter
 */
public class OO2CADGuiFileFilter extends FileFilter
{
	String _ending;
	
	public OO2CADGuiFileFilter(String ending)
	{
		this._ending = ending;
	}

	@Override
	public boolean accept(File f)
	{
		return f.getName().endsWith("."+_ending) || f.isDirectory();
	}

	@Override
	public String getDescription()
	{
		if(_ending.equals("odg"))
		{
			return "OpenOfficeDraw(*.odg)";
		}
		else 
		{
			return "PictureByPc(*.vec)";
		}
		
	}

}
