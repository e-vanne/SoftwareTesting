package mastermindGUI;

import javax.swing.JLabel;

/**
 * For the line of result after each try.
 * @author e-vanne
 *
 */
public class Result extends JLabel {

	private static final long serialVersionUID = 1L;
	private int xCoord;
	private int yCoord;
	private int line;

	/**
	 * Constructor
	 * @param xCoord : use by initialize to set the location of the JLabel results
	 * @param yCoord : use by initialize to set the location of the JLabel results
	 * @param line
	 */
	public Result(int xCoord, int yCoord, int line) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.line = line;
		initialize();
	}

	/**
	 * initialize : set the location and size of the JLabel, set it visible 
	 * and add text inside
	 */
	private void initialize() {
		setName("Results");
		setSize(200, 30); 
		setVisible(false);
		setLocation(xCoord, yCoord);
	}

	public int getLine() {
		return line;
	}

}
