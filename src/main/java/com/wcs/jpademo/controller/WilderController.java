package com.wcs.jpademo.controller;

import com.wcs.jpademo.entity.Wilder;
import com.wcs.jpademo.repository.WilderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wilders")
public class WilderController {

    @Autowired
    WilderRepository wilderRepository;

    @PostMapping
    public Wilder create(String name, String email, String category){
        Wilder wilder = new Wilder(name, email, category);
        return wilderRepository.save(wilder);
    }

    @GetMapping
    public List<Wilder> getAll(){
        return wilderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Wilder getById(@PathVariable Integer id){
        // on recupère un conteneur qui contient ou pas un wilder.
        Optional<Wilder> wilderOptional = wilderRepository.findById(id);
        // Si le conteneur contient qqchose, on renvoit le wilder
        if(wilderOptional.isPresent()) return wilderOptional.get();
        // sinon on ne renvoit rien.
        return null;
    }

    @PutMapping("/{id}")
    public Wilder updateById(@PathVariable Integer id,String name, String email, String category ){
        // on recupère un conteneur qui contient ou pas un wilder.
        Optional<Wilder> wilderOptional = wilderRepository.findById(id);

        if(wilderOptional.isEmpty()) return null;

        Wilder wilder = wilderOptional.get();
        wilder.setName(name);
        wilder.setEmail(email);
        wilder.setCategory(category);
        return wilderRepository.save(wilder);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        wilderRepository.deleteById(id);
    }

    @GetMapping("/findbyemail/{email}")
    public Wilder findByEmail(@PathVariable String email){
        return wilderRepository.findByEmail(email);
    }

    @GetMapping("/findbynamecontaining/{name}")
    public List<Wilder> findByNameContaining(@PathVariable String name){
        return wilderRepository.findByNameContaining(name);
    }
}
