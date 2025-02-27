package IO;

import java.util.ArrayList;

public class Output {

    public void PrintSearch(ArrayList<String> searchedLines, String target) {
        if(searchedLines.size() <= 0){
            System.out.println(target + " not found");
            return;
        }

        System.out.println(searchedLines.size() + " sentence is found: ");

        for(String line: searchedLines){
            System.out.println(line);
        }
    }
    
}
