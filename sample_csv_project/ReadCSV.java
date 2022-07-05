package sample_csv_project;

import java.io.FileReader;

import java.util.ArrayList;

import com.opencsv.CSVReader;
public class ReadCSV {

	static ArrayList<TranslationRule> main() {

		CSVReader reader = null;
		ArrayList<TranslationRule> translationList = new ArrayList<TranslationRule>();
		try {
			// parsing a CSV file into CSVReader class constructor
			reader = new CSVReader(new FileReader("C:\\Users\\EbinBThomas\\Desktop\\sample.csv"));
			String[] nextLine;
			// reads one line at a time
			boolean isHeading = true;
			while ((nextLine = reader.readNext()) != null) {
				if (isHeading) {
					isHeading = false;
					continue;
				}
				if(nextLine[0].equals("")) {
					break;
				}
				TranslationRule translationRule = new TranslationRule();
				translationRule.setResourceType(nextLine[0]);
				translationRule.setInherit(nextLine[2].toLowerCase().replaceAll("\\s+", ""));
				translationRule.setName(nextLine[1].toLowerCase());
				translationRule.setTranslate(nextLine[3].toLowerCase().replaceAll("\\s+", ""));
				translationList.add(translationRule);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return translationList;
	}
	
}
