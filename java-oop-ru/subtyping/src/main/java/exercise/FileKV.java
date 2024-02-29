package exercise;

import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {
    private String keyValueDataPath;
    private Map<String, String> kvMap;

    FileKV(String keyValueDataPath, Map<String, String> kvMap) {
        this.keyValueDataPath = keyValueDataPath;
        this.kvMap = kvMap;
        Utils.writeFile(keyValueDataPath, Utils.serialize(kvMap));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> setNewMap;
        setNewMap = Utils.unserialize(Utils.readFile(keyValueDataPath));
        setNewMap.put(key, value);
        Utils.writeFile(keyValueDataPath, Utils.serialize(setNewMap));
    }

    @Override
    public void unset(String key) {
        Map<String, String> setNewMap;
        setNewMap = Utils.unserialize(Utils.readFile(keyValueDataPath));
        setNewMap.remove(key);
        Utils.writeFile(keyValueDataPath, Utils.serialize(setNewMap));
    }

    @Override
    public String get(String key, String defaultValue) {
        return Utils.unserialize(Utils.readFile(keyValueDataPath)).getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(Utils.readFile(keyValueDataPath));
    }
}
// END
