package com.codecool.histogram;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistogramTest {

    private static final Histogram histogram = new Histogram();
    private static final String fileName = "src/test/resources/test.txt";
    private static final String emptyFileName = "src/test/resources/empty.txt";

    private static final int step = 4;
    private static final int amount = 5;

    @Test
    public void positiveIntegersAddedAsParameters() {

        List<Range> list = histogram.generateRanges(step, amount);

        int size = list.size();

        int fromAmount = ((list.get(size - 1).getFrom() - 1) / step) + 1;
        assertTrue(amount == size && amount == fromAmount);
    }

    @Test
    public void negativeIntegerAsStepParameter() {
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(-1,2));
    }

    @Test
    public void negativeIntegerAsAmountParameter() {
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(1,-2));
    }

    @Test
    public void allWordsInTextIsInOneOfTheGivenRanges() throws IOException {

        List<Range> ranges = new ArrayList<>();
        ranges.add(new Range(1, 100));

        File testFile = new File(fileName);
        FileWriter writer = new FileWriter(testFile);
        writer.write("Harry Potter and the Chamber of Secrets");
        writer.flush();

        TextReader reader = new TextReader(fileName);
        String words = reader.read();
        Map<Range, Integer> map = histogram.generate(words, ranges);

        assertEquals(map.get(ranges.get(0)), 7);
    }

    @Test
    public void textWithWordsOutOfGivenRanges() throws IOException {
        List<Range> ranges = new ArrayList<>();
        ranges.add(new Range(1, 2));

        File testFile = new File(fileName);
        FileWriter writer = new FileWriter(testFile);
        writer.write("Harry Potter and the Chamber of Secrets");
        writer.flush();

        TextReader reader = new TextReader(fileName);
        String words = reader.read();
        Map<Range, Integer> map = histogram.generate(words, ranges);

        assertEquals(map.get(ranges.get(0)), 1);
    }

    @Test
    public void emptyText() throws IOException {
        TextReader reader = new TextReader(emptyFileName);
        assertEquals(reader.read(), "");
    }

    @Test
    public void nullAsText() {
        List<Range> ranges = new ArrayList<>();
        ranges.add(new Range(1, 2));
        String words = null;
        assertThrows(IllegalArgumentException.class, () -> histogram.generate(words, ranges));
    }

    @Test
    public void nullAsRanges() {
        List<Range> ranges = new ArrayList<>();
        Range range = null;
        ranges.add(range);
        String words = "postgres";
        assertThrows(NullPointerException.class, () -> histogram.generate(words, ranges));
    }

    @Test
    public void generateHistogramsMultipleTimes() {
        ArrayList<Histogram> histograms = new ArrayList<>();
        histograms.add(new Histogram());
        histograms.add(new Histogram());
        histograms.add(new Histogram());
        assertEquals(histograms.size(), 3);
    }
}
