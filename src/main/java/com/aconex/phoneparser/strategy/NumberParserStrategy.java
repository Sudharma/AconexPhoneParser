
package com.aconex.phoneparser.strategy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aconex.phoneparser.constants.PhoneConstants;
import com.aconex.phoneparser.dictionaries.Dictionary;

/**
 * This class parses numbers and determine words based on the
 * dictionary provided.
 * 
 * @author sudharma
 */
public class NumberParserStrategy extends ParserStrategy {
	/**
	 * The dictionary instance for this parser.
	 */
	private Dictionary coDictionary;

	/**
	 * The constructor of the NumberParser class.
	 * 
	 * @param oDictionary
	 *            The dictionary to be used to analyze the numbers.
	 */
	public NumberParserStrategy(Dictionary oDictionary) {
		coDictionary = oDictionary;
	}

	/**
	 * This will analyze the string sequence if it is a valid number.
	 * 
	 * @param sLine
	 *            The line to analyze.
	 * @return The resulting analysis of the number.
	 */
	private List<String> processNumber(String sLine) {
		String cleanseNum = cleanNumber(sLine);
		List<String> oResult = new ArrayList<>();
		List<String> oChildResult = null;
		Collection<String> oWordList;
		String sTemp;
		String sRemaining;
		String sResult;
		String sAppend;

		try {
			new BigInteger(cleanseNum);
		} catch (NumberFormatException e) {
			System.err.println("Unable to parse { "+ cleanseNum +" }, Invalid number. Clearing the string to avoid processing.");
			cleanseNum = PhoneConstants.STR_EMPTY;
		}

		for (int bgn = 0; bgn < cleanseNum.length() - 1; bgn++) {
			if (bgn == 1) {
				sAppend = String.valueOf(cleanseNum.charAt(0))
						+ PhoneConstants.STR_DASH;
			} else if (bgn > 1) {
				// No two consecutive digits should remain unchanged.
				break;
			} else {
				sAppend = PhoneConstants.STR_EMPTY;
			}
			oWordList = null;
			sTemp = null;
			sRemaining = null;
			sResult = null;

			for (int nEnd = bgn + 1; nEnd <= cleanseNum.length(); nEnd++) {
				sTemp = cleanseNum.substring(bgn, nEnd);
				oWordList = coDictionary.get(sTemp);

				if (oWordList.isEmpty()) {
					continue;
				} else {
					if (nEnd != cleanseNum.length()) {
						// Not yet at its end.
						// Analyze the rest of the numbers.
						sRemaining = cleanseNum.substring(nEnd);
						oChildResult = process(sRemaining);

						if (!oChildResult.isEmpty()) {
							for (String sWord : oWordList) {
								// Loop through the word list result.
								for (String sChild : oChildResult) {
									// Loop through the child results.
									sResult = sAppend + sWord
											+ PhoneConstants.STR_DASH + sChild;

									oResult.add(sResult);
								}
							}
						} else if (sRemaining.length() == 1) {
							for (String sWord : oWordList) {
								// Loop through the word list and append the
								// single number.
								sResult = sAppend + sWord
										+ PhoneConstants.STR_DASH + sRemaining;

								oResult.add(sResult);
							}
						}
					} else {
						// Reached the end of the number sequence.
						for (String sWord : oWordList) {
							// Loop through the word list.
							oResult.add(sAppend + sWord);
						}
					}
				}
			}
		}

		return oResult;
	}

	/**
	 * Cleans the number of any invalid characters: punctuation and white
	 * spaces.
	 * 
	 * @param sNumber
	 *            The number to be cleaned.
	 * @return The cleaned number.
	 */
	private String cleanNumber(String sNumber) {
		// Remove all white spaces.
		sNumber = sNumber.replaceAll("\\p{Z}", PhoneConstants.STR_EMPTY);

		// Remove all punctuation.
		sNumber = sNumber.replaceAll("\\p{P}", PhoneConstants.STR_EMPTY);

		return sNumber;
	}

	/**
	 * This will analyze the string sequence if it is a valid number.
	 * 
	 * @param line
	 *            The line to analyze.
	 * @return The resulting analysis of the number.
	 */
	@Override
	public List<String> process(String line) {
		return processNumber(line);
	}
}
