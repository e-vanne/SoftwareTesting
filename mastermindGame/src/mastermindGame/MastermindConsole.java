package mastermindGame;

import java.util.Scanner;

public class MastermindConsole {
	
	static int timeLimit;
	static int length;
	static String template;
	static ScoreService scoreService;

	public static void main(String[] args) {
		init();
		game();
	}
	
	private static void init(){
		System.out.println("Mastermind Game : R=RED, G=GREEN, Y=YELLOW, W=WHITE, B=BLACK, P=PINK");
		
		timeLimit = 10;	//how many time player can guess
		length = 4;	//length of the color
		
		template = MastermindLogic.random(length);
		System.out.println(template); // KEY

		scoreService = new ScoreService(); //read ScoreFile

	}
	
	private static void game(){
		
		int countWellPlace = 0;
		int countMatchingColor = 0;
		int time = 0;

		while (time != timeLimit) {
			
			System.out.print("Guess :");
			Scanner user_Input = new Scanner(System.in);
			String input = user_Input.next().toUpperCase();
			
			String errorMsg = MastermindLogic.validateInput(input, length);

			if (errorMsg == null) {
				countWellPlace = MastermindLogic.checkWellPlace(template, input);
				countMatchingColor = MastermindLogic.checkMatchingColor(template, input);
				System.out.println("well place =>:" + countWellPlace+ " matching color =>:" + countMatchingColor);
				time++;
				
				if (countWellPlace == length) {
					System.out.println("\n--You Win!!--");
					winnerConsole(time);
					break;

				}

			} else {
				System.out.println(errorMsg);
			}
			
		}

		if (time == timeLimit) {
			System.out.println("\n--You lose!!--");
		}

	}
	
	private static void winnerConsole(int time){
		
		if (MastermindLogic.ranking(time) == true) {
			
			System.out.print("You are now in top10\nPlase enter your name :");
			Scanner user_Input = new Scanner(System.in);
			String name = user_Input.next();
			
			MastermindLogic.newTopTen(name, time);
			
			int index = 0;
			for (ScoreModel score : scoreService.parsing()) {
				index++;
				System.out.print(index + " " + score.getName()+ " " + score.getScore() + "\n");
			}
		}
	}

}
