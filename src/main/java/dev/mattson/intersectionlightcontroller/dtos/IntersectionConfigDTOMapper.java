package dev.mattson.intersectionlightcontroller.dtos;

import dev.mattson.intersectionlightcontroller.entities.Intersection;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class IntersectionConfigDTOMapper implements Function<Intersection, IntersectionConfigDTO> {

    @Override
    public IntersectionConfigDTO apply(Intersection intersection) {
        return new IntersectionConfigDTO(
                intersection.getIntersectionId(),
                intersection.getNSLightRed(),
                intersection.getNSLightYellow(),
                intersection.getNSLightGreen(),
                intersection.getEWLightRed(),
                intersection.getEWLightYellow(),
                intersection.getEWLightGreen()
        );
    }
}
