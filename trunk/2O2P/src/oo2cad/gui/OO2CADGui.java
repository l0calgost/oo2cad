package oo2cad.gui;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import oo2cad.config.Config;


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
	
	private JTextField offSetXTextField;
	private JTextField offSetYTextField;
	
	private JLabel measureLabelX;
	private JLabel measureLabelY;
	
	private JLabel offSetLabelHeight;
	private JLabel offSetLabelWidth;
	
	MaskFormatter mask;
	
	OO2CADGuiActionHandler actionHandler = new OO2CADGuiActionHandler(this);
	
	//Config
	Config config = Config.getInstance();
	
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
		JFrame jf = new JFrame("OO2CAD");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new GridLayout(0,2,6,3));		
		jf.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getSize().width / 2, 
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getSize().height / 2);
		
		//Button Labeln		
		sourceButton = new JButton("Select ODG File");
		destButton = new JButton("Save CAD File");
		
		measureLabelX = new JLabel("Maßstab");
		measureLabelY = new JLabel(":");
		
		offSetLabelHeight = new JLabel("Offset Höhe:(X) ");
		offSetLabelWidth = new JLabel("Offset Breite:(Y) ");
		
		convertButton = new JButton("Start...");
		closeButton = new JButton("Close");
		
		//TextFelder erzeugen
		sourcePathTextField = new JTextField();
		destPathTextField = new JTextField();	
		
		
		measureXTextField = new JTextField();		
		measureXTextField.setText(Float.toString(config.getScaleInc()));
		//Eingabeprüfen
		measureXTextField.getDocument().addDocumentListener(new OO2CADTextFieldListener());
		
		measureYTextField = new JTextField();
		measureYTextField.setText(Float.toString(config.getScaleDec()));
		
		offSetYTextField = new JTextField();
		offSetYTextField.setText(Float.toString(config.getOffSetY()));
		
		offSetXTextField = new JTextField();
		offSetXTextField.setText(Float.toString(config.getOffSetY()));
		
		//ActionListener's		
		sourceButton.addActionListener(actionHandler);
		destButton.addActionListener(actionHandler);
		
		convertButton.addActionListener(actionHandler);
		closeButton.addActionListener(actionHandler);
		
		
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
		jf.add(offSetYTextField);
		jf.add(offSetLabelWidth);
		jf.add(offSetXTextField);		
		
		//Close und Convert Button
		jf.add(convertButton);
		jf.add(closeButton);
		
		jf.pack();
		jf.setVisible(true);
	}


	public JButton getCloseButton()
	{
		return closeButton;
	}


	public JButton getConvertButton()
	{
		return convertButton;
	}


	public JButton getSourceButton()
	{
		return sourceButton;
	}


	public JButton getDestButton()
	{
		return destButton;
	}


	public JTextField getSourcePathTextField()
	{
		return sourcePathTextField;
	}


	public JTextField getDestPathTextField()
	{
		return destPathTextField;
	}


	public JTextField getMeasureXTextField()
	{
		return measureXTextField;
	}


	public JTextField getMeasureYTextField()
	{
		return measureYTextField;
	}


	public JTextField getOffSetWidthTextField()
	{
		return offSetXTextField;
	}

	public JTextField getOffSetHeightTextField()
	{
		return offSetYTextField;
	}	
	
	
}
