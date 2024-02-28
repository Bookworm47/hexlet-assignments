package exercise;

import java.util.Locale;
// BEGIN
class Cottage implements Home {
    private double area; //Общая площадь коттеджа, число типа `double
    private int floorCount; //количество этажей

    Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home anotherHome) {
        return Home.super.compareTo(anotherHome);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%d этажный коттедж площадью %.1f метров", floorCount, area);
    }
}
// END
