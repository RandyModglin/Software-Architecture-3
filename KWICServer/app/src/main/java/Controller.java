import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import IO.Input;
import IO.LineStorage;
import IO.Output;

public class Controller {	

	public void runKWIC(String Command){
		String[] sepCommand = Command.split(" ");
		
		if(sepCommand.length < 2){
			System.out.println("Incorrect Number of Arguments");
			return;
		}


		//Get input object
		OptionReader.readOptions();
		String inputObjStr = OptionReader.getString("Input"); 
		
		Input inputObj = (Input) OptionReader.getObjectFromKey(inputObjStr);

		//Create Sorter
		Sorter.configure();

		//Get output object
		String outputObjStr = OptionReader.getString("Output"); 		
		Output outputObj = (Output) OptionReader.getObjectFromKey(outputObjStr);

		

		//Get input file
		String inputFileStr = OptionReader.getString("InputFileName");
		if(inputObj.getInput(inputFileStr) == true){
			
			//Populate Line Storage
			LineStorage lineStorage = new LineStorage();
			lineStorage.storeLines(inputObj);


			// Start process
			String processName = sepCommand[0];

			switch (processName) {
				case "kwic-processing" -> {
                                    KWICProcessor processor = (KWICProcessor) OptionReader.getObjectFromStr("KWICProcessor");
                                    HashMap<String, Integer> processedLines = processor.ProcessFile(lineStorage);
                                    TreeMap<String, Integer> sortedProcess = Sorter.sortProcess(processedLines);
                                    outputObj.PrintProcess(sortedProcess);
                        }
				case "keyword-search" -> {
                                    if(sepCommand.length == 3){
                                        KWICSearcher searcher= (KWICSearcher) OptionReader.getObjectFromStr("KWICSearcher");
                                        ArrayList<String> searchedLines = searcher.SearchFile(lineStorage, sepCommand[1]);
                                        outputObj.PrintSearch(searchedLines, sepCommand[1]);
                                    }else{
                                        System.out.println("Incorrect Number of Arguments for Search Function");
                                    }
                        }
				case "index-generation" -> {
                                    KWICIndexer indexer = (KWICIndexer) OptionReader.getObjectFromStr("KWICIndexer");
                                    HashMap<String, ArrayList<Integer>> indexedLines = indexer.IndexFile(lineStorage);
                                    TreeMap<String, ArrayList<Integer>> sortedIndexes = Sorter.sortIndex(indexedLines);
                                    outputObj.PrintIndex(sortedIndexes);
                        }
				default -> System.out.println("Unsupported Process");
			}
		}
	}
}