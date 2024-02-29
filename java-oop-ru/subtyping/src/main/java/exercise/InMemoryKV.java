package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
class InMemoryKV implements KeyValueStorage {
    private Map<String, String> kvStorage;

    InMemoryKV(Map<String, String> kvStorage) {
        Map<String, String> inputMap = new HashMap<>(kvStorage);
        this.kvStorage = inputMap;
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> changedStorage = new HashMap<>(kvStorage);
        changedStorage.put(key, value);
        kvStorage = changedStorage;
    }

    @Override
    public void unset(String key) {
        Map<String, String> changedStorage = new HashMap<>(kvStorage);
        changedStorage.remove(key);
        kvStorage = changedStorage;
    }

    @Override
    public String get(String key, String defaultValue) {
        return kvStorage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> faceMap = new HashMap<>(kvStorage);
        return faceMap;
    }
}
// END
