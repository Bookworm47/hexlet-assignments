package exercise;

import java.io.IOException;
import java.nio.file.Path;


import static java.nio.file.Files.readString;
import static java.nio.file.Files.writeString;

// BEGIN
class App {
    public static void save(Path pathToFile, Car car) {
        String objToFile = car.serialize();
        try {
            writeString(pathToFile, objToFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car extract(Path pathToFile) {
        String carJson = null;
        try {
            carJson = readString(pathToFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Car.unserialize(carJson);
    }
}

// END
