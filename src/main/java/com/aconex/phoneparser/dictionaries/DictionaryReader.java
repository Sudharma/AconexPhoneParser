package com.aconex.phoneparser.dictionaries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import com.aconex.phoneparser.constants.PhoneConstants;

/**
 * This class reads the dictionary words and determines  number sequences.
 * 
@author sudharma
 */
public class DictionaryReader {
	/**
	 * This will hold the number sequences and their corresponding words based
	 * on the dictionary.
	 */
	private Dictionary dictionary;

	/**
	 * Initializes the DictionaryReader class with the default dictionary.
	 */
	public DictionaryReader() {
	}

	/**
	 * Initializes the DictionaryReader class based on the filename provided.
	 * The dictionary file should be located inside the <strong>dict</strong>
	 * folder.
	 * 
	 * @param sDictionaryName
	 *            The dictionary's filename.
	 * @throws IOException 
	 */
	public DictionaryReader(String sDictionaryName) throws IOException  {
		File oFile;
		if (sDictionaryName == null
				|| sDictionaryName.equals(PhoneConstants.STR_EMPTY)) {
			sDictionaryName = PhoneConstants.DEF_DICTIONARY;
		}
		oFile = new File(sDictionaryName);
		try {
			processDictionary(oFile);
		} catch (FileNotFoundException e) {
			System.err.println("Error occurred while accessing the file: "
					+ sDictionaryName);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error occurred while reading the file: "
					+ sDictionaryName);
			throw e;
		} finally {

		}

	}

	/**
	 * Open to read {@link Stream} , it can be used for UnitTesting from any source of Input
	 * @param stream
	 */
	public void processDictionary(Stream<String> stream) {
		// Initialize the dictionary to be populated.
		dictionary = new Dictionary();
		stream.filter(word -> !word.isEmpty()).forEach(
				eachWord -> addWord(eachWord.trim()));
	}

	/**
	 * This will analyze the dictionary and populate the dictionary map.
	 * 
	 * @param oFile
	 *            The {@link File} reading the dictionary.
	 */
	protected void processDictionary(File oFile) throws IOException {
		processDictionary(Files.readAllLines(oFile.toPath()).stream());

	}

	/**
	 * Retrieves the dictionary generated from initialization.
	 * 
	 * @return The dictionary generated.
	 */
	public Dictionary getDictionary() {
		return dictionary;
	}

	/**
	 * Adds the word into the dictionary with its number sequence as its value.
	 * 
	 * @param sWord
	 *            The word to be added.
	 */
	private void addWord(String sWord) {
		String sCurrentChar = null;
		String sValue = PhoneConstants.STR_EMPTY;

		// Initialize the integer string buffer.
		StringBuffer oInteger = new StringBuffer();
		sWord = sWord.toUpperCase();
		// Traverse through the word
		for (int i = 0; i < sWord.length(); i++) {
			sCurrentChar = String.valueOf(sWord.charAt(i));

			if (PhoneConstants.STR_2.contains(sCurrentChar)) {
				oInteger.append(2);
			} else if (PhoneConstants.STR_3.contains(sCurrentChar)) {
				oInteger.append(3);
			} else if (PhoneConstants.STR_4.contains(sCurrentChar)) {
				oInteger.append(4);
			} else if (PhoneConstants.STR_5.contains(sCurrentChar)) {
				oInteger.append(5);
			} else if (PhoneConstants.STR_6.contains(sCurrentChar)) {
				oInteger.append(6);
			} else if (PhoneConstants.STR_7.contains(sCurrentChar)) {
				oInteger.append(7);
			} else if (PhoneConstants.STR_8.contains(sCurrentChar)) {
				oInteger.append(8);
			} else if (PhoneConstants.STR_9.contains(sCurrentChar)) {
				oInteger.append(9);
			}
		}

		// Convert the number sequence into an integer and add them
		// into the dictionary.
		sValue = oInteger.toString();

		// Check if the number has already been encountered.
		if (dictionary.containsKey(sValue)) {
			Collection<String> oWordList = dictionary.get(sValue);
			if (!oWordList.contains(sWord)) {
				// The word is new. Add the word into the list.
				oWordList.add(sWord);
			}
		} else {
			// Add the number and its word to the map.
			Collection<String> oWordList = new ArrayList<>();
			oWordList.add(sWord);
			dictionary.put(sValue, oWordList);
		}

	}
}
