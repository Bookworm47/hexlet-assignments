package exercise;

import java.util.Map;


// BEGIN
class App {

    public static void swapKeyValue(KeyValueStorage kvs) {
        for (Map.Entry<String, String> change : kvs.toMap().entrySet()) {
            String key = change.getKey();
            String value = change.getValue();
            kvs.unset(key);
            kvs.set(value, key);
        }
    }
}
// END
