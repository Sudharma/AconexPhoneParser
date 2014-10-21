package com.aconex.phoneparser.strategy;

import java.util.List;
import java.util.Scanner;

import com.aconex.phoneparser.constants.PhoneConstants;
import com.aconex.phoneparser.dictionaries.Dictionary;
import com.aconex.phoneparser.main.PhoneNumReader;

/**
 * This is the class that will process inputs from the command line.
 * 
 * @author sudharma
 */
public class CmdLineParserStrategy extends NumberParserStrategy {
	/**
	 * Constructor for CmdLineParser.
	 * 
	 * @param dictionary
	 *            The dictionary to user for parsing.
	 */
	public CmdLineParserStrategy(Dictionary dictionary) {
		super(dictionary);
	}

	/**
	 * Starts up the STDIN to receive input.
	 */
	public void processNumbers() {
		Scanner oScanner = new Scanner(System.in);
		try {
			List<String> oResult;
			String sLine = null;
			System.out.println("PHONE NUMBER ANALYZER");
			System.out.println("(Type \"" + PhoneConstants.STR_EXIT
					+ "\" if you want to end.)\n");
			while (true) {
				System.out.print("Input number: ");
				sLine = oScanner.nextLine().trim();

				if (PhoneConstants.STR_EXIT.startsWith(sLine)) {
					// End the application.
					System.out.println("Exiting Phone Parser.");
					break;
				} else {
					// Analyze the line.
					oResult = process(sLine);
					// Display the results.
					PhoneNumReader.printOutput(sLine, oResult);
				}
			}
		} finally {
			oScanner.close();
		}
	}
}
