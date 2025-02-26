import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import IO.Input;
import IO.LineStorage;
import IO.Output;

public class Controller {	
	
	private static final int PORT = 1234;

	//3rd Arg is the function
	//4th arg for search in the keyword
	public static void main(String[] args) {
	
		if(args.length < 2){
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
			String processName = args[0];

			switch (processName) {
				case "kwic-processing" -> {
                    KWICProcessor processor = (KWICProcessor) OptionReader.getObjectFromStr("KWICProcessor");
                    HashMap<String, Integer> processedLines = processor.ProcessFile(lineStorage);
                    TreeMap<String, Integer> sortedProcess = Sorter.sortProcess(processedLines);
                    outputObj.PrintProcess(sortedProcess);
                }
				case "keyword-search" -> {
					try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            			
						System.out.println("Server started on port " + PORT);
            			
						while (true) {
                			Socket clientSocket = serverSocket.accept();
                			new ClientHandler(clientSocket, lineStorage).searchLines();
            			}
					}

					catch (IOException e) {
            			e.printStackTrace();
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