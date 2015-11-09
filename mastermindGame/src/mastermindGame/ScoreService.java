package mastermindGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
	public static List<ScoreModel> parsing() {
		List<ScoreModel> topRank = new ArrayList<ScoreModel>();
		try {
			// read file
			FileInputStream file = new FileInputStream(new File(filePath));

			// Get the object of DataInputStream
			DataInputStream instream = new DataInputStream(file);
			BufferedReader bf = new BufferedReader(new InputStreamReader(instream));
			String line;

			// each line
			
			topRank = new ArrayList<ScoreModel>();
			while ((line = bf.readLine()) != null) {
				String l = line;
				if (l.indexOf(' ') >= 0) {
					String name = l.substring(0, l.indexOf(' '));
					String score = l.substring(l.indexOf(' ') + 1, l.length());
					/*wordArray[0][lineNo] = name;
					wordArray[1][lineNo] = score;*/
					// System.out.println(name);
					ScoreModel model = new ScoreModel();
					model.setName(name);
					model.setScore(score);
					
					topRank.add(model);
					
					lineNo++;
				}
			}

			// close input stream
			instream.close();

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return topRank;
	}

	public static void newRanking(List<ScoreModel> newTopRanks) {
		 BufferedWriter writer = null;
			try {
		            //create a temporary file
				 	File file = new File(filePath);
				 	
		            // This will output the full path where the file will be written to...
//		            System.out.println(file.getCanonicalPath());

				 	System.out.println("::Top 10 of The Mastermind game ::");
				 	
		            writer = new BufferedWriter(new FileWriter(file));
		            String templateTopRanks = "%s %s\n";
		            for(ScoreModel model :newTopRanks){
		            	writer.write(String.format(templateTopRanks, model.getName(), model.getScore()));
		            }
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                // Close the writer regardless of what happens...
		                writer.close();
		            } catch (Exception e) {
		            }
		        }
	}
}
