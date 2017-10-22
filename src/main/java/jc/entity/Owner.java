package jc.entity;

public class Owner {

    public final String name;
    public final Car car;

    public Owner(String name, Car car) {
        this.name = name;
        this.car = car;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", car=" + car +
                '}';
    }
}
