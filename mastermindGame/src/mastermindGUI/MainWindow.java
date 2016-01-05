package mastermindGUI;

import java.awt.event.ActionListener;
import javax.swing.JFrame;


/**
 * Main window
 * @author e-vanne
 *
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private GameScreen screen;

	/**
	 * Constructor
	 * @param listener
	 */
	public MainWindow(ActionListener listener) {
		super();
		initialize();
		/* In Swing, a JFrame (for constructing the window) contains JPanel, and it's a bit easier 
		 * to write this (these) JPanel using another class
		 */
		screen = new GameScreen(listener);
		setContentPane(screen);
		setVisible(true);
	}

	/**
	 * Initialize : set size and title
	 */
	private void initialize() {
		setName("Main window");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setSize(460, 393);
		setTitle("Mastermind");
	}

}
