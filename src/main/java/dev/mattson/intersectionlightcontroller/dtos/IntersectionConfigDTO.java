package dev.mattson.intersectionlightcontroller.dtos;

public record IntersectionConfigDTO(
        int id,
        int NSLightRed,
        int NSLightYellow,
        int NSLightGreen,
        int EWLightRed,
        int EWLightYellow,
        int EWLightGreen
) {
}
