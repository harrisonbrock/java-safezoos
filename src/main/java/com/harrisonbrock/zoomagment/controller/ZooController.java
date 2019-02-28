package com.harrisonbrock.zoomagment.controller;

import com.harrisonbrock.zoomagment.domain.Zoo;
import com.harrisonbrock.zoomagment.services.MapValidationErrorService;
import com.harrisonbrock.zoomagment.services.ZooService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/zoos")
public class ZooController {

    private final ZooService zooService;
    private final MapValidationErrorService errorService;

    public ZooController(ZooService service, MapValidationErrorService errorService) {
        this.zooService = service;
        this.errorService = errorService;
    }

    @GetMapping
    public ResponseEntity<?> getAllZoos() {

        List<Zoo> zoos = zooService.getAllZoos();
        return new ResponseEntity<>(zoos, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> findZooById(@PathVariable long id) {
        Zoo zoo = zooService.findZooById(id);
        return new ResponseEntity<>(zoo, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findZooByName(@PathVariable String name) {
        Zoo zoo = zooService.findByZooName(name);
        return new ResponseEntity<>(zoo, HttpStatus.OK);
    }
}
