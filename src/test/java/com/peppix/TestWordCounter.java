package com.peppix;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class TestWordCounter {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void testWordCountOnEmptyFile() {
        String filePath = "files/empty-file.txt";
        runWordCountAndAssertResult(filePath, "");
    }

    @Test
    public void testWordCountOnFileWithSingleWord() {
        String expected = "foo: 1\r\n"
                       .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        String filePath = "files/single-word-file.txt";
        runWordCountAndAssertResult(filePath, expected);
    }

    @Test
    public void testWordCountOnFileWithMultipleWords() {
        String expected = "foo: 3\r\nbar: 2\r\nbaz: 1\r\n"
                       .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        String filePath = "files/multiple-words-file.txt";
        runWordCountAndAssertResult(filePath, expected);
    }

    @Test
    public void compareReshuffledFiles() {
        
        String fileOnePath = "files/multiple-words-file.txt";
        System.setOut(new PrintStream(outContent));
        String[] args = { fileOnePath };
        WordCounter.main(args);
        
        String expectedFirstFile = outContent.toString().replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        outContent.reset();
        
        String fileTwoPath = "files/multiple-words-file2.txt";
        System.setOut(new PrintStream(outContent));
        String[] argsTwo = { fileTwoPath };
        WordCounter.main(argsTwo);
        String expectedSecondFile = outContent.toString().replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        //assertTrue(expectedFirstFile.equals(expectedSecondFile));
        //assertThat(expectedFirstFile, containsString(expectedSecondFile));
        assertEquals(expectedFirstFile, expectedSecondFile, "Method outputs are not equal");
}

    private void runWordCountAndAssertResult(String filePath, String expectedOutput) {
        try {
            System.setOut(new PrintStream(outContent));
            String[] args = { filePath };
            WordCounter.main(args);
            assertEquals(expectedOutput, outContent.toString());
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            System.setOut(originalOut);
        }
    }
}
