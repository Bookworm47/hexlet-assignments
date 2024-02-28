package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {

    public static List<String> buildApartmentsList(List<Home> realEstate, int num) {
        List<String> result = new ArrayList<>();
        List<Home> homeList = new ArrayList<>(realEstate);
        homeList.sort(Home::compareTo);
        int i = 0;
        for (Home home : homeList) {
            if (i < num) {
                result.add(home.toString());
                i++;
            } else {
                break;
            }
        }
        return result;
    }
}
// END
