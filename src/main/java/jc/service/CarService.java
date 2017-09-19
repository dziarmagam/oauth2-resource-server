package jc.service;

import jc.entity.Car;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService {

    private final List<Car> cars = Arrays.asList(
            new Car(1, "RED", "Red BMW"),
            new Car(2, "RED", "Red Skoda"),
            new Car(3, "BLUE", "Red Fiat"),
            new Car(4, "Black", "Red Ford")
    );

    public List<Car> getCars() {
        return cars;
    }

    public Car getCar(int id) {
        if (id < 0 || id >= cars.size()) {
            return null;
        } else {
            return cars.get(id);
        }
    }
}
