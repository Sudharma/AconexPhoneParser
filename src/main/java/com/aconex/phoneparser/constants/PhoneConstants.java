
package com.aconex.phoneparser.constants;

import java.util.ArrayList;
import java.util.Collection;

public interface PhoneConstants
{
    /**
     * The default dictionary name. This will be used if in case no dictionary
     * filename has been defined.
     */
    String DEF_DICTIONARY = "dict/Dictionary.txt";

    /**
     * Empty string.
     */
    String STR_EMPTY = "";

    /**
     * The directory of the default coding dictionary.
     */
    String DEF_DICT_DIR = "dict/";
    
    /**
     * The directory where to find the input files.
     */
    String DEF_INPUT_DIR = "input/";

    /**
     * The letters found in phone key 2.
     */
    String STR_2 = "ABC";

    /**
     * The letters found in phone key 3.
     */
    String STR_3 = "DEF";

    /**
     * The letters found in phone key 4.
     */
    String STR_4 = "GHI";

    /**
     * The letters found in phone key 5.
     */
    String STR_5 = "JKL";

    /**
     * The letters found in phone key 6.
     */
    String STR_6 = "MNO";

    /**
     * The letters found in phone key 7.
     */
    String STR_7 = "PQRS";

    /**
     * The letters found in phone key 8.
     */
    String STR_8 = "TUV";

    /**
     * The letters found in phone key 9.
     */
    String STR_9 = "WXYZ";

    /**
     * The quit string.
     */
    String STR_EXIT = "exit";

    /**
     * An empty list.
     */
    Collection<String> EMPTY_LIST = new ArrayList<>( );

    /**
     * Dash
     */
    String STR_DASH = "-";

    /**
     * The dictionary file option.
     */
    String STR_DICT_OPTION = "-d";

    /**
     * Usage instructions.
     */
    String STR_INSTRUCTIONS = "Usage: java -jar aconex-phone-parser.jar  [-d \"Dictionary File\"] [Filename / Numbers]";
    
}
