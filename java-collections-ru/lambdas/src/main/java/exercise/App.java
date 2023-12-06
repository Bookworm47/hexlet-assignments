package exercise;


import java.util.Arrays;


// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] image) {

        String[][] rowMiddle = Arrays.stream(image)
                .map(App::dublicateValuses)
                .toArray(String[][]::new);

        return Arrays.stream(rowMiddle)
                .flatMap(row -> Arrays.stream(new String[][]{row, row}))
                .toArray(String[][]::new);
    }

    private static String[] dublicateValuses(String[] strings) {
        return Arrays.stream(strings)
                .flatMap(element -> Arrays.stream(new String[]{element, element}))
                .toArray(String[]::new);
    }
}

