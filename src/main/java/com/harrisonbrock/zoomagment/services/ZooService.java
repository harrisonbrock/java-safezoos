package com.harrisonbrock.zoomagment.services;

import com.harrisonbrock.zoomagment.domain.Animal;
import com.harrisonbrock.zoomagment.domain.Zoo;
import com.harrisonbrock.zoomagment.exception.ZooIdException;
import com.harrisonbrock.zoomagment.exception.ZooNameException;
import com.harrisonbrock.zoomagment.repositories.ZooRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ZooService {

    private final ZooRepository repository;

    public ZooService(ZooRepository repository) {
        this.repository = repository;
    }

    public Zoo addNewZoo(Zoo zoo) {

        return repository.save(zoo);
    }

    public List<Zoo> getAllZoos() {
        return repository.findAll();
    }

    public List<String> getAllAnimals() {


        return repository.findAllAnimals();
    }

    public Zoo findZooById(long id) {
        Optional<Zoo> zoo = repository.findById(id);

        if (zoo.isEmpty()) {
            throw new ZooIdException("Zoo ID '" + id + "' does not exist");
        }
        else {
            return zoo.get();
        }
    }

    public Zoo findByZooName(String name) {
        Optional<Zoo> zoo = repository.findZooByName(name);

        if (zoo.isEmpty()) {
            throw  new ZooNameException("Zoo Name '" + name + "' does not exit");
        }
        else {
            return  zoo.get();
        }
     }

     public Animal createNewAnimalForZooById(Animal animal, long id) {
        Optional<Zoo> zoo = repository.findById(id);

        if (zoo.isEmpty()) {
            // TODO: add exception
            System.out.println("not found");
        }
        else {
            zoo.get().getAnimals().add(animal);
            repository.save(zoo.get());
            return animal;
        }
        return null;
     }
}
