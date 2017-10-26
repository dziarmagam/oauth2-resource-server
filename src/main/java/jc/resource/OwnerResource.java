package jc.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import jc.entity.Owner;
import jc.service.OwnerService;

public class OwnerResource {

    @Autowired
    OwnerService ownerService;

    @RequestMapping("/owners/{id}")
    public Owner getOwner(@PathVariable("id") int id) {
        return ownerService.getOwner(id);
    }

    @RequestMapping("/owners")
    public List<Owner> getOwners() {
        return ownerService.getOwners();
    }
}
