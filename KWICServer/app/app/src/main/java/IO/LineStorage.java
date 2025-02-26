package IO;

import java.util.ArrayList;

public class LineStorage {
    
    private ArrayList<String[]> storedLines;

    public LineStorage(){
        storedLines = new ArrayList<>();
    }

    public void storeLines(Input inputObj) {
        storedLines = inputObj.readInput();
    }

    public ArrayList<String[]> getLines(){   
        return storedLines;
    }
    
}