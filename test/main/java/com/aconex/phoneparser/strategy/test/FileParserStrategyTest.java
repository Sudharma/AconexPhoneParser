package com.aconex.phoneparser.strategy.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.aconex.phoneparser.constants.PhoneConstants;
import com.aconex.phoneparser.dictionaries.DictionaryReader;
import com.aconex.phoneparser.strategy.FileParserStrategy;

/**
 * Unit Test for {@link FileParserStrategy}
 * @author sudharma
 *
 */
public class FileParserStrategyTest {
	
	public FileParserStrategyTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testProcessFile() throws IOException {
		DictionaryReader oReader = new DictionaryReader(
				PhoneConstants.DEF_DICTIONARY);
		FileParserStrategy oFileParser = new FileParserStrategy(
				oReader.getDictionary());
		Map<String, List<String>> oExpected = new HashMap<>();

		List<String> oTempList = new ArrayList<>();

		oTempList.add("WAS-ME-4");
		oTempList.add("WAS-OF-4");
		oTempList.add("9-AS-ME-4");
		oTempList.add("9-AS-OF-4");
		oExpected.put("927.634", oTempList);

		oTempList = new ArrayList<>();
		oTempList.add("SHE-FAR-BY-0");
		oTempList.add("SHE-3-AS-BY-0");
		oTempList.add("7-IF-FAR-BY-0");
		oTempList.add("7-IF-3-AS-BY-0");
		oTempList.add("7-HE-FAR-BY-0");
		oTempList.add("7-HE-3-AS-BY-0");
		oExpected.put("743-327.290", oTempList);

		oTempList = new ArrayList<>();
		oTempList.add("UNAFFECTED-YE-9");
		oExpected.put("8623332833-939", oTempList);

		oTempList = new ArrayList<>();
		oTempList.add("REGRET-ON-5");
		oTempList.add("REGRET-NO-5");
		oExpected.put("734738.665", oTempList);
		
		oTempList = new ArrayList<>();
		oTempList.add("SHE-FAR-BY-0-ME-4");
		oTempList.add("SHE-FAR-BY-0-OF-4");
		oTempList.add("SHE-3-AS-BY-0-ME-4");
		oTempList.add("SHE-3-AS-BY-0-OF-4");
		oTempList.add("7-IF-FAR-BY-0-ME-4");
		oTempList.add("7-IF-FAR-BY-0-OF-4");
		oTempList.add("7-IF-3-AS-BY-0-ME-4");
		oTempList.add("7-IF-3-AS-BY-0-OF-4");
		oTempList.add("7-HE-FAR-BY-0-ME-4");
		oTempList.add("7-HE-FAR-BY-0-OF-4");
		oTempList.add("7-HE-3-AS-BY-0-ME-4");
		oTempList.add("7-HE-3-AS-BY-0-OF-4");
		oExpected.put("74/3!3#27%29%0&63*4", oTempList);
		
		oTempList = new ArrayList<>();
		oTempList.add("IN-9-SET-IF");
		oTempList.add("IN-9-SET-HE");
		oTempList.add("HOW-SET-IF");
		oTempList.add("HOW-SET-HE");
		oTempList.add("4-MY-SET-IF");
		oTempList.add("4-MY-SET-HE");
		oExpected.put("469-738.43", oTempList);
		
		
		oTempList = new ArrayList<>();
		oTempList.add("CALL-ME");
		oTempList.add("CALL-OF");
		oExpected.put("2255.63", oTempList);
		
		try {
			Map<String, List<String>> oResult = oFileParser
					.processFile("input/Numbers.txt");

			assertNotNull(oResult);

			Set<String> oKeys = oResult.keySet();
			for (String sKey : oKeys) {
				System.out.println(sKey);
				assertTrue(oExpected.containsKey(sKey));
				assertEquals(oExpected.get(sKey), oResult.get(sKey));
			}

		} catch (IOException e) {
			fail("No exception should  occurr!");
		}

	}

}
