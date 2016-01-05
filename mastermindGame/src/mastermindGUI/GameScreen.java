package mastermindGUI;

import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * JPanel in which we can add the elements to be displayed (JLabels and JButtons here)
 * @author e-vanne
 *
 */
public class GameScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel title = new JLabel();
	// private int nbColors=0; // number of colors to guess
	private ActionListener listener;

	/**
	 * Constructor, follows ActionListener form MainWindow
	 * @param listener
	 */
	public GameScreen(ActionListener listener) {
		super();
		initialize(listener);
		listener = listener;
	}
	
	private void initialize(ActionListener listener) {
		
		// initialize window 
		setName("Game Screen");
		setLayout(null); // we'll precise the place of each JLabel
		setSize(460, 393);
		
		// title : in top of the window
		title.setName("title");
		title.setFont(new java.awt.Font("Arial", 1, 18));
		title.setText("Mastermind");
		title.setBounds(87, 22, 116, 14);
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		add(title); // add to the JPanel

		// res : JLabel where result for a try is displayed, now is before first real result, 
		// we just give its name to the column
		Result res = new Result(260,60,-1);
		res.setText("Well-placed   Good Color");
		res.setForeground(java.awt.Color.black);
		res.setVisible(true);
		add(res); // add to the JPanel
		
		initializeElements(listener);
	}
	
	private void initializeElements(ActionListener listener) {
		int row=0; // current line
		int squareNb = 0;
		
		for (int y=80; y<355; y+=25) 
		{
			for (int x=20; x<145; x+=25)
			{
				// insert in the squares vector
				MastermindUI.squares.insertElementAt(new Square(x,y,row),squareNb);
				// add the element to GameScreen (JPanel)
				add((Square)MastermindUI.squares.elementAt(squareNb));
				squareNb++; // move to next square in the row
			}
			if (row <10)
			{
				MastermindUI.playButtons.insertElementAt(new PlayButton(listener,170,y,row),row);
				add((PlayButton)MastermindUI.playButtons.elementAt(row));
			}
			
			// add a new empty line for the results (
			MastermindUI.results.insertElementAt(new Result(310,y,row),row);
			add((Result)MastermindUI.results.elementAt(row));
			
			row++;
		}
		
	}
	
}
