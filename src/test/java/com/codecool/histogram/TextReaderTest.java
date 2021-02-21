package com.codecool.histogram;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class TextReaderTest {

    private final String fileName = "src/test/resources/test.txt";
    private final File testFile = new File(fileName);
    private final FileWriter writer = new FileWriter(testFile);

    private final TextReader textReader = new TextReader(fileName);

    public TextReaderTest() throws IOException {
        if (!testFile.exists()) {
            testFile.delete();
        }
        testFile.createNewFile();
    }

    @Test
    public void isFileNotExistsThrowsException() {
        assertThrows(FileNotFoundException.class, () -> new TextReader("").read());
    }

    @Test
    public void existingButEmptyFiles() throws IOException {
        assertEquals(textReader.read().length(), 0);
    }

    @Test
    public void oneLineTextInTextFile() throws IOException {
        writer.write("Harry Potter and the Sorcerer's Stone");
        writer.flush();
        String[] lines = textReader.read().split("\n\r|\r|\n");
        assertEquals(lines.length, 1);
    }

    @Test
    public void multiplyLineTextInTextFile() throws IOException {
        writer.write("Harry Potter and the Sorcerer's Stone" +
                "\nHarry Potter and the Chamber of Secrets");
        writer.flush();
        String[] lines = textReader.read().split("\n\r|\r|\n");
        assertTrue(lines.length > 1);
    }
    
}
