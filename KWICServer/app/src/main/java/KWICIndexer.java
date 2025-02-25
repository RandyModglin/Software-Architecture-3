import java.util.ArrayList;
import java.util.HashMap;

import IO.LineStorage;

public class KWICIndexer {
    public HashMap<String, ArrayList<Integer>> IndexFile(LineStorage lineStorage) {
        HashMap<String, ArrayList<Integer>> keyWordMap = new HashMap<>();
    
        int currIndex = 1;

        for(String[] line : lineStorage.getLines()) {
            for (String word : line) {
                ArrayList<Integer> indexes = keyWordMap.get(word);
            
                if(indexes == null){
                    keyWordMap.put(word, new ArrayList<>());
                }
            
                keyWordMap.get(word).add(currIndex);
            }

            currIndex += 1;
        }
    
        return keyWordMap;
    }
}
