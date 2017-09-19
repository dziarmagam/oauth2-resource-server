package jc.entity;

public class Car {

    public final int id;
    public final String color;
    public final String name;

    public Car(int id, String color, String name) {
        this.id = id;
        this.color = color;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
