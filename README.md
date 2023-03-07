
# Adaptavist test comments 
The application checks that a command-line argument is provided and that points to an existing file
Reads the file and counts the occurrences of each word, ignoring whitespace and empty lines (method: readFile()) 
Sorts the words by their frequency in descending orderand prints each word with its count (method: orderWordCount()) 

# To reshuffle file lines I used the following online app 
https://onlinetools.com/random/shuffle-lines

# maven usual commands
- mvn clean - to clean the target build files and folders
- mvn compile - build the classes
- mvn package - build the executable jar file
- mvn test - run the test suite

# command line:
 java  '-cp' '.\target\classes' 'com.peppix.WordCounter' 'files\loremipsumshuffled.txt'

### executable jar
java -jar .\target\maven-unit-test-jar-with-dependencies.jar .\files\loremipsum.txt

# test
Created a ByteArrayOutputStream and redirected the System.out stream to it, so that we can capture the output of the WordCounter application during the tests
It defines several test cases that cover different scenarios, such as:
- An empty file (testWordCountOnEmptyFile())
- A file with a single word (testWordCountOnFileWithSingleWord())
- A file with multiple words, including duplicates (testWordCountOnFileWithMultipleWord())
- Two copies of the same file content reshuffled (compareReshuffledFiles())

For each test case, it invokes the WordCounter application with the appropriate command-line arguments and asserts that the output matches the expected output (in the case of successful tests) or the expected error message (in the case of tests that are expected to fail)
It cleans up by resetting the System.out stream to its original value, so that subsequent tests are not affected by the previous ones
Note that the test cases assume that the test files are located in the "files" directory. 

