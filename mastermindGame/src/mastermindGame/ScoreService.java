package mastermindGame;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ScoreService {
	private static String filePath;
	private static String wordArray[][];
	static int lineNo;
	int rnd;
	
	public ScoreService() {
		filePath = "/Users/Mew/Documents/workspaceArch/mastermindGame/src/mastermindGame/ScoreFile.txt";
		wordArray = new String[2][50];
		lineNo = 0;
		parsing();
	}
	
	// scan files
	public static void parsing() {
		try {
			// read file
			System.out.println("yeah ");
			FileInputStream file = new FileInputStream(new File(filePath));
			
			// Get the object of DataInputStream
			DataInputStream instream = new DataInputStream(file);
			BufferedReader bf = new BufferedReader(new InputStreamReader(instream));
			String line;

			// each line
			while ((line = bf.readLine()) != null) {
				String l = line;
				if (l.indexOf(' ') >= 0) {
					String score = l.substring(0, l.indexOf(' '));
					String name = l.substring(l.indexOf(' ') + 1, l.length());
					wordArray[0][lineNo] = name;
					wordArray[1][lineNo] = score;

					lineNo++;
				}
			}

			System.out.println("yeah ");
					
			// close input stream
			instream.close();

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
