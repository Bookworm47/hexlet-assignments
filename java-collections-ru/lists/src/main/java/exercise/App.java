package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static boolean scrabble(String symbols, String word) {
        boolean result = true;
        String checkWord = word.toLowerCase();
        int count = 0;
        if (symbols.length() < word.length()) {
            return false;
        }
        List<String> wordSymbols = new ArrayList<>(Arrays.asList(symbols.toLowerCase().split("")));
        List<String> wordArray = new ArrayList<>(Arrays.asList(word.toLowerCase().split("")));
        for (String s : wordArray) {
            if (!wordSymbols.contains(s)) {
                result = false;
                break;
            }
            wordSymbols.remove(s);
        }
        return result;
    }
}
//END
