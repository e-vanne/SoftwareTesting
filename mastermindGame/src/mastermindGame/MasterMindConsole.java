package mastermindGame;

import java.util.Scanner;

public class MasterMindConsole {

	public static void main(String[] args) {
		System.out
				.println("Mastermind Game : R=RED, G=GREEN, Y=YELLOW, W=WHITE, B=BLACK, P=PINK");
		int times = 0;
		int length = 4;

		String template = MastermindLogic.random(length);
		System.out.println(template); // KEY

		ScoreService scoreService = new ScoreService();

		int countWellPlace = 0;
		int countMatchingColor = 0;

		while (times != 10) {

			Scanner user_Input = new Scanner(System.in);
			String input = user_Input.next().toUpperCase();
			String errorMsg = MastermindLogic.validateInput(input, length);

			if (errorMsg == null) {
				countWellPlace = MastermindLogic.checkWellPlace(template, input);
				countMatchingColor = MastermindLogic.checkMatchingColor(template, input);
				System.out.println("well place =>:" + countWellPlace+ " maching color =>:" + countMatchingColor);
				times++;

				if (countWellPlace == length) {
					System.out.println("\n--You Win!!--");

					if (MastermindLogic.ranking(times) == true) {
						System.out.print("You are now in top10\nPlase enter your name :");
						String name = user_Input.next();
						MastermindLogic.newTopTen(name, times);
						int index = 0;
						for (ScoreModel score : scoreService.parsing()) {
							index++;
							System.out.print(index + " " + score.getName()+ " " + score.getScore() + "\n");
						}
					}
					break;

				}

			} else {
				System.out.println(errorMsg);
			}
			
		}

		if (times == 10) {
			System.out.println("\n--You lose!!--");
		}

	}

}
