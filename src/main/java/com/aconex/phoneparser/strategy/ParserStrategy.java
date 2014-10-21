package com.aconex.phoneparser.strategy;

/**
 * 
 * Base Strategy to be used by Child to implement specific process.
 * 
 * @author sudharma
 *
 */
public abstract class ParserStrategy {

	/**
	 * Implement specific parsing strategy and visit 
	 * @param name
	 * @return
	 */
	public abstract <T> T process(String name);

}
