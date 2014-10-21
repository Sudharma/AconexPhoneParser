package com.aconex.phoneparser.strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aconex.phoneparser.dictionaries.Dictionary;

/**
 * This is the class that will process the numbers found in a file.
 * 
 * @author sudharma
 */
public class FileParserStrategy extends NumberParserStrategy {
	/**
	 * Constructor for FileParser.
	 * 
	 * @param dictionary
	 *            The dictionary to user for parsing.
	 */
	public FileParserStrategy(Dictionary dictionary) {
		super(dictionary);
	}

	/**
	 * Analyzes the numbers found in the filename.
	 * 
	 * @param sFileName
	 *            The filename of the input file.
	 * @throws IOException 
	 */
	public Map<String, List<String>> processFile(String sFileName) throws IOException {
		File oFile = new File(sFileName);
		Map<String, List<String>> oResult = new HashMap<>();
		try {
			if (!oFile.exists()) {
				System.out.println("Not a file so numbers are considered!!");
				throw new FileNotFoundException();
			}

			Files.lines(oFile.toPath())
					.filter(line -> !line.isEmpty())
					.forEach(
							eachLine -> oResult
									.put(eachLine, process(eachLine)));
		} catch (FileNotFoundException e) {
			System.err.println("Unable to locate the file  " + sFileName);
			throw e;
		} catch (IOException e) {
			System.err.println("Unable to locate the file  " + sFileName);
			throw e;
		} finally {
		}

		return oResult;
	}

}
