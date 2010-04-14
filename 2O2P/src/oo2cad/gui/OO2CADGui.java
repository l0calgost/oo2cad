package oo2cad.gui;

import java.awt.Color;
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
import javax.swing.border.LineBorder;
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
		
		//JPanel für GridBagLayout
		//JPanel jp = new JPanel(new GridBagLayout());
		//GridBagConstraints bgc = new GridBagConstraints();
		//bgc.fill = GridBagConstraints.HORIZONTAL;
		
		JFrame jf = new JFrame("OO2CAD");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;

		//Abstand
		gbc.insets = new Insets(2, 2, 2, 2);
		
		//Programm Icons		
		Icon iconAdd = new ImageIcon(System.getProperty("user.dir") + "\\icons\\folder_add.png");///2o2cad/src/icons/folder_add.png
		Icon iconFind = new ImageIcon(System.getProperty("user.dir") + "\\icons\\folder_add.png");
		Icon iconAccept = new ImageIcon(System.getProperty("user.dir") + "\\icons\\accept.png");		
		Icon iconStop = new ImageIcon(System.getProperty("user.dir") + "\\icons\\stop.png");
		
		//Previw Picture
		Icon preview = new ImageIcon(System.getProperty("user.dir") + "\\icons\\Preview.png");
				
		//Source und Dest
		sourcePathLabel = new JLabel("Source path:");
		destPathLabel = new JLabel("Dest path:");
		//Button Labeln		
		sourceButton = new JButton();
		sourceButton.setIcon(iconFind);
		
		destButton = new JButton();
		destButton.setIcon(iconAdd);
		
		measureLabelX = new JLabel("Maﬂstab");
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
		previewPicture.setIcon(preview);
		LineBorder border = new LineBorder(Color.BLACK);
		previewPicture.setBorder(border);
		
		//TextFelder erzeugen
		sourcePathTextField = new JTextField();
		destPathTextField = new JTextField();			
		
		measureXTextField = new JTextField();		
		measureXTextField.setText(Float.toString(config.getScaleInc()));
		//Eingabepr¸fen
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
		jf.add(sourcePathLabel,makegbc(0, 0, 1, 1));
		
		jf.add(sourcePathTextField, makegbc(1, 0, 5, 1));			
		jf.add(sourceButton,makegbc(6, 0, 1, 1));
		
		//Dest	
		jf.add(destPathLabel,makegbc(0, 1, 1, 1));
		
		jf.add(destPathTextField, makegbc(1, 1, 5, 1));

		jf.add(destButton, makegbc(6, 1, 1, 1));		
		
		//Maﬂstab
		//							X Y WITH HEIGHT
		jf.add(measureLabelX, makegbc(0, 2, 1, 1));
		
		jf.add(measureXTextField, makegbc(1, 2, 2, 1));
		
		jf.add(measureLabelY, makegbc(3, 2, 1, 1));
		
		jf.add(measureYTextField, makegbc(4, 2, 2, 1));
		
		//Offset
		jf.add(offSetLabelHeight, makegbc(0, 3, 1, 1));

		jf.add(offSetYTextField, makegbc(1, 3, 2, 1));
		
		jf.add(offSetLabelWidth, makegbc(3, 3, 1, 1));
		
		jf.add(offSetXTextField, makegbc(4, 3, 2, 1));
		
		//Preview
		jf.add(previewLabel, makegbc(0, 4, 1, 1));
		
		jf.add(previewPicture, makegbc(1, 4, 5, 2));
		
		//Close und Convert Button
		jf.add(convertButton, makegbc(2, 6, 1, 1));
		
		jf.add(closeButton, makegbc(4, 6, 1, 1));
						
		jf.pack();	
		
		//Location
		jf.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - jf.getSize().width / 2, 
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - jf.getSize().height / 2);

		jf.setVisible(true);
	}
	
	private GridBagConstraints makegbc(int x, int y, int width, int height)
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		
		//Anordnung
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		//Abstand
		gbc.insets = new Insets(2, 2, 2, 2);
		
		return gbc;
	}
		
	public void setPreviewPictureImage(Icon image) {
		this.previewPicture.setIcon(image);
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
