# Building

Build using ant:

    ant jar
    generating build will also copy the dictionary and number file to the same dist folder

#Prerequisite to Run the program
  
•	Java 1.8 
•	If Eclipse , LUNA version.
•	Ant version > 1.9.0
•	Junit 4.0

# Running

Running without a Number file, will result in reading number from STDIN:

    java -jar dist/aconex-phone-parser.jar -d dict/Dictionary.txt

Or alternatively, you can run it with a number file:

    java -jar dist/ aconex-phone-parser.jar  -d dict/Dictionary.txt input/Numbers.txt

for help

     java –jar aconex-phone-parser.jar –h or --help

# Design overview

I chose this coding test because I was curious to know what all the numbers can make meaning to sentences.

The `PhoneNumParser` class is the primary class, It initializes the Dictionary Object. `PhoneNumParser`  internally uses Strategy Pattern to use various Strategies depending on the input type given to the program. 
	 FileParserStrategy takes care of the  reading of the Number file and processes with the Dictionary. Similar is the case with the NumberParserStrategy which Uses input as Numbers. CmdLineParserStrategy is used to read it from STDIN. Dictionary is always passed to Different Strategy for the processing. 
	 For Various reading of file and other processing I have used Java-8 lambda expressions.

The Test package is written for Testing of a) Numbers with valid and invalid format. b) reading from number text file and comparing the outputs. c) handling case insensitive.



 
	   
