package exercise;

import java.util.Locale;

// BEGIN
class Flat implements Home {
    private double area; //жилая площадь квартиры, число типа `double`
    private double balconyArea; //площадь балкона, число типа `double`
    private int floor; //этаж, на котором расположена квартира

    Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home anotherHome) {
        return Home.super.compareTo(anotherHome);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "Квартира площадью %.1f метров на %d этаже", getArea(), floor);
    }
}
// END
