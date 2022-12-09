public class Aggregation {
    public static void main(String[] args) {

    }
}

class Engine {
    private String model;
    private int year;

    public Engine(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}


class Car {
    private String name;
    private Engine engine;

    public Car(String name, Engine engine) {
        this.name = name;
        this.engine = engine;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}

