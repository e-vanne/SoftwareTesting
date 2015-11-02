package mastermindGame;

import java.util.Scanner;

public class MasterMindConsole {

	public static void main(String[] args) {
		System.out.println("Mastermind Game : R=RED, G=GREEN, Y=YELLOW, W=WHITE, B=BLACK, P=PINK");
		
		int length = 4; 
		String template = MastermindLogic.random(length);
		System.out.println(template);
		
		new ScoreService();
		ScoreService.parsing();
		
		int countWellPlace = 0;
		int countMatchingColor = 0;
		do {
			Scanner user_Input = new Scanner(System.in);
			String input = user_Input.next().toUpperCase();
			String errorMsg = MastermindLogic.validateInput(input, length);
			
			if(errorMsg == null){
				countWellPlace = MastermindLogic.checkWellPlace(template, input);
				countMatchingColor = MastermindLogic.checkMatchingColor(template, input);
				System.out.println("well place =>:" + countWellPlace+" maching color =>:"+countMatchingColor);
				
				if(countWellPlace == length){
					System.out.println("\n--You Win!!--");
				}
				
			}else{
				System.out.println(errorMsg);
			}
			
		} while (countWellPlace != length);

	}

}
