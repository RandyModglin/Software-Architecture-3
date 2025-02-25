package IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class TxtOutput extends Output{

    private void setOut(){
        PrintStream out;
        try {
            out = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(out);
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void PrintProcess(TreeMap<String, Integer> sortedProcess) {
        setOut();
        
        super.PrintProcess(sortedProcess);
    }

    @Override
    public void PrintIndex(TreeMap<String, ArrayList<Integer>> sortedIndex) {
        setOut();

        super.PrintIndex(sortedIndex);
    }

    @Override
    public void PrintSearch(ArrayList<String> searchedLines, String target) {
        setOut();

        super.PrintSearch(searchedLines, target);
    }
    
}
