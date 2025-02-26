
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import IO.LineStorage;

public class KWICProcessor {
    
    public HashMap<String, Integer> ProcessFile(LineStorage lineStorage)  {
        HashMap<String, Integer> processedLines = new HashMap<>();
        
        int currIndex = 1;

        ArrayList<String[]> storedLines = lineStorage.getLines();

        for(String[] line : storedLines){
            for(@SuppressWarnings("unused") String word : line){
                Collections.rotate(Arrays.asList(line), 1);
                String joinLine = String.join(" ", line);
                processedLines.put(joinLine, currIndex);
            }

            currIndex++;
        }

        return processedLines;
    }
    
}