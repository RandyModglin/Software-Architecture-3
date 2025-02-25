package IO;

import java.io.File;
import java.util.ArrayList;

public abstract class Input {
    protected File currFile;
    protected String supportedType;

    abstract public ArrayList<String[]> readInput();

    public boolean getInput(String inputFileStr) {
        currFile = new File(inputFileStr);

        if (currFile.exists()) {
    
            if(validateInput(currFile)){
                return true;
            }  
        
            else{
                System.out.println("Sorry, please set your config files for the correct file type");
                return false;
            }
        }
        
        else {
            System.out.println("File not found: " + inputFileStr);
            System.out.println("Ensure you have the correct setting in your config file");
            return false;
        }
    } 

    protected boolean validateInput(File currFile) {
        String fileName = currFile.getName().toUpperCase();

        return fileName.endsWith(supportedType);
    }
}
