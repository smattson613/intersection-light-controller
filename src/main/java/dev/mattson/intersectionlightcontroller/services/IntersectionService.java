package dev.mattson.intersectionlightcontroller.services;

import dev.mattson.intersectionlightcontroller.dtos.IntersectionConfigDTO;
import dev.mattson.intersectionlightcontroller.entities.Intersection;
import dev.mattson.intersectionlightcontroller.exceptions.InvalidIntersectionConfigException;

import java.util.List;
import java.util.Optional;

public interface IntersectionService {
    Intersection createIntersection(Intersection intersection) throws InvalidIntersectionConfigException;

    List<Intersection> getAllIntersections();

    void startIntersectionById(int id);

    void stopIntersectionById(int id);

    void toggleIntersectionPowerById(int id);

    Optional<IntersectionConfigDTO> getIntersectionConfigDTOById(int id);

    void setIntersectionConfigById(int id, int[] sixDigits);
}
