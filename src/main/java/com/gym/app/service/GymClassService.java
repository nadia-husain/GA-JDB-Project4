package com.gym.app.service;

import com.gym.app.exception.InformationNotFoundException;
import com.gym.app.model.GymClass;
import com.gym.app.model.User;
import com.gym.app.model.enums.GymClassStatus;
import com.gym.app.repository.GymClassRepository;
import com.gym.app.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymClassService {
    private final GymClassRepository gymClassRepository;

    @Autowired
    public GymClassService(GymClassRepository gymClassRepository) {
        this.gymClassRepository = gymClassRepository;
    }

    public static User getCurrentLoggedInUser() {
        return ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

    public GymClass createGymClass(GymClass gymClassObject) {
        gymClassObject.setCreatedBy(getCurrentLoggedInUser());
        gymClassObject.setStatus(GymClassStatus.AVAILABLE);
        return gymClassRepository.save(gymClassObject);
    }

    public GymClass getGymClassById(Long gymClassId) {
        return gymClassRepository.findById(gymClassId).orElseThrow(() -> new InformationNotFoundException(
                "Gym class with ID " + gymClassId + " does not exist."));
    }

    public List<GymClass> getAllGymClasses() {
        List<GymClass> gymClasses = gymClassRepository.findAll();
        if(gymClasses.isEmpty()) throw  new InformationNotFoundException("No gym class was found.");
        return gymClasses;
    }

    public GymClass updateGymClass(Long gymClassId, GymClass gymClassObject) {
        GymClass gymClass = gymClassRepository.findById(gymClassId)
                .orElseThrow(() -> new InformationNotFoundException("Gym class with ID " + gymClassId + " does not exist."));
        gymClass.setName(gymClassObject.getName());
        gymClass.setDuration(gymClassObject.getDuration());
        gymClass.setStatus(gymClassObject.getStatus());
        gymClass.setStartDate(gymClassObject.getStartDate());

        return gymClassRepository.save(gymClassObject);
    }

    public  void deleteGymClass(Long gymClassId) {
        gymClassRepository.findById(gymClassId)
                .orElseThrow(() -> new InformationNotFoundException("Gym class with ID " + gymClassId + " does not exist."));
        gymClassRepository.deleteById(gymClassId);
    }
}
