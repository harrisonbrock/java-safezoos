package com.harrisonbrock.zoomagment.repositories;

import com.harrisonbrock.zoomagment.domain.Animal;
import com.harrisonbrock.zoomagment.domain.Zoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface ZooRepository extends JpaRepository<Zoo, Long> {

    Optional<Zoo> findZooByName(String name);

    @Query(value = "SELECT DISTINCT (animal_type) FROM  zoo_animals", nativeQuery = true)
    List<String> findAllAnimals();

}
