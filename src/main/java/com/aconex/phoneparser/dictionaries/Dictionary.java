
package com.aconex.phoneparser.dictionaries;

import java.util.Collection;
import java.util.HashMap;

import com.aconex.phoneparser.constants.PhoneConstants;

/**
 * This is a hashmap with a String Key and an Integer Value. The String is the
 * word, and the Integer is its phone number value.
 * 
 * @author Sudharma
 * 
 */
public class Dictionary extends HashMap<String, Collection<String>> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Returns the value to which the specified key is mapped in this identity
	 * hash map, or <i>an empty list</i> if the map contains no mapping for this
	 * key.
	 * 
	 * @param key
	 *            the key whose associated value is to be returned.
	 * @return the value to which this map maps the specified key, or <i>an
	 *         empty list</i> if the map contains no mapping for this key.
	 */
	public Collection<String> get(String key) {
		Collection<String> oValue = super.getOrDefault(key,PhoneConstants.EMPTY_LIST);
		return oValue;
	}
}
