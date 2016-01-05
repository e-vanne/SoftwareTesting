package mastermindGUI;

import javax.swing.*;
import java.awt.event.*;

/*
 * Squares are buttons : you click on them to change their color
 */
public class Square extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	public java.awt.Color[] colors = {
			java.awt.Color.red,
			java.awt.Color.blue,
			java.awt.Color.green,
			java.awt.Color.yellow,
			java.awt.Color.orange,
			new java.awt.Color(119,102,0),
			java.awt.Color.white,
			java.awt.Color.black
	};
	private int currentColor = -1; // no color
	private int xCoord; 
	private int yCoord;
	private int row;
	private int nbOfColors = 8;

	public Square(int x, int y, int l) 
	{
		this.xCoord = x;
		this.yCoord = y;
		this.row = l;
		initialize();
	}
	/**
	 * Change the color of the square each it's clicked on.
	 */
	public void actionPerformed(ActionEvent ae) 
	{
		String orig= ((javax.swing.JButton) ae.getSource()).getActionCommand();

		if (orig=="Square")
		{
			if (currentColor!=(nbOfColors-1)) 
				currentColor++;
			else 
				currentColor=0;
			setBackground(colors[currentColor]);
		}
	}

	public int getColor() {
		return currentColor;
	}

	public int getRow() {
		return row;
	}

	private void initialize() {

		setName("square");
		setText("");
		setSize(18, 18);
		setActionCommand("Square");

		setEnabled(false);
		setLocation(xCoord, yCoord);
		addActionListener(this);
	}
}

