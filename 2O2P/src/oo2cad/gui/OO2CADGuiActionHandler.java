package oo2cad.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import oo2cad.OO2CAD;
import oo2cad.config.Config;
import oo2cad.exception.OO2CADException;
import oo2cad.unzip.Unzip;


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
			
			File file = new File("C:\\");
			jfSource.setCurrentDirectory(file);
			jfSource.setAcceptAllFileFilterUsed(false);
			
			//File filter
			FileFilter filter = new OO2CADGuiFileFilter("odg");
			jfSource.setFileFilter(filter);
			
			jfSource.showOpenDialog(gui);
			
			//Angewählt ja nein?
			if(jfSource.getSelectedFile() != null)
			{
				gui.getSourcePathTextField().setText(jfSource.getSelectedFile().toString());
			}
			else
			{
				;
			}
			
			/*
			 * Preview bild anzeigen lassen 
			 */
			String pfad = "\\\\adv3ksrv\\daten\\schueler\\bki\\I31\\schugt\\Desktop\\preview.png";
			try {
				File preview = new Unzip().extractFile(gui.getSourcePathTextField().getText(), pfad , "Thumbnails/thumbnail.png");
				Icon previewBild = new ImageIcon(preview.getAbsolutePath().toString());
				gui.setPreviewPictureImage(previewBild);
				//Datei wieder löchen
				File filePreview = new File(pfad);
				filePreview.delete();
				
			} catch (OO2CADException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == gui.getDestButton())
		{
			//OpenFile Dialog
			JFileChooser jfDest = new JFileChooser();
			jfDest.setDialogType(JFileChooser.SAVE_DIALOG);
			jfDest.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
						
			File file = new File("C:\\");
			jfDest.setCurrentDirectory(file);
			
			//File filter
			FileFilter filter = new OO2CADGuiFileFilter("vec");
			jfDest.setFileFilter(filter);
			jfDest.setAcceptAllFileFilterUsed(false);
			
			jfDest.showSaveDialog(gui);
			
						
			if(jfDest.getSelectedFile() != null)
			{
				
				if(!(jfDest.getSelectedFile().toString().endsWith(".vec")))
				{
					gui.getDestPathTextField().setText(jfDest.getSelectedFile().toString() + ".vec");
				}
				else
				{
					gui.getDestPathTextField().setText(jfDest.getSelectedFile().toString());
				}
				
			}
			else
			{
				;
			}
		}
		if(e.getSource() == gui.getConvertButton())
		{
			//Überprüfung ob die Angaben in der Gui stimmen
			if(validateInput(gui))
			{
				//OK
				Config config = Config.getInstance();
				config.setSourceFilePath(gui.getSourcePathTextField().getText());
				config.setDestFilePath(gui.getDestPathTextField().getText());
				config.setScaleInc(Float.parseFloat(gui.getMeasureXTextField().getText()));
				config.setScaleDec(Float.parseFloat(gui.getMeasureYTextField().getText()));
				config.setOffSetX(Float.parseFloat(gui.getOffSetWidthTextField().getText()));
				config.setOffSetY(Float.parseFloat(gui.getOffSetHeightTextField().getText()));
				OO2CAD.start();
			}
			else
			{
				//False
			}
		}
		
		if(e.getSource() == gui.getCloseButton())
		{
			gui.setVisible(false);
			gui.dispose();
			System.exit(0);
		}
		
	}
	
	public boolean validateInput(OO2CADGui gui)
	{
		boolean error = true;
		
		if(gui.getSourcePathTextField().getText().equals(""))
		{
			//Error Message
			JOptionPane.showMessageDialog(gui, "Quellpfad darf nicht leer sein", "Error", JOptionPane.ERROR_MESSAGE);			
			error = false;
		}
		if(gui.getDestPathTextField().getText().equals(""))
		{
			JOptionPane.showMessageDialog(gui, "Zielpfad darf nicht leer sein", "Error", JOptionPane.ERROR_MESSAGE);			
			error = false;
		}
		//Maßstab
		if(gui.getMeasureYTextField().getText().equals(""))
		{
			gui.getMeasureYTextField().setText("1");
		}
		if(gui.getMeasureXTextField().getText().equals(""))
		{
			gui.getMeasureXTextField().setText("1");
		}
		//Offset
		if(gui.getOffSetHeightTextField().getText().equals(""))
		{
			gui.getOffSetHeightTextField().setText("0");
		}
		if(gui.getOffSetWidthTextField().getText().equals(""))
		{
			gui.getOffSetWidthTextField().setText("0");
		}
		
		return error;
	}

}
