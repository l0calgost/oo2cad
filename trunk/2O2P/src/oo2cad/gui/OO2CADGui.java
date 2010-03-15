package oo2cad.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import oo2cad.gui.action.OO2CADGuiActionHandler;

public class OO2CADGui extends JFrame
{
	/*
	 * Komponenten
	 */
	private JButton closeButton;
	private JButton convertButton;

	private JButton sourceButton;
	private JButton destButton;
	
	private JTextField sourcePathTextField;
	private JTextField destPathTextField;
	
	private JTextField measureXTextField;
	private JTextField measureYTextField;
	
	private JTextField offSetWidthTextField;
	private JTextField offSetHeightTextField;
	
	private JLabel measureLabelX;
	private JLabel measureLabelY;
	
	private JLabel offSetLabelHeight;
	private JLabel offSetLabelWidth;
	
	OO2CADGuiActionHandler actionHandler = new OO2CADGuiActionHandler(this);

	
	
	public void createWindow()
	{
		//Look and Feel
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//JFrame
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new GridLayout(0,2,6,3));		
		
		//Button Labeln		
		sourceButton = new JButton("Select");
		destButton = new JButton("Save");
		
		measureLabelX = new JLabel("Verhältnis");
		measureLabelY = new JLabel("Verhältnis");
		
		offSetLabelHeight = new JLabel("Offset Höhe:(X) ");
		offSetLabelWidth = new JLabel("Offset Width:(Y) ");
		
		convertButton = new JButton("Start...");
		closeButton = new JButton("Close");
		
		//TextFelder erzeugen
		sourcePathTextField = new JTextField();
		destPathTextField = new JTextField();
		
		measureXTextField = new JTextField();
		measureYTextField = new JTextField();
		
		offSetHeightTextField = new JTextField();
		offSetWidthTextField = new JTextField();
		
		//OpenFile Dialog
		JFileChooser jfSource = new JFileChooser();
		jfSource.setDialogType(JFileChooser.OPEN_DIALOG);
		jfSource.setFileSelectionMode(JFileChooser.FILES_ONLY);
		File file = new File("C:\\");
		jfSource.setCurrentDirectory(file);
		
		JFileChooser jfDest = new JFileChooser();
		jfDest.setDialogType(JFileChooser.SAVE_DIALOG);
		
		sourceButton.addActionListener(actionHandler);
		
		
		//Source Pfad
		jf.add(sourceButton);
		jf.add(sourcePathTextField);		
		//Dest Pfad
		jf.add(destButton);
		jf.add(destPathTextField);
		
		
		// Größenverhältnis
		jf.add(measureLabelX);
		jf.add(measureXTextField);
		jf.add(measureLabelY);
		jf.add(measureYTextField);
		
		//Offset
		jf.add(offSetLabelHeight);
		jf.add(offSetHeightTextField);
		jf.add(offSetLabelWidth);
		jf.add(offSetWidthTextField);		
		
		//Close und Convert Button
		jf.add(convertButton);
		jf.add(closeButton);
		
		jf.pack();
		jf.setVisible(true);
	}	
}
