package jc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import jc.entity.Owner;

@Service
public class OwnerService {


    private final List<Owner> owners;

    @Autowired
    public OwnerService(CarService carService) {
        this.owners = Arrays.asList(
                new Owner("Sam", carService.getCar(0)),
                new Owner("Max", carService.getCar(1)),
                new Owner("Niko", carService.getCar(2))
        );
    }


    public Owner getOwner(int id) {
        if (owners.size() <= id) {
            return null;
        } else {
            return owners.get(id);
        }
    }

    public List<Owner> getOwners() {
        return owners;
    }
}
