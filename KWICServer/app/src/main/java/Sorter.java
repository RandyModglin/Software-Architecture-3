import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Sorter {

    private static Comparator<String> sortingMethod;
    private static Boolean isFiltering;
    private static List<String> filteredWords;

    public static void configure(){        
        if(OptionReader.getString("Order").equals("Ascending")){
            sortingMethod = String.CASE_INSENSITIVE_ORDER;
        }
        else{
            sortingMethod = String.CASE_INSENSITIVE_ORDER.reversed();
        }

        if(OptionReader.getString("WordFiltering").equals("Yes")){
            isFiltering = true;
            filteredWords = Arrays.asList(OptionReader.getString("TrivialWords").split("\\,"));
        }
        else{
            isFiltering = false;
        }
    }

    public static TreeMap<String, Integer> sortProcess(HashMap<String, Integer> processedLines) {
        TreeMap<String, Integer> sortedMap = new TreeMap<>(sortingMethod);
        sortedMap.putAll(processedLines);

        return sortedMap;
    }

    public static TreeMap<String, ArrayList<Integer>> sortIndex(HashMap<String, ArrayList<Integer>> keyWordMap) {
        TreeMap<String, ArrayList<Integer>> sortedMap = new TreeMap<>(sortingMethod);

        if(isFiltering == true){
            for(Entry<String, ArrayList<Integer>> entry : keyWordMap.entrySet()) {
            String key = entry.getKey();

                if(filteredWords.contains(key) == false){
                    sortedMap.put(key, keyWordMap.get(key));
                }
            }
        }
        else{
            sortedMap.putAll(keyWordMap);
        }

        return sortedMap;
    }
}
