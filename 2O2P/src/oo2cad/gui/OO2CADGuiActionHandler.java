package oo2cad.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;


public class OO2CADGuiActionHandler implements ActionListener
{

	private OO2CADGui gui;
	
	public OO2CADGuiActionHandler(OO2CADGui gui)
	{
		this.gui = gui;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == gui.getSourceButton())
		{
			//OpenFile Dialog
			JFileChooser jfSource = new JFileChooser();
			jfSource.setDialogType(JFileChooser.OPEN_DIALOG);
			jfSource.setFileSelectionMode(JFileChooser.FILES_ONLY);
			//ExampleFileFilter filter = new ExampleFileFilter();
			File file = new File("C:\\");
			jfSource.setCurrentDirectory(file);
			jfSource.showOpenDialog(gui);
											
			gui.getSourcePathTextField().setText(jfSource.getSelectedFile().toString());
		}
		if(e.getSource() == gui.getDestButton())
		{
			//OpenFile Dialog
			JFileChooser jfSource = new JFileChooser();
			jfSource.setDialogType(JFileChooser.SAVE_DIALOG);
						
			//jfSource.setFileFilter(filter);
			//ExampleFileFilter filter = new ExampleFileFilter();
			File file = new File("C:\\");
			jfSource.setCurrentDirectory(file);
			jfSource.showOpenDialog(gui);
											
			gui.getDestPathTextField().setText(jfSource.getSelectedFile().toString());
		}
		
		if(e.getSource() == gui.getCloseButton())
		{
			gui.setVisible(false);
			gui.dispose();
			System.exit(0);
		}
		
	}

}
