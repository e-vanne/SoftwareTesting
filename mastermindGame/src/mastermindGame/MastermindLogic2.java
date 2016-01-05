package mastermindGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * I modified the logic a bit to adapt it to the integers representing the colors, but
 * i make mistakes in checkMatchingColors.
 * @author e-vanne
 *
 */
public class MastermindLogic2 {

	static final int COLORS[] = {0,1,2,3,4,5,6,7};
	int solution[] = new int[5];

	public void random(int len) {
		Random rnd = new Random();
		for (int i = 0; i < len; i++) {
			int color = rnd.nextInt(COLORS.length);
			solution[i] = color;
		}
	}
	
	public void getSolution(int[] sol, int len) {
		random(len);
		for (int i=0 ; i < len; i++) {
			sol[i] = solution[i];
		}
	}

	public int checkWellPlaced(int[] input, int len) {
		int countWellPlaced = 0;
		for (int i = 0; i < len; i++) {
			if (input[i] == solution[i]) {
				countWellPlaced++;
			}
		}
		return countWellPlaced;
	}

	public int checkMatchingColors(int[] input, int len) {
		int matchingColors = 0;
		for (int i = 0; i < len; i++) {
			int sol = solution[i];
			
			for (int j = 0; j < len; j++) {
				if (input[j] == sol) 
					matchingColors++;
				
			}
		}
		return matchingColors;
	}

	public int scoring(int times) {
		int score = 110;
		return score - (times * 10);
	}

	public boolean ranking(int times) {

		int myScore = scoring(times);
		List<ScoreModel> topRanks = ScoreService.parsing();

		for (ScoreModel score : topRanks) {
			if (myScore >= Integer.valueOf(score.getScore())) {
				return true;
			}
		}
		return false;

	}

	public void newTopTen(String name, int times) {
		int myScore = scoring(times);
		List<ScoreModel> topRanks = ScoreService.parsing();
		List<ScoreModel> newTopRanks = new ArrayList<ScoreModel>();

		boolean flag = true;
		int index=0;
		int topTen = 10;
		
		for (ScoreModel score : topRanks) {
			if (index < topTen) {
				index++;
				if (myScore >= Integer.valueOf(score.getScore()) && flag == true) {
					ScoreModel model = new ScoreModel();

					model.setName(name);
					model.setScore(String.valueOf(myScore));

					newTopRanks.add(model);

					flag = false;
				} else {
					newTopRanks.add(score);
				}
			}
		}
		ScoreService.newRanking(newTopRanks);
	}

}

