package mastermindGUI;

import javax.swing.*;
import java.awt.event.*;

public class PlayButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int xCoord; 
	private int yCoord;
	private int line;
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public PlayButton(ActionListener listener, int x, int y, int l) 
	{
		super();
		this.xCoord = x;
		this.yCoord = y;
		this.line = l;
		initialize(listener);
	}

	private void initialize(ActionListener listener) {

		setName("Play button");
		setText("play");
		setActionCommand("play");
		setSize(70, 18);

		setEnabled(false);
		setLocation(xCoord, yCoord);
		addActionListener(listener);
	}

	public int getLine() {
		return line;
	}
}

