package com.aconex.phoneparser.dictionaries.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.stream.Stream;

import org.junit.Test;

import com.aconex.phoneparser.constants.PhoneConstants;
import com.aconex.phoneparser.dictionaries.DictionaryReader;

/**
 * Unit Test class for {@link DictionaryReader}
 * @author sudharma
 *
 */
public class DictionaryReaderTest {
	
	public DictionaryReaderTest() {
		// TODO Auto-generated constructor stub
	}

	 @Test
	public void testProcessDictionary() throws IOException {
		DictionaryReader oReader = new DictionaryReader(
				PhoneConstants.DEF_DICTIONARY);
		assertNotNull(oReader.getDictionary());
	}

	@Test
	public void testForAllCapitalLetters() {
		Stream<String> build = Stream.of("CaLl", "Me", "HI");
		DictionaryReader dr = new DictionaryReader();
		dr.processDictionary(build);

		long countOfUpperCase = dr.getDictionary().keySet().stream()
				.filter(str -> str.equals(str.toUpperCase())).count();
		org.junit.Assert.assertEquals(3, countOfUpperCase);
	}
	
	/**
	 * HugeDictionary can be more than 20k inputs.
	 * @throws IOException 
	 */
//	@Test
	public void testHugeDataForAllCapitalLetters() throws IOException {
		DictionaryReader dr = new DictionaryReader("HugeDictionary.txt");
		long countOfUpperCase = dr.getDictionary().keySet().parallelStream()
				.filter(str -> str.equals(str.toUpperCase())).count();
		org.junit.Assert.assertEquals(1145, countOfUpperCase);
	}
}
