package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    public void testFileKV() {
        Map<String, String> testMap = Map.of("key1", "value1", "key2", "value2");
        KeyValueStorage testKVFile = new FileKV(filepath.toString(), testMap);
        assertThat(testKVFile.get("key1", "default")).isEqualTo("value1");
        assertThat(testKVFile.get("key5", "default")).isEqualTo("default");

        testKVFile.set("key3", "value3");

        assertThat(testKVFile.get("key3", "default")).isEqualTo("value3");

        testKVFile.unset("key3");

        assertThat(testKVFile.get("key3", "default")).isEqualTo("default");
        assertThat(testKVFile.toMap()).isEqualTo(testMap);
    }
    // END
}
