package com.aconex.phoneparser.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.aconex.phoneparser.dictionaries.test.DictionaryReaderTest;
import com.aconex.phoneparser.strategy.test.FileParserStrategyTest;
import com.aconex.phoneparser.strategy.test.NumberParserStrategyTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
		{ DictionaryReaderTest.class, 
		  FileParserStrategyTest.class,
		NumberParserStrategyTest.class 
		})
public class TestSuiteRunner {

}
