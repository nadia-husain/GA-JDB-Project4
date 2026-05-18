package com.gym.app.controller;

import com.gym.app.model.GymClass;
import com.gym.app.service.GymClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/gymClass/")
public class GymClassController {
    private GymClassService gymClassService;

    @Autowired
    public GymClassController(GymClassService gymClassService) {
        this.gymClassService = gymClassService;
    }

    @PostMapping("new")
    public GymClass createGymClass(@RequestBody GymClass gymClass) {
        System.out.println("calling createGymClass ==> ");
        return gymClassService.createGymClass(gymClass);
    }

    @GetMapping("all")
    public List<GymClass> getAllGymClass() {
        System.out.println("calling getAllGymClass ==> ");
        return gymClassService.getAllGymClasses();
    }

    @GetMapping(path = "{gymClassId}")
    public GymClass getGymClassById(@PathVariable("gymClassId") Long gymClassId) {
        System.out.println("calling getGymClassById ==> ");
        return gymClassService.getGymClassById(gymClassId);
    }

    @PatchMapping(path = "{gymClassId}")
    public GymClass updateGymClass(@PathVariable Long  gymClassId, @RequestBody GymClass gymClassObject) {
        System.out.println("calling updateGymClass ==> ");
        return gymClassService.updateGymClass(gymClassId, gymClassObject);
    }

    @DeleteMapping(path = "{gymClassId}")
    public void deleteGymClass(@PathVariable Long gymClassId) {
        System.out.println("calling deleteGymClass ==> ");
        gymClassService.deleteGymClass(gymClassId);
    }
}
