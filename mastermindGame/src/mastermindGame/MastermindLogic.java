package mastermindGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MastermindLogic {

	static final String COLORS[] = { "R", "G", "Y", "W", "B", "P" };

	public static String random(int len) {
		Map<String, String> dupColor = new HashMap<String, String>();

		Random rnd = new Random();

		StringBuilder sb = new StringBuilder();
		while (dupColor.size() < len) {
			String color = COLORS[rnd.nextInt(COLORS.length)];
			if (dupColor.containsKey(color)) {
				continue;
			}
			sb.append(color);
			dupColor.put(color, color);

		}
		return sb.toString();

	}

	public static int checkWellPlace(String template, String input) {
		int countWellPlace = 0;
		for (int i = 0; i < template.length(); i++) {
			char tem = template.charAt(i);
			char in = input.charAt(i);
			if (tem == in) {
				countWellPlace++;
			}
		}

		return countWellPlace;
	}

	public static int checkMatchingColor(String template, String input) {
		int matchingColor = 0;
		for (int i = 0; i < template.length(); i++) {
			char tem = template.charAt(i);

			for (int j = 0; j < input.length(); j++) {
				char in = input.charAt(j);
				if (tem == in) {
					matchingColor++;
				}
			}
		}
		return matchingColor;

	}

	public static String validateInput(String input, int length) {
		if (input == null || input.isEmpty()) {
			return "input is required.";
		}

		if (input.length() != length) {
			return "lenth is not match.";
		}

		return null;
	}

	public static int Scoring(int times) {
		int score = 110;
		return score - (times * 10);
	}

	public static boolean ranking(int times) {

		int myScore = Scoring(times);
		List<ScoreModel> topRanks = ScoreService.parsing();

		for (ScoreModel score : topRanks) {
			if (myScore >= Integer.valueOf(score.getScore())) {
				return true;
			}
		}
		return false;

	}

	public static void newTopTen(String name, int times) {
		int myScore = Scoring(times);
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
