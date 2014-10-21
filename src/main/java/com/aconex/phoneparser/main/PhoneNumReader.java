package com.aconex.phoneparser.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aconex.phoneparser.constants.PhoneConstants;
import com.aconex.phoneparser.dictionaries.Dictionary;
import com.aconex.phoneparser.dictionaries.DictionaryReader;
import com.aconex.phoneparser.strategy.CmdLineParserStrategy;
import com.aconex.phoneparser.strategy.FileParserStrategy;
import com.aconex.phoneparser.strategy.NumberParserStrategy;

/**
 * This class will have the main class of the PhoneNumberReader.
 * 
 * @author sudharma
 */
public class PhoneNumReader {
	/**
	 * 
	 * Main method to start
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length > 0) {
			try {
				checkForHelp(args);
				processInputArguments(args);
			} catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			}
		} else {
			processCmdLineArguments();
		}

	}

	private static void checkForHelp(String[] args) {
		if ("-h".equals(args[0]) || "--help".equals(args[0])) {
			System.out.println(PhoneConstants.STR_INSTRUCTIONS);
			System.exit(0);
		}
	}

	private static void processCmdLineArguments()  {
		DictionaryReader oDictionaryReader;
		CmdLineParserStrategy oCmdLine;
		// Invoke command line to gather inputs from the user.
		try {
			oDictionaryReader = new DictionaryReader(PhoneConstants.DEF_DICTIONARY);
			oCmdLine = new CmdLineParserStrategy(oDictionaryReader.getDictionary());
			oCmdLine.processNumbers();
		} catch (IOException e) {
			// Ignore Exception as its not needed
		}
		
	}

	private static void processInputArguments(String[] args) throws IOException {
		String sDictionaryName = null;
		DictionaryReader oDictionaryReader;
		Dictionary oDict;
		CmdLineParserStrategy oCmdLine;
		NumberParserStrategy oNumParser;
		FileParserStrategy oFileParser;
		Map<String, List<String>> oResults;
		List<String> oValues;
		String sCurrent;
		int nArgsLength;
		int i = 0;
		if (args[i].equals(PhoneConstants.STR_DICT_OPTION)) {
			i++;
			if(args.length==1) {
				throw new FileNotFoundException("Missing Dictionary File");
			}
			sDictionaryName = args[i++];
		}
		nArgsLength = args.length - i;
		oDictionaryReader = new DictionaryReader(sDictionaryName);
		oDict = oDictionaryReader.getDictionary();

		if (nArgsLength > 0) {
			// Remaining arguments could either be a series of numbers, a
			// series of filenames, or both.
			oNumParser = new NumberParserStrategy(oDict);
			oFileParser = new FileParserStrategy(oDict);
			for (; i < args.length; i++) {
				sCurrent = args[i];
				try {
					oResults = oFileParser.processFile(sCurrent);
					printOutput(oResults);
				} catch (FileNotFoundException e) {
					// It's not a filename. It might be a number sequence.
					oValues = oNumParser.process(sCurrent);
					printOutput(sCurrent, oValues);
				} catch (IOException e) {
					System.err.println("");
				}
			}
		} else {
			// Invoke command line to gather inputs from the user.
			oCmdLine = new CmdLineParserStrategy(oDict);
			oCmdLine.processNumbers();
		}
	}

	/**
	 * Displays the results.
	 * 
	 * @param sSequence
	 *            The number sequence in string format.
	 * @param oValues
	 *            The resulting words of the number sequence.
	 */
	public static void printOutput(String sSequence, List<String> oValues) {
		Map<String, List<String>> oResults = new HashMap<>();
		oResults.put(sSequence, oValues);
		printOutput(oResults);
	}

	/**
	 * Displays the results. Traverses through the map and displays all
	 * information.
	 * 
	 * @param oResults
	 *            The map of the results.
	 */
	public static void printOutput(Map<String, List<String>> oResults) {
		Set<String> oKeys = oResults.keySet();
		List<String> oValues = null;
		for (String sKey : oKeys) {
			oValues = oResults.get(sKey);
			if (oValues.size() > 1) {
				System.out
						.println("All possible words  for " + sKey + " are: ");
				oValues.forEach(System.out::println);
			} else if (oValues.size() == 1) {
				System.out.println("The result for " + sKey + " is: "
						+ oValues.get(0));
			} else {
				System.out.println("No words were found in the sequence: "
						+ sKey);
			}

			System.out.println();
		}
	}
}
