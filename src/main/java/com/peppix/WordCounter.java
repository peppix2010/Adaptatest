package com.peppix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WordCounter {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: WordCounter <file_path>");
            System.exit(1);
        }
        String filePath = args[0];
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            System.exit(1);
        }
        if (!file.isFile()) {
            System.out.println("Invalid file.");
            System.exit(1);
        }

        Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
        readFile(file, wordCountMap);

        List<Map.Entry<String, Integer>> wordCountList = new LinkedList<Map.Entry<String, Integer>>(wordCountMap.entrySet());
        orderWordCount(wordCountList);
    }

    private static void readFile(File file, Map<String, Integer> wordCountMap) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.length() > 0) {
                        if (wordCountMap.containsKey(word)) {
                            wordCountMap.put(word, wordCountMap.get(word) + 1);
                        } else {
                            wordCountMap.put(word, 1);
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void orderWordCount(List<Entry<String, Integer>> wordCountList) {
        Collections.sort(wordCountList, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        for (Map.Entry<String, Integer> entry : wordCountList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
