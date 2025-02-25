package IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVInput extends Input {

    public CSVInput(){
        supportedType = ".CSV";
    }

    @Override
    public ArrayList<String[]> readInput(){
        ArrayList<String[]> readLines = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(currFile))){
            String line;
            StringBuilder fullText = new StringBuilder();

            while ((line = br.readLine()) != null) {
                fullText.append(line).append(" ");
            }

            String[] sentences = fullText.toString().trim().split("\\.");

            for (String sentence : sentences) {
                sentence = sentence.trim();
                if (!sentence.isEmpty()) {
                    String[] words = sentence.split("\\s+");
                    readLines.add(words);
                }
            }
        }catch (FileNotFoundException e) {
		}
        catch (IOException e) {
		}

        return readLines;
    }
}
