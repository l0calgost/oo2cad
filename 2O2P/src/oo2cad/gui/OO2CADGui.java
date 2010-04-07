package oo2cad.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	private JLabel sourcePathLabel;
	private JLabel destPathLabel;
	
	private JLabel measureLabelX;
	private JLabel measureLabelY;
	
	private JLabel offSetLabelHeight;
	private JLabel offSetLabelWidth;
	
	private JLabel previewLabel;
	private JLabel previewPicture;
	
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
		//JFrame jf = new JFrame("OO2CAD");
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//jf.setLayout(new GridLayout(0,2,6,3));		
		
		//JPanel fŸr GridBagLayout
		//JPanel jp = new JPanel(new GridBagLayout());
		//GridBagConstraints bgc = new GridBagConstraints();
		//bgc.fill = GridBagConstraints.HORIZONTAL;
		
		JFrame jf = new JFrame("OO2CAD");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//Abstand
		gbc.insets = new Insets(2, 2, 2, 2);
		
		//Programm Icons
		Icon iconAdd = new ImageIcon("/Users/tobi/Documents/workspace/2o2cad/src/icons/folder_add.png"); ///2o2cad/src/icons/folder_add.png
		Icon iconFind = new ImageIcon("../../icons/folder_add.png");
		Icon iconAccept = new ImageIcon("../../icons/accept.png");		
		Icon iconStop = new ImageIcon("../../icons/stop.png");
		
		//Source und Dest
		sourcePathLabel = new JLabel("Source path:");
		destPathLabel = new JLabel("Dest path:");
		//Button Labeln		
		sourceButton = new JButton("select");
		sourceButton.setIcon(iconFind);
		
		destButton = new JButton();
		destButton.setIcon(iconAdd);
		
		measureLabelX = new JLabel("Ma§stab");
		measureLabelY = new JLabel(":");
		
		offSetLabelHeight = new JLabel("Offset ");
		offSetLabelWidth = new JLabel(":");
		
		convertButton = new JButton("Start...");
		convertButton.setIcon(iconAccept);
		
		closeButton = new JButton("Close");
		closeButton.setIcon(iconStop);
		
		/**
		 *  PREVIEW
		 *  JLabel
		 */
		previewLabel = new JLabel("Preview:");
		previewPicture = new JLabel();
		
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
		
		
		//JFrame
		
		//Source
		gbc.gridx = 0;
		gbc.gridy = 0;		
		jf.add(sourcePathLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.2;
		//gbc.weightx = 0.5;
		jf.add(sourcePathTextField, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;		
		jf.add(sourceButton,gbc);
		
		
		//Dest
		gbc.gridx = 0;
		gbc.gridy = 1;		
		jf.add(destPathLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		//gbc.weightx = 0.5;
		jf.add(destPathTextField, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		//gbc.weightx = 0.5;
		jf.add(destButton, gbc);
		
		
		//Ma§stab
		gbc.gridx = 0;
		gbc.gridy = 2;
		//gbc.weightx = 0.5;
		jf.add(measureLabelX, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		//gbc.weightx = 0.5;
		jf.add(measureXTextField, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		//gbc.anchor = GridBagConstraints.HORIZONTAL;
		//gbc.weightx = 0.5;
		jf.add(measureLabelY, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		//gbc.weightx = 0.5;
		jf.add(measureYTextField, gbc);
		
		//Offset
		gbc.gridx = 0;
		gbc.gridy = 3;
		//gbc.weightx = 0.5;
		jf.add(offSetLabelHeight, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		//gbc.weightx = 0.5;
		jf.add(offSetYTextField, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		//gbc.weightx = 0.5;
		jf.add(offSetLabelWidth, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		//gbc.weightx = 0.5;
		jf.add(offSetXTextField, gbc);
		
		//Close und Convert Button
		gbc.gridx = 0;
		gbc.gridy = 4;
		//gbc.weightx = 0.5;
		jf.add(convertButton, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 4;
		//gbc.weightx = 0.5;
		jf.add(closeButton, gbc);
		
		//Preview
		gbc.gridx = 0;
		gbc.gridy = 5;
		jf.add(previewLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		jf.add(previewPicture, gbc);
		
		jf.pack();		
		jf.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - jf.getSize().width / 2, 
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - jf.getSize().height / 2);

		jf.setVisible(true);
	}

	
	
	
	public void setPreviewPicture(JLabel previewPicture) {
		this.previewPicture = previewPicture;
	}
	
	
	public JLabel getPreviewPicture() {
		return previewPicture;
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
