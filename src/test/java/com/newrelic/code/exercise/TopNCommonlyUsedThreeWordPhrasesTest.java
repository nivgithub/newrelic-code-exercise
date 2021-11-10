package com.newrelic.code.exercise;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class TopNCommonlyUsedThreeWordPhrasesTest {

    @Test
    public void testMainWithNoArgument() {
        String[] args = {};
        Exception exception = assertThrows(IllegalArgumentException.class , () -> TopNCommonlyUsedThreeWordPhrases.main(args) );
        assertEquals("Missing file name as argument.", exception.getLocalizedMessage());

    }

    @Test
    public void testMainWithFileNameAsArgument() {
        String[] args = {"simple-words.txt"};
        TopNCommonlyUsedThreeWordPhrases.main(args);
        assertTrue(true);
    }

    @Test
    public void testProcessFileContentsWithGivenFileNameIsBlank(){
        Exception exception = assertThrows(IllegalArgumentException.class , () -> TopNCommonlyUsedThreeWordPhrases.processFileContents(null, 1) );
        assertEquals("Input Filename should not be null or empty!", exception.getLocalizedMessage());
    }

    @Test
    public void testProcessFileContentsWithNonExistingFile(){
        Exception exception = assertThrows(IllegalArgumentException.class , () -> TopNCommonlyUsedThreeWordPhrases.processFileContents("no-file-exists", 1) );
        assertEquals("Input File# 'no-file-exists' is either not exists or empty file or not readable!", exception.getLocalizedMessage());
    }

    @Test
    public void testProcessFileContentsWithAnEmptyFile(){
        Exception exception = assertThrows(IllegalArgumentException.class , () -> TopNCommonlyUsedThreeWordPhrases.processFileContents("empty-file.txt", 1));
        assertEquals("Input File# 'empty-file.txt' is empty!", exception.getLocalizedMessage());
    }

    @Test
    public void testProcessFileContentsWithValidFileWithSimpleContent(){
        Map<String, Integer> result = TopNCommonlyUsedThreeWordPhrases.processFileContents("simple-words.txt", 1);
        assertEquals(1, result.size());
        assertEquals(3, result.get("apple orange banana"));
    }

    @Test
    public void testProcessFileContentsWithApostrophes() {
        Map<String, Integer> result = TopNCommonlyUsedThreeWordPhrases.processFileContents("simple-words-apostrophe.txt", 1);
        assertEquals(1, result.size());
        assertEquals(3, result.get("my plant's leaves"));
    }

    @Test
    public void testProcessFileContentsWithHyphens() {
        Map<String, Integer> result = TopNCommonlyUsedThreeWordPhrases.processFileContents("simple-words-hyphens.txt", 3);
        assertEquals(2, result.size());
        assertEquals(2, result.get("world is very"));
        assertEquals(2, result.get("is very beautiful"));
    }

    @Test
    public void testProcessFileContentsWithPunctuations() {
        Map<String, Integer> result = TopNCommonlyUsedThreeWordPhrases.processFileContents("simple-words-punctuations.txt", 3);
        assertEquals(2, result.size());
        assertEquals(3, result.get("world is very"));
        assertEquals(3, result.get("is very good"));
    }

    @Test
    public void testProcessFileContentsWithSemicolons() {
        Map<String, Integer> result = TopNCommonlyUsedThreeWordPhrases.processFileContents("simple-words-semicolons.txt", 3);
        assertEquals(2, result.size());
        assertEquals(3, result.get("world is very"));
        assertEquals(3, result.get("is very good"));
    }

    @Test
    public void testProcessFileContentsWithTop100MatchesWithMobyDick() {
        Map<String, Integer> result = TopNCommonlyUsedThreeWordPhrases.processFileContents("moby-dick.txt", 100);
        assertEquals(100, result.size());
        assertEquals(89, result.get("the sperm whale"));
        assertEquals(22, result.get("now and then"));
        assertEquals(16, result.get("the head of"));
    }

    @Test
    public void testProcessFileContentsWithTop100MatchesWithBrothers() {
        Map<String, Integer> result = TopNCommonlyUsedThreeWordPhrases.processFileContents("brothers-karamazov.txt", 100);
        assertEquals(100, result.size());
        assertEquals(53, result.get("the old man"));
        assertEquals(34, result.get("i don't know"));
        assertEquals(12, result.get("what are you"));
    }





}
