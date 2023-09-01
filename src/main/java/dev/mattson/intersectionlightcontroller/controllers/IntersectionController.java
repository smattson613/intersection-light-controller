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
    public Intersection createIntersection(@RequestBody Intersection intersection) {
        return this.intersectionService.createIntersection(intersection);
    }

    @GetMapping
    public List<Intersection> getAllIntersections() {
        return this.intersectionService.getAllIntersections();
    }

    @GetMapping("/config/{id}")
    public Optional<IntersectionConfigDTO> getIntersectionConfigDTOById(@PathVariable int id) {
        return this.intersectionService.getIntersectionConfigDTOById(id);
    }

    // Below are two separate routes for turning Intersection power true and false.
    @PatchMapping("/start/{id}")
    public void startIntersectionById(@PathVariable int id) {
        this.intersectionService.startIntersectionById(id);
    }

    @PatchMapping("/stop/{id}")
    public void stopIntersectionById(@PathVariable int id) {
        this.intersectionService.stopIntersectionById(id);
    }

    // Below is a singular route for toggling Intersection power true and false.
    @PatchMapping("/toggle/{id}")
    public void toggleIntersectionPowerById(@PathVariable int id) {
        this.intersectionService.toggleIntersectionPowerById(id);
    }

    @PatchMapping("/{id}")
    public void setIntersectionConfigById(@PathVariable int id, @RequestBody int[] sixDigits) {
        this.intersectionService.setIntersectionConfigById(id, sixDigits);
    }
}
