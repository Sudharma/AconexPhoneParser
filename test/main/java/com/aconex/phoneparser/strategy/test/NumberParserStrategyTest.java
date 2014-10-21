package com.aconex.phoneparser.strategy.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.aconex.phoneparser.constants.PhoneConstants;
import com.aconex.phoneparser.dictionaries.DictionaryReader;
import com.aconex.phoneparser.strategy.NumberParserStrategy;

/**
 * Unit Test fo {@link NumberParserStrategy}
 * 
 * @author sudharma
 *
 */
public class NumberParserStrategyTest {
	public NumberParserStrategyTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testProcessNumber_1() throws IOException {
		DictionaryReader oReader = new DictionaryReader(
				PhoneConstants.DEF_DICTIONARY);
		NumberParserStrategy oNumParse = new NumberParserStrategy(
				oReader.getDictionary());
		List<String> oResult = oNumParse.process("726.293");
		List<String> oExpected = new ArrayList<String>();

		oExpected.add("7-AM-BY-3");
		oExpected.add("7-AM-2-YE");
		oExpected.add("7-AN-BY-3");
		oExpected.add("7-AN-2-YE");

		assertEquals(oExpected.size(), oResult.size());
		oExpected.removeIf(exp -> oResult.contains(exp));
		assertEquals(0, oExpected.size());
	}

	@Test
	public void testProcessNumber_2() throws IOException {
		DictionaryReader oReader = new DictionaryReader(
				PhoneConstants.DEF_DICTIONARY);
		NumberParserStrategy oNumParse = new NumberParserStrategy(
				oReader.getDictionary());
		List<String> oResult = oNumParse.process("7469-43");
		List<String> oExpected = new ArrayList<String>();

		oExpected.add("SHOW-IF");
		oExpected.add("SHOW-HE");
		oExpected.add("7-IN-9-IF");
		oExpected.add("7-IN-9-HE");
		oExpected.add("7-HOW-IF");
		oExpected.add("7-HOW-HE");

		assertEquals(oExpected.size(), oResult.size());
		oExpected.removeIf(exp -> oResult.contains(exp));
		assertEquals(0, oExpected.size());
	}

	@Test
	public void testProcessNumber_3() throws IOException {
		DictionaryReader oReader = new DictionaryReader(
				PhoneConstants.DEF_DICTIONARY);
		NumberParserStrategy oNumParse = new NumberParserStrategy(
				oReader.getDictionary());
		List<String> oResult = oNumParse.process("74/3!3#27%29%0&63*4");
		List<String> oExpected = new ArrayList<>();

		oExpected.add("SHE-FAR-BY-0-ME-4");
		oExpected.add("SHE-FAR-BY-0-OF-4");
		oExpected.add("SHE-3-AS-BY-0-ME-4");
		oExpected.add("SHE-3-AS-BY-0-OF-4");
		oExpected.add("7-IF-FAR-BY-0-ME-4");
		oExpected.add("7-IF-FAR-BY-0-OF-4");
		oExpected.add("7-IF-3-AS-BY-0-ME-4");
		oExpected.add("7-IF-3-AS-BY-0-OF-4");
		oExpected.add("7-HE-FAR-BY-0-OF-4");
		oExpected.add("7-HE-FAR-BY-0-ME-4");
		oExpected.add("7-HE-3-AS-BY-0-ME-4");
		oExpected.add("7-HE-3-AS-BY-0-OF-4");

		assertEquals(oExpected.size(), oResult.size());
		oExpected.removeIf(exp -> oResult.contains(exp));
		assertEquals(0, oExpected.size());
	}

	@Test
	public void testProcessNumberFail_1() throws IOException {
		DictionaryReader oReader = new DictionaryReader(
				PhoneConstants.DEF_DICTIONARY);
		NumberParserStrategy oNumParse = new NumberParserStrategy(
				oReader.getDictionary());
		List<String> oResult = oNumParse.process("a5483728873");

		assertEquals(0, oResult.size());
	}

	@Test
	public void testAnalyzeNumberFail_2() throws IOException {
		DictionaryReader oReader = new DictionaryReader(
				PhoneConstants.DEF_DICTIONARY);
		NumberParserStrategy oNumParse = new NumberParserStrategy(
				oReader.getDictionary());
		List<String> oResult = oNumParse.process("56837a25873");

		assertEquals(0, oResult.size());
	}

	@Test
	public void testProcessNumberFail_3() throws IOException {
		DictionaryReader oReader = new DictionaryReader(
				PhoneConstants.DEF_DICTIONARY);
		NumberParserStrategy oNumParse = new NumberParserStrategy(
				oReader.getDictionary());
		List<String> oResult = oNumParse.process("148$3727873");

		assertEquals(0, oResult.size());
	}

	@Test
	public void testProcessNumberFail_4() throws IOException {
		DictionaryReader oReader = new DictionaryReader(
				PhoneConstants.DEF_DICTIONARY);
		NumberParserStrategy oNumParse = new NumberParserStrategy(
				oReader.getDictionary());
		List<String> oResult = oNumParse.process("54883728873+");

		assertEquals(0, oResult.size());
	}

	@Test
	public void testProcessNumberFail_5() throws IOException {
		DictionaryReader oReader = new DictionaryReader(
				PhoneConstants.DEF_DICTIONARY);
		NumberParserStrategy oNumParse = new NumberParserStrategy(
				oReader.getDictionary());
		List<String> oResult = oNumParse.process("54831728128673");

		assertEquals(0, oResult.size());
	}

}
