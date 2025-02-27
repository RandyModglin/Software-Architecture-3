package IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

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
    public void PrintSearch(ArrayList<String> searchedLines, String target) {
        setOut();

        super.PrintSearch(searchedLines, target);
    }
    
}
