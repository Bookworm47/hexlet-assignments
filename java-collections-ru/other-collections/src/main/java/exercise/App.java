package exercise;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

// BEGIN
class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> mapFirst, Map<String, Object> mapSecond) {
        LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
        TreeSet<String> allKeys = new TreeSet<>();
        allKeys.addAll(mapFirst.keySet());
        allKeys.addAll(mapSecond.keySet());

        for (String key : allKeys) {
            if (mapFirst.containsKey(key) && mapSecond.containsKey(key)) {
                if (mapFirst.get(key).equals(mapSecond.get(key))) {
                    resultMap.put(key, "unchanged");
                } else {
                    resultMap.put(key, "changed");
                }
            } else if (mapFirst.containsKey(key)) {
                resultMap.put(key, "deleted");
            } else if (mapSecond.containsKey(key)) {
                resultMap.put(key, "added");
            }
        }
        return resultMap;
    }
}
//END
