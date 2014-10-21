package com.aconex.phoneparser.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.aconex.phoneparser.constants.PhoneConstants;

/**
 * A Simple Utility to generate set of Dictionary words out of random paragraphs
 * 
 * @author spuranik
 *
 */
public class WordGenerator {

	public static void main(String[] args) throws IOException {

		File oFile = new File(PhoneConstants.DEF_DICT_DIR + "wordGenerator.txt");
		List<String> allWords = new ArrayList<>();
		Files.lines(oFile.toPath())
				.parallel()
				.map(eachLine -> {
					return eachLine.replaceAll("\\p{P}", "");
				})
				.distinct()
				.forEach(
						eachLine -> allWords.addAll(Arrays.asList(eachLine
								.split("\\s+"))));

		FileWriter fw = new FileWriter(PhoneConstants.DEF_DICT_DIR
				+ "wordGenerator_out.txt");
		try {
			allWords.forEach(eachWord -> {
				eachWord.trim();
				try {
					fw.write(eachWord + System.lineSeparator());
				} catch (Exception e) {
					System.err.println("Unable to Write to File "
							+ oFile.getName());
				}
			});
			fw.flush();
		} finally {
			fw.close();
		}

	}
}
