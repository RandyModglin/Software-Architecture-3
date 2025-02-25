package IO;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TxtInput extends Input {

    public TxtInput(){
        supportedType = ".TXT";
    }


    @Override
    public ArrayList<String[]> readInput(){
        ArrayList<String[]> readLines = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(currFile))){
            String line;

            while ((line = br.readLine()) != null){
                String[] sepLine = line.split("\\s+");

                readLines.add(sepLine);
            }
        }catch (FileNotFoundException e) {
		}
        catch (IOException e) {
		}

        return readLines;
    }
    
}
