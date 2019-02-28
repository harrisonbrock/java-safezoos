package com.harrisonbrock.zoomagment.controller;

import com.harrisonbrock.zoomagment.domain.TelephoneNumber;
import com.harrisonbrock.zoomagment.domain.Zoo;
import com.harrisonbrock.zoomagment.services.MapValidationErrorService;
import com.harrisonbrock.zoomagment.services.ZooService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ZooService zooService;
//    private final PhoneService phoneService;
    private final MapValidationErrorService errorService;

    public AdminController(ZooService zooService,  MapValidationErrorService errorService) {
        this.zooService = zooService;
//        this.phoneService = phoneService;
        this.errorService = errorService;
    }

    @PostMapping("/zoos")
    public ResponseEntity<?> createNewZoo(@Valid @RequestBody Zoo newZoo, BindingResult result) {

        ResponseEntity<?> errorMap = errorService.mapValidationService(result);

        if (errorMap != null) return  errorMap;

        Zoo zoo = zooService.addNewZoo(newZoo);
        return new ResponseEntity<>(zoo, HttpStatus.CREATED);
    }

    @PostMapping("/phones/zoo/{id}")
    public ResponseEntity<?> createNewPhoneNumber(@Valid @RequestBody TelephoneNumber number, @PathVariable long id, BindingResult result) {
        ResponseEntity<?> errorMap = errorService.mapValidationService(result);
        System.out.println("phones");
        if (errorMap != null) return  errorMap;
        System.out.println("admin add phone");
        Zoo zoo = zooService.findZooById(id);
        zoo.getTelephoneNumbers().add(number);
        zooService.addNewZoo(zoo);

        return new ResponseEntity<>(zoo, HttpStatus.CREATED);

    }

    @GetMapping("/zoos/id/{id}")
    public ResponseEntity<?> findZooById(@PathVariable long id) {
        Zoo zoo = zooService.findZooById(id);
        return new ResponseEntity<>(zoo, HttpStatus.OK);
    }
}
