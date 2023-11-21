package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {

    public static long getCountOfFreeEmails(List<String> emails) {
        List<String> freeDom = Arrays.asList("@gmail.com", "@yandex.ru", "@hotmail.com");
        return emails.stream()

                .filter(x -> freeDom.stream().anyMatch(x::contains))
                .count();
    }
}
// END
