package jc.resource;

import jc.entity.Car;
import jc.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarResource {

    @Autowired
    CarService carService;

    @RequestMapping("/cars/{id}")
    public Car getCar(@PathVariable("id") int id){
        return carService.getCar(id);
    }

    @RequestMapping("/cars")
    public List<Car> getCars(){
        return carService.getCars();
    }

}
