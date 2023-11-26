package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {
    public static String getForwardedVariables(String conf) {
//        "X[^,]*,"
        String[] arrayConf = conf.split("\n");
        return Arrays.stream(arrayConf)
                .filter(string -> string.contains("X_FORWARDED") && string.startsWith("environment="))
                .flatMap(s -> Arrays.stream(s.split(",")))
                .filter(string -> string.contains("X_FORWARDED"))
                .map(x -> x.replaceAll("environment=|\"|X_FORWARDED_|,", ""))
                .collect(Collectors.joining(","));
    }
}
//END
