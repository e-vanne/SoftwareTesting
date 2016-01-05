package mastermindGUI;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import mastermindGame.MastermindLogic2;

/**
 * Most important method !!!
 * Here are all the actions and their effect on the support (MainWindow, GameScreen where the elements are)
 * @author e-vanne
 *
 */
public class MastermindUI  implements ActionListener {
	private MainWindow mainWindow;

	public static Vector<Square> squares = new Vector<Square>();
	public static Vector<PlayButton> playButtons = new Vector<PlayButton>();
	public static Vector<Result> results = new Vector<Result>();

	private int[] input = {0,0,0,0,0}; // colors chosen by user
	private int[] solution = {0,0,0,0,0};
	private int time = 0; // number of the current try
	private int len = 5; // number of colors to guess

	private PlayButton currentButton;
	private Square currentSquare;

	private MastermindLogic2 logic = new MastermindLogic2();

	public MastermindUI() 
	{
		super();
		initialize();
	}

	public void initialize() 
	{
		mainWindow = new MainWindow(this);
		logic.getSolution(solution, len);
		for (int i=0; i<5; i++)
			System.out.print(solution[i]+" ");
		activation(true);
	}

	/**
	 * Update the game when the player presses "play"
	 */
	public void actionPerformed(ActionEvent ae) 
	{
		String orig= ((javax.swing.JButton) ae.getSource()).getActionCommand();
		boolean endOfGame = false;

		if (orig=="play")
		{

			activation(false); // block any action

			// Update input 
			Square inputSquare; // to iterate over the input squares
			for (int i=(time*len); i<(time*len+len); i++)
			{
				inputSquare = (Square) squares.elementAt(i);
				input[i%len] = inputSquare.getColor();
			}

			// compare input and solution
			int wp = logic.checkWellPlaced(input, len); // well-placed colors
			int gc = logic.checkMatchingColors(input, len); // good colors

			// print result for this try
			Result result = (Result) results.elementAt(time);
			result.setText(wp +"              "+ gc);
			result.setVisible(true);

			// this try is finished, move to next one
			time++;


			if (wp!=5) {
				if (time >= 10)	{ // the player lost
					displaySol();
					endOfGame = true;
				}
				else activation(true); // re-allow playing
			}
			else { // the player won

				displaySol();
				endOfGame = true;

				if (logic.ranking(time))  { // the player is in top 10

					JOptionPane top = new JOptionPane();
					int choice = top.showConfirmDialog(
							null,
							"You're in top ten !",
							"Do you want your score to be stored ?",
							JOptionPane.YES_NO_OPTION);
					top.setSize(300, 200);//On lui donne une taille
					top.setVisible(true);//On la rend visible

					if(choice == JOptionPane.YES_OPTION){
						String response = JOptionPane.showInputDialog(null,
								"What is your name?",
								"Enter your name",
								JOptionPane.QUESTION_MESSAGE);
						System.out.println(response);
						// TODO : update scoreboard
					}
				}
				else { // the player isn't in top 10
					JOptionPane dialog = new JOptionPane();
					dialog.showMessageDialog(null,
							"You won with " + logic.scoring(time) + " points.",
							"End of the game.", JOptionPane.INFORMATION_MESSAGE);
				}

			}

			if (endOfGame) {
				// replay ?
				JOptionPane dialog = new JOptionPane();
				int reply = dialog.showConfirmDialog(null,
						"Do you want to play again ?",
						"End of the game.",
						JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION)
					replay();

				dialog.setSize(300, 200);//On lui donne une taille
				dialog.setVisible(true);//On la rend visible
			}

		}
	}



	private void replay() {
		time = 0;
		logic.getSolution(solution, len);
		for (int i=0; i<5; i++)
			System.out.print(solution[i]+" ");
		for (Result res : results) 
			res.setText("");
		for (Square s : squares) {
			s.setBackground(null);
			s.setOpaque(true);
			s.setEnabled(false);
		}
		for (PlayButton p : playButtons)
			p.setEnabled(false);
		activation(true);
	}

	public void activation(boolean option) {
		currentButton = (PlayButton) playButtons.elementAt(time);
		currentButton.setEnabled(option);

		for (int i=0; i<squares.size(); i++) {
			currentSquare=(Square) squares.elementAt(i);
			if (currentSquare.getRow()==time)
				currentSquare.setEnabled(option);
		}
	}

	public void displaySol() {
		for (int i=10*len; i<(10*len+len); i++) {
			Square s = (Square) squares.elementAt(i);
			s.setBackground(s.colors[solution[i%len]]);
		} 
	}


}

