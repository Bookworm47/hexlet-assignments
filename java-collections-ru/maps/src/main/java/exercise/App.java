package exercise;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> result = new HashMap<>();
        if (sentence.isEmpty()) {
            return result;
        }
        String[] wordsArray = sentence.split(" ");
        Set<String> uniqeKeys = new HashSet<>(Arrays.asList(wordsArray));

        for (String word : uniqeKeys) {
            int count = 0;
            for (String countWords : wordsArray) {
                if (word.equals(countWords)) {
                    count++;
                }
            }
            result.put(word, count);
        }
        return result;
    }


    public static String toString(Map<String, Integer> someMap) {
        StringBuilder resultToString = new StringBuilder();
        resultToString.append("{");
        if (!someMap.isEmpty()) {
            resultToString.append("\n");
            for (String keys : someMap.keySet()) {
                resultToString.append("  ").append(keys).append(": ")
                        .append(Integer.toString(someMap.get(keys))).append("\n");
            }
        }
        resultToString.append("}");
        return resultToString.toString();
    }
}
//END
