import java.util.ArrayList;

import IO.LineStorage;

public class KWICSearcher {
    public ArrayList<String> SearchFile(LineStorage lineStorage, String target) {
        ArrayList<String> matchingLines = new ArrayList<>();

        for(String[] line : lineStorage.getLines()){
            String currLine = "";
            boolean matching = false;

            for(String word: line){
                if(word.equals(target)){
                    currLine += ("[" + word + "] ");
                    matching = true;
                }
                else{
                    currLine += (word + " ");
                }
            }

            if(matching == true){
                matchingLines.add(currLine);
            }
        }

        return matchingLines;
    }
}
