package exercise;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        String keyBirthday = "birthday";
        return users.stream()
                .filter(male -> male.get("gender").compareTo("male") == 0)
                .sorted(Comparator.comparing(x -> LocalDate.parse(x.get(keyBirthday))))
                .map(x -> x.get("name"))
                .collect(Collectors.toList());
    }
}
// END
