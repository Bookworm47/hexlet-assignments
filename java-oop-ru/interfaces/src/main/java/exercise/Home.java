package exercise;

// BEGIN
public interface Home {

    double getArea();
    default int compareTo(Home anotherHome) {
        int result;
        if (getArea() == anotherHome.getArea()) {
            result = 0;
        } else {
            result = getArea() > anotherHome.getArea() ? 1 : -1;
        }
        return result;
    }
}
// END
