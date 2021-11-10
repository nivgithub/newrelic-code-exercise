package com.newrelic.code.exercise;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/**
 * Reads the file name from the standard input.
 * Reads the file from resources with given file name and returns the top 100 repeated 3 word sequences.
 */
public class TopNCommonlyUsedThreeWordPhrases {
    private static final String REGEX_WORD_PATTERN = "((\\w+'\\w+)|(\\w+)|(\\w+-?\\w+))";

    /**
     * Process standard input and prints top 100 3 word sequences and also prints the time taken for the whole process.
     *
     * @param args
     */
    public static void main(String args[]) {
        String fileName;
        if (args.length > 0 && args[0] != null) {
            fileName = args[0];
        } else {
            throw new IllegalArgumentException("Missing file name as argument.");
        }

        System.out.println("Given file name is:" + fileName);
        long startTime = System.nanoTime();

        Map<String, Integer> threeWordPhraseCounts = processFileContents(fileName, 100);
        System.out.println("----------------------------------------");
        threeWordPhraseCounts.entrySet().forEach(entry -> {
            System.out.println(String.format("%-30s  %d" , entry.getKey() , entry.getValue()));
        });
        System.out.println("----------------------------------------");

        long endTime = System.nanoTime();
        System.out.println("Time taken : " + (endTime - startTime) + " nano seconds");
        long totalTime = TimeUnit.SECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS);
        System.out.println("Time taken : " + totalTime + " seconds");

    }

    /**
     * Process the file contents and returns topN matching 3 word sequences and no of times sequence is repeated.
     *
     * @param fileName the file name which present in the resources.
     * @param topN     the number of matches.
     * @return returns the given topN matching 3 word sequences and no of times the sequences repeated.
     */
    public static Map<String, Integer> processFileContents(String fileName, int topN) {

        if (StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException("Input Filename should not be null or empty!");
        }

        InputStream inputStream = TopNCommonlyUsedThreeWordPhrases.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException(String.format("Input File# '%s' is either not exists or empty file or not readable!", fileName));
        }

        List<String> lines;
        try {
            lines = IOUtils.readLines(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid file. " + e.getMessage());
        }

        if (CollectionUtils.isEmpty(lines)) {
            throw new IllegalArgumentException(String.format("Input File# '%s' is empty!", fileName));
        }

        Pattern pattern = Pattern.compile(REGEX_WORD_PATTERN);
        Map<String, Integer> wordMatchingMap = new HashMap<>();

        lines.stream().forEach(line -> {
            updateThreeWordPhrasesMap(pattern, wordMatchingMap, line);
        });

        return findTopGivenPhrases(wordMatchingMap, topN);
    }

    private static void updateThreeWordPhrasesMap(Pattern pattern, Map<String, Integer> wordMatchingMap, String line) {
        List<String> matchList = new ArrayList<>();

        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            matchList.add(matcher.group().toLowerCase());
            if (matchList.size() == 3) {
                String phrase = buildPhrase(new String[]{matchList.get(0), matchList.get(1), matchList.get(2)});
                if (wordMatchingMap.containsKey(phrase)) {
                    wordMatchingMap.put(phrase, wordMatchingMap.get(phrase) + 1);
                } else {
                    wordMatchingMap.put(phrase, 1);
                }
                matchList.remove(0);
            }
        }
    }

    private static String buildPhrase(String[] words) {
        return StringUtils.join(words, " ");
    }

    private static Map<String, Integer> findTopGivenPhrases(Map<String, Integer> wordMatchingMap, int topN) {
        //print top 100 commonly used phases
        Map<String, Integer> topPhrases = wordMatchingMap.entrySet().stream().filter(entry -> entry.getValue() > 1)
                .sorted(Collections.reverseOrder(comparingByValue())).limit(topN)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));

        return topPhrases;
    }
}
