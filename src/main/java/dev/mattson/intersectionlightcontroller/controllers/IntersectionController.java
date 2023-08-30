package dev.mattson.intersectionlightcontroller.controllers;

import dev.mattson.intersectionlightcontroller.dtos.IntersectionConfigDTO;
import dev.mattson.intersectionlightcontroller.entities.Intersection;
import dev.mattson.intersectionlightcontroller.services.IntersectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/intersection")
public class IntersectionController {

    private final IntersectionService intersectionService;

    public IntersectionController(IntersectionService intersectionService) {
        this.intersectionService = intersectionService;
    }

    @PostMapping
    public void createIntersection(@RequestBody Intersection intersection) {
        this.intersectionService.createIntersection(intersection);
    }

    @GetMapping
    public List<Intersection> getAllIntersections() {
        return this.intersectionService.getAllIntersections();
    }

    @GetMapping("/{id}")
    public Optional<Intersection> getIntersectionById(@PathVariable int id) {
        return this.intersectionService.getIntersectionById(id);
    }

    @GetMapping("/config/{id}")
    public IntersectionConfigDTO getIntersectionConfigDTOById(@PathVariable int id) {
        return this.intersectionService.getIntersectionConfigDTOById(id);
    }

    // Below are two separate routes for turning intersection lights on and off.
    @PatchMapping("/start/{id}")
    public void startIntersectionById(@PathVariable int id) {
        this.intersectionService.startIntersectionById(id);
    }

    @PatchMapping("/stop/{id}")
    public void stopIntersectionById(@PathVariable int id) {
        this.intersectionService.stopIntersectionById(id);
    }

    // Below is a singular route for toggling intersections on and off.
    @PatchMapping("/toggle/{id}")
    public void toggleIntersectionPowerById(@PathVariable int id) {
        this.intersectionService.toggleIntersectionPowerById(id);
    }

//    @PatchMapping("{id}")
//    public void setNewIntersectionConfigById(@PathVariable int id, int NSRed, int NSYellow, int NSGreen, int EWRed, int EWYellow, int EWGreen) {
//
//    }

    @PatchMapping("{id}")
    public void setIntersectionConfigById(@PathVariable int id, @RequestBody int[] sixDigits) {
        this.intersectionService.setIntersectionConfigById(id, sixDigits);
    }

}
